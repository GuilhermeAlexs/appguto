package com.fatura.view.fatura.calls;

import java.text.SimpleDateFormat;
import java.util.List;

import com.fatura.R;
import com.fatura.model.Call;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

@SuppressLint("SimpleDateFormat")
public class CallListAdapter extends ArrayAdapter<Call> {
	private final Context context;
	private final List<Call> calls;

	public CallListAdapter(Context context, List<Call> calls) {
		super(context, R.layout.fatura_call_list, calls);
		this.context = context;
		this.calls = calls;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.fatura_call_list_item, parent, false);
			holder = new ViewHolder();

			holder.lblDate = (TextView) convertView.findViewById(R.id.fatura_lblDate);
			holder.lblTo = (TextView) convertView.findViewById(R.id.fatura_lblTo);
			holder.lblDuration = (TextView) convertView.findViewById(R.id.fatura_lblDuration);
			holder.lblPrice = (TextView) convertView.findViewById(R.id.fatura_lblPrice);

			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}

		Call c = calls.get(position);

		if(c != null ){
			if(c.getTo().isFormatNotFound()){
				holder.lblTo.setTextColor(Color.RED);
				holder.lblDate.setTextColor(Color.RED);
				holder.lblDuration.setTextColor(Color.RED);
				holder.lblPrice.setTextColor(Color.RED);
			}else{
				holder.lblTo.setTextColor(Color.BLACK);
				holder.lblDate.setTextColor(Color.BLACK);
				holder.lblDuration.setTextColor(Color.BLACK);
				holder.lblPrice.setTextColor(Color.BLACK);
			}

			holder.lblTo.setText(c.getTo().getFullNumber());
			holder.lblDate.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm").format(c.getDate()));
			holder.lblDuration.setText(((int)c.getDuration()) + " s");
			holder.lblPrice.setText("R$" + String.format("%.2f",c.getPrice()));
		}
		return convertView;
	}

	static class ViewHolder{
		TextView lblDate;
		TextView lblTo;
		TextView lblDuration;
		TextView lblPrice;
	}
}