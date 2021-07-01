package com.encore.pms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.encore.pms.dto.Phone;
import com.encore.pms.dto.UserInfo;
import com.encore.pms.service.IPhoneService;

@Controller
public class MainController {	
	
	@Autowired
	private IPhoneService iPhoneService;
	
	@GetMapping("login.do")
	public String getLoginForm() {
		return "Login";
	}
	
	@PostMapping("login.do")
	public String doLogin(UserInfo user, Model model, HttpSession session) {
		try {
			UserInfo selected = iPhoneService.select(user);
			if(selected != null) {//로그인 성공
				session.setAttribute("loginUser",selected);
				
				//모든 폰이 검색된 결과 페이지로 이동....
				return "redirect:searchPhone.do"; //redirect 반드시 해야 하는 경우이다.
			}else {//로그인 실패
				return "Login"; //Login.jsp로 이동하도록..다시 로그인 시도
			}
		}catch(Exception e) {// 비즈니스 로직 수행 중 에러남...
			model.addAttribute("title", "핸드폰 관리 - 로그인 실패");
			model.addAttribute("message","문제 내용 - 로그인 중 오류가 발생했습니다. ");
			return "Error";
		}
	}
	
	@GetMapping("searchPhone.do") //전체 폰 목록을 보여주는 기능...결과 페이지는 PhoneList.jsp
	public String doList(Model model) {
		try {
			List<Phone> list = iPhoneService.select();
			model.addAttribute("title","핸드폰 관리 - 폰 리스트");
			model.addAttribute("phones",list);
			return "PhoneList";
		}catch(Exception e) {
			model.addAttribute("title","핸드폰 관리 - 에러");
			model.addAttribute("message","문제 내용 - 폰 목록 조회 중 오류가 발생했습니다.");
			return "Error";
		}
	}
	
	@GetMapping("regPhone.do")
	public String getRegPhone(Model model) {
		model.addAttribute("title", "핸드폰 관리 - 핸드폰 등록폼");
		return "PhoneReg";
	}

	@PostMapping("savePhone.do")
	public String doRegPhone(Phone phone, Model model) {
		try {
			iPhoneService.insert(phone);
			model.addAttribute("title", "핸드폰 관리 - 핸드폰 등록 성공");
			return "Result";
		}catch(Exception e) {
			model.addAttribute("title","핸드폰 관리 - 에러");
			model.addAttribute("message","문제 내용 - 폰 목록 조회 중 오류가 발생했습니다.");
			return "Error";
		}
	}
	
	@GetMapping("logout.do")
	public String logout() {
		return "index";
	}
	
}





