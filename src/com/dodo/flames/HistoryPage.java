package com.dodo.flames;

import java.util.ArrayList;
import java.util.Arrays;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HistoryPage extends ActionBarActivity 
{
	DataHandler handler;
	int i,num;
	String nm1,nm2,result;
	private ListView mainListView ;  
	private ArrayAdapter<String> listAdapter ;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.page);
		ActionBar bar=getSupportActionBar();
		bar.setTitle("History");
		bar.show();
		mainListView = (ListView) findViewById( R.id.mainListView );
		handler=new DataHandler(getBaseContext());
		handler.open();
		Cursor c=handler.returnData();
		c.moveToFirst();
		num=c.getCount();
		String[] Results = new String[num];
		for(i=0 ; i<num ; i++)
		{
			nm1=c.getString(0).toString();
			nm2=c.getString(1).toString();
			result=c.getString(2).toString();
			Results[i]=nm1+" & "+nm2+" - "+result;
			c.moveToNext();
		}
		handler.close();
		ArrayList<String> hstry = new ArrayList<String>();
		hstry.addAll(Arrays.asList(Results));
		listAdapter = new ArrayAdapter<String>(this, R.layout.textv,hstry);
		mainListView.setAdapter( listAdapter );
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.clear, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch(item.getItemId())
		{
			case R.id.cc:handler=new DataHandler(getBaseContext());
						 handler.open();
						 handler.removeall();
						 listAdapter.clear();
						 break;
			case R.id.Back_d:finish();
		}
		return false;
	}

}
