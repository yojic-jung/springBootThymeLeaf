package com.example.controller;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.JSONParser;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;

import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.BookLibrary;
import com.example.model.PersonInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import http.communication.HttpClientUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.minidev.json.JSONObject;
import social.login.apple.model.Key;
import social.login.apple.model.Keys;

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
		
		@GetMapping("/appleLoginPage")
		public String appleLoginPage(Model model) {
			String state = new BigInteger(130, new SecureRandom()).toString(32);
			model.addAttribute("client_nonce", "75dbac00-b326-42fa-86b4-dde6b38c7201");
			model.addAttribute("state", state);
			
			return "appleLoginPage";
		}
		
		
		
		@RequestMapping(value = "/appleLoginCallBack", method = {RequestMethod.GET, RequestMethod.POST})
		public String appleLoginCallBack(@RequestBody String apple_data, Model model, HttpServletRequest request) throws ParseException, JOSEException, org.json.simple.parser.ParseException, IOException {
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
			
			SignedJWT signedJWT = SignedJWT.parse(id_token);
			JWTClaimsSet payload = signedJWT.getJWTClaimsSet();
			
			String publicKeys = HttpClientUtils.doGet("https://appleid.apple.com/auth/keys");
	        ObjectMapper objectMapper = new ObjectMapper();
	        Keys keys = objectMapper.readValue(publicKeys, Keys.class);
	        
	        boolean signature=false;
	        for (Key key : keys.getKeys()) {
	        	 RSAKey rsaKey = (RSAKey) JWK.parse(objectMapper.writeValueAsString(key));
	             RSAPublicKey publicKey = rsaKey.toRSAPublicKey();
	             JWSVerifier verifier = new RSASSAVerifier(publicKey);
	             if (signedJWT.verify(verifier)) {
	            	 signature=true;
	             }
	        }
	        
	        if(signature == false) {
	        	model.addAttribute("appleCerity", "fail");
	        	return "member/appleLoginCallBack";
	        }
	        
	        Date currentTime = new Date(System.currentTimeMillis());
	       
	        String aud = payload.getAudience().get(0);
	        String iss = payload.getIssuer();
	      
	        String nonce =(String)payload.getClaim("nonce");
	        if (!currentTime.before(payload.getExpirationTime())) {
	        	model.addAttribute("appleCerity", "fail");
	        	 System.out.println("��ū���� ����");
	        	 return "appleLoginCallBack";
	        }
	        
	        //aud �����ʿ�
	        if (!aud.equals("com.lessonwang.coksabu")) {
	        	model.addAttribute("appleCerity", "fail");
	        	System.out.println("aud ����");
	        	return "appleLoginCallBack";
	        }
	        if (!iss.contains("https://appleid.apple.com")) {
	        	model.addAttribute("appleCerity", "fail");
	        	System.out.println("iss ����");
	        	return "appleLoginCallBack";
	        }
	        if (!nonce.equals("75dbac00-b326-42fa-86b4-dde6b38c7201")) {
	        	model.addAttribute("appleCerity", "fail");
	        	System.out.println("nonce ����");
	        	return "appleLoginCallBack";
	        }
	        
	        String email = (String)payload.getClaim("email");
	        System.out.println(email);
	        model.addAttribute("email", email);
	        
	        
	        //��������� �����ص� ��ǻ� �������� �������
	        
	        
	      /*client_secret���� 
	       * 
	       *Ű���� �ʿ���
	        ClassPathResource resource = new ClassPathResource("AuthKey_6GLL8F2426.p8");
	        String privateKey = new String(Files.readAllBytes(Paths.get(resource.getURI())));
	        Reader pemReader = new StringReader(privateKey);
	        PEMParser pemParser = new PEMParser(pemReader);
	        JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
	        PrivateKeyInfo object = (PrivateKeyInfo) pemParser.readObject();
	        
	       
	        Date expirationDate = Date.from(LocalDateTime.now().plusDays(30).atZone(ZoneId.systemDefault()).toInstant());
	        String clientSecret = Jwts.builder()
	                   .setHeaderParam("kid", "6GLL8F2426")
	                   .setHeaderParam("alg", "ES256")
	                   .setIssuer("6GR3L5NV8P")
	                   .setIssuedAt(new Date(System.currentTimeMillis()))
	                   .setExpiration(expirationDate)
	                   .setAudience("https://appleid.apple.com")
	                   .setSubject("com.coksabu.coksabu")
	                   .signWith(SignatureAlgorithm.ES256, converter.getPrivateKey(object))
	                   .compact();
	                
	        
	        Map<String, String> tokenRequest = new HashMap<>();

	        StringBuffer now_url =request.getRequestURL();
	        String current_url = now_url.toString();
	        String redirect_url="";
	        if(current_url.contains("www.coksabu")) {
	        	redirect_url="https://www.coksabu.com/loginCallBackApple";
	        }else if(current_url.contains("m.coksabu")) {
	        	redirect_url="https://m.coksabu.com/loginCallBackApple";
	        }else {
	        	redirect_url="https://coksabu.com/loginCallBackApple";
	        }
	        tokenRequest.put("client_id", "com.coksabu.coksabu");
	        tokenRequest.put("client_secret", clientSecret);
	        tokenRequest.put("code", code);
	        tokenRequest.put("grant_type", "authorization_code");
	        tokenRequest.put("redirect_uri", redirect_url);

	        String apple_response = HttpClientUtils.doPost("https://appleid.apple.com/auth/token", tokenRequest);
	        JSONParser parser = new JSONParser();
	        Object obj = parser.parse(apple_response);
	        JSONObject jsonObj = (JSONObject) obj;
	        String valid_id_token = (String) jsonObj.get("id_token");
	        String[] valid_id_tokens = valid_id_token.split("[.]");
	        Decoder decoder = Base64.getDecoder(); 
	        byte[] decodedBytes = decoder.decode(valid_id_tokens[1]);
	        String payLoad = new String(decodedBytes);
	        Object obj2 = parser.parse(payLoad);
	        JSONObject jsonObj2 = (JSONObject) obj2;
	        String apple_email = (String) jsonObj2.get("email");
	        System.out.println(apple_email);
	        */
	        
			return "appleLoginCallBack";
		}
		
}
