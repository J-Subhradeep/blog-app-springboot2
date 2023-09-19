package com.blogapp.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapp.app.entities.Category;
import com.blogapp.app.entities.Post;
import com.blogapp.app.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer>{
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
	List<Post> findByTitleIgnoringCaseContaining(String title);
	Post findByPostId(Integer postId);
}
