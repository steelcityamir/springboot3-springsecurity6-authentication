package com.example.demo.repository;

import com.example.demo.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    List<Login> findAllByEmailOrderByCreatedOn(String email);
}

