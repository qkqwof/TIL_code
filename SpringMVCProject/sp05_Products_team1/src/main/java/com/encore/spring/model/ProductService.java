package com.encore.spring.model;

import java.util.List;
import java.util.Map;

import com.encore.spring.domain.Product;

public interface ProductService {

	public List<Product> getProductList();

	public List<Product> findProducts(Map map);

	public void enrollProduct(Product product);

	public void deleteProduct(String name);

	public void updateProduct(Product product);

}
