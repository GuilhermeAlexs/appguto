package com.fatura.view.fatura.internet;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import com.fatura.R;
import com.fatura.model.Call;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class InternetListAdapter extends ArrayAdapter<String>{
	private final Context context;
	private List<String> application;
	
	public InternetListAdapter(Context context, List<String> application) {
		super(context, R.layout.fatura_call_list, application);
		this.context = context;
		this.application = application;
		Collections.reverse(this.application);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.internet_item, parent, false);
			holder = new ViewHolder();

			holder.appName = (TextView) convertView.findViewById(R.id.name);
			holder.appSize = (TextView) convertView.findViewById(R.id.kbTextView);

			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}

//		Call c = calls.get(position);
//		
//		if(c != null){
//			holder.lblTo.setText(c.getTo().getNumber());
//			holder.lblDate.setText(new SimpleDateFormat("dd-MM-yyyy").format(c.getDate()));
//			holder.lblDuration.setText(c.getDuration() + " seg");
//			holder.lblPrice.setText("R$" + c.getPrice());
//		}
 
		return convertView;
	}
	
	static class ViewHolder{
		TextView appName;
		TextView appSize;
	}
}
