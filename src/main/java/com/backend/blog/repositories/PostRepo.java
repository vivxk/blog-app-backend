package com.backend.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backend.blog.entities.Category;
import com.backend.blog.entities.Post;
import com.backend.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);

	@Query("select p from Post p where p.title like :key") // using manual query
	List<Post> findByTitleContaining(@Param("key") String title);

	// List<Post> findByTitleContaining(String title);
	/*
	 * not being used currently due to bug in hibernate 5.6.7 version Which returns
	 * error when searching for the second time
	 */
}
