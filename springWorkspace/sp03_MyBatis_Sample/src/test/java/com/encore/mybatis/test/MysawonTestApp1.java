package com.encore.mybatis.test;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.encore.mybatis.vo.MySawon;

public class MysawonTestApp1 {
	public static void main(String[] args) throws Exception{
		// 원래 이부분은 화면으로부터 받은 값을 가지고 VO가 생이 되는 부분이라고 생각하자
		// req.getParameter("id");
//		MySawon vo = new MySawon();
//		vo.setId("encore01");
//		vo.setPwd("0101");
//		vo.setName("아이유");
//		vo.setAge(33);
		
		MySawon vo = new MySawon();
		vo.setId("encore02");
		vo.setPwd("0202");
		vo.setName("정지소");
		vo.setAge(22);
		
		//2. mybatis 핵심 설정문서 r로 할당
		Reader r = Resources.getResourceAsReader("config/SqlMapConfig.xml");
		
		//3. SqlSessionFactoryBuilder...설정문서가 주입 DI...SqlSessionFactory
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(r);
		
		//4. SqlSessionFactory로부터 SqlSession
		SqlSession session = factory.openSession();
		
		// SqlSession이 쿼리문을 실행하는 모든 기능을 다 가지고 있다...
		// insert(), delete(), update(), selectOne(),selectList()
		session.insert("mysawonMapper.sawonAdd",vo);
		session.commit(); // DML일 때는 반드시 commit 해줘야 한다.
		session.close();
		
		System.out.println(vo.getName()+" 님 회원등록 성공");
	}

}
