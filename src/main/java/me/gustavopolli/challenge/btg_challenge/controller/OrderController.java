package me.gustavopolli.challenge.btg_challenge.controller;

import lombok.RequiredArgsConstructor;
import me.gustavopolli.challenge.btg_challenge.controller.dto.OrderResponse;
import me.gustavopolli.challenge.btg_challenge.service.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/customers/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> listOrders(@PathVariable Long customerId,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderService.listOrders(customerId, pageable);
    }

}
