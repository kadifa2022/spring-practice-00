package com.cydeo.controller;

import com.cydeo.dto.OrderDTO;
import com.cydeo.model.ResponseWrapper;
import com.cydeo.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> retrieveListOrder(){
        return ResponseEntity.ok(new ResponseWrapper("Order are retrieved",
                orderService.retrieveListOrder(), HttpStatus.OK));
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper> updateOrder(@RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok(new ResponseWrapper("Order are updated",
                orderService.updateOrder(orderDTO), HttpStatus.OK));
    }
}

