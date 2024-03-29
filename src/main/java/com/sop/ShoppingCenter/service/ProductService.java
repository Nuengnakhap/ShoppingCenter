package com.sop.ShoppingCenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sop.ShoppingCenter.model.Product;
import com.sop.ShoppingCenter.model.Store;
import com.sop.ShoppingCenter.repository.ProductRepository;

@Service("productService")
public class ProductService implements Services {

	@Autowired
	ProductRepository productRepository;
	
	@Override
	public Object getById(int id) {
		if (productRepository.findById(id).isPresent()) {
			return productRepository.findById(id).get();
		}
		return null;
	}

	@Override
	public Object getAll() {
		return productRepository.findAll();
	}

	@Override
	public void create(Object item) {
		Product product = (Product) item;
		product.setId(((int) productRepository.count() + 1));
		productRepository.save(product);
	}
	
	public Product create(Product item) {
		item.setId(((int) productRepository.count() + 1));
		return productRepository.save(item);
	}

	@Override
	public Boolean update(int id, Object item) {
		if (productRepository.findById(id).isPresent()) {
			Product product = (Product) item;
			product.setId(id);
			productRepository.save(product);
			return true;
		}
		return false;
	}
	
	public void updateMany(List<Product> item) {
		for (Product product : item) {
			productRepository.save(product);
		}
	}

	@Override
	public Boolean deleteById(int id) {
		if (productRepository.findById(id).isPresent()) {
			productRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public void deleteAll() {
		productRepository.deleteAll();
	}
	
	public List<Product> getByStore(Store store) {
		return productRepository.findByStore(store).get();
	}

}
