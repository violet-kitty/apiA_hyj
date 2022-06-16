package edu.board.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
//@Component의 하위 어노테이션
//ioc 컨테이너가 가지고 있는 객체가 됨
//ioc가 싱글톤 구조로 컨트롤러,dao 등을 하나만 만들어서 재사용하기 때문에 경량구조임
//실행하게 되면 requestMapping value가 일치하는 메소드가 있는 @controller 어노테이션 쓴 controller 뒤짐
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	//요청받은 경로와 메소드를 매핑하는 어노테이션
	//경로가 "/" 프로젝트패스 뒤의 /를 의미함
	//index 페이지임을 의미함
	//만약 index로 보낼 데이터가 있다면 model에 담기만 하면 됨
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		//모델에 담는것 : 화면에 값을 보내겠다.
		//addAttribute("키",값);
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	//@RequestMapping(value = "/sample1.do", method = RequestMethod.GET)
	public String sample01(Model model) {
		String str = "안녕하세요";
		
		//데이터 보내기
		model.addAttribute("str", str);
		
		return "sample/sample01";
	}
	
	//@RequestMapping(value = "/sample2.do", method = RequestMethod.GET)
	public String sample02(Model model) {
		String str = "hello";
		
		//데이터 보내기
		model.addAttribute("str", str);
		
		return "sample/sample02";
	}
	
	
}
