package com.example.demo.service;

import java.util.List;

import com.example.demo.model.CategoryEntity;

/**
 * CategoryService
 */
public interface CategoryService {
    List<CategoryEntity> findAll();
}