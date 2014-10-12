package com.fatura.view;

import java.util.ArrayList;
import java.util.List;

import com.fatura.R;
import com.fatura.database.DatabaseHelper;
import com.fatura.view.fatura.FaturaView;
import com.fatura.view.settings.SettingsView;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity implements OnItemClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		DatabaseHelper dataHelper = new DatabaseHelper(this);
		dataHelper.updateSession();
		
		List<String> namesList = new ArrayList<String>();
		namesList.add("Minha Fatura");
		namesList.add("Mapa de Potência");
		namesList.add("Configuração");
		
		MainViewAdapter adapter = new MainViewAdapter(this, namesList);
		ListView listView = (ListView) findViewById(R.id.main_list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		startActivity(intent);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent;
		switch(position){
			case 0:
			    intent = new Intent(view.getContext(), FaturaView.class);
				this.startActivity(intent);
				break;
			case 2:
				intent = new Intent(view.getContext(), SettingsView.class);
				this.startActivity(intent);
				break;				
		}
	}
}
