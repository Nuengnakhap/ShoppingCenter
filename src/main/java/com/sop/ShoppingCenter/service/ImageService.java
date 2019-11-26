package com.sop.ShoppingCenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sop.ShoppingCenter.model.Image;
import com.sop.ShoppingCenter.repository.ImageRepository;

@Service("imageService")
public class ImageService implements Services {

	@Autowired
	ImageRepository imageRepository;
	
	@Override
	public Object getById(int id) {
		if (imageRepository.findById(id).isPresent()) {
			return imageRepository.findById(id);
		}
		return null;
	}

	@Override
	public Object getAll() {
		return imageRepository.findAll();
	}

	@Override
	public void create(Object item) {
		Image order = (Image) item;
		imageRepository.save(order);
	}
	
	public void create(Image item) {
		imageRepository.save(item);
	}

	@Override
	public Boolean update(int id, Object item) {
		if (imageRepository.findById(id).isPresent()) {
			Image order = (Image) item;
			order.setId(id);
			imageRepository.save(order);
			return true;
		}
		return false;
	}

	@Override
	public Boolean deleteById(int id) {
		if (imageRepository.findById(id).isPresent()) {
			imageRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public void deleteAll() {
		imageRepository.deleteAll();
	}

}
