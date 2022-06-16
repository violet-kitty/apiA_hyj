package edu.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SampleController {
	
	
	//public이 아닌 다른 접근제한자를 쓰면 dispatcher가 접근할 수 없음. 반드시 public!
	//서버로 들어오는 것만 post, 나머지는 무조건 get
	//이 메소드로 접근하려면 GET 방식이어야함. GET 방식만 이 메소드로 접근 가능
	@RequestMapping(value="/sample1.do", method=RequestMethod.GET)
	public String sample1(Model model) {
		
		model.addAttribute("key", "안녕하세요");
		
		//views/ 하위 경로부터 확장자 제외 이름까지만 리턴해주면 됨
		return "sample/sample01";
	}
	//메소드 옵션 생략 가능. 그렇게 되면 get이든 post든 다 이 메소드 호출
	//하나의 메소드는 get방식, post방식으로 두번 사용가능한데 method 생략하면 재사용 불가능
	
	@RequestMapping(value="/sample2.do")
	public String sample2(String testData, Model model) {
	//보낸 데이터 받아오기
	//변수명을 원하는 파라미터 이름이랑 똑같게 해줘야 받아옴
	//다르게 받아올수는 있으나 어노테이션 사용해야함
	//해당 변수 앞쪽에 @RequestParam("testData") 써줘야함
	/* 
	 *
	 * spring mvc에서 파라미터를 받는 방법은 매개변수 명을 파라미터 명과 일치시키던가 @RequestParam 에노테이션을 사용하면됨
	 * 이때 파라미터는 기본적으로 문자열 데이터를 가지나 기본타입에 대해서는 자동 형변환이 가능하다.
	 * 만약 형변환 할 수 없는 데이터가 넘어오는 경우 요청 오류가 날 수 있으므로 주의해야한다.
	 * 
	 * */
		model.addAttribute("key", "hello");
		System.out.println("testData : "+testData);
		
		//views/ 하위 경로부터 확장자 제외 이름까지만 리턴해주면 됨
		return "sample/sample02";
	}
}
