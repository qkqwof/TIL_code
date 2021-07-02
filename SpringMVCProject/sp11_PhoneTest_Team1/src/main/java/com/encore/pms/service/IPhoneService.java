package com.encore.pms.service;

import java.util.List;

import com.encore.pms.dto.Phone;
import com.encore.pms.dto.UserInfo;

public interface IPhoneService {
	int insert(Phone phone);
	int delete(String num);
	Phone select(Phone phone);
	List<Phone> select();
	UserInfo selectUser(UserInfo user);
}
