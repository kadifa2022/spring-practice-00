package com.cydeo.controller;

import com.cydeo.dto.CustomerDTO;
import com.cydeo.model.ResponseWrapper;
import com.cydeo.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> findById(Long customerId){
        return ResponseEntity.ok(new ResponseWrapper("Customer are successfully retrieved "
        , customerService.findById(customerId), HttpStatus.OK));

    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> listAllCustomers(){
        return ResponseEntity.ok(new ResponseWrapper("Customers are successfully retrieved "
        , customerService.readAll(), HttpStatus.OK));

    }
    @PutMapping
    public ResponseEntity<ResponseWrapper> updateCustomer(@RequestBody CustomerDTO customerDTO){
        return  ResponseEntity.ok(new ResponseWrapper("Costumer is successfully updated "
        , customerService.update(customerDTO), HttpStatus.OK));

    }
    @PostMapping
    public ResponseEntity<ResponseWrapper>  createCustomer(@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.ok(new ResponseWrapper("Customer is successfully created "
        , customerService.create(customerDTO), HttpStatus.OK));

    }

    @GetMapping("/{email")
    public ResponseEntity<ResponseWrapper> findByEmail(@PathVariable("email") String email){
        return ResponseEntity.ok(new ResponseWrapper("Customer is  successfully retrieved "
        ,customerService.readByEmail(email), HttpStatus.OK));

    }

}
