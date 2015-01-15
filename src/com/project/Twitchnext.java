package com.project;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Twitchnext extends Activity implements SensorEventListener {
     
	String ss1,ss2,ss3,ss4;
	Sensor accelerometer;
    SensorManager sm;
    TextView acceleration,k;
    AlertDialog alertDialog;
    Intent intent;
	
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_twitchnext);
		k=(TextView)findViewById(R.id.textView2);
		sm=(SensorManager)getSystemService(SENSOR_SERVICE);
		accelerometer=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
		
		ss1=getIntent().getExtras().getString("Value1"); //left
		ss2=getIntent().getExtras().getString("Value2"); //rgh
		ss3=getIntent().getExtras().getString("Value3"); //top
		ss4=getIntent().getExtras().getString("Value4"); //bot
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

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		float x=event.values[0];
		float y=event.values[1];
		float z=event.values[2];
		k.setText(x+"\n"+y+"\n"+z+"\n");
		//AudioManager am = (AudioManager) getSystemService(AUDIO_SERVICE);		
		if(x<-11 && y<10)
		{
			if(ss1.equals("Camera"))
			{
				try
				{
					Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
					startActivityForResult(intent, 0);
				}
				catch(Exception e)
				{
					Toast.makeText(getApplicationContext(), "error"+e, Toast.LENGTH_SHORT).show();
				}	
			}
			else if(ss1.equals("Music")) 
			{
				try
				{
					Intent intent = new Intent("android.intent.action.MUSIC_PLAYER");
					startActivity(intent);
				}	
				catch(Exception e)
				{
					Toast.makeText(getApplicationContext(), "error"+e, Toast.LENGTH_SHORT).show();
				}	
			}
			else if(ss1.equals("Emergency")) 
			{
				try
				{
					Intent intent = new Intent(Twitchnext.this,Emergency.class);
					startActivity(intent);
				}
				catch(Exception e)
				{
					Toast.makeText(getApplicationContext(), "error"+e, Toast.LENGTH_SHORT).show();
				}	
			}
			else if(ss1.equals("Calculator")) 
			{
				try
				{
					Intent i = new Intent();
					i.setClassName("com.android.calculator2", "com.android.calculator2.Calculator");
					startActivity(i); 
				}
				catch(Exception e)
				{
					Toast.makeText(getApplicationContext(), "error"+e, Toast.LENGTH_SHORT).show();
				}	
			}
		}
		else if(x>9) 
		{
			if(ss2.equals("Camera")) 
			{
				try
				{
					Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
					startActivityForResult(intent, 0);
				}
				catch(Exception e)
				{
					Toast.makeText(getApplicationContext(), "error"+e, Toast.LENGTH_SHORT).show();
				}
			}
			else if(ss2.equals("Music")) 
			{
				try
				{
					Intent intent = new Intent("android.intent.action.MUSIC_PLAYER");
					startActivity(intent);
				}
				catch(Exception e)
				{
					Toast.makeText(getApplicationContext(), "error"+e, Toast.LENGTH_SHORT).show();
				}	
			}
			else if(ss2.equals("Emergency")) 
			{
				try
				{
					Intent intent = new Intent(Twitchnext.this,Emergency.class);
					startActivity(intent);
				}
				catch(Exception e)
				{
					Toast.makeText(getApplicationContext(), "error"+e, Toast.LENGTH_SHORT).show();
				}	
			}
			else if(ss2.equals("Calculator")) 
			{
				try
				{
					Intent i = new Intent();
					i.setClassName("com.android.calculator2", "com.android.calculator2.Calculator");
					startActivity(i); 
				}
				catch(Exception e)
				{
					Toast.makeText(getApplicationContext(), "error"+e, Toast.LENGTH_SHORT).show();
				}	
			}
		}
		else if(y>11) 
		{
			if(ss3.equals("Camera")) 
			{
				try
				{
					Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
					startActivityForResult(intent, 0);
				}
				catch(Exception e)
				{
					Toast.makeText(getApplicationContext(), "error"+e, Toast.LENGTH_SHORT).show();
				}
			}
			else if(ss3.equals("Music")) 
			{
				try
				{
					Intent intent = new Intent("android.intent.action.MUSIC_PLAYER");
					startActivity(intent);
				}
				catch(Exception e)
				{
					Toast.makeText(getApplicationContext(), "error"+e, Toast.LENGTH_SHORT).show();
				}	
			}
			else if(ss3.equals("Emergency")) 
			{
				try
				{
					Intent intent = new Intent(Twitchnext.this,Emergency.class);
					startActivity(intent);
				}
				catch(Exception e)
				{
					Toast.makeText(getApplicationContext(), "error"+e, Toast.LENGTH_SHORT).show();
				}	
			}
			else if(ss3.equals("Calculator")) 
			{
				try
				{
					Intent i = new Intent();
					i.setClassName("com.android.calculator2", "com.android.calculator2.Calculator");
					startActivity(i); 
				}
				catch(Exception e)
				{
					Toast.makeText(getApplicationContext(), "error"+e, Toast.LENGTH_SHORT).show();
				}	
			}
		}
		else if(y<-5)
		{
			if(ss4.equals("Camera")) 
			{
				try
				{
    				Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
    				startActivityForResult(intent, 0);
    			}
    			catch(Exception e)
    			{
    				Toast.makeText(getApplicationContext(), "error"+e, Toast.LENGTH_SHORT).show();
    			}
    		}
    		else if(ss4.equals("Music")) 
    		{
    			try
    			{
    				Intent intent = new Intent("android.intent.action.MUSIC_PLAYER");
    				startActivity(intent);
    			}
    			catch(Exception e)
    			{
    				Toast.makeText(getApplicationContext(), "error"+e, Toast.LENGTH_SHORT).show();
    			}	
    		}
    		else if(ss4.equals("Emergency")) 
    		{
    			try
    			{
    				Intent intent = new Intent(Twitchnext.this,Emergency.class);
    				startActivity(intent);
    			}
    			catch(Exception e)
    			{
    				Toast.makeText(getApplicationContext(), "error"+e, Toast.LENGTH_SHORT).show();
    			}	
    		}
    		else if(ss4.equals("Calculator")) 
    		{
    			try
    			{
    				Intent i = new Intent();
    				i.setClassName("com.android.calculator2", "com.android.calculator2.Calculator");
    				startActivity(i); 
    			}
    			catch(Exception e)
    			{
    				Toast.makeText(getApplicationContext(), "error"+e, Toast.LENGTH_SHORT).show();
    			}	
    		}
		}
	}
}
