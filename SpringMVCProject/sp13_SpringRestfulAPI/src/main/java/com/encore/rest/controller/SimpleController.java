package com.encore.rest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.encore.rest.dto.Customer;

/*
 * @Controller
 *  --> @RequestMapping() 실행결과로 결과페이지 정보가 반환된다.
 * 
 * @RestController
 *  --> @RequestMapping() 실행결과로 데이터가 반환된다.
 *  --> @RequestMapping() Rest API 서비스를 사용해서 요청을 한다.
 *  	C   -   INSERT :: doPost()
 *  	R	-   SELECT :: doGet()
 *  	U	-   SELECT :: doPut()
 *  	D	-   SELECT :: doDelete()
 */
@RestController
@RequestMapping("/simple") // 이게 붙으면 http://127.0.0.1:8888/rest/simple/hello/  <--- 이렇게 요청해야한다. 
public class SimpleController {
	
	//http://127.0.0.1:8888/rest/hello ---> 브라우저에 Hello Restful Service!! 이 데이터가 반환되어진다..
	@GetMapping("/hello") //데이터를 SELECT 해오는 요청 GetMapping
	public String sayHello() {
		return "Hello Restful Service!!";
	}
		
	//http://127.0.0.1:8888/rest/simple/custom
	//객체가 리턴되는 경우...JSON으로 변환해서 리턴하기 때문에 라이브러리를 반드시 추가해야 한다.
	@GetMapping("/custom")
	public Customer detail() {
		//비즈니스 로직 수행한 결과로 Customer 객체가 리턴됐다고 가정하자
		Customer custom = new Customer("아이유", "새벽거리를 걸었어요");
		return custom;
	}
	
	@GetMapping("/allCustom")
	public List<Customer> allCustom(){
		//비즈니스 로직 수행한 결과로 List 객체가 리턴됐다고 가정하자
		List<Customer> list = new ArrayList<Customer>();
		for(int i=0; i<3; i++) {
			Customer vo = new Customer();
			vo.setName("아이유 , "+i);
			vo.setMessage("새벽길을 걸어요 , "+i);
			list.add(vo);
		}
		return list;
	}
	
	//http://127.0.0.1:8888/rest/simple/mapCustom
	@GetMapping("/mapCustom")
	public Map<Integer, Customer> sendCustom() {
		//비즈니스 로직 수행한 결과로 Map 객체가 리턴됐다고 가정하자
		Map<Integer, Customer> map = new HashMap<Integer, Customer>();
		map.put(1, new Customer("아이유1", "새벽길을 걸어요1"));
		map.put(2, new Customer("아이유2", "새벽길을 걸어요2"));
		map.put(3, new Customer("아이유3", "새벽길을 걸어요3"));
		return map;
	}
}
