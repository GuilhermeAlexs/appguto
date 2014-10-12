package com.fatura.view.settings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.fatura.R;

public class SettingsListAdapter extends ArrayAdapter<String>{
	private final Context context;
	private final String[] settings;
 
	public SettingsListAdapter(Context context, String[] sett) {
		super(context, R.layout.settings_list, sett);
		this.context = context;
		this.settings = sett;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.settings_list_item, parent, false);
			holder = new ViewHolder();

			holder.icon = (ImageView) convertView.findViewById(R.id.settings_imageIcon);
			holder.lblName = (TextView) convertView.findViewById(R.id.settings_lblName);

			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}

		String name = settings[position];
		
		if(name != null){
			if(name.equals("Dados Pessoais"))
				holder.icon.setImageResource(R.drawable.profile32);
			else if(name.equals("Planos"))
				holder.icon.setImageResource(R.drawable.plans32);
			else if(name.equals("Notificações"))
				holder.icon.setImageResource(R.drawable.notifications32);
			
			holder.lblName.setText(name);
		}
 
		return convertView;
	}

	static class ViewHolder{
		ImageView icon;
		TextView lblName;
	}

}
