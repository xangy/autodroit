package com.project;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class TimeManager extends Activity {
	Intent it, intent;
	AlertDialog alertDialog;
	
	public class A implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId())
			{
				case R.id.btnDu:it=new Intent(TimeManager.this,ByDuration.class);
				startActivity(it);
				break;
				case R.id.btnTime:it=new Intent(TimeManager.this,ByTime.class);
				startActivity(it);
				break;
	
			}
		}

	}

	TimePicker tp;
	TextView tv;
	Button b1,b2;
	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_manager);
		tv=(TextView)findViewById(R.id.tv);
	//	tp=(TimePicker)findViewById(R.id.tp);
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(" HH:mm ");
		String strDate = sdf.format(c.getTime());
         tv.setText(strDate);
         b1=(Button)findViewById(R.id.btnDu);
         b2=(Button)findViewById(R.id.btnTime);
         b1.setOnClickListener(new A());
         b2.setOnClickListener(new A());
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
