package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.Order;
import org.example.domain.requests.GetOrderStatusRequest;
import org.example.domain.requests.OrderRegisterRequest;
import org.example.domain.requests.ReverseRequest;
import org.example.domain.response.GetOrderStatusResponse;
import org.example.domain.response.OrderRegisterResponse;
import org.example.domain.response.ReverseResponse;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class OrderControllerManager {
    private final OrderService orderService;
    private final UserService userService;

    public OrderRegisterResponse registerDo(OrderRegisterRequest orderRequest) {
        final var registeredOrder = orderService.register(fromRequest(orderRequest));
        return mapFromOrder(registeredOrder);
    }

    public GetOrderStatusResponse getOrderStatusDo(GetOrderStatusRequest orderRequest) {
        final var order = orderService.getById(orderRequest.getOrderId());
        userService.verifyAndLoad(orderRequest.getUserName(), orderRequest.getPassword());
        return GetOrderStatusResponse.builder()
                .orderId(order.getId())
                .orderStatus(order.getStatus())
                .amount(order.getAmount())
                .currency(order.getCurrency())
                .errorCode(0)
                .errorMessage(null)
                .hasErrors(Boolean.FALSE)
                .build();
    }

    public ReverseResponse reverseDo(ReverseRequest orderRequest) {
        final var realOrderId = orderService.getById(orderRequest.getOrderId()).getId();
        userService.verifyAndLoad(orderRequest.getUserName(), orderRequest.getPassword());
        orderService.cancel(realOrderId);
        return ReverseResponse.builder()
                .errorCode(0)
                .errorMessage(null)
                .hasErrors(Boolean.FALSE)
                .build();
    }

    public Order fromRequest(OrderRegisterRequest orderRequest) {
        final var user = userService.verifyAndLoad(orderRequest.getUserName(), orderRequest.getPassword());
        return Order.builder()
                .userId(user.getId())
                .orderNumber(orderRequest.getOrderNumber())
                .amount(orderRequest.getAmount())
                .currency(orderRequest.getCurrency())
                .failUrl(orderRequest.getFailUrl())
                .returnUrl(orderRequest.getReturnUrl())
                .status("Received")
                .build();
    }

    public OrderRegisterResponse mapFromOrder(Order order) {
        return OrderRegisterResponse.builder()
                .createdOrder(order)
                .hasErrors(Boolean.FALSE)
                .errorCode(0)
                .errorMessage(null)
                .requestTimestamp(Date.valueOf(LocalDate.now()))
                .status(order.getStatus())
                .build();
    }
}
