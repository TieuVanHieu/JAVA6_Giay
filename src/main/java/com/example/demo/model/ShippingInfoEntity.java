package com.example.demo.model;

import org.hibernate.annotations.Nationalized;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ShippingInfo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ShippingInfo")
public class ShippingInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer shippingId;
    @NotBlank(message = "Vui lòng nhập họ và tên !")
    @Nationalized
    String shippingName;
    @NotBlank(message = "vui long nhập địa chỉ nhận hàng !")
    @Nationalized
    String shippingAddress;

    @NotBlank(message = "Vui long nhập số điện thoại !")
    @Size(max = 10, min = 10, message = "Số điện thoại phải 10 số !")
    @Pattern(regexp = "^(0[3|5|7|8|9])+([0-9]{8})", message = "Số điện thoại di động không hợp lệ!")
    String shippingSdt;

    Boolean active = false;

    @JsonIgnore
    @OneToMany(mappedBy = "shippingInfo")
    List<OrderEntity> order;

    @ManyToOne
    @JoinColumn(name = "userName")
    AccountEntity account;


}
