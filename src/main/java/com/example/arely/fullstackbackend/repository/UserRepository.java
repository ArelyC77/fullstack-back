package com.example.arely.fullstackbackend.repository;

import com.example.arely.fullstackbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.converter.json.GsonBuilderUtils;

public interface UserRepository extends JpaRepository<User,Long> {

}
