package com.fatura.model;

public class Carrier {
	public static String TIM = "TIM";
	public static String VIVO = "VIVO";
	public static String CLARO = "CLARO";
	public static String OI = "OI";
	public static String FIXO = "FIXO";

	private int id;
	private String name;

	public Carrier(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
