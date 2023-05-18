package com.cydeo.service.serviceImp;



import com.cydeo.dto.CustomerDTO;
import com.cydeo.entity.Customer;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.CustomerRepository;
import com.cydeo.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final MapperUtil mapperUtil;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(MapperUtil mapperUtil, CustomerRepository customerRepository) {
        this.mapperUtil = mapperUtil;
        this.customerRepository = customerRepository;
    }


    @Override
    public CustomerDTO findById(Long customerId) {
        return customerRepository.findById(customerId).stream()
                .map(customer -> mapperUtil.convert(customer, new CustomerDTO()))
                .findFirst().orElseThrow();
    }



    @Override
    public List<CustomerDTO> readAll() {
        return customerRepository.findAll().stream()
                .map(customer -> mapperUtil.convert(customer, new CustomerDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {// Assuming userName is uniq
        Customer customer = customerRepository.findByUserName(customerDTO.getUserName());// checking for the user in DB
       // customerRepository.save(mapperUtil.convert(customerDTO, new Customer())); don't want to map all fields
        customer.setEmail(customerDTO.getEmail());// updating email, firstName, lastName
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        Customer savedCustomer = customerRepository.save(customer);// saving new customer
        return mapperUtil.convert(savedCustomer, new CustomerDTO());
    }

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        Customer customer = customerRepository.save(mapperUtil.convert(customerDTO, new Customer()));
        return mapperUtil.convert(customer, new CustomerDTO());
    }

    @Override
    public CustomerDTO readByEmail(String email) {
        return mapperUtil.convert(customerRepository.retrieveByCustomerEmail(email), new CustomerDTO());
    }
}













