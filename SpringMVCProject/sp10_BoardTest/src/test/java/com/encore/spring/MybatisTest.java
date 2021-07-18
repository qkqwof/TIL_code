package com.encore.spring;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.encore.spring.vo.Board;

public class MybatisTest {
	@Test
	public void unit() throws Exception{
		Reader r = Resources.getResourceAsReader("spring/SqlMapConfig.xml");	
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(r);
		SqlSession session = factory.openSession();
		session.insert("BoardMapper.insert",new Board("id","title","contents"));
				
		System.out.println("-------------");
		for(Object b:session.selectList("BoardMapper.selectAll")) {
			System.out.println((Board)b);
		}
		
		System.out.println("--------------");
		System.out.println(session.selectOne("BoardMapper.selectOne",1));
	}
}
