package com.github.alexandergromyko.restaurants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RestaurantsApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestaurantsApplication.class, args);
    }
}