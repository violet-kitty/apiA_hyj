package edu.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SampleController {
	
	
	//public�� �ƴ� �ٸ� ���������ڸ� ���� dispatcher�� ������ �� ����. �ݵ�� public!
	//������ ������ �͸� post, �������� ������ get
	//�� �޼ҵ�� �����Ϸ��� GET ����̾����. GET ��ĸ� �� �޼ҵ�� ���� ����
	@RequestMapping(value="/sample1.do", method=RequestMethod.GET)
	public String sample1(Model model) {
		
		model.addAttribute("key", "�ȳ��ϼ���");
		
		//views/ ���� ��κ��� Ȯ���� ���� �̸������� �������ָ� ��
		return "sample/sample01";
	}
	//�޼ҵ� �ɼ� ���� ����. �׷��� �Ǹ� get�̵� post�� �� �� �޼ҵ� ȣ��
	//�ϳ��� �޼ҵ�� get���, post������� �ι� ��밡���ѵ� method �����ϸ� ���� �Ұ���
	
	@RequestMapping(value="/sample2.do")
	public String sample2(String testData, Model model) {
	//���� ������ �޾ƿ���
	//�������� ���ϴ� �Ķ���� �̸��̶� �Ȱ��� ����� �޾ƿ�
	//�ٸ��� �޾ƿü��� ������ ������̼� ����ؾ���
	//�ش� ���� ���ʿ� @RequestParam("testData") �������
	/* 
	 *
	 * spring mvc���� �Ķ���͸� �޴� ����� �Ű����� ���� �Ķ���� ��� ��ġ��Ű���� @RequestParam �������̼��� ����ϸ��
	 * �̶� �Ķ���ʹ� �⺻������ ���ڿ� �����͸� ������ �⺻Ÿ�Կ� ���ؼ��� �ڵ� ����ȯ�� �����ϴ�.
	 * ���� ����ȯ �� �� ���� �����Ͱ� �Ѿ���� ��� ��û ������ �� �� �����Ƿ� �����ؾ��Ѵ�.
	 * 
	 * */
		model.addAttribute("key", "hello");
		System.out.println("testData : "+testData);
		
		//views/ ���� ��κ��� Ȯ���� ���� �̸������� �������ָ� ��
		return "sample/sample02";
	}
}
