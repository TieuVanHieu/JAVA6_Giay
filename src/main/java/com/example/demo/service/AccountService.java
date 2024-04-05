package com.example.demo.service;


import com.example.demo.model.AccountEntity;

/**
 * AccountService
 */
public interface AccountService {

    AccountEntity findById(String username);   

}