package com.cydeo.controller;

import com.cydeo.dto.ProductDTO;
import com.cydeo.model.ResponseWrapper;
import com.cydeo.service.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping
    public ResponseEntity<ResponseWrapper> updateProduct(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(new ResponseWrapper("Product is updated", productService.updateProduct(productDTO), HttpStatus.OK));
    }

}
