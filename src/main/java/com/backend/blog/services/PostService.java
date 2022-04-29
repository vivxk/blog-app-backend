package com.backend.blog.services;

import java.util.List;

import com.backend.blog.payloads.PostDto;
import com.backend.blog.payloads.PostResponse;

public interface PostService {
	PostDto createPost(PostDto postDto, int userId, int categoryId);

	PostDto updatePost(PostDto postDto, int postId);

	void deletePost(int postId);

	PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String sortOrder);

	PostDto getPostById(int postId);

	List<PostDto> getPostsByCategory(int categoryId); // get all posts by category

	List<PostDto> getPostsByUser(int userId); // get all posts by user

	List<PostDto> searchPosts(String keyword); // search post by keyword

}
