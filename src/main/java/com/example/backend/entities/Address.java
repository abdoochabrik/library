package com.example.backend.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Address {
 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String city;
	private String country;
	//@ManyToOne(fetch = FetchType.LAZY, optional = false)
	//@JoinColumn(name = "user_id", nullable = false)
	//private User user;
}
