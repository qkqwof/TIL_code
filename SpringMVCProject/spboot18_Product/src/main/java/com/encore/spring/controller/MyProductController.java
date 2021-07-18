package com.encore.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.encore.spring.domain.MyProduct;
import com.encore.spring.model.MyProductService;

@Controller
public class MyProductController {
	
	@Autowired
	private MyProductService myProductService;
	
//	@RequestMapping(value="myProduct.do", method=RequestMethod.POST)
	@RequestMapping("myProduct.do")
	public ModelAndView insert(MyProduct pvo) throws Exception{
		
		System.out.println("폼으로부터 받은 데이타 "+pvo.getId()); //0
		
		myProductService.addProduct(pvo); //여기서 dao, sqlSession, 디비 로 가서 id 값이 자동증가...--> 그 값을 다시 vo에 set해주기 떄문에
		
		System.out.println("폼으로부터 받은 데이타 "+pvo.getId()); //6,7...
		return new ModelAndView("insert_result", "info", pvo);
	}
	
	@RequestMapping("myProductSearch.do")
	public ModelAndView getProducts(String word, String command) throws Exception{
		String viewName = "";
		List<MyProduct> list = null;
		
		if(command.equals("findByProductName")) {
			list = myProductService.findByProductName(word);
			viewName=  "find_result";
		}else if(command.equals("findByProductMaker")) {
			list = myProductService.findByProductMaker(word);
			viewName=  "find_result";
		}
		return new ModelAndView(viewName, "list", list);		
	}
}




















