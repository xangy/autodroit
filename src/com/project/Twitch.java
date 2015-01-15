package com.project;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Twitch extends Activity {
	String s1,s2,s3,s4;
	TextView tw;
	AlertDialog alertDialog;
	Intent intent;
	
	public class A implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Intent it=new Intent(Twitch.this,Twitchnext.class); 
			it.putExtra("Value1", s1);
			it.putExtra("Value2", s2);
			it.putExtra("Value3", s3);
			it.putExtra("Value4", s4);
			startActivity(it);
			
			
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_twitch);
		
		Button b=(Button)findViewById(R.id.button1);
		b.setOnClickListener(new A());	
		Spinner sp1=(Spinner)findViewById(R.id.Spinner01);
		sp1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				s1=arg0.getItemAtPosition(arg2).toString();
					}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		Spinner sp2=(Spinner)findViewById(R.id.Spinner02);
		
		sp2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				s2=arg0.getItemAtPosition(arg2).toString();
					}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method 			
			}
		});

		Spinner sp3=(Spinner)findViewById(R.id.Spinner03);
		
		sp3.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				s3=arg0.getItemAtPosition(arg2).toString();
					}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	
		Spinner sp4=(Spinner)findViewById(R.id.spinner04);
		
		sp4.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				s4=arg0.getItemAtPosition(arg2).toString();
					}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch(item.getItemId()) {
		    case R.id.home_page:
		    	intent = new Intent(this, HomePageActivity.class);
		        startActivity(intent);
		        return true;
		    case R.id.greetings:
		    	alertDialog = new AlertDialog.Builder(this).setMessage(R.string.greetings_text).create();
		    	alertDialog.setTitle("Greetings");
		    	alertDialog.show();
		        return true;
		    case R.id.feedback:
		    	intent = new Intent(this, Feedback.class);
		        startActivity(intent);
		        return true;
		    case R.id.credits:
		    	alertDialog = new AlertDialog.Builder(this).setMessage(R.string.credits_text).create();
		    	alertDialog.setTitle("Credits");
		    	alertDialog.show();
		        return true;
		    default:
		    	return super.onOptionsItemSelected(item);
	    }
	}

}
