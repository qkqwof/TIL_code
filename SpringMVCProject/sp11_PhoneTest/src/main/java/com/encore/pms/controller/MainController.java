package com.encore.pms.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.encore.pms.dto.Phone;
import com.encore.pms.dto.UserInfo;
import com.encore.pms.service.IPhoneService;

@Controller
public class MainController {	
	
	@Autowired
	private IPhoneService iPhoneService;
	
	//@RequestMapping("login.do") //value가 요청하는 값이 login.do고 method 방식이 get방식
	
	@GetMapping("login.do") //4.3.2버전 이상이면 사용할 수 있다. 이걸로 이제 간다. 이렇게 요청이 들어오면
	public String getLoginForm() {
		//여기서 바로 login 폼인 Login.jsp로 간다
		return "Login"; // 이렇게 써주면 바로 간다.
	}
	
	@PostMapping("login.do") //Get도 있으면 Post매핑도 있다
	public String doLogin(UserInfo user, Model model, HttpSession session) {
		try {
			UserInfo selected = iPhoneService.select(user); //아이디와 패스워드 같은 사람을 찾았기 때문에 selected로...
			if(selected != null) { //로그인 성공
				session.setAttribute("loginUser", selected); //UserInfo 정보를 세션에 바인딩 한다..로그인 진행되는 동안 바인딩 되어있음 (데이터 바인딩은 이미 세션에다 해줬다.)
				
				//모든 폰이 검색된 결과페이지로 이동...
				//로그인이 되면 핸드폰 목록보는 곳으로 바로 이동시키고자 함
				return "redirect:searchPhone.do"; //redirect 반드시 해야하는 경우이다. DispatcherServlet 거쳐서 오기 때문에...
			}else { //로그인 실패
				return "Login"; //Login.jsp로 이동하도록..다시 로그인 시도
			}
		}catch(Exception e) { //비즈니스 로직 수행중 에러남
			model.addAttribute("title", "핸드폰 관리 - 로그인 실패");
			model.addAttribute("message", "문제 내용 - 로그인 중 오류가 발생했습니다.");
			return "Error"; //에러날 경우 Error.jsp로 돌림 
		}	
	}
	
	@GetMapping("searchPhone.do") //전체 폰 목록을 보여주는 기능.. 결과 페이지는 PhoneList.jsp
	public String doList(Model model) {
		try {
			List<Phone> list = iPhoneService.select();
			model.addAttribute("title", "핸드폰 관리 - 폰 리스트");
			model.addAttribute("phones", list);
			return "PhoneList";
		}catch(Exception e) {
			model.addAttribute("title", "핸드폰 관리 - 에러");
			model.addAttribute("phones", "문제 내용 - 폰 목록 조회 중 오류가 발생했습니다.");
			return "Error";
		}
	}
	
	@GetMapping("regPhone.do")
	public String getRegPhone(Model model) {
		model.addAttribute("title",  "핸드폰 관리 - 핸드폰 등록 폼");
		return "PhoneReg";
	}
	
	@PostMapping("savePhone.do")
	public String doRegPhone(Phone phone, Model model) {
		try {
			iPhoneService.insert(phone);
			model.addAttribute("title", "핸드폰 관리 - 핸드폰 등록 성공");
			return "Result";
		}catch(Exception e) {
			model.addAttribute("title", "핸드폰 관리 - 핸드폰 등록 에러");
			return "Error";
		}
	}
	
	@GetMapping("detail.do")
	public String doDetail(Phone phone, Model model) {
		try {
			Phone selected = iPhoneService.select(phone);
			model.addAttribute("title", "핸드폰 관리 - 핸드폰 상세조회 성공");
			model.addAttribute("phone", selected);
			return "PhoneView";
		}catch(Exception e) {
			model.addAttribute("title", "핸드폰 관리 - 핸드폰 상세조회 에러");
			model.addAttribute("message", "문제 내용 - 상세 조회 중 오류가 발생했습니다.");
			return "Error";
		}
	}
	
	@GetMapping("delete.do")
	public String doDelete(@RequestParam List<String> num, Model model) { //요청할 때 넘어오는 값 받을 때... RequestParam 써야.. 배열보다 List가 간단
		try {
			for(String n : num) iPhoneService.delete(n);
			return "redirect:searchPhone.do";
		}catch(Exception e) {
			model.addAttribute("title", "핸드폰 관리 - 핸드폰 삭제 시 에러");
			model.addAttribute("message", "문제 내용 - 삭제 중 오류가 발생했습니다");
			return "Error";
		}
	}
	
	@PostMapping("deleteAjax.do")
	public String doAjaxDelete(@RequestParam List<String> num, Model model) { //요청할 때 넘어오는 값 받을 때... RequestParam 써야.. 배열보다 List가 간단
		try {
			for(String n : num) iPhoneService.delete(n);
			return "JsonView";
		}catch(Exception e) {
			model.addAttribute("title", "핸드폰 관리 - 핸드폰 삭제 시 에러");
			model.addAttribute("message", "문제 내용 - 삭제 중 오류가 발생했습니다");
			return "Error";
		}
	}
}
