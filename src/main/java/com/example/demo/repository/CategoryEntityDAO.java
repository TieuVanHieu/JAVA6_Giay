package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.CategoryEntity;

/**
 * CategoryEntityDAO
 */
public interface CategoryEntityDAO extends JpaRepository<CategoryEntity, Integer> {

}
