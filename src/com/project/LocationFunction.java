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
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;

public class LocationFunction extends Activity {
	
	String value,phone_no,sms,s1,s2,s3;
	int mode;
	double lon1,lon2,dlon;
	double lat1,lat2,dlat,a,c,d,r,u;
	Handler handler;
	TextView tx;
	Spinner sp;
	GPSTracker mGPS = new GPSTracker(this);
	AudioManager am;
	AlertDialog alertDialog;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location_function);
		
		lon1=getIntent().getExtras().getDouble("long");
		lat1=getIntent().getExtras().getDouble("lati");
		u=getIntent().getExtras().getDouble("dist");
			   
		final Handler handler=new Handler();
		Timer timer =new Timer();
		TimerTask getgps=new TimerTask() {
			public void run() {
				handler.post(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						mGPS.getLocation();
						lat2=(double)mGPS.getLatitude();
					    lon2=(double)mGPS.getLongitude();		     
					    
					    //Haversine Formula
					    dlat = Math.toRadians(lat2 - lat1) ;
					    a = Math.pow(Math.sin(dlat/2),2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon/2),2);
						c = 2 * Math.atan2( Math.sqrt(a), Math.sqrt(1-a) ) ;
						r=6371000;
						d = r * c; 
												
						sp=(Spinner)findViewById(R.id.spinner1);
						sp.setOnItemSelectedListener(new OnItemSelectedListener() {

							@Override
							public void onItemSelected(AdapterView<?> arg0, View arg1,int pos, long arg3) {
								// TODO Auto-generated method stub
								value=arg0.getItemAtPosition(pos).toString();
								if(value.equals("CallSomebody")) 
								{
									showPopUp2();  
							    }
								else if(value.equals("SendMessage")) 
								{
									showPopUp3();
								}
								else if(value.equals("ChangeProfile")) 
								{
									if(d>u) 
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
								if(d>u) 
								{
									if(value.equals("CallSomebody")) 
									{
										Intent it=new Intent(Intent.ACTION_CALL);
										it.setData(Uri.parse("tel:" + phone_no));
							    		startActivity(it);
									}
								}
							}

							@Override
							public void onNothingSelected(AdapterView<?> arg0) {
								// TODO Auto-generated method stub	
							}
						});
					}
				});
			}
		};
		timer.schedule(getgps,0,100);
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
	
	private void showPopUp2() {
		AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
		helpBuilder.setTitle("Call");
		helpBuilder.setMessage("Enter the number");
		final EditText input = new EditText(this);
		input.setSingleLine();
		input.setText("");
		helpBuilder.setView(input);
		helpBuilder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// Do nothing but close the dialog
		    	phone_no=input.getText().toString();
		    }
		});
		helpBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// Do nothing
			}
		});
		// Remember, create doesn't show the dialog
		AlertDialog helpDialog = helpBuilder.create();
		helpDialog.show();
	}
	
	private void showPopUp3() {
		AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
		helpBuilder.setTitle("SMS");
		helpBuilder.setMessage("Enter the number");
		final EditText number = new EditText(this);
		final EditText message = new EditText(this);
		helpBuilder.setView(number);
		helpBuilder.setView(message);
		number.setText("Enter the number");
		message.setText("Enter message");

		helpBuilder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// Do nothing but close the dialog
				phone_no = number.getText().toString();
				sms = message.getText().toString();
			}
		});
	
		helpBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// Do nothing
			}
		});
		// Remember, create doesn't show the dialog
		AlertDialog helpDialog = helpBuilder.create();
		helpDialog.show();
	}
}
