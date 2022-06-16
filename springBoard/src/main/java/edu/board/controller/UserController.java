package edu.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.board.service.UserService;
import edu.board.vo.UserVO;

//user���� url�� ����κ��� �ִ� ��� �ش� ����κ��� �ִ� url�� �� �� ��Ʈ�ѷ��� ���� �ϴ� ������̼�
@RequestMapping(value="/user")
@Controller
public class UserController {
	
	//�� implement�� �Ⱦ��� �������̽��� ���°�?
	//���������� �����ϰ� ����� ����, �ڵ尡 �ٲ�� imple�� �����ϸ� �ǵ��� �Ϸ���
	//new �ؼ� ��� �����ϴ� �� �������� �ѹ��� �����ϰ� ����
	//�������̽��� �־����� �� �������̽� ����ϴ� ��� @service���� �� ���� ����
	@Autowired
	UserService userService;
	
	
	//������ ����κ��� �����Ƿ� '/user/��¼��' �Ⱦ��� '/��¼��'�� �ᵵ ��
	@RequestMapping(value = "/register.do", method = RequestMethod.GET)
	public String register() {
		return "user/register";
	}
	
	@RequestMapping(value = "/info.do", method = RequestMethod.POST)
	public String info(UserVO vo, Model model) {
		/*
		 * �Ķ���͸� �޼ҵ�� ���� �޴� ����� �Ű������� ���� �Ķ���� Ű�� ���ߴ� �����
		 * �Ű����� VO�� �ʵ���� ���ߴ� ����� �ִ�.
		 * */
		
		/*
		 * �Ű������� ������ �ϳ��� �޾ƿ� �����ֱ�
		 * model.addAttribute("name", name); model.addAttribute("age", age);
		 * model.addAttribute("addr", addr); model.addAttribute("phone", phone);
		 */
		
		/* vo �����ֱ� */
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
		
		
		
		//�׳� home���� �Ѱܹ����� ȭ�� ��� url�� join.do�� ��
		//�׷��� redirect�� �Ұ�!
		//redirect:/�� home.jsp�� ���ڴٴ� ���
		//redirect�� ���� ȭ����� �ƴ� ��ΰ��� �����
		return "redirect:/";//redirect �Ҷ��� ���� ���ڿ��� redirect: Ű���� �ڷ� url ��� ���� �ش�.
	}
}
