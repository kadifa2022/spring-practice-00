package com.cydeo.service;

import com.cydeo.dto.OrderDTO;
import com.cydeo.dto.UpdateOrderDTO;
import com.cydeo.enums.PaymentMethod;

import java.util.List;

public interface OrderService {
    List<OrderDTO> retrieveListOrder();

    OrderDTO updateOrder(OrderDTO orderDTO);

    OrderDTO createOrder(OrderDTO orderDTO);

    List<OrderDTO> retrieveOrderByPaymentMethod(PaymentMethod paymentMethod);

    List<OrderDTO> retrieveOrderByEmail(String email);

    OrderDTO updateOrderById(Long id, UpdateOrderDTO updateOrderDTO);
}