package com.dodo.flames;

import java.util.List;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentLayout extends ActionBarActivity
{
	Button cl;
	int ans;
	TextView res;
	ImageView Img;
	String name1,name2,msg;
	SharedPreferences prf;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment);
		ActionBar bar=getSupportActionBar();
		bar.setTitle("Result");
		bar.show();
		Bundle b1=getIntent().getExtras();
		ans=b1.getInt("answer");
		res=(TextView)findViewById(R.id.result_t);
		Img=(ImageView)findViewById(R.id.result);
		name1=b1.getString("nam1");
		name2=b1.getString("nam2");
		String font_path="fonts/CracklingFire.ttf";
		Typeface tf=Typeface.createFromAsset(getAssets(), font_path);
		res.setTypeface(tf);
		res.setTextColor(getResources().getColor(R.color.Red));
		switch(ans)
		{
			case 1:msg=name1+" and "+name2+" are- \n FRIENDS";
					res.setText(msg);
						Img.setImageResource(R.drawable.friends);
					break;
			case 2:msg=name1+" and\n"+name2+" are- \n LOVERS";
					res.setText(msg);
						Img.setImageResource(R.drawable.lovers);
					break;
			case 3:msg=name2+" is\n ATTRACTED \n to "+name1;
					res.setText(msg);
						Img.setImageResource(R.drawable.attracted);
					break;
			case 4:msg=name1+" and "+name2+" are- \n MARRIED";
					res.setText(msg);
						Img.setImageResource(R.drawable.married);
					break;
			case 5:msg=name1+" and "+name2+" are- \n ENEMIES";
					res.setText(msg);
						Img.setImageResource(R.drawable.enemies);
					break;
			case 6:msg=name1+" and "+name2+" are- \n SIBLINGS";
					res.setText(msg);
						Img.setImageResource(R.drawable.siblings);
					break;
		}
		cl=(Button)findViewById(R.id.close);
		cl.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
					finish();
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.action, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch(item.getItemId())
		{
			case R.id.Share:msg="According To FLAMES, "+msg;
							Intent i=new Intent();
							i.setAction(Intent.ACTION_SEND);
							i.putExtra(Intent.EXTRA_TEXT,msg);
							i.setType("text/plain");
							PackageManager packageManager = getPackageManager();
							List<ResolveInfo> activities = packageManager.queryIntentActivities(i, 0);
							boolean isIntentSafe = activities.size() > 0;
							if(isIntentSafe == true)
								startActivity(Intent.createChooser(i, "Share with..."));
							else
								Toast.makeText(getApplicationContext(), "No App with which you can Share",Toast.LENGTH_LONG).show();
							break;

		}
		return super.onOptionsItemSelected(item);
	}
}
