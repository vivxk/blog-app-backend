package com.backend.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.blog.payloads.ApiResponse;
import com.backend.blog.payloads.PostDto;
import com.backend.blog.payloads.PostResponse;
import com.backend.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/user/{userid}/category/{categoryid}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable int userid,
			@PathVariable int categoryid) {
		PostDto createdPost = this.postService.createPost(postDto, userid, categoryid);
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);

	}

	// get posts by user
	@GetMapping("/user/{userid}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable int userid) {
		List<PostDto> postsByUser = this.postService.getPostsByUser(userid);
		return new ResponseEntity<List<PostDto>>(postsByUser, HttpStatus.OK);

	}

	// get posts by category
	@GetMapping("/category/{categoryid}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable int categoryid) {
		List<PostDto> postsByCategory = this.postService.getPostsByCategory(categoryid);
		return new ResponseEntity<List<PostDto>>(postsByCategory, HttpStatus.OK);

	}

	// get all posts
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false) String sortBy,
			@RequestParam(value = "sortOrder", defaultValue = "asc", required = false) String sortOrder) {
		PostResponse allPosts = this.postService.getAllPosts(pageNumber, pageSize, sortBy, sortOrder);

		return new ResponseEntity<PostResponse>(allPosts, HttpStatus.OK);
	}

	// get posts by postid
	@GetMapping("/post/{postid}")
	public ResponseEntity<PostDto> getPostById(@PathVariable int postid) {
		PostDto postById = this.postService.getPostById(postid);
		return new ResponseEntity<PostDto>(postById, HttpStatus.OK);
	}

	// update post
	@PutMapping("/posts/{postid}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable int postid) {
		PostDto updatedPost = this.postService.updatePost(postDto, postid);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);
	}

	// delete post
	@DeleteMapping("posts/{postid}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable int postid) {
		this.postService.deletePost(postid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted successfully", true), HttpStatus.OK);
	}

	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchPostsByTitle(@PathVariable String keywords) {
		List<PostDto> searchPostsbyTitle = this.postService.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(searchPostsbyTitle, HttpStatus.OK);

	}
}
