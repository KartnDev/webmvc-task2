package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.requests.DepositRequest;
import org.example.domain.requests.GetOrderStatusRequest;
import org.example.domain.requests.OrderRegisterRequest;
import org.example.domain.requests.ReverseRequest;
import org.example.domain.response.DepositResponse;
import org.example.domain.response.GetOrderStatusResponse;
import org.example.domain.response.OrderRegisterResponse;
import org.example.domain.response.ReverseResponse;
import org.example.service.OrderControllerManager;
import org.example.service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class OrderController {
    private final OrderControllerManager orderControllerManager;

    @PostMapping("register.do")
    public OrderRegisterResponse registerDo(@RequestBody OrderRegisterRequest orderRequest){
        return orderControllerManager.registerDo(orderRequest);
    }

    @PostMapping("deposit.do")
    public DepositResponse depositDo(@RequestBody DepositRequest depositRequest){
        return orderControllerManager.depositDo(depositRequest);
    }

    @PostMapping("getOrderStatus.do")
    public GetOrderStatusResponse getOrderStatusDo(@RequestBody GetOrderStatusRequest getOrderStatusRequest){
        return orderControllerManager.getOrderStatusDo(getOrderStatusRequest);
    }

    @PostMapping("reverse.do")
    public ReverseResponse reverseDo(@RequestBody ReverseRequest orderRequest){
        return orderControllerManager.reverseDo(orderRequest);
    }
}
