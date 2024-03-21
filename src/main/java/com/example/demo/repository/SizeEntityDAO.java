package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.SizeEntity;

/**
 * SizeEntityDAO
 */
public interface SizeEntityDAO extends JpaRepository<SizeEntity, Integer> {

}