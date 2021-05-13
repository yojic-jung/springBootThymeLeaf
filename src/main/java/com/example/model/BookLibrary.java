package com.example.model;

public class BookLibrary {
	
	private String loan;
	private String reservation;
	private String returnBook;
	private String preservation;
	
	public String getLoan() {
		return loan;
	}
	public void setLoan(String loan) {
		this.loan = loan;
	}
	
	public String getReservation() {
		return reservation;
	}
	public void setReservation(String reservation) {
		this.reservation = reservation;
	}
	
	public String getReturnBook() {
		return returnBook;
	}
	public void setReturnBook(String returnBook) {
		this.returnBook = returnBook;
	}
	
	public String getPreservation() {
		return preservation;
	}
	public void setPreservation(String preservation) {
		this.preservation = preservation;
	}
	
}
