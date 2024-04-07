package com.example.demo.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ProductDetail")
public class ProductDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer productDetailId;
    Integer quantity;

    @ManyToOne
    @JoinColumn(name = "productId")
    ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "sizeId")
    SizeEntity size;

    @ManyToOne
    @JoinColumn(name = "colorId")
    ColorEntity color;

    @JsonIgnore
    @OneToMany(mappedBy = "productDetail")
    List<CartDetailEntity> cartDetail;

    @JsonIgnore
    @OneToMany(mappedBy = "productDetail")
    List<OrderDetailEntity> orderDetail;
}

