package com.encore.rest.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.encore.rest.dto.Greeting;

@RestController
public class GreetingController {
	//필드추가
	private final AtomicLong counter = new AtomicLong(); // AtomicLong은 카운팅 할 때 자주 쓰임
	
	//http://127.0.0.1:8888/rest/greet
	@GetMapping("/greet") //데이터를 SELECT 해오는 요청 GetMapping
	public Greeting sayGreet() {
		return new Greeting(314L, "Restful API");
	}
	
	//주소창에 파라미터를 포함시키는 방법
	//http://127.0.0.1:8888/rest/greet/33
	/*
	 * @PathVariable은 단순 파라미터 값을 URL경로에 포함시키는 방법이다.
	 * greet / {num} 처럼 {}를 이용해서 매핑되는 URL 경로를 메소드 인자값 옵션인 
	 * @PathVariable 어노테이션으로 작성된 인자값 num에 자동 매핑된다.
	 */
	@GetMapping("/greet/{num}") // 이렇게 하게되면 num에 해당하는 값을 "/" 뒤에 넣어줘야한다.
	public String showSample(@PathVariable int num) {
		return "Hello Restful API case number... "+num;
	}
	
	// http://127.0.0.1:8888/rest/greet2?msg=ENCORE :: get방식
	@GetMapping("/greet2")
	public Greeting sayGreet(@RequestParam(value="msg", required=false, defaultValue="world") String name) { // 아무것도 안넣어주게 되면 디폴트로 world가 들어간다
		return new Greeting(counter.incrementAndGet(), name); //incrementAndGet()은 Auto Increment 같은 효과
	}
}
