package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.requests.GetOrderStatusRequest;
import org.example.domain.requests.OrderRegisterRequest;
import org.example.domain.requests.ReverseRequest;
import org.example.domain.response.GetOrderStatusResponse;
import org.example.domain.response.OrderRegisterResponse;
import org.example.domain.response.ReverseResponse;
import org.example.service.OrderControllerManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController {
    private final OrderControllerManager orderControllerManager;

    @PostMapping("register.do")
    // TODO is it be better to use @ResponseStatus(HttpStatus.CREATED) instead of default 200 OK
    public OrderRegisterResponse registerDo(OrderRegisterRequest orderRequest) {
        return orderControllerManager.registerDo(orderRequest);
    }

    @PostMapping("getOrderStatus.do")
    public GetOrderStatusResponse getOrderStatusDo(GetOrderStatusRequest getOrderStatusRequest) {
        return orderControllerManager.getOrderStatusDo(getOrderStatusRequest);
    }

    @PostMapping("reverse.do")
    public ReverseResponse reverseDo(ReverseRequest orderRequest) {
        return orderControllerManager.reverseDo(orderRequest);
    }
}
