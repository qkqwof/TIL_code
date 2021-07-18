package com.encore.spring.model.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.encore.spring.domain.Product;
import com.encore.spring.model.ProductDAO;

@Repository
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SqlSession sqlSession;
	private final String NS = "ProductMapper.";

	@Override
	public List<Product> getProductList() {
		return sqlSession.selectList(NS + "getProductList");
	}

	@Override
	public List<Product> findProducts(Map map) {
		return sqlSession.selectList(NS + "findProducts", map);
	}

	@Override
	public int enrollProduct(Product product) {
		int result = sqlSession.insert(NS + "enrollProduct", product);
		return result;
	}

	@Override
	public int deleteProduct(String name) {
		int result = sqlSession.delete(NS + "deleteProduct", name);
		return result;
	}

	@Override
	public int updateProduct(Product product) {
		int result = sqlSession.update(NS + "updateProduct", product);
		return result;
	}

}
