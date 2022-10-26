package com.example.Springh2database.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Springh2database.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long>{


    
}
