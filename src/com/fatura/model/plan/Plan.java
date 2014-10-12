package com.fatura.model.plan;

import java.util.List;

import com.fatura.model.Call;
import com.fatura.model.DataUsage;

public interface Plan {
	public double price(DataUsage data);
	public List<Call> partial(List<Call> callList);
	double price(List<Call> callList);
}
