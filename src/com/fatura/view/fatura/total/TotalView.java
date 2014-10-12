package com.fatura.view.fatura.total;

import com.fatura.R;
import com.fatura.model.CallService;
import com.fatura.model.Session;
import com.fatura.model.plan.Plan;
import com.fatura.model.plan.TimLibertyPlan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class TotalView extends Fragment{
	private CallService callService;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		
        View rootView = inflater.inflate(R.layout.total_layout, container, false);
        TextView nameTextView = (TextView) rootView.findViewById(R.id.nameTextView);
        TextView priceTextView = (TextView) rootView.findViewById(R.id.priceTextView);
        
        TextView nameTextView2 = (TextView) rootView.findViewById(R.id.nameTextView2);
        TextView priceTextView2 = (TextView) rootView.findViewById(R.id.priceTextView2);
        
        TextView totalPriceTextView = (TextView) rootView.findViewById(R.id.totalPriceTextView);
        
        Plan plan = new TimLibertyPlan();
        callService = CallService.getInstance(this.getActivity());
        Double callPrice = plan.price(callService.getCallLog(Session.getInstance().getPaymentDay()));
        Double internetPrice = 0.0;
        
        priceTextView.setText("R$ " + String.format("%.2f", callPrice));
        totalPriceTextView.setText("R$ " + String.format("%.2f", (callPrice+internetPrice)));
        
        return rootView;
    }
}
