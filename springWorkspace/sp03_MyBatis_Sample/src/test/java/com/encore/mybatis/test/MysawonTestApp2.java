package com.encore.mybatis.test;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.encore.mybatis.vo.MySawon;

public class MysawonTestApp2 {
	public static void main(String[] args) throws Exception{
		// 1. sqlMapConfig.xml 파일을 읽어들인다.
		Reader r = Resources.getResourceAsReader("config/SqlMapConfig.xml");
		
		// 2. SqlSessionFactory 리턴 받는다.
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(r);
		
		// 3. SqlSession 리턴 받는다.
		SqlSession session = factory.openSession();
		
		System.out.println("++++++++++++++++++++++++++");
		/*
		 *        SqlSession
		 *        
		 *     query statement                          execute method
		 *     INSERT INTO ---------------------------- insert("namespace.id이름",vo)
		 *     DELETE FROM ---------------------------- delete("namespace.id이름",xxx)
		 *     UPDATE table --------------------------- update("namespace.id이름",vo)
		 *     SELECT ALL ----------------------------- selectList("namespace.id이름") -> ArrayList가 리턴타입 아니다.
		 *     											selectList("namespace.id이름",xxx) -> 30번 부서 다 가져옴(파라미터 타입)
		 *     
		 *     SELECT one ----------------------------- selectOne("namespace.id이름",xxx)
		 */
		
		List<MySawon> list=session.selectList("mysawonMapper.sawonList");
		for(MySawon v : list) {
			System.out.println(v.getId() + "," + v.getName() + "," + v.getNum());
		}
	}
}
