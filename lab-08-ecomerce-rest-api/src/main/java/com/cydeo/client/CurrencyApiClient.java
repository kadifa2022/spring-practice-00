package com.cydeo.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(url = "http://apilayer.net/api", name = "currencyApiClient")
public interface CurrencyApiClient {

    //http://apilayer.net/api/live?access_key=483b1ef51d63928d3aeb7079d3bd5557&currencies=EUR,TRY&source=USD&format=1

    //dividing url

    //  http://apilayer.net/api => base url
    //  live --> endpoint that we are going to put --> @GetMapping
    //  ? = stop concatenating url, everything after ? mark is path parameter
    //  access_key=483b1ef51d63928d3aeb7079d3bd5557
    //  &currencies=EUR,TRY
    //  &source=USD
    //  &format=1


    // we will use this api inside OrderServiceImpl.
    @GetMapping("/live") //live is endpoints
    Map<String, Object> getCurrencyRates(@RequestParam("access_key") String accessKey,
                                         @RequestParam("currencies") String currencies,
                                         @RequestParam("source") String source,
                                         @RequestParam("format") int format);
}

