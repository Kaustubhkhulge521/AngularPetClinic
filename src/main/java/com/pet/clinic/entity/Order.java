package com.pet.clinic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	@Id
	private int id;
	private String vaccine;
	private int quantity;
	private double price;
}
