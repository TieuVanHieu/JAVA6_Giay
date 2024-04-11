package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.RoleEntity;

/**
 * RoleEntityDAO
 */
public interface RoleEntityDAO extends JpaRepository<RoleEntity,String>{

    public Optional<RoleEntity> findById(String id);

}