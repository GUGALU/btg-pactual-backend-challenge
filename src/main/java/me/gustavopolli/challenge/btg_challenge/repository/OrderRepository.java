package me.gustavopolli.challenge.btg_challenge.repository;

import me.gustavopolli.challenge.btg_challenge.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, Long> {

    List<Order> findByCostumerId(Long costumerId, Pageable pageable);
}
