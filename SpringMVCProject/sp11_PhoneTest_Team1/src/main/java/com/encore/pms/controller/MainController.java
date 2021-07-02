package com.encore.pms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
			UserInfo selected = iPhoneService.selectUser(user);
			if(selected != null) { //로그인 성공
				session.setAttribute("loginUser",selected);
				
				//모든 폰이 검색된 결과 페이지로 이동...
				//return "redirect:searchPhone.do"; //redirect 반드시 해야하는 경우이다..
				return "redirect:index.jsp"; // 홈으로 가게 변경..
				
			}else { //로그인 실패
				model.addAttribute("message", "아이디 혹은 비밀번호가 틀렸습니다.");
				return "Login";
			}
		}catch(Exception e) { //비지니스 로직 수행중 에러남
			model.addAttribute("title", "핸드폰 관리 - 로그인 실패");
			model.addAttribute("message", "문제 내용 - 로그인 중 에러 발생했습니다.");
			return "Error";
		}
	}
	
	@GetMapping("searchPhone.do")
	public String doList(Model model) {
		try {
			List<Phone> list = iPhoneService.select();
			System.out.println(list);
			model.addAttribute("title", "핸드폰 관리 - 폰 리스트");
			model.addAttribute("phones",list);
			return "PhoneList";
		}catch(Exception e) {
			model.addAttribute("title", "핸드폰 관리 - 에러");
			model.addAttribute("message", "문제 내용 - 폰 목록 조회 중 에러가 발생했습니다.");
			return "Error";
		}
		
	}
	
	@GetMapping("regPhone.do")
	public String getRegisterForm(Model model) {
		model.addAttribute("title", "핸드폰 관리 - 핸드폰 등록");
		return "PhoneReg";
	}
	
	@PostMapping("savePhone.do")
	public String doRegister(Phone phone, Model model) {
		try {
			//System.out.println(phone);
			
			iPhoneService.insert(phone);
			model.addAttribute("title", "핸드폰 관리 - 등록 성공");
			return "Result";
		}catch(Exception e) {
			model.addAttribute("title", "핸드폰 관리 - 등록 실패");
			model.addAttribute("message", "문제 내용 - 핸드폰 등록 중 에러 발생했습니다.");
			return "Error";
		}
	}
	
	@GetMapping("detail.do")
	public String doDetail(Phone phone, Model model) {
		try {
			System.out.println(phone);
			Phone selected = iPhoneService.select(phone);
			model.addAttribute("title", "핸드폰 관리 - 상세 조회 성공");
			model.addAttribute("phone", selected);
			
			return "PhoneView";
		}catch(Exception e) {
			model.addAttribute("title", "핸드폰 관리 - 상세 조회 실패");
			model.addAttribute("message", "문제 내용 - 상세 조회 중 에러 발생했습니다.");
			
			return "Error";
		}
		
	}
	
	@GetMapping("logout.do")
	public String logout(HttpSession session) throws Exception{
		session.invalidate();
		
		return "index";
	}
	
	@PostMapping("delete.do")
	public String delete(@RequestParam(value="numList[]") List<String> numList, Model model) throws Exception{
		System.out.println(numList);
		
		for(String num : numList) {
			iPhoneService.delete(num);
		}
		
		model.addAttribute("msg", "삭제 성공");
		
		return "JsonView";
	}
}





