package com.fatura.view.fatura.calls;

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
import android.widget.ListView;

public class CallsView extends Fragment {
	private CallListAdapter adapter;
	private CallService callService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fatura_call_list, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.faturaCallList_listViewCalls);

        callService = CallService.getInstance(this.getActivity());
        Plan plan = new TimLibertyPlan();
        adapter = new CallListAdapter(this.getActivity(), plan.partial(callService.getCallLog(Session.getInstance().getPaymentDay())));
        listView.setAdapter(adapter);
        return rootView;
    }
}