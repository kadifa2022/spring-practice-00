package com.cydeo.service.serviceImp;


import com.cydeo.dto.DiscountDTO;
import com.cydeo.entity.Discount;
import com.cydeo.mapper.MapperUtil;
import com.cydeo.repository.DiscountRepository;
import com.cydeo.service.DiscountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountServiceImp implements DiscountService {
    private final DiscountRepository discountRepository;
    private final MapperUtil mapperUtil;

    public DiscountServiceImp(DiscountRepository discountRepository, MapperUtil mapperUtil) {
        this.discountRepository = discountRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<DiscountDTO> readAll() {
        return discountRepository.findAll().stream()
                .map(discount -> mapperUtil.convert(discount, new DiscountDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public DiscountDTO update(DiscountDTO discountDTO) {//saving dto because we don't have anything in dto uniq
        Discount discount = discountRepository.save(mapperUtil.convert(discountDTO, new Discount()));
        return mapperUtil.convert(discount, new DiscountDTO());
    }

    @Override
    public DiscountDTO create(DiscountDTO discountDTO) {
        Discount discount= discountRepository.save(mapperUtil.convert(discountDTO, new Discount()));
        return mapperUtil.convert(discount, new DiscountDTO());
    }

    @Override
    public DiscountDTO readByName(String name) {
        return mapperUtil.convert(discountRepository.findFirstByName(name), new DiscountDTO());
    }

    }













