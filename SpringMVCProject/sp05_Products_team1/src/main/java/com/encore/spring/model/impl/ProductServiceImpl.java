package com.encore.spring.model.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.encore.spring.domain.Product;
import com.encore.spring.model.ProductService;
import com.encore.spring.model.ProductDAO;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;

	@Override
	public List<Product> getProductList() {
		return productDAO.getProductList();
	}

	@Override
	public List<Product> findProducts(Map map) {
		return productDAO.findProducts(map);
	}

	@Override
	public void enrollProduct(Product product) {
		productDAO.enrollProduct(product);
	}

	@Override
	public void deleteProduct(String name) {
		productDAO.deleteProduct(name);
	}

	@Override
	public void updateProduct(Product product) {
		productDAO.updateProduct(product);
	}

}
