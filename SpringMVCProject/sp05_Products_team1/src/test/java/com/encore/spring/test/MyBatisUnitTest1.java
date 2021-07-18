package com.encore.spring.test;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.encore.spring.domain.Product;

// MyBatisramework 단위테스트
public class MyBatisUnitTest1 {
	public static void main(String[] args) throws Exception {
		// 1. 설명문서 읽기
		Reader r = Resources.getResourceAsReader("config/SqlMapConfig.xml");

		// 2. SqlSessionFactory
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(r);

		// 3. SqlSession
		SqlSession session = factory.openSession();

		// 4.쿼리문 수행
		Product product1 = new Product("고성능 세탁기", "LG", 500000);

//		// INSERT : ProductMapper.enrollProduct
//		System.out.println("ProductMapper.enrollProduct");
//		session.insert("ProductMapper.enrollProduct", product1);
//		session.commit();
//		System.out.println("INSERT SUCCESS");
//		System.out.println("\n==============\n");
//
//		// SELECT : ProductMapper.getProductList
//		System.out.println("ProductMapper.getProductList");
//		List<Product> list1 = session.selectList("ProductMapper.getProductList");
//		for (Product vo : list1)
//			System.out.println(vo);
//		System.out.println("\n==============\n");

		// SELECT : ProductMapper.findProducts
		System.out.println("ProductMapper.findProducts");
		List<Product> list2 = session.selectList("ProductMapper.findProducts", "테스트");
		for (Product vo : list2)
			System.out.println(vo);
		System.out.println("\n==============\n");
//
//		// UPDATE : ProductMapper.updateProduct
//		System.out.println("ProductMapper.updateProduct");
//		session.update("ProductMapper.updateProduct", new Product("고성능 세탁기", "삼성", 100000));
//		session.commit();
//		System.out.println("UPDATE SUCCESS");
//		List<Product> list3 = session.selectList("ProductMapper.getProductList");
//		for (Product vo : list3)
//			System.out.println(vo);
//		System.out.println("\n==============\n");
//
//		// DELETE : ProductMapper.deleteProduct
//		System.out.println("ProductMapper.deleteProduct");
//		session.delete("ProductMapper.deleteProduct", "고성능 세탁기");
//		session.commit();
//		System.out.println("DELETE SUCCESS");
//		List<Product> list0 = session.selectList("ProductMapper.getProductList");
//		for (Product vo : list0)
//			System.out.println(vo);
	}

}
