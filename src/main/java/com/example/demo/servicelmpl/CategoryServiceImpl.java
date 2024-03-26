package com.example.demo.servicelmpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CategoryEntity;
import com.example.demo.repository.CategoryEntityDAO;
import com.example.demo.service.CategoryService;

/**
 * CategoryServiceImpl
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryEntityDAO categoryEntityDAO;

    @Override
    public List<CategoryEntity> findAll() {
        return categoryEntityDAO.findAll();
    }

    
}
