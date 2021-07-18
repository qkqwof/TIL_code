package com.encore.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.encore.spring.domain.Product;
import com.encore.spring.model.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;

	//@RequestMapping(value="myProduct.do", method=RequestMethod.POST)
	@RequestMapping("myProduct.do") // 위 주석을 줄여서 이렇게
	public ModelAndView myProduct(String name, String maker, int price) throws Exception {
		Product product = new Product(name, maker, price);
		productService.enrollProduct(product);

		List<Product> list = productService.getProductList();
		return new ModelAndView("WEB-INF/views/productList.jsp", "list", list);
	}

	@RequestMapping("mySearch.do")
	public ModelAndView mySearch(String choice, String word) throws Exception {
		Map map = new HashMap();
		
		map.put("choice", choice);
		map.put("word", word);
		
		List<Product> list = productService.findProducts(map);
		return new ModelAndView("WEB-INF/views/productList.jsp", "list", list);
	}
}
