package com.encore.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// <bean name="/anno.do" class="com.encore.spring.controller.AnnoController"/> <-- 이 태그가 없어지는 것
@Controller
public class AnnoController {

	//? 마음대로 메소드를 지정하면 된다. 제약조건이 없어진 셈이다..리턴타입만 ModelAndView로 맞춰준다(나중엔 이것도 안지켜도 됨)
	@RequestMapping("anno.do") //여기선 슬러시("/")안해도 된다.
	public ModelAndView anno() {
		return new ModelAndView("WEB-INF/views/anno_result.jsp", "info", "Annotation Success!!");
	}
}
