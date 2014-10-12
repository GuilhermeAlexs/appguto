package com.fatura.view;

import java.util.List;

import com.fatura.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MainViewAdapter extends BaseAdapter{
	private Context context;
	private LayoutInflater inflater;
	private List<String> list;
	
	public MainViewAdapter(Context context, List<String> list) {
		super();
		this.context = context;
		this.list = list;
		this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	public static class ViewHolder
	{
		TextView nameTextView;
		ImageView imageIcon;
		TextView item_description;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.main_list_item, null);
			
			holder.nameTextView = (TextView) convertView.findViewById(R.id.item_name);
			holder.imageIcon = (ImageView) convertView.findViewById(R.id.image_icon);
			holder.item_description = (TextView) convertView.findViewById(R.id.item_description);
			
			if(position == 0) {
				holder.nameTextView.setText(list.get(position));
				holder.imageIcon.setImageResource(R.drawable.banknote);
			} else if(position == 1) {
				holder.nameTextView.setText(list.get(position));
				holder.imageIcon.setImageResource(R.drawable.podcast);
			} else if(position == 2) {
				holder.nameTextView.setText(list.get(position));
				holder.imageIcon.setImageResource(R.drawable.cogs);
			}
			
			
			convertView.setTag(holder);
		} else {
			holder=(ViewHolder)convertView.getTag();
		}
		return convertView;
	}
}
