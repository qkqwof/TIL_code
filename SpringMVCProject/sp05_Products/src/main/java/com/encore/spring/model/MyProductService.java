package com.encore.spring.model;

import java.util.List;

import com.encore.spring.domain.MyProduct;
//Transaction 처리는 1)설정문서 2)인터페이스 3)~Impl 클래스 4)처리하고자 하는 메소드


public interface MyProductService {
	void addProduct(MyProduct vo) throws Exception;
	List<MyProduct> findByProductName(String words)throws Exception;
	List<MyProduct> findProducts()throws Exception;
	List<MyProduct>  findByProductMaker(String words)throws Exception;
}
