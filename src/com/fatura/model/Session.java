package com.fatura.model;

public class Session {
	private static Session instance;
	
	private User user;
	private int paymentDay;
	
	private Session() {
		this.user = new User();
	}
	
	public static Session getInstance() {
		if (instance == null)
			instance = new Session();
		return instance;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getPaymentDay() {
		return paymentDay;
	}

	public void setPaymentDay(int paymentDay) {
		this.paymentDay = paymentDay;
	}
}
