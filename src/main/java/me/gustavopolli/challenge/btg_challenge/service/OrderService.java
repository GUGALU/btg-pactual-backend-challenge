package me.gustavopolli.challenge.btg_challenge.service;

import lombok.RequiredArgsConstructor;
import me.gustavopolli.challenge.btg_challenge.entity.Order;
import me.gustavopolli.challenge.btg_challenge.entity.OrderItem;
import me.gustavopolli.challenge.btg_challenge.listener.dto.OrderCreatedEvent;
import me.gustavopolli.challenge.btg_challenge.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

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

    public List<Order> listOrders(Long costumerId, Pageable pageable) {
        return orderRepository.findByCostumerId(costumerId, pageable);
    }

}
