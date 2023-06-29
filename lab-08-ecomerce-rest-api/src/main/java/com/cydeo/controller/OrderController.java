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
    ResponseEntity<ResponseWrapper> retrieveListOrder(){
        return ResponseEntity.ok(new ResponseWrapper("Orders are successfully retrieved"
                , orderService.retrieveListOrder(), HttpStatus.OK));

    }

    @PutMapping
    ResponseEntity<ResponseWrapper> updateOrder(@RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok(new ResponseWrapper("Order is updated" , orderService.updateOrder(orderDTO),HttpStatus.OK));
    }



}
