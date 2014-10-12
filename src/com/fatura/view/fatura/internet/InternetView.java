package com.fatura.view.fatura.internet;

import java.util.ArrayList;
import java.util.List;

import com.fatura.R;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class InternetView extends Fragment{
	private InternetListAdapter adapter;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.internet_layout, container, false);
        ListView listView = (ListView) rootView.findViewById(R.id.internet_list);
        
        List<String> list = new ArrayList<String>();
        list.add("Facebook");
        adapter = new InternetListAdapter(this.getActivity(), list);
        listView.setAdapter(adapter);
        return rootView;
    }
}
