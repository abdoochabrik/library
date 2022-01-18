package com.example.backend.entities;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;
	private String username;
	private String password;
	private String role;
	@OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "cp_fk", referencedColumnName = "user_id")
	private List<Address> adresses;
	//@OneToMany(targetEntity = Book.class, cascade = CascadeType.ALL)
	//@JoinColumn(name = "cp_fk2", referencedColumnName = "user_id")
	//private List<Book> books;
}
