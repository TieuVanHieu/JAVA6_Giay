package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "roles")
public class RoleEntity  implements Serializable{
	@Id
	private String roleId;
	private String roleName;
	
	@JsonIgnore
	@OneToMany(mappedBy = "role")
	List<AuthorityEntity> authorities;
}