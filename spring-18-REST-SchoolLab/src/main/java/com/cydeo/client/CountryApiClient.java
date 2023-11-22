package com.cydeo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url= "http://restcountries.com/v3.1", name = "COUNTRY-CLIENT")
public interface CountryApiClient {

    //http://restcountries.com/v3.1/name/Finland

    @GetMapping("/name/{countryName}")
    Object getCountryInfo(@PathVariable("countryName") String countryName);

    }




