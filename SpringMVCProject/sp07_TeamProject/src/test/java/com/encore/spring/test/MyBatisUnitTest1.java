package com.encore.spring.test;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.encore.spring.domain.MyBook;
import com.encore.spring.domain.User;



//MyBatisFramework 단위 테스트 ....
public class MyBatisUnitTest1 {

	public static void main(String[] args) throws Exception {
		//1. 설정문서 읽어들여서
		Reader r = Resources.getResourceAsReader("config/SqlMapConfig.xml");
		
		//2. SqlSessionFactory
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(r);
		
		SqlSession session = factory.openSession();
//		User user = new User("admin","admin");
//		User user2 = session.selectOne("UserMapper.Login",user);
//		System.out.println(user2);
//		
//		
		MyBook vo = new MyBook("155235","test","test","test","2021-05-31","test","test",123,"test","test");
		
		session.insert("bookMapper.addBook",vo);
		session.commit();
		
//		List<MyBook> list = session.selectList("bookMapper.getBookList");
//		
//		System.out.println(list);
	}
}
