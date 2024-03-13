package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.AccountEntity;

/**
 * AccountEntityDAO
 */
public interface AccountEntityDAO extends JpaRepository<AccountEntity,String>{

    
}
