package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Nationalized;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity 
@Table(name = "Accounts")
public class AccountEntity  implements Serializable{
	@Id
	@NotBlank(message = "User name không thể để tróng !")
    String userName;
    @NotBlank(message = "Full name không thể để tróng !")
    @Nationalized
    String fullName;
    @NotBlank(message = "Password không thể để tróng !")
    String passWord;
    @Email(message = "Email không đúng định dạng !")
    @NotBlank(message = "Email không thê để trong !")
    String email;
	@NotBlank(message = "Vui long nhập số điện thoại !")
    @Size(max = 10, min = 10, message = "Số điện thoại phải 10 số !")
    @Pattern(regexp = "^(0[3|5|7|8|9])+([0-9]{8})", message = "Số điện thoại di động không hợp lệ!")
    String phone;

	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<ShippingInfoEntity> shippingInfo;
	
	@JsonIgnore
	@OneToOne(mappedBy = "account")
	CartEntity cart;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	List<AuthorityEntity> authorities;
}
