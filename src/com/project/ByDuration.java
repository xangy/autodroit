package com.project;


import java.util.Timer;
import java.util.TimerTask;

import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ByDuration extends Activity {
	EditText e1,e2,e3;
	Button b1,b2;
	TextView tv;
	int hour,min,sec,total_second;
	AudioManager am;
	int count=0;
	Handler handler;
	Timer time;
	AlertDialog alertDialog;
	Intent intent;

	public class A implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			hour = Integer.valueOf(e1.getText().toString());
			min = Integer.valueOf(e2.getText().toString());
			sec = Integer.valueOf(e3.getText().toString());
			total_second = (hour * 3600) + (min * 60) + sec;
			//TextView tv=(TextView)findViewById(R.id.tv2);
			//tv.setText(String.valueOf(total_second));
			switch(v.getId())
			{
				case R.id.btnm:
					handler=new Handler();
					time =new Timer();
					TimerTask tm=new TimerTask() {
						public void run()
						{
							handler.post(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									count++;
									if(count==total_second)
									{
										am= (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
										switch (am.getRingerMode()) 
										{
									    	case AudioManager.RINGER_MODE_SILENT:
									    		am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
									    		break;
									    	case AudioManager.RINGER_MODE_NORMAL:
									    		am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
									    		break;
									    	default:
									    		am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
										}
									}
								}
							});
						}        
					};
					time.schedule(tm,0,1000);
					break;
				case R.id.btnp:
					handler=new Handler();
					time =new Timer();
					TimerTask tm1=new TimerTask() {
						public void run()
						{
							handler.post(new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									count++;
									if(count==total_second)
									{
										Intent it=new Intent(Intent.ACTION_CALL);
										it.setData(Uri.parse("tel:"+"8009519611"));
										startActivity(it);
									}
								}
							});
						}        
					};
					time.schedule(tm1,0,1000);
					break;
				}
			}
		}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_by_duration);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		e3=(EditText)findViewById(R.id.editText3);
		b1=(Button)findViewById(R.id.btnm);
		b2=(Button)findViewById(R.id.btnp);
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
