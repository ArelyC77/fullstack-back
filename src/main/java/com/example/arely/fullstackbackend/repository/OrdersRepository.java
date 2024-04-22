package com.example.arely.fullstackbackend.repository;

import com.example.arely.fullstackbackend.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders,Long> {

}
