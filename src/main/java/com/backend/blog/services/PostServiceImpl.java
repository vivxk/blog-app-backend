package com.backend.blog.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.backend.blog.entities.Category;
import com.backend.blog.entities.Post;
import com.backend.blog.entities.User;
import com.backend.blog.exceptions.ResourceNotFoundException;
import com.backend.blog.payloads.PostDto;
import com.backend.blog.payloads.PostResponse;
import com.backend.blog.repositories.CategoryRepo;
import com.backend.blog.repositories.PostRepo;
import com.backend.blog.repositories.UserRepo;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelmapper;

	@Override
	public PostDto createPost(PostDto postDto, int userId, int categoryId) {
		User user = this.userRepo.findById(userId)// find if user exists
				.orElseThrow(() -> new ResourceNotFoundException("User with", "id", userId));
		Category category = this.categoryRepo.findById(categoryId) // find if category exists
				.orElseThrow(() -> new ResourceNotFoundException("Category with", "id", categoryId));

		Post post = this.modelmapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post createdPost = this.postRepo.save(post);

		return this.modelmapper.map(createdPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, int postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());

		Post updatedPost = this.postRepo.save(post);
		return this.modelmapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(int postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		this.postRepo.delete(post);

	}

	/*
	 * pageSize-amount of posts to be returned on a single page, pageNumber-current
	 * page number, sortOrder=direction to sort(ascending or descending)
	 */
	@Override
	public PostResponse getAllPosts(int pageNumber, int pageSize, String sortBy, String sortOrder) {
		Sort sort = (sortOrder.equalsIgnoreCase("asc")) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		/*
		 * if (sortOrder.equalsIgnoreCase("asc")) { sort = Sort.by(sortBy).ascending();
		 * } else { sort = Sort.by(sortBy).descending(); }
		 */

		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = this.postRepo.findAll(pageable);
		List<Post> allPosts = pagePost.getContent();
		List<PostDto> allPostDtos = allPosts.stream().map((post) -> this.modelmapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setContent(allPostDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());

		return postResponse;
	}

	@Override
	public PostDto getPostById(int postId) {
		Post getPostById = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

		return this.modelmapper.map(getPostById, PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(int categoryId) {
		Category cat = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId)); // find category
																									// by id
		List<Post> posts = this.postRepo
				.findByCategory(cat); /* pass the output from prev in findByCategory() of postRepo */

		List<PostDto> postDtos = posts.stream().map((post) -> this.modelmapper.map(post, PostDto.class))
				.collect(Collectors.toList()); // converting each post from list of posts into PostDto obj.
		return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(int userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post) -> this.modelmapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining("%" + keyword + "%");
		/*
		 * manual query due to bug in hibernate 5.6.7
		 */ List<PostDto> postDtos = posts.stream().map((post) -> this.modelmapper.map(post, PostDto.class))
				.collect(Collectors.toList()); // changing every post to PostDto
		return postDtos;
	}

}
