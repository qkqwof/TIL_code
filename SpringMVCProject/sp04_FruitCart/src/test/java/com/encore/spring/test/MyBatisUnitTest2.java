package com.encore.spring.test;
//JUnit 사용해서 단위테스트...

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.encore.spring.domain.Item;

public class MyBatisUnitTest2 {
	
	@Test //이렇게 여러개 블럭을 만들어놓고 단위테스트를 계속 할 수 있다
	public void unit() throws Exception{ //메인 대신에 이거 넣고
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
