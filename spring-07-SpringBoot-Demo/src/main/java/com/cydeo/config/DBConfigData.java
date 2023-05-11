package com.cydeo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@ConfigurationProperties(prefix = "db")
@Data
@Configuration
public class DBConfigData {
    private String username;
    private String password;
    private List<String> type;// more than one value


}
