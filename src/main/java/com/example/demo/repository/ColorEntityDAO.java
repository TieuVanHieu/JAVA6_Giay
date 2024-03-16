package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ColorEntity;

/**
 * ColorEntityDAO
 */
public interface ColorEntityDAO extends JpaRepository<ColorEntity, Integer> {

}
