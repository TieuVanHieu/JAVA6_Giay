package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.AccountEntity;

/**
 * AccountEntityDAO
 */
public interface AccountEntityDAO extends JpaRepository<AccountEntity,String>{
    List<AccountEntity> findByUserName(String userName);

    AccountEntity getByuserName(String userName);

    List<AccountEntity> findByEmail(String email);
    
}
