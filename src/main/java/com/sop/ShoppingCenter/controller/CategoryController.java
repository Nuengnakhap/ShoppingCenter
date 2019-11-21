package com.sop.ShoppingCenter.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sop.ShoppingCenter.model.Category;
import com.sop.ShoppingCenter.response.ResponseMessage;
import com.sop.ShoppingCenter.service.CategoryService;

@RestController
public class CategoryController implements Controllers {

	@Autowired
	@Qualifier("categoryService")
	CategoryService categoryService;
	
	@Override
	@GetMapping("/category/{id}")
	public Object getById(@PathVariable int id) {
		if (categoryService.getById(id) != null) {
			return new ResponseMessage(HttpStatus.OK.value(), categoryService.getById(id));
		}
		return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
	}

	@Override
	@GetMapping("/category")
	public Object getAll() {
		return new ResponseMessage(HttpStatus.OK.value(), categoryService.getAll());
	}

	@Override
	@PostMapping("/category")
	public Object create(@RequestBody @Valid Object item) {
		ObjectMapper mapper = new ObjectMapper();
		Category cate = mapper.convertValue(item, new TypeReference<Category>(){});
		categoryService.create(cate);
		return categoryService.getAll();
	}

	@Override
	@PutMapping("/category/{id}")
	public Object update(@PathVariable int id, @RequestBody @Valid Object item) {
		ObjectMapper mapper = new ObjectMapper();
		Category cate = mapper.convertValue(item, new TypeReference<Category>(){});
		if (categoryService.update(id, cate)) {
			return new ResponseMessage(HttpStatus.OK.value(), cate);
		}
		return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
	}

	@Override
	@DeleteMapping("/store/{id}")
	public Object deleteById(@PathVariable int id) {
		if (categoryService.deleteById(id)) {
			return new ResponseMessage(HttpStatus.OK.value(), "Data has been deleted");
		}
		return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "Data not found");
	}

	@Override
	@DeleteMapping("/store")
	public void deleteAll() {
		categoryService.deleteAll();
	}

}
