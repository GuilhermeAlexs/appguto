package com.fatura.view.settings;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;


public class SettingsView extends ListActivity{
	static final String[] SETTINGS = 
            new String[] { "Dados Pessoais", "Planos", "Notificações",};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getListView().setBackgroundColor(Color.BLACK);
		setListAdapter(new SettingsListAdapter(this, SETTINGS));

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

	}
}
