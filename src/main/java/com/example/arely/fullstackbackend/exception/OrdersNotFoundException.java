package com.example.arely.fullstackbackend.exception;

public class OrdersNotFoundException extends RuntimeException {
    public OrdersNotFoundException(Long id){
        super("Could not find the order with id "+ id);
    }
}
