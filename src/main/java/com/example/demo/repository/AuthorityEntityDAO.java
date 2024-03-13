package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.AuthorityEntity;

/**
 * AuthorityEntityDAO
 */
public interface AuthorityEntityDAO extends JpaRepository<AuthorityEntity,Integer>{

    
}
