package com.dodo.flames;

import java.util.Vector;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity
{
	String name1,name2;
	int ans,chk=0,jk=0;
	int alpha1[]=new int[26],alpha2[]=new int[26],i,j=0,flag=0,ctr=0,count=0;
	ImageView fl;
	EditText n1,n2;
	Button c,w;
	SharedPreferences prf;
	DataHandler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		n1=(EditText)findViewById(R.id.f_name);
		n2=(EditText)findViewById(R.id.s_name);
		fl=(ImageView)findViewById(R.id.flame_it);
		c=(Button)findViewById(R.id.clear);
		w=(Button)findViewById(R.id.what);
		String font_path="fonts/charred.ttf";
		Typeface tf=Typeface.createFromAsset(getAssets(), font_path);
		n1.setTypeface(tf);
		n1.setTextColor(getResources().getColor(R.color.Red));
		n2.setTypeface(tf);
		n2.setTextColor(getResources().getColor(R.color.Red));
		for(i=0 ; i<26 ; i++)
		{
			alpha1[i]=0;
			alpha2[i]=0;
		}
		ActionBar bar=getSupportActionBar();
		bar.setTitle("Flames");
		bar.show();
		fl.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
				name1=n1.getText().toString();
				name2=n2.getText().toString();
				for(i=0 ; i<name1.length() ; i++)
				{
					if(name1.charAt(i)==' ')
					{
						chk=1;
						continue;
					}
					if(name1.charAt(i)>='a' && name1.charAt(i)<='z')
					{
						if(chk==1)
						{
							jk=1;
							break;
						}
						alpha1[name1.charAt(i)-97]++;
						continue;
					}
					else if(name1.charAt(i)>='A' && name1.charAt(i)<='Z')
					{
						if(chk==1)
						{
							jk=1;
							break;
						}
						alpha1[name1.charAt(i)-65]++;
						continue;
					}
					else
					{
						flag=1;
						break;
					}
					
				}
				chk=0;
				for(i=0 ; i<name2.length() ; i++)
				{
					if(jk==1)
						break;
					if(name2.charAt(i)==' ')
					{
						chk=1;
						continue;
					}
					if(name2.charAt(i)>='a' && name2.charAt(i)<='z')
					{
						if(chk==1)
						{
							jk=1;
							break;
						}
						alpha2[name2.charAt(i)-97]++;
						continue;
					}
					else if(name2.charAt(i)>='A' && name2.charAt(i)<='Z')
					{
						if(chk==1)
						{
							jk=1;
							break;
						}
						alpha2[name2.charAt(i)-65]++;
						continue;
					}
					else
					{
						flag=1;
						break;
					}
					
				}
				if(flag!=1)
				{
					for(i=0 ; i<26 ; i++)
					{
						if(alpha1[i]>alpha2[i])
							ctr+=alpha1[i]-alpha2[i];
						else if(alpha2[i]>alpha1[i])
							ctr+=alpha2[i]-alpha1[i];
					}
					Vector<Integer> flames=new Vector<Integer>(6);
					flames.addElement(new Integer(1));
					flames.addElement(new Integer(2));
					flames.addElement(new Integer(3));
					flames.addElement(new Integer(4));
					flames.addElement(new Integer(5));
					flames.addElement(new Integer(6));
					for(i=0 ; i<5 ; i++)
					{
						for(j=0 ; j<ctr-1 ; j++)
						{
							if(flames.elementAt(count)==flames.lastElement())
								count=0;
							else
								count++;
						}
						if(flames.elementAt(count)==flames.lastElement())
						{
							flames.remove(count);
							count=0;
						}
						else
							flames.remove(count);
					}
					ans=flames.firstElement();
				}
				if(flag==1)
				{
					Toast.makeText(getApplicationContext(), "Names have only Alphabetical characters",Toast.LENGTH_LONG).show();
					j=0;flag=0;ctr=0;count=0;chk=0;jk=0;
					for(i=0 ; i<26 ; i++)
					{
						alpha1[i]=0;
						alpha2[i]=0;
					}
				}
				else if(name1.length()==0)
				{
					Toast.makeText(getApplicationContext(), "Please Enter Your Name",Toast.LENGTH_LONG).show();
					j=0;flag=0;ctr=0;count=0;chk=0;jk=0;
					for(i=0 ; i<26 ; i++)
					{
						alpha1[i]=0;
						alpha2[i]=0;
					}
				}
				else if(name2.length()==0)
				{
					Toast.makeText(getApplicationContext(), "Please Enter Partner's Name",Toast.LENGTH_LONG).show();
					j=0;flag=0;ctr=0;count=0;chk=0;jk=0;
					for(i=0 ; i<26 ; i++)
					{
						alpha1[i]=0;
						alpha2[i]=0;
					}
				}
				else if(jk==1)
				{
					Toast.makeText(getApplicationContext(), "This Game allows Only First Names",Toast.LENGTH_LONG).show();
					j=0;flag=0;ctr=0;count=0;chk=0;jk=0;
					for(i=0 ; i<26 ; i++)
					{
						alpha1[i]=0;
						alpha2[i]=0;
					}
				}
				else
				{
					handler=new DataHandler(getBaseContext());
					handler.open();
					handler.Insert(name1, name2, ans);
					handler.close();
					Bundle b=new Bundle();
					b.putInt("answer", ans);
					b.putString("nam1", name1);
					b.putString("nam2", name2);
					Intent conn=new Intent(MainActivity.this, FragmentLayout.class);
					conn.putExtras(b);
					startActivity(conn);
					j=0;flag=0;ctr=0;count=0;chk=0;jk=0;
					n1.setText("");
					n2.setText("");
					for(i=0 ; i<26 ; i++)
					{
						alpha1[i]=0;
						alpha2[i]=0;
					}
				}
			}
		});
		c.setOnClickListener(new OnClickListener() 
		{	
			@Override
			public void onClick(View v) 
			{
				name1=name2="";
				n1.setText("");
				n2.setText("");
				j=0;flag=0;ctr=0;count=0;chk=0;jk=0;
				for(i=0 ; i<26 ; i++)
				{
					alpha1[i]=0;
					alpha2[i]=0;
				}
			}
		});
		w.setOnClickListener(new OnClickListener() 
		{
			
			@Override
			public void onClick(View v) 
			{
				Intent i=new Intent(MainActivity.this,What.class);
				startActivity(i);
			}
		});
	}
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) 
	{
		String na1,na2;
		n1=(EditText)findViewById(R.id.f_name);
		n2=(EditText)findViewById(R.id.s_name);
		na1=n1.getText().toString();
		na2=n1.getText().toString();
		savedInstanceState.putString("name1", na1);
		savedInstanceState.putString("name2", na2);
		super.onSaveInstanceState(savedInstanceState);
	}
	@Override
	protected void onStop() 
	{
		super.onStop();
		String na1,na2;
		n1=(EditText)findViewById(R.id.f_name);
		n2=(EditText)findViewById(R.id.s_name);
		na1=n1.getText().toString();
		na2=n2.getText().toString();
		prf=getSharedPreferences("data", MODE_PRIVATE);
		SharedPreferences.Editor edit=prf.edit();
		edit.putString("name1", na1);
		edit.putString("name2", na2);
		edit.commit();
	}
	@Override
	protected void onStart() 
	{
		super.onStart();
		prf=getSharedPreferences("data",MODE_PRIVATE);
		n1=(EditText)findViewById(R.id.f_name);
		n2=(EditText)findViewById(R.id.s_name);
		prf=getSharedPreferences("data", MODE_PRIVATE);
		n1.setText(prf.getString("name1", ""));
		n2.setText(prf.getString("name2", ""));
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch(item.getItemId())
		{
			case R.id.History:Intent i=new Intent(MainActivity.this,HistoryPage.class);
								startActivity(i);
								break;
			case R.id.Exit:finish();
							break;
		}
		return false;
	}

}
