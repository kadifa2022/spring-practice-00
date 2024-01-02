package com.cydeo.service.impl;

import com.cydeo.dto.OrderDTO;
import com.cydeo.entity.Cart;
import com.cydeo.entity.Customer;
import com.cydeo.entity.Order;
import com.cydeo.entity.Payment;
import com.cydeo.enums.PaymentMethod;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.OrderRepository;
import com.cydeo.service.CartService;
import com.cydeo.service.CustomerService;
import com.cydeo.service.OrderService;
import com.cydeo.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private  final MapperUtil mapperUtil;
    private final CustomerService customerService;
    private final PaymentService paymentService;

    private final CartService cartService;


    public OrderServiceImpl(OrderRepository orderRepository, MapperUtil mapperUtil, CustomerService customerService, PaymentService paymentService, CartService cartService) {
        this.orderRepository = orderRepository;
        this.mapperUtil = mapperUtil;
        this.customerService = customerService;
        this.paymentService = paymentService;
        this.cartService = cartService;
    }

    @Override
    public List<OrderDTO> retrieveListOrder() { //retrieve all orders and return as a list of DTO
        return orderRepository.findAll().stream()
                .map(order -> mapperUtil.convert(order, new OrderDTO())).collect(Collectors.toList());
    }

    @Override
    public OrderDTO updateOrder(OrderDTO orderDTO) {
        //Look for orderId inside the DB and throw exception (getting id from OrderDTO) if order exists return order
        Order order = orderRepository.findById(orderDTO.getId()).orElseThrow(// if we don't find the order throw exception
                ()->new RuntimeException("Order could not be found"));
        // then we need to check if the order fields exists or not (important)
        return null;
    }

  /*  @Override   // this is done with OZZY, Cundullah
    public OrderDTO updateOrder(OrderDTO orderDTO) {// why we are setting all fields one by one? From DTO we have more than one objects/ in DTO we are representing with Long variable name / but those names are holding  data with own id
        Order order = mapperUtil.convert(orderDTO, new Order());   // converting DTO to new Order entity because inside dto we have field with Long, but they are objects from another tables with id
        order.setCustomer(mapperUtil.convert(customerService.findById(orderDTO.getCustomerId()), new Customer()));
        order.setPayment(mapperUtil.convert(paymentService.findById(orderDTO.getPaymentId()), new Payment()));
        order.setCart(mapperUtil.convert(cartService.findById(orderDTO.getCartId()), new Cart()));
        order.setPaidPrice(orderDTO.getPaidPrice());
        order.setTotalPrice(orderDTO.getTotalPrice());

        Order updatedOrder = orderRepository.save(order);

        return mapperUtil.convert(updatedOrder, new OrderDTO());
    }


   */



    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = mapperUtil.convert(orderDTO, new Order());
        order.setCustomer(mapperUtil.convert(customerService.findById(orderDTO.getCustomerId()), new Customer()));
        order.setPayment(mapperUtil.convert(paymentService.findById(orderDTO.getPaymentId()), new Payment()));
        order.setCart(mapperUtil.convert(cartService.findById(orderDTO.getCartId()), new Cart()));
        order.setPaidPrice(orderDTO.getPaidPrice());
        order.setTotalPrice(orderDTO.getTotalPrice());

        Order updatedOrder = orderRepository.save(order);

        return mapperUtil.convert(updatedOrder, new OrderDTO());
    }

    @Override
    public List<OrderDTO> retrieveOrderByPaymentMethod(PaymentMethod paymentMethod) {
        return orderRepository.findAllByPayment_PaymentMethod(paymentMethod)
                .stream().map(order -> mapperUtil.convert(order, new OrderDTO())).collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> retrieveOrderByEmail(String email) {
        return orderRepository.findAllByCustomer_Email(email)
                .stream().map(order -> mapperUtil.convert(order, new OrderDTO())).collect(Collectors.toList());
    }
}
