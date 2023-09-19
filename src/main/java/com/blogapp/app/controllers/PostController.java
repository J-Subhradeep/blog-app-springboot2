package com.blogapp.app.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blogapp.app.entities.Post;
import com.blogapp.app.payloads.PostDto;
import com.blogapp.app.payloads.PostResponse;
import com.blogapp.app.services.FileService;
import com.blogapp.app.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

//	create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId

	) {
		PostDto createdPost = this.postService.createPost(postDto, userId, categoryId);

		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
	}

	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(

			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "2", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy

	) {
		return new ResponseEntity<PostResponse>(this.postService.getAllPosts(pageNumber, pageSize, sortBy),
				HttpStatus.ACCEPTED);
	}

	@GetMapping("/posts/category/{categoryId}")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
		return new ResponseEntity<List<PostDto>>(this.postService.getPostByCategory(categoryId), HttpStatus.ACCEPTED);
	}

	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPosts(@PathVariable("keyword") String keyword) {
		return new ResponseEntity<List<PostDto>>(this.postService.searchPost(keyword), HttpStatus.OK);
	}

	@PostMapping("/posts/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable("postId") Integer postId) throws IOException {
		String uploadImage;
		PostDto post = this.postService.getPost(postId);
		uploadImage = this.fileService.uploadImage(path, image);
		post.setImageName(uploadImage);
		PostDto updatePost = this.postService.updatePost(post, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.ACCEPTED);
	}

	
	@GetMapping(value = "/posts/images/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
	public void downloadImage(
			@PathVariable("imageName") String imageName,
			HttpServletResponse response
			) throws IOException {
		
		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_PNG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());
	}
}
