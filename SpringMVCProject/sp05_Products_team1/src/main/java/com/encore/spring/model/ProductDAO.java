package com.encore.spring.model;

import java.util.List;
import java.util.Map;

import com.encore.spring.domain.Product;

public interface ProductDAO {
	public List<Product> getProductList();

	public List<Product> findProducts(Map map);

	public int enrollProduct(Product product);

	public int deleteProduct(String name);

	public int updateProduct(Product product);

}
