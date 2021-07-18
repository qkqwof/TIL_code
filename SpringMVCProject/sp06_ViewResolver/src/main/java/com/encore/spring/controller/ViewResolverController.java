package com.encore.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewResolverController {
	
	@RequestMapping("register.do")
	public ModelAndView register() { //필요하면 인자값 넣어주면 되고, 필요없으면 안넣으면 된다.
		System.out.println("register() calling....");
		return new ModelAndView("register_result", "info", "InternalResourceViewResolver....");
	}
	
	//BeanNameViewResolver와 컨트롤러가 어떻게 연결되는지..
	@RequestMapping("findBoard.do")
	public ModelAndView findBoard() { //필요하면 인자값 넣어주면 되고, 필요없으면 안넣으면 된다.
		System.out.println("findBoard() calling....");
		return new ModelAndView("board_result"); // board/result/find_ok.jsp
	}
	
	@RequestMapping("findProduct.do")
	public ModelAndView findProduct() { //필요하면 인자값 넣어주면 되고, 필요없으면 안넣으면 된다.
		System.out.println("findProduct() calling....");
		return new ModelAndView("product_result"); // product/result/find_ok.jsp
	}
}
