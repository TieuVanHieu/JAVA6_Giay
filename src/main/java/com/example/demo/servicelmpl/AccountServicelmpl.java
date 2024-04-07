package com.example.demo.servicelmpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.AccountEntity;
import com.example.demo.repository.AccountEntityDAO;
import com.example.demo.service.AccountService;


@Service
public class AccountServicelmpl implements AccountService {

    @Autowired
    AccountEntityDAO accountEntityDAO;

    @Override
    public List<AccountEntity> findAll() {
        // TODO Auto-generated method stub
        return accountEntityDAO.findAll();
    }

    @Override
    public Optional<AccountEntity> findById(String userName) {
        // TODO Auto-generated method stub
       return accountEntityDAO.findById(userName);
    }
    
}
