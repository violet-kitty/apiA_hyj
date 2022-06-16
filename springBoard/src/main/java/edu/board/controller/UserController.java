package edu.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.board.service.UserService;
import edu.board.vo.UserVO;

//user같이 url에 공통부분이 있는 경우 해당 공통부분이 있는 url가 다 이 컨트롤러로 오게 하는 어노테이션
@RequestMapping(value="/user")
@Controller
public class UserController {
	
	//왜 implement를 안쓰고 인터페이스를 쓰는가?
	//유지보수에 용이하게 만들기 위해, 코드가 바뀌면 imple만 수정하면 되도록 하려고
	//new 해서 계속 생성하는 일 막기위해 한번만 생성하고 재사용
	//인터페이스를 넣었지만 그 인터페이스 상속하는 모든 @service들이 다 여기 들어옴
	@Autowired
	UserService userService;
	
	
	//위에서 공통부분을 뺏으므로 '/user/어쩌구' 안쓰고 '/어쩌구'만 써도 됨
	@RequestMapping(value = "/register.do", method = RequestMethod.GET)
	public String register() {
		return "user/register";
	}
	
	@RequestMapping(value = "/info.do", method = RequestMethod.POST)
	public String info(UserVO vo, Model model) {
		/*
		 * 파라미터를 메소드로 전달 받는 방법은 매개변수의 명을 파라미터 키와 맞추는 방법과
		 * 매개변수 VO의 필드명을 맞추는 방법이 있다.
		 * */
		
		/*
		 * 매개변수로 일일히 하나씩 받아와 보내주기
		 * model.addAttribute("name", name); model.addAttribute("age", age);
		 * model.addAttribute("addr", addr); model.addAttribute("phone", phone);
		 */
		
		/* vo 보내주기 */
		model.addAttribute("vo", vo);
		
		return "user/info";
	}
	
	@RequestMapping(value = "/join.do", method = RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	@RequestMapping(value = "/join.do", method = RequestMethod.POST)
	public String join(UserVO vo, Model model) {
		int result = userService.insertUser(vo);
		
		
		
		//그냥 home으로 넘겨버리면 화면 상단 url이 join.do가 됨
		//그러니 redirect를 할것!
		//redirect:/는 home.jsp로 가겠다는 얘기
		//redirect를 쓰면 화면명이 아닌 경로값을 줘야함
		return "redirect:/";//redirect 할때는 리턴 문자열에 redirect: 키워드 뒤로 url 경로 값을 준다.
	}
}
