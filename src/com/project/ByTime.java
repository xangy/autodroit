package com.project;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

@SuppressLint("SimpleDateFormat")
public class ByTime extends Activity {
	
	String myDate, phone_no, sms;
	String strDate;
	AudioManager am;
	int count=0;
	AlertDialog alertDialog;
	Intent intent;
	
	public class A implements OnClickListener {
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			switch(arg0.getId())
			{
				case R.id.btnc:
					if(strDate.equals(myDate))
					{
						am= (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
						//am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
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
					count=1;
					break;
	 
				case R.id.btncs:
					showPopUp2();
					count=2;
					if(strDate.equals(myDate))
					{
						Intent it=new Intent(Intent.ACTION_CALL);
						it.setData(Uri.parse("tel:" + phone_no));
						startActivity(it);
					}
					break;
				case R.id.btnsms:
					showPopUp3();
					count=3;
					if(strDate.equals(myDate))
					{
						SmsManager smsManager = SmsManager.getDefault();
						smsManager.sendTextMessage(phone_no, null, sms, null, null);
					}
					break;
			}
		}
	}

	Button b1,b2,b3;
	EditText e1,e2;
	TimePicker tp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_by_time);
		b1=(Button)findViewById(R.id.btnc);
		b2=(Button)findViewById(R.id.btncs);
		b3=(Button)findViewById(R.id.btnsms);
		e1=(EditText)findViewById(R.id.editText1);
		tp=(TimePicker)findViewById(R.id.timePicker1);
		b1.setOnClickListener(new A());
		b2.setOnClickListener(new A());
		b3.setOnClickListener(new A());
		final Handler handler=new Handler();
		Timer time =new Timer();
		TimerTask tm=new TimerTask() {
			public void run() {
				handler.post(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Calendar c = Calendar.getInstance();
						SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
						strDate = sdf.format(c.getTime());
						myDate=tp.getCurrentHour().toString()+":"+tp.getCurrentMinute().toString();
						if(count==1)
						{
							if(strDate.equals(myDate))
							{
								count=10;
								am= (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
								am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
								//else  am.setRingerMode(AudioManager.RINGER_MODE_NORMAL
							}
						}
						if(count==2)
						{
							if(strDate.equals(myDate))
							{   
								count=10;
								Intent it=new Intent(Intent.ACTION_CALL);
								it.setData(Uri.parse("tel:"+phone_no));
								startActivity(it);
							}
						}
						else if(count==3)
						{
							if(strDate.equals(myDate))
							{
								Intent itt=new Intent(Intent.ACTION_SEND);
								itt.setData(Uri.parse("sms:"+phone_no));
								startActivity(itt);
							}
						}
					}
				});
			}        
		};
		time.schedule(tm,0,50);
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
		helpBuilder.setTitle("Set Number");
		helpBuilder.setMessage("Enter the number");
		final EditText input = new EditText(this);
	 
		input.setSingleLine();
		input.setText("");
		helpBuilder.setView(input);
			helpBuilder.setPositiveButton("Set", new DialogInterface.OnClickListener() {
			@Override
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
		// helpBuilder.setMessage("Enter the number");
		final EditText number = new EditText(this);
		final EditText message=new EditText(this);
	
		helpBuilder.setView(number);
		helpBuilder.setView(message);
	
		message.setHint("Enter Message");

		helpBuilder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// Do nothing but close the dialog
				phone_no=number.getText().toString();
				sms=message.getText().toString();
				showPopUp2();
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