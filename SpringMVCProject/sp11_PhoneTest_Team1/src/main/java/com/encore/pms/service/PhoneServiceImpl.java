package com.encore.pms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.encore.pms.dao.IPhoneDAO;
import com.encore.pms.dto.Phone;
import com.encore.pms.dto.UserInfo;

@Service
public class PhoneServiceImpl implements IPhoneService {
	
	@Autowired
	private IPhoneDAO iPhoneDao;
	
	@Transactional
	@Override
	public int insert(Phone phone) {
		// TODO Auto-generated method stub
		return iPhoneDao.insert(phone);
	}
	
	@Transactional
	@Override
	public int delete(String num) {
		// TODO Auto-generated method stub
		return iPhoneDao.delete(num);
	}

	@Override
	public Phone select(Phone phone) {
		// TODO Auto-generated method stub
		return iPhoneDao.select(phone);
	}

	@Override
	public List<Phone> select() {
		// TODO Auto-generated method stub
		return iPhoneDao.select();
	}

	@Override
	public UserInfo selectUser(UserInfo user) {
		// TODO Auto-generated method stub
		return iPhoneDao.selectUser(user);
	}

}
