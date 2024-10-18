package me.gustavopolli.challenge.btg_challenge.controller.dto;

import me.gustavopolli.challenge.btg_challenge.entity.Order;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Map;

public record OrderResponse(
        Map<String, Object> summary,
        Map<String, Object> data,
        Pageable pageable
) {

    public static OrderResponse toPresentation(Order order, BigDecimal totalOrders) {
        return new OrderResponse(
                Map.of(
                        "totalOrders", totalOrders
                ),
                Map.of(
                        "orderId", order.getId(),
                        "costumerId", order.getCostumerId(),
                        "total", order.getTotal()
                        ),
                Pageable.ofSize(10).withPage(0)
        );
    }
}
