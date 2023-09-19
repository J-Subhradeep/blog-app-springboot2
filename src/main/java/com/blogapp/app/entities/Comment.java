package com.blogapp.app.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String content;
	
	@ManyToOne
	private Post postId;
	
	
}
