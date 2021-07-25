package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Order;
import org.example.exception.ItemNotFoundException;
import org.example.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order getById(String orderId) {
        return orderRepository.getById(orderId).orElseThrow(ItemNotFoundException::new);
    }

    public Order register(Order order) {
        order.setStatus("Registered");
        return orderRepository.save(order);
    }

    public Order end(String orderId) {
        final var toEndOrder = getById(orderId);
        toEndOrder.setStatus("Ended");
        return orderRepository.save(toEndOrder);
    }

    public Order cancel(String orderId) {
        final var toEndOrder = getById(orderId);
        toEndOrder.setStatus("Cancel");
        return orderRepository.save(toEndOrder);
    }
}
