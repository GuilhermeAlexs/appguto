package com.fatura.model.plan;

import java.util.List;


import com.fatura.model.Call;
import com.fatura.model.Carrier;
import com.fatura.model.DataUsage;
import com.fatura.model.PhoneNumber;

public class TimLibertyPlan implements Plan{
	public TimLibertyPlan(){
	}

	@Override
	public double price(List<Call> callList) {
		double sumMinute = 0;
		double sumPrice = 0;
		PhoneNumber phTo;
		PhoneNumber phFrom;
		for(Call call : callList){
			double duration = Math.ceil(call.getDuration()/60); //em minutos
			sumMinute = duration + sumMinute;

			if(sumMinute <= 120)
				continue;

			phTo = call.getTo();
			phFrom = call.getFrom();

			if(phTo.isFormatNotFound()) continue;

			if((phTo.getCarrier() == null || phTo.getCarrier().equals(Carrier.TIM)) &&
			  (phTo.getDDD() == phFrom.getDDD() ||
			   phTo.getDDD() == 21 ||
			   phTo.getDDD() == 0)){
				continue;
			}else{
				if(phTo.getDDD() == phFrom.getDDD())
				   sumPrice = sumPrice + 0.99*duration;
				else if(phTo.getDDD() == 21 || phFrom.getDDD() == 0)
				   sumPrice = sumPrice + 1.99*duration;
			}
		}
		return sumPrice;
	}

	@Override
	public double price(DataUsage data) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Call> partial(List<Call> callList) {
		double sumMinute = 0;
		PhoneNumber phTo;
		PhoneNumber phFrom;

		for(Call call : callList){
			double duration = Math.ceil(call.getDuration()/60); //em minutos
			sumMinute = duration + sumMinute;

			if(sumMinute <= 120){
				call.setPrice(0);
				continue;
			}

			phTo = call.getTo();
			phFrom = call.getFrom();

			if((phTo.getCarrier() == null || phTo.getCarrier().equals(Carrier.TIM)) &&
			   (phTo.getDDD() == phFrom.getDDD() ||
			    phTo.getDDD() == 21 ||
			    phTo.getDDD() == 0)){
				call.setPrice(0);
				continue;
			}else{
				if(phTo.getDDD() == phFrom.getDDD())
				   call.setPrice(0.99*duration);
				else if(phTo.getDDD() == 21 || phFrom.getDDD() == 0)
				   call.setPrice(1.99*duration);
			}
		}
		return callList;	
	}
}
