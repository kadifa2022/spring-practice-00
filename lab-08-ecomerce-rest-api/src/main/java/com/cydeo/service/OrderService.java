package com.cydeo.service;

import com.cydeo.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> retrieveListOrder();

    OrderDTO updateOrder(OrderDTO orderDTO);
}
