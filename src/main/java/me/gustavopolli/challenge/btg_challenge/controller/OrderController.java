package me.gustavopolli.challenge.btg_challenge.controller;

import lombok.RequiredArgsConstructor;
import me.gustavopolli.challenge.btg_challenge.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;

@RequiredArgsConstructor
@RestController("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/costumers/{costumerId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> listOrders(@PathVariable Long costumerId, @RequestParam Pageable pageable) {
        return ResponseEntity.ok(orderService.listOrders(costumerId, pageable));
    }
}
