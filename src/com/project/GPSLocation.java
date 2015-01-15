package com.project;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GPSLocation extends Activity {
	AlertDialog alertDialog;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gpslocation);
		
		final TextView text ;
		final EditText et;
		final Button b1, b2, b3;
	    final GPSTracker mGPS = new GPSTracker(this);
		
	    text = (TextView) findViewById(R.id.texts);
		b1 = (Button) findViewById(R.id.button1);
		b1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(mGPS.canGetLocation ) {
					mGPS.getLocation();
					text.setText("Latitude: " + mGPS.getLatitude() + "\nLongitude:" + mGPS.getLongitude());
				}
				else {
					text.setText("Unable to find your location");
				    System.out.println("Unable to find the location");
				}
			}
		});
	    
		et = (EditText) findViewById(R.id.editText1);
	    b2 = (Button) findViewById(R.id.button2);
	    b2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				text.setText(null);	
			}
		});

	    b3 = (Button) findViewById(R.id.button3);
	    b3.setOnClickListener(new OnClickListener() {
	    	@Override
	 		public void onClick(View v) {
	 			// TODO Auto-generated method stub
		       	AlertDialog.Builder ab = new android.app.AlertDialog.Builder(GPSLocation.this);
		       	android.content.DialogInterface.OnClickListener listener1, listener2;
		       	ab.setTitle("Are you sure?");
				listener1 = new  android.content.DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
			 		 	double longitude = 0 ;
			 		 	double latitude = 0;
			 		 	mGPS.getLocation();
			 			latitude = (double) mGPS.getLatitude();
				       	longitude = (double) mGPS.getLongitude();
				       	Intent it = new Intent(GPSLocation.this, LocationFunction.class);
			 			it.putExtra("long",longitude );
			 			it.putExtra("lati",latitude);
			 			it.putExtra("dist",et.getText());
			 			startActivity(it);
					}
				};
				listener2 = new android.content.DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
					}
				};
				ab.setPositiveButton("Yes", listener1);  
				ab.setNegativeButton("No", listener2);
				ab.show();
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
