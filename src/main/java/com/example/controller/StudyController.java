package com.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.BookLibrary;
import com.example.model.PersonInfo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sun.el.parser.ParseException;

@Controller
public class StudyController {
		
		@GetMapping("")
		public String index(Model model) {
			return "index";
		}
		
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
			personInfo.setName("정요직");
			personInfo.setBirth("930727");
			personInfo.setPhone("01086491176");
			personInfo.setCertify("인증");
			personInfo.setCareer("4");
			
			PersonInfo personInfo2 = new PersonInfo();
			personInfo2.setEmail("ekhdcks@naver.com");
			personInfo2.setName("최동찬");
			personInfo2.setBirth("930321");
			personInfo2.setPhone("01076787700");
			personInfo2.setCertify("미인증");
			
			PersonInfo personInfo3 = new PersonInfo();
			personInfo3.setEmail("ekfgns@naver.com");
			personInfo3.setName("김달훈");
			personInfo3.setBirth("930205");
			personInfo3.setPhone("01073020978");
			personInfo3.setCertify("인증");
			
			List<PersonInfo> list = new ArrayList<>();
			list.add(personInfo);
			list.add(personInfo2);
			list.add(personInfo3);
			String htmlTag = "<h1>H1 텍스트 테스트</h1>"; 
			String xssAttack = "<script>alert('스크립트 공격 개시!');</script>"; 
			
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
		
		@GetMapping("/appleLoginPage")
		public String appleLoginPage(Model model) {
			return "appleLoginPage";
		}
		
		@RequestMapping(value = "/appleLoginCallBack", method = {RequestMethod.GET, RequestMethod.POST})
		public String appleLoginCallBack(@RequestBody String apple_data, HttpServletRequest request, Model model, HttpSession session, HttpServletResponse response) throws java.text.ParseException, JsonParseException, JsonMappingException, IOException, ParseException  {
			String[] datas = apple_data.split("[&]");
			String code = "";
			String id_token = "";
			for(String data : datas ) {
				if(data.contains("code=")) {
					code = data.replace("code=", "");
				}
				if(data.contains("id_token=")) {
					id_token = data.replace("id_token=", "");
				}
			}
			System.out.println(id_token);
			return "appleLoginCallBack";
		}
		
}
