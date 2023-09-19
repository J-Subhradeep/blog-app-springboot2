package com.blogapp.app.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.blogapp.app.entities.Comment;
import com.blogapp.app.entities.Post;
import com.blogapp.app.exceptions.ResourceNotFoundException;
import com.blogapp.app.payloads.CommentDto;
import com.blogapp.app.repositories.CommentRepository;
import com.blogapp.app.repositories.PostRepository;
import com.blogapp.app.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private PostRepository postRepository;
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		// TODO Auto-generated method stub
		Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "ID", postId));
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		comment.setPostId(post);
		Comment savedComment = this.commentRepository.save(comment);
		
		return this.modelMapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		// TODO Auto-generated method stub
		Comment comment = this.commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "ID", commentId));
		this.commentRepository.delete(comment);
	}

	
}
