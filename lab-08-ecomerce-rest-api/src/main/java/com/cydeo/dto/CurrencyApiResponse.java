package com.cydeo.dto;



import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class CurrencyApiResponse {

    //This is my custom class/ POJO
    //Using fields from Json that we consume,
    // Jackson is looking for key names to matched it and can't be changed, but
    // we can change what can be converted to Java

    private boolean success;
    private String terms;
    private String privacy;
   // @JsonProperty("time stamp") just the sample if we put space between that we have annotation to matched
    private Long timestamp;
    private String source;
    private Map<String, Double> quotes;
}
