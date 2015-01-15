package com.project;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomePageActivity extends Activity {

	Button b,secm,desm,netm,powm,appm;
	Intent it, intent;
	AlertDialog alertDialog;
	
	public class A implements OnClickListener {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId()) {
				case R.id.button1:
					it = new Intent(HomePageActivity.this, GPSLocation.class);
					startActivity(it);
					break;
				case R.id.button2:
					it = new Intent(HomePageActivity.this, TimeManager.class);
					startActivity(it);
					break;
				case R.id.button3:
					it = new Intent (HomePageActivity.this, Twitch.class);
					startActivity(it);
					break;
			}
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_page);
		b = (Button) findViewById(R.id.button1);
		secm = (Button) findViewById(R.id.button2);
		netm = (Button) findViewById(R.id.button3);
		b.setOnClickListener(new A());
		secm.setOnClickListener(new A());
		netm.setOnClickListener(new A());	
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