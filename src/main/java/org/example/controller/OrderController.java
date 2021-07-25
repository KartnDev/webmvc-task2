package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.requests.GetOrderStatusRequest;
import org.example.domain.requests.OrderRegisterRequest;
import org.example.domain.requests.ReverseRequest;
import org.example.domain.response.GetOrderStatusResponse;
import org.example.domain.response.OrderRegisterResponse;
import org.example.domain.response.ReverseResponse;
import org.example.service.OrderControllerManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class OrderController {
    private final OrderControllerManager orderControllerManager;

    @PostMapping("register.do")
    public OrderRegisterResponse registerDo(OrderRegisterRequest orderRequest){
        return orderControllerManager.registerDo(orderRequest);
    }

    @PostMapping("getOrderStatus.do")
    public GetOrderStatusResponse getOrderStatusDo(GetOrderStatusRequest getOrderStatusRequest){
        return orderControllerManager.getOrderStatusDo(getOrderStatusRequest);
    }

    @PostMapping("reverse.do")
    public ReverseResponse reverseDo(ReverseRequest orderRequest){
        return orderControllerManager.reverseDo(orderRequest);
    }
}
