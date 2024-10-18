package me.gustavopolli.challenge.btg_challenge.service;

import lombok.RequiredArgsConstructor;
import me.gustavopolli.challenge.btg_challenge.controller.dto.OrderResponse;
import me.gustavopolli.challenge.btg_challenge.entity.Order;
import me.gustavopolli.challenge.btg_challenge.entity.OrderItem;
import me.gustavopolli.challenge.btg_challenge.listener.dto.OrderCreatedEvent;
import me.gustavopolli.challenge.btg_challenge.repository.OrderRepository;
import org.bson.Document;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MongoTemplate mongoTemplate;

    public void save(OrderCreatedEvent event) {
        var order = new Order();

        order.setId(event.codigoPedido());
        order.setCostumerId(event.codigoCliente());
        getOrderItens(event, order);
        order.setTotal(getTotal(event));

        orderRepository.save(order);
    }

    private static void getOrderItens(OrderCreatedEvent event, Order order) {
        order.setItens(event.itens().stream().map(item -> {
            var orderItem = new OrderItem();
            orderItem.setProduct(item.produto());
            orderItem.setQuantity(item.quantidade());
            orderItem.setPrice(item.preco());
            return orderItem;
        }).collect(Collectors.toList()));
    }

    private static BigDecimal getTotal(OrderCreatedEvent event){
        return event.itens().stream()
                .map(item -> item.preco().multiply(BigDecimal.valueOf(item.quantidade())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    public List<OrderResponse> listOrders(Long costumerId, Pageable pageable) {
        var orders = orderRepository.findAllByCostumerId(costumerId, pageable);
        var totalOrders = findTotalByCostumerId(costumerId);

        return orders.stream().map(order -> OrderResponse.toPresentation(order, totalOrders)).collect(Collectors.toList());
    }

    public BigDecimal findTotalByCostumerId(Long costumerId) {
        var aggregations = newAggregation(
                match(Criteria.where("costumerId").is(costumerId)),
                group("costumerId").sum("total").as("total")
        );
        var response = mongoTemplate.aggregate(aggregations, "order", Document.class);

        return new BigDecimal(response.getUniqueMappedResult().get("total").toString());
    }

}
