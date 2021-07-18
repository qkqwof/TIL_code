package com.encore.spring.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.encore.spring.domain.Item;

@Repository
public class ItemDAOImpl implements ItemDAO{

	@Autowired
	private SqlSession sqlSession;
	private static final String NS = "ItemMapper."; //상수값으로 지정
	
	@Override
	public List<Item> getItemList() throws Exception {
		return sqlSession.selectList(NS+"getItemList"); //이렇게하면 ItemMapper. 과 붙는다
	}

	@Override
	public Item getItem(Integer itemid) throws Exception {	
		return sqlSession.selectOne(NS+"getItem", itemid);
	}
}
