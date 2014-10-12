package com.fatura.model;

public class Call {
	private int id;
	private PhoneNumber from;
	private PhoneNumber to;
	private int duration;
	private long date;
	private double price;

	public Call(){}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public PhoneNumber getFrom() {
		return from;
	}

	public void setFrom(PhoneNumber from) {
		this.from = from;
	}

	public PhoneNumber getTo() {
		return to;
	}

	public void setTo(PhoneNumber to) {
		this.to = to;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
}
