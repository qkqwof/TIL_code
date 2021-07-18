package com.encore.spring.test;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.encore.spring.domain.Item;

//MyBatisFramework 단위테스트..
public class MyBatisUnitTest1 {
	public static void main(String[] args) throws Exception{
		//1. 설정문서 읽어들여서
		Reader r = Resources.getResourceAsReader("config/SqlMapConfig.xml");
		
		//2. SqlSessionFactory
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(r); //MyBatis 프레임워크 단위테스트이기 때문에 new 나와도됨, (new는 DI 단위테스트때 쓰면 안됨!)
		
		//3. SqlSession
		SqlSession session = factory.openSession();
		
		//4. 쿼리문 수행..
		List<Item> list = session.selectList("ItemMapper.getItemList");
		for(Item vo : list) System.out.println(vo);
		
		System.out.println("\n==============================================\n");
		
		Item item = session.selectOne("ItemMapper.getItem", 1111);
		System.out.println(item);
	}
}
