package com.example.arely.fullstackbackend.repository;

import com.example.arely.fullstackbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
