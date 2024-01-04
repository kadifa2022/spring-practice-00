package com.cydeo.service;

import com.cydeo.dto.CartDTO;

public interface CartService {
   CartDTO findById(Long id);

    boolean existById(Long id);
}
