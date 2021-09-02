package com.ecommerce.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Table(name = "items")
@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String category;
	private String explanation;
	private Boolean available = true;
	private long seller;
	private LocalDateTime registeredAt;
	private String image;

	@Override
	public String toString()
	{
		return "{ id: " + id +
				"\n\tname: " + name +
				"\n\texplanation: " + explanation +
				"\n\tavailable: " + available +
				"\n\tseller: " + seller +
				"\n\tregisteredAt: " + registeredAt +
				"\n\timage: " + image +
				" }";
	}
}
