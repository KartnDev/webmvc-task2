package org.example.repository;

import org.example.domain.Order;

import java.util.Optional;

public interface OrderRepository {
    Optional<Order> getById(String id);
    Order save(Order newOrUpdate);
}
