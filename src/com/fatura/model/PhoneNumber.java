package com.fatura.model;

public class PhoneNumber {
	private String fullNumber;
	private String coreNumber;
	private Carrier carrier;
	private int ddd;
	private int ddi;
	private Carrier choosenCarrier;
	private boolean isLDCollectNumber;
	private boolean isLCollectNumber;
	private boolean isFreeBusinessNumber;

	private User user;

	private boolean formatNotFound = false;
	private boolean carrierNotFound = false;

	public PhoneNumber(String fullNumber){
		this.fullNumber = fullNumber;
		this.coreNumber = null;
		this.ddd = 0;
		this.ddi = 0;
		this.isLDCollectNumber = false;
		this.isLCollectNumber = false;
		this.isFreeBusinessNumber = false;
	}

	public PhoneNumber() {
		this.coreNumber = null;
		this.ddd = 0;
		this.ddi = 0;
		this.isLDCollectNumber = false;
		this.isLCollectNumber = false;
		this.isFreeBusinessNumber = false;
	}
	
	public boolean isFormatNotFound() {
		return formatNotFound;
	}

	public void setFormatNotFound(boolean formatNotFound) {
		this.formatNotFound = formatNotFound;
	}

	public boolean isCarrierNotFound() {
		return carrierNotFound;
	}

	public void setCarrierNotFound(boolean carrierNotFound) {
		this.carrierNotFound = carrierNotFound;
	}

	public String getFullNumber() {
		return fullNumber;
	}

	public void setFullNumber(String fullNumber) {
		this.fullNumber = fullNumber;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCoreNumber() {
		return coreNumber;
	}

	public void setCoreNumber(String coreNumber) {
		this.coreNumber = coreNumber;
	}

	public Carrier getCarrier() {
		return carrier;
	}

	public void setCarrier(Carrier carrier) {
		this.carrier = carrier;
	}

	public int getDDD() {
		return ddd;
	}

	public void setDDD(int ddd) {
		this.ddd = ddd;
	}

	public int getDDI() {
		return ddi;
	}

	public void setDDI(int ddi) {
		this.ddi = ddi;
	}
	
	public Carrier getChoosenCarrier() {
		return choosenCarrier;
	}

	public void setChoosenCarrier(Carrier choosenCarrier) {
		this.choosenCarrier = choosenCarrier;
	}

	public boolean isLDCollectNumber() {
		return isLDCollectNumber;
	}

	public boolean isLCollectNumber() {
		return isLCollectNumber;
	}

	public boolean isCollectNumber(){
		return isLDCollectNumber || isLCollectNumber;
	}

	public boolean isFreeBusinessNumber() {
		return isFreeBusinessNumber;
	}

	public void setLDCollectNumber(boolean isLDCollectNumber) {
		this.isLDCollectNumber = isLDCollectNumber;
	}

	public void setLCollectNumber(boolean isLCollectNumber) {
		this.isLCollectNumber = isLCollectNumber;
	}

	public void setFreeBusinessNumber(boolean isFreeBusinessNumber) {
		this.isFreeBusinessNumber = isFreeBusinessNumber;
	}
}
