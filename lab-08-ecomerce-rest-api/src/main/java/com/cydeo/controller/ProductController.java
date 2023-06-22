package com.cydeo.controller;

import com.cydeo.model.ResponseWrapper;
import com.cydeo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> listProduct(){
        return ResponseEntity.ok(new ResponseWrapper("Products are successfully retrieved", productService.retrieveListProduct(), HttpStatus.OK));
    }
}
