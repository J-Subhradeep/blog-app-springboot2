package com.blogapp.app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="categories")
@NoArgsConstructor
@Getter
@Setter
public class Category {

	@Id
	@GeneratedValue
	private Integer categoryId;
	@Column(name="title",length = 500)
	private String categoryTitle;
	@Column(name="description")
	private String categoryDescription;
}