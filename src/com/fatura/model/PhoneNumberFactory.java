package com.fatura.model;

import android.util.Log;

public class PhoneNumberFactory {
	public static PhoneNumber createPhoneNumber(String number){
		PhoneNumber ph = new PhoneNumber();
		Log.d("Number",number);
		ph.setFullNumber(number);
		if(number.matches("(9090)((2|3|8|9)([0-9]{7,8}))")){
			Log.d("Regex",1 + "");
			ph.setDDD(Session.getInstance().getUser().getPhoneNumber().getDDD());
			ph.setCoreNumber(number.substring(4));
			ph.setLCollectNumber(true);
		}else if(number.matches("(9090)([0-9][0-9])([0-9][0-9])((2|3|8|9)([0-9]{7,8}))")){
			Log.d("Regex",2 + "");
			ph.setDDD(Integer.parseInt(number.substring(6,8)));
			ph.setCoreNumber(number.substring(8));
			ph.setChoosenCarrier(CarrierFactory.createCarrier(Integer.parseInt(number.substring(4, 6))));
			ph.setLCollectNumber(true);
		}else if(number.matches("(90)([0-9][0-9])([0-9][0-9])((2|3|8|9)([0-9]{7,8}))")){
			Log.d("Regex",3 + "");
			ph.setDDD(Integer.parseInt(number.substring(4,6)));
			ph.setCoreNumber(number.substring(6));
			ph.setLDCollectNumber(true);
			ph.setChoosenCarrier(CarrierFactory.createCarrier(Integer.parseInt(number.substring(2, 4))));
		}else if(number.matches("(90)([0-9][0-9])((2|3|8|9)([0-9]{7,8}))")){
			Log.d("Regex",3.1 + "");
			return null;
		}else if(number.matches("((2|3|8|9)([0-9]{7,8}))")){
			Log.d("Regex",4 + "");
			ph.setDDD(Session.getInstance().getUser().getPhoneNumber().getDDD());
			ph.setCoreNumber(number);
		}else if(number.matches("(\\+[0-9][0-9])(90)([0-9][0-9])([0-9][0-9])((2|3|8|9)([0-9]{7,8}))")){
			Log.d("Regex",5 + "");
			ph.setDDI(Integer.parseInt(number.substring(1,3)));
			ph.setDDD(Integer.parseInt(number.substring(7,9)));
			ph.setCoreNumber(number.substring(9));
			ph.setLDCollectNumber(true);
			ph.setChoosenCarrier(CarrierFactory.createCarrier(Integer.parseInt(number.substring(5, 7))));
		}else if(number.matches("(\\+[0-9][0-9])([0-9][0-9])((2|3|8|9)([0-9]{7,8}))")){
			Log.d("Regex",6 + "");
			ph.setDDI(Integer.parseInt(number.substring(1,3)));
			ph.setDDD(Integer.parseInt(number.substring(3,5)));
			ph.setCoreNumber(number.substring(5));
			ph.setChoosenCarrier(null);
		}else if(number.matches("(\\+[0-9][0-9])(9090)([0-9][0-9])((2|3|8|9)([0-9]{7,8}))")){
			Log.d("Regex",7 + "");
			ph.setDDI(Integer.parseInt(number.substring(1,3)));
			ph.setDDD(Integer.parseInt(number.substring(9,11)));
			ph.setCoreNumber(number.substring(11));
			ph.setChoosenCarrier(CarrierFactory.createCarrier(Integer.parseInt(number.substring(7, 9))));
			ph.setLCollectNumber(true);
		}else if(number.matches("(\\+[0-9][0-9])([0-9][0-9])([0-9][0-9])((2|3|8|9)([0-9]{7,8}))")){
			Log.d("Regex",8 + "");
			ph.setDDI(Integer.parseInt(number.substring(1,3)));
			ph.setDDD(Integer.parseInt(number.substring(5,7)));
			ph.setCoreNumber(number.substring(7));
			ph.setChoosenCarrier(CarrierFactory.createCarrier(Integer.parseInt(number.substring(3, 5))));
		}else if(number.matches("([0-9][0-9])([0-9][0-9])((2|3|8|9)([0-9]{7,8}))")){
			Log.d("Regex",9 + "");
			ph.setDDD(Integer.parseInt(number.substring(2,4)));
			ph.setCoreNumber(number.substring(4));
			ph.setChoosenCarrier(CarrierFactory.createCarrier(Integer.parseInt(number.substring(0, 2))));
		}else if(number.matches("(0[0-9][0-9])([0-9][0-9])((2|3|8|9)([0-9]{7,8}))")){
			Log.d("Regex",10 + "");
			ph.setDDD(Integer.parseInt(number.substring(3,5)));
			ph.setCoreNumber(number.substring(5));
			ph.setChoosenCarrier(CarrierFactory.createCarrier(Integer.parseInt(number.substring(1, 3))));
		}else if(number.matches("(0[0-9][0-9])((2|3|8|9)([0-9]{7,8}))")){
			Log.d("Regex",11 + "");
			ph.setDDD(Integer.parseInt(number.substring(1,3)));
			ph.setCoreNumber(number.substring(3));
			ph.setChoosenCarrier(null);
		}else if(number.matches("([0-9][0-9])((2|3|8|9)([0-9]{7,8}))")){
			Log.d("Regex",12 + "");
			ph.setDDD(Integer.parseInt(number.substring(0,2)));
			ph.setCoreNumber(number.substring(2));
			ph.setChoosenCarrier(null);
		}else if(number.matches("([0-9][0-9])(90)([0-9][0-9])([0-9][0-9])((2|3|8|9)([0-9]{7,8}))")){
			Log.d("Regex",13 + "");
			ph.setDDI(Integer.parseInt(number.substring(0,2)));
			ph.setDDD(Integer.parseInt(number.substring(6,8)));
			ph.setCoreNumber(number.substring(8));
			ph.setChoosenCarrier(CarrierFactory.createCarrier(Integer.parseInt(number.substring(4, 6))));
			ph.setLDCollectNumber(true);
		}else if(number.contains("0800") || number.contains("*")){
			ph.setFreeBusinessNumber(true);
			return ph;
		}else{
			Log.d("Regex",14 + "");//81389558
			return null;
		}

		ph.setCarrier(CarrierFactory.createCarrier(ph.getDDD(), ph.getCoreNumber()));

		return ph;
	}
}
