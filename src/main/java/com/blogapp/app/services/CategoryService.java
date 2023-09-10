package com.blogapp.app.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogapp.app.payloads.CategoryDto;


@Service
public interface CategoryService {

	
//	create
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	
	// update
	CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	
	
	//delete 
	public void deleteCategory(Integer categoryId);
	
	
	//get 
	public CategoryDto getCategory(Integer categoryId);
	
	//get all
	List<CategoryDto> getCategories();
}
