package com.sop.ShoppingCenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sop.ShoppingCenter.model.Category;
import com.sop.ShoppingCenter.repository.CategoryRepository;

@Service("categoryService")
public class CategoryService implements Services {

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public Object getById(int id) {
		if (categoryRepository.findById(id).isPresent()) {
			return categoryRepository.findById(id);
		}
		return null;
	}

	@Override
	public Object getAll() {
		return categoryRepository.findAll();
	}

	@Override
	public void create(Object item) {
		Category cate = (Category) item;
		cate.setId(((int) categoryRepository.count() + 1));
		categoryRepository.save(cate);
	}
  
	@Override
	public Boolean update(int id, Object item) {
		if (categoryRepository.findById(id).isPresent()) {
			Category cate = (Category) item;
			cate.setId(id);
			categoryRepository.save(cate);
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteById(int id) {
		if (categoryRepository.findById(id).isPresent()) {
			categoryRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public void deleteAll() {
		categoryRepository.deleteAll();
	}
}