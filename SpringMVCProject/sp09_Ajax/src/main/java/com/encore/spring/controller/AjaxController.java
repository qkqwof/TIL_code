package com.encore.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.encore.spring.domain.Person;

@Controller
public class AjaxController {

	@RequestMapping("/") //contextPath로 요청을 한다면...(http://127.0.0.1:8888/spring 요청이 이렇게 들어온다면...)
	public String index() { //Spring MVC는 자유도가 꽤 높다. 반드시 ModelAndView를 리턴할 필요는 없다.
		System.out.println("/....calling.."); //호출이 어떻게 되는지만..
		return "redirect:index.jsp"; // String을 리턴한다는 건 결과페이지 이름만 리턴한다는 것이다.
	}
	
	@RequestMapping("synchro")
	public String synchro(Model model) { //데이터 프레임워크를 위한 기술 Model
		model.addAttribute("info", "와~~~ 동기통신!!!");
		System.out.println("동기통신...synchro() calling..");
		
		return "synchro_result"; // WEB-INF밑에 있는 view폴더 밑에 저장.. 확장자는 jsp로 붙게되어져있다.
	}
	
	@RequestMapping("asynch")
	public String asynch(Model model) { //데이터 바인딩 필요
		System.out.println("동기통신...asynchro() calling..");
		//비즈니스 로직 수행된 결과로...Service 호출결과...person객체가 리턴되었다고 가정하자.
		model.addAttribute("person", new Person("아이유", "서초동"));
		return "JsonView"; //비동기 통신에서 결과페이지는 없기 때문에 요청한 곳으로 되돌아간다. 파일 다운로드 될 뿐... BeanNameViewResolver필요
	}
}
