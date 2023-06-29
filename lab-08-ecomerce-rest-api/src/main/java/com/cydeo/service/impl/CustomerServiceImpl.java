package com.cydeo.service.impl;

import com.cydeo.dto.CustomerDTO;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.CustomerRepository;
import com.cydeo.service.CustomerService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository  customerRepository;
    private final MapperUtil mapperUtil;

    public CustomerServiceImpl(CustomerRepository customerRepository, MapperUtil mapperUtil) {
        this.customerRepository = customerRepository;
        this.mapperUtil = mapperUtil;
    }


    @Override
    public CustomerDTO findById(Long customerId) {
        return customerRepository.findById(customerId)
                .stream()
                .map(customer -> mapperUtil.convert(customer, new CustomerDTO())).findFirst().orElseThrow();
    }
}
