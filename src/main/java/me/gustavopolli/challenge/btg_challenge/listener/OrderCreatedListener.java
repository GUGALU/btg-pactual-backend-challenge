package me.gustavopolli.challenge.btg_challenge.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.gustavopolli.challenge.btg_challenge.listener.dto.OrderCreatedEvent;
import me.gustavopolli.challenge.btg_challenge.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import static me.gustavopolli.challenge.btg_challenge.config.RabbitMqConfig.ORDER_CREATED_QUEUE;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderCreatedListener {

    private final OrderService orderService;

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listen(Message<OrderCreatedEvent> message) {
        log.info("Order created: {}", message.getPayload());
        orderService.save(message.getPayload());
    }

}
