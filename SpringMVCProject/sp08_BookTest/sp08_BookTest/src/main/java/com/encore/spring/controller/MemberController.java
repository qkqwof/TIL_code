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
		String msg = "���̵� Ȥ�� �н����尡 Ʋ���ϴ�";
		
		if(rvo!=null) { // �ش��ϴ� id, pw ����� �����ϰ� �α��� �����ߴٸ� 
			// HttpServletRequest request�� �ϸ� �Ʒ�ó��
			request.getSession().setAttribute("user", rvo);
			msg = "���������� �α��� �Ǿ����ϴ�.";
		}
		// HttpSession session�� �ϸ� �Ʒ�ó��
		// session.setAttribute("msg", msg); // �α��� ���� ���ο� ���� �޼����� �޶�����.
		
		// HttpServletRequest request�� �ϸ� �Ʒ�ó��
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
