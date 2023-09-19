package com.blogapp.app.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blogapp.app.entities.Category;
import com.blogapp.app.entities.Post;
import com.blogapp.app.entities.User;
import com.blogapp.app.exceptions.ResourceNotFoundException;
import com.blogapp.app.payloads.PostDto;
import com.blogapp.app.payloads.PostResponse;
import com.blogapp.app.repositories.CategoryRepo;
import com.blogapp.app.repositories.PostRepository;
import com.blogapp.app.repositories.UserRepo;
import com.blogapp.app.services.PostService;
@Service
public class PostServiceImpl implements PostService {

	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId, Integer categoryId) {
		// TODO Auto-generated method stub
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "ID", userId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "ID", categoryId));
		
		Post post = this.modelMapper.map(postDto,  Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);
		
		Post res = this.postRepository.save(post);
		
		return this.modelMapper.map(res, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepository.findByPostId(postId);
		post.setImageName(postDto.getImageName());
		post.setContent(postDto.getContent());
		post.setTitle(postDto.getTitle());
		this.postRepository.save(post);
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub

	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy) {

		Pageable pageable = PageRequest.of(pageNumber, pageSize,Sort.by(sortBy).ascending());
		Page<Post> allPosts = this.postRepository.findAll(pageable);
		List<Post> content = allPosts.getContent();
		
		
		List<PostDto> posts = content.stream().map(c->this.modelMapper.map(c, PostDto.class)).collect(Collectors.toList());
		
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(posts);
		postResponse.setPageNumber(allPosts.getNumber());
		postResponse.setPageSize(allPosts.getSize());
		postResponse.setTotalElements(allPosts.getTotalElements());
		postResponse.setLastPage(allPosts.isLast());
		postResponse.setTotalPages(allPosts.getTotalPages());
		
		
		return postResponse;
	}

	@Override
	public PostDto getPost(Integer postId) {
		// TODO Auto-generated method stub
		this.postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "ID", postId));
		return this.modelMapper.map(this.postRepository.findByPostId(postId), PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "ID", categoryId));
		List<PostDto> posts = this.postRepository.findByCategory(category).stream().map(p->this.modelMapper.map(p,PostDto.class)).collect(Collectors.toList());
		
		
		return posts; 
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		// TODO Auto-generated method stub
		
		return this.postRepository.findByTitleIgnoringCaseContaining(keyword).stream().map(p->this.modelMapper.map(p, PostDto.class)).collect(Collectors.toList());
	}

}
