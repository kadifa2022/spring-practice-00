package com.cydeo.service.impl;

import com.cydeo.client.CurrencyApiClient;
import com.cydeo.dto.OrderDTO;
import com.cydeo.dto.UpdateOrderDTO;
import com.cydeo.entity.Cart;
import com.cydeo.entity.Customer;
import com.cydeo.entity.Order;
import com.cydeo.entity.Payment;
import com.cydeo.enums.Currency;
import com.cydeo.enums.PaymentMethod;
import com.cydeo.exception.CurrencyTypeNotFoundException;
import com.cydeo.exception.NotFoundException;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.OrderRepository;
import com.cydeo.service.CartService;
import com.cydeo.service.CustomerService;
import com.cydeo.service.OrderService;
import com.cydeo.service.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MapperUtil mapperUtil;
    private final CustomerService customerService;
    private final PaymentService paymentService;
    private final CartService cartService;
    private final CurrencyApiClient currencyApiClient;
    @Value("${access_key}")    // what is the reason of separations? because 1.security/ different environment prod/test
                                // 2. we want to control in central place (if we need to update / can  be used in different places
    private String accessKey; // get the value from application.properties


    public OrderServiceImpl(OrderRepository orderRepository, MapperUtil mapperUtil, CustomerService customerService,
                            PaymentService paymentService, CartService cartService, CurrencyApiClient currencyApiClient) {
        this.orderRepository = orderRepository;
        this.mapperUtil = mapperUtil;
        this.customerService = customerService;
        this.paymentService = paymentService;
        this.cartService = cartService;
        this.currencyApiClient = currencyApiClient;
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
                () -> new NotFoundException("Order could not be found"));
        // then we need to check if the order fields exists or not (important)
        validateRelatedFieldsAreExist(orderDTO);// private method to make sure they have those fields
        //if fields exists, then convert orderDTO to Order and save it(and i can use OrderRepository save() method)
        Order willBeUpdatedOrder = mapperUtil.convert(orderDTO, new Order());// take the orderDTO converted to new Order will be stored in willBeUpdatedOrder
        Order updatedOrder = orderRepository.save(willBeUpdatedOrder);// then saved in DB, and then retrieve updatedOrder
        return mapperUtil.convert(updatedOrder, new OrderDTO());// get the updatedOrder from DB  then converted to DTO
    }

    private void validateRelatedFieldsAreExist(OrderDTO orderDTO) {
        // in this method we have 3 different service and make sure they have those fields
        //we will create services and existById method and verify
        // we are reversing business logic with !
        if (!customerService.existById(orderDTO.getCustomerId())) {// IF customerService existById, if not throw exceptions
            throw new NotFoundException("Customer could not found");
        }
        if (!paymentService.existById(orderDTO.getPaymentId())) {
            throw new NotFoundException("Payment could not found");
        }
        if (!cartService.existById(orderDTO.getCartId())) {
            throw new NotFoundException("Cart could not found");
        }

    }

  /*  @Override   // this is done with OZZY, Cundullah
    public OrderDTO updateOrder(OrderDTO orderDTO) {// why we are setting all fields one by one? From DTO we have
    more than one objects/ in DTO we are representing with Long variable name / but those names are holding  data with own id
        Order order = mapperUtil.convert(orderDTO, new Order()); // converting DTO to new Order entity because inside dto we have
                                                                 //field with Long, but they are objects from another tables with id
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

    @Override
    public OrderDTO updateOrderById(Long id, UpdateOrderDTO updateOrderDTO) {// making sure order exists and setting that order
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Order could not be found."));

        //if we are getting same value, it is not necessary to update the actual value
        boolean changeDetected = false;// creating boolean variable by default is false
        // if is false will go directly to the end ("no changes detected") no reason for update

        if (!order.getPaidPrice().equals(updateOrderDTO.getPaidPrice())) {// if they are equal don't do anything
            order.setPaidPrice(updateOrderDTO.getPaidPrice());//if they are not equal set the price
            changeDetected = true; // change happens
        }
        if (!order.getTotalPrice().equals(updateOrderDTO.getTotalPrice())) {// same logic as above
            order.setTotalPrice(updateOrderDTO.getTotalPrice());
            changeDetected = true;
        }
        //if there is any change, update the order and return it
        if (changeDetected) {
            Order updateOrder = orderRepository.save(order);
            return mapperUtil.convert(updateOrder, new OrderDTO());
        } else {
            throw new NotFoundException("No changes detected");
        }

    }

    @Override //method to retrieve one order   // Why we iml. this structure (Pass Optional)
    public OrderDTO retrieveOrderDetailById(Long id, Optional<String> currency) { // to handle null pointer excepting
        //Find the order based on id, convert and return it
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Order could not be found."));// if we couldn't find id throw exception
       // if we are getting currency value from the user if not find order and returned it
        currency.ifPresent(curr -> {  // business logic of consuming// we are not saving this in DB
            validateCurrency(curr);//validate before sending api because of cost/performance //if is invalid currency i will throw our costume exception
            //get the currency date based on currency type -> api consume
            // steps we will have private method which is accepting the currency and returning the currency result
           BigDecimal currencyRate = getCurrencyRate(curr); // this method will return double and accept currency
            //do calculations and set new paidPrice and totalPrice
            //these prices for just to give value to customer, we will not update the DB based on other currencies
            BigDecimal newPaidPrice = order.getPaidPrice().multiply(currencyRate).setScale(2, RoundingMode.HALF_UP );// to see 2 digit round up
            BigDecimal newTotalPrice = order.getTotalPrice().multiply(currencyRate).setScale(2, RoundingMode.HALF_UP);
            // set the value to order that we retrieved
            order.setPaidPrice(newPaidPrice);
            order.setTotalPrice(newTotalPrice);
        });
        // let say we find the order, convert and returned id
        return mapperUtil.convert(order, new OrderDTO());
    }

    private void validateCurrency(String curr) {
        //check if the currency is valid currency
        List<String> currencies = Stream.of(Currency.values())
                .map(currency -> currency.value)
                .collect(Collectors.toList());
       boolean isCurrencyValid  =  currencies.contains(curr);
       if(!isCurrencyValid){
           throw new CurrencyTypeNotFoundException("Currency type for " + curr + " could not be found");


       }

    }


    private BigDecimal getCurrencyRate(String currency) {// this method will return double and accept currency
        //will do all consuming related, separate where we are going to send request
        // in this method i will communicate with api and get the response
        // What we will do in this method? 1. Consume API
                                    // cast because we have Object inside client
        Map<String, Double> quotes = (Map<String, Double>) currencyApiClient.getCurrencyRates(accessKey, currency, "USD",1).get("quotes"); // returning map-> we save response inside map
        // after request what we can do? check if success is true, then retrieve value (feignClient fold back)

        Boolean isSuccess = (Boolean) currencyApiClient.getCurrencyRates(accessKey,currency,"USD",1).get("success");
        if(!isSuccess){
            throw new RuntimeException("API IS DOWN");
        }
        String expectedCurrency = "USD" + currency.toUpperCase();// "USDEUR"api  USD + whatever we're expecting
        BigDecimal currencyRate = BigDecimal.valueOf(quotes.get(expectedCurrency));

        //Before currencyRate check if currency rate is valid (feignClient fold Back)

        return currencyRate;

    }


}
















