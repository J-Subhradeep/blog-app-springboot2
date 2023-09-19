package com.blogapp.app.services;

import org.springframework.stereotype.Service;

import com.blogapp.app.payloads.CommentDto;

@Service
public interface CommentService {
	CommentDto createComment(CommentDto commentDto, Integer postId);
	void deleteComment(Integer commentId);
}
