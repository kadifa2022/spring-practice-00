package com.cydeo.controller;

import com.cydeo.dto.ProductDTO;
import com.cydeo.dto.ProductRequest;
import com.cydeo.model.ResponseWrapper;
import com.cydeo.service.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


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

    @PostMapping
    public ResponseEntity<ResponseWrapper> createProduct(@RequestBody ProductDTO productDTO){
        return ResponseEntity.ok(new ResponseWrapper("Product is updated", productService.createProduct(productDTO), HttpStatus.OK));
    }
    //if we have list we can't put @PathVariable{""}
    @PostMapping("/categoryandprice")           //we are catching JSON Body(@RequestBody) from different DTO witch is ProductRequest
    public ResponseEntity<ResponseWrapper> retrieveProductByCategoryAndPrice(@RequestBody ProductRequest productRequest){
        return ResponseEntity.ok(new ResponseWrapper("Product are successfully retrieved"
                , productService.retrieveProductByCategoryAndPrice(productRequest.getCategoryList(), productRequest.getPrice()), HttpStatus.OK));
    }
    @GetMapping("/{name}")
    public ResponseEntity<ResponseWrapper> retrieveProductByName(@PathVariable("name") String name){
        return ResponseEntity.ok(new ResponseWrapper("Product are successfully retrieved"
                , productService.retrieveByName(name),HttpStatus.OK));
    }


}
