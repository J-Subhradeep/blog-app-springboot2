package com.blogapp.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blogapp.app.entities.Post;
import com.blogapp.app.payloads.PostDto;
import com.blogapp.app.payloads.PostResponse;
import com.blogapp.app.repositories.PostRepository;

public interface PostService {

	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	PostDto updatePost(PostDto postDto,Integer postId);
	void deletePost(Integer postId);
	PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy);
	PostDto getPost(Integer postId);
	List<PostDto> getPostByCategory(Integer categoryId);
	List<PostDto> getPostByUser(Integer userId);
	List<PostDto> searchPost(String keyword);
	
}
