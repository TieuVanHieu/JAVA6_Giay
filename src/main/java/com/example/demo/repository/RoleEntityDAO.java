package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.RoleEntity;

/**
 * RoleEntityDAO
 */
public interface RoleEntityDAO extends JpaRepository<RoleEntity,String>{

    
}