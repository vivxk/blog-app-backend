package com.backend.blog.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.blog.entities.Category;
import com.backend.blog.exceptions.ResourceNotFoundException;
import com.backend.blog.payloads.CategoryDto;
import com.backend.blog.repositories.CategoryRepo;
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepo categoryrepo;

	@Autowired
	private ModelMapper modelmapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat = this.modelmapper.map(categoryDto, Category.class);
		Category addedCat = this.categoryrepo.save(cat);
		return this.modelmapper.map(addedCat, CategoryDto.class);

	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId) {
		Category cat = this.categoryrepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		cat.setCategoryDescription(categoryDto.getCategoryDescription());

		Category updatedCat = this.categoryrepo.save(cat);
		return this.modelmapper.map(updatedCat, CategoryDto.class);
	}

	@Override
	public void deleteCategory(int categoryId) {
		Category cat = this.categoryrepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Cateogry Id", categoryId));

		this.categoryrepo.delete(cat);
	}

	@Override
	public CategoryDto getCategoryById(int categoryId) {
		Category cat = this.categoryrepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category ", "Cateogry Id", categoryId));

		return this.modelmapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategories() { 
		List<Category> categories = this.categoryrepo.findAll();
		List<CategoryDto> allCategories = categories.stream().map((cat) -> modelmapper.map(cat, CategoryDto.class))
				.collect(Collectors.toList()); //converting Category type to CategoryDto to match method return type

		return allCategories;
	}

}
