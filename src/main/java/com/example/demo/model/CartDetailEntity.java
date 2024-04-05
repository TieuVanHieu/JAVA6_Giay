package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CartDetailEntity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CartDetail")
public class CartDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer cartDetailId;
    Integer quantity;

    @ManyToOne
    @JoinColumn(name = "productDetailId")
    ProductDetailEntity productDetail;

    @ManyToOne
    @JoinColumn(name = "CartId")
    CartEntity cart;
}
