package com.example.model;

public class PersonInfo {
	
	private String email;
	private String name;
	private String birth;
	private String phone;
	private String career;
	private String certify;
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getBirth() {
		return birth;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhone() {
		return phone;
	}
	
	public void setCareer(String career) {
		this.career = career;
	}
	public String getCareer() {
		return career;
	}
	
	public void setCertify(String certify) {
		this.certify = certify;
	}
	public String getCertify() {
		return certify;
	}
	
	public String toString() {
		return name+", 생년월일 : "+birth+", 휴대폰 번호 : "+phone;
	}
}
