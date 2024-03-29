package com.cydeo.controller;

import com.cydeo.dto.OrderDTO;
import com.cydeo.dto.UpdateOrderDTO;
import com.cydeo.enums.PaymentMethod;
import com.cydeo.model.ResponseWrapper;
import com.cydeo.service.OrderService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
    ResponseEntity<ResponseWrapper> updateOrder(@Valid @RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok(new ResponseWrapper("Order is updated"
                , orderService.updateOrder(orderDTO),HttpStatus.OK));
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseWrapper> updateOrderById(@PathVariable("id") Long id, @Valid @RequestBody  UpdateOrderDTO updateOrderDTO){
        return ResponseEntity.ok(new ResponseWrapper("Order is successfully updated",
                orderService.updateOrderById(id, updateOrderDTO),HttpStatus.OK));
    }

    @PostMapping
    ResponseEntity<ResponseWrapper> createOrder(@RequestBody OrderDTO orderDTO){

        return ResponseEntity.ok(new ResponseWrapper("Order is created"
                , orderService.createOrder(orderDTO), HttpStatus.OK));
    }
    @GetMapping("/{id}")                                                        //passing Optional to avoid nullPointerAcceptation
    public ResponseEntity<ResponseWrapper> getOrderById(@PathVariable("id") Long id,@RequestParam(required = false) Optional<String> currency){
        return ResponseEntity.ok(new ResponseWrapper("Order is successfully retrieved.",
                orderService.retrieveOrderDetailById(id, currency), HttpStatus.OK));
    }


    @GetMapping("/paymentMethod/{paymentMethod}")
    ResponseEntity<ResponseWrapper> retrieveOrderByPaymentMethod(@PathVariable("paymentMethod") PaymentMethod paymentMethod){
        return ResponseEntity.ok(new ResponseWrapper("Orders are successfully retrieved"
                , orderService.retrieveOrderByPaymentMethod(paymentMethod), HttpStatus.OK));
    }

    @GetMapping("/email/{email}")
    ResponseEntity<ResponseWrapper> retrieveCustomerOrderByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(new ResponseWrapper("Orders are successfully retrieved"
                , orderService.retrieveOrderByEmail(email), HttpStatus.OK));
    }




}
