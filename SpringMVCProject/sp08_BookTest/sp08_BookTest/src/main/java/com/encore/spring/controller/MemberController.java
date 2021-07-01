package com.encore.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.encore.spring.service.MemberService;
import com.encore.spring.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	@RequestMapping("memberLogin.do")
	public ModelAndView login(MemberVO vo, HttpServletRequest request) throws Exception{
		System.out.println("Login VO: "+ vo);
		
		
		MemberVO rvo = memberService.loginCheck(vo);
		
		System.out.println("RVO:: "+rvo);
		
		String path = "index.jsp";
		String msg = "아이디 혹은 패스워드가 틀립니다";
		
		if(rvo!=null) { // 해당하는 id, pw 멤버가 존재하고 로그인 성공했다면 
			// HttpServletRequest request로 하면 아래처럼
			request.getSession().setAttribute("user", rvo);
			msg = "정상적으로 로그인 되었습니다.";
		}
		// HttpSession session로 하면 아래처럼
		// session.setAttribute("msg", msg); // 로그인 성공 여부에 따라서 메세지가 달라진다.
		
		// HttpServletRequest request로 하면 아래처럼
		request.getSession().setAttribute("msg", msg);
		return new ModelAndView("redirect:"+path);
	}
	
	@RequestMapping("memberLogout.do")
	public ModelAndView logout(HttpSession session) throws Exception{
		String path = "index.jsp";
		session.invalidate();
		
		return new ModelAndView("redirect:"+path);
	}

	
}
