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
		System.out.println("login VO :: "+vo); //?
		
		MemberVO rvo=memberService.loginCheck(vo);
		
		System.out.println("RVO :: "+rvo); //?
		
		String path = "index.jsp";
		String msg = "아이디 혹은 패스워드가 틀립니다.";
		
		if(rvo!=null) { //해당하는 id, pw 멤버가 존재하고 로그인 성공했다면
			request.getSession().setAttribute("user", rvo); //request사용 시 session에 바인딩 필요
			
			msg = "정상적으로 로그인 되었습니다.";
		}else {
			path="error.jsp";
			msg="로그인 실패하였습니다";
		}
		
		request.getSession().setAttribute("msg", msg);//로그인 성공 여부에 따라서 메세지가 달라진다.//request사용 시 session에 바인딩 필요
		return new ModelAndView("redirect:"+path);
	}
	
	@RequestMapping("memberLogout.do")
	public ModelAndView logout(HttpSession session) throws Exception{
		String path = "index.jsp";
		session.invalidate();
		
		return new ModelAndView("redirect:" + path);
	}
	
}






