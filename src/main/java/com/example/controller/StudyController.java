package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.model.BookLibrary;
import com.example.model.PersonInfo;

@Controller
public class StudyController {
		
		@GetMapping("/greeting")
		public String greeting(Model model) {
			BookLibrary library = new BookLibrary();
			library.setLoan("500");
			library.setPreservation("186");
			library.setReservation("220");
			library.setReturnBook("821");
			
			model.addAttribute("library", library);
			model.addAttribute("newBook", 156);
			model.addAttribute("bookStack", 8623);
			
			return "NewFile";   // html name
		}
		
		@GetMapping("/modelTest")
		public String modelTest(Model model) {
			PersonInfo personInfo = new PersonInfo();
			personInfo.setEmail("dywlr@naver.com");
			personInfo.setName("������");
			personInfo.setBirth("930727");
			personInfo.setPhone("01086491176");
			personInfo.setCertify("����");
			personInfo.setCareer("4");
			
			PersonInfo personInfo2 = new PersonInfo();
			personInfo2.setEmail("ekhdcks@naver.com");
			personInfo2.setName("�ֵ���");
			personInfo2.setBirth("930321");
			personInfo2.setPhone("01076787700");
			personInfo2.setCertify("������");
			
			PersonInfo personInfo3 = new PersonInfo();
			personInfo3.setEmail("ekfgns@naver.com");
			personInfo3.setName("�����");
			personInfo3.setBirth("930205");
			personInfo3.setPhone("01073020978");
			personInfo3.setCertify("����");
			
			List<PersonInfo> list = new ArrayList<>();
			list.add(personInfo);
			list.add(personInfo2);
			list.add(personInfo3);
			String htmlTag = "<h1>H1 �ؽ�Ʈ �׽�Ʈ</h1>"; 
			String xssAttack = "<script>alert('��ũ��Ʈ ���� ����!');</script>"; 
			
			List<String> stringList = new ArrayList<>();
			stringList.add("A");
			stringList.add("B");
			stringList.add("C");
			stringList.add("D");
			
			model.addAttribute("list", list);
			model.addAttribute("person", personInfo);
			model.addAttribute("test", "good");
			model.addAttribute("htmlTag", htmlTag);
			model.addAttribute("xssAttack", xssAttack);
			model.addAttribute("stringList", stringList);
			
			
			return "NewFile2";   // html name
		}
}
