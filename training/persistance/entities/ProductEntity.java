package com.milankas.training.persistance.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "products")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name="description")
	private String description;

	@Column(name="company_id")
	private UUID companyId;

	@Column(name="blocked")
	private Boolean blocked;

	@Column(name="categories")
	private String categories;

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", description="+ description+ ", companyId="+ companyId+ ", blocked="+ blocked+ ", categories="+ categories+ "]";
	}

}
