package com.fatura.model;

import java.util.Date;


public class DataUsage {
	private int id;
	private Date date;
	private int size;
	private String url;
	private double price;

	public DataUsage(){};

	public DataUsage(Date date, int size, String url, double price) {
		this.date = date;
		this.size = size;
		this.url = url;
		this.price = price;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public long getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
}
