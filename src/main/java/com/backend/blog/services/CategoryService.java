package com.backend.blog.services;

import java.util.List;

import com.backend.blog.payloads.CategoryDto;

public interface CategoryService {

	CategoryDto createCategory(CategoryDto categoryDto);

	CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);

	void deleteCategory(int categoryId);

	CategoryDto getCategoryById(int categoryId);

	List<CategoryDto> getAllCategories();
}
