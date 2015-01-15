package com.project;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class ByTimeCall extends Activity {
	AlertDialog alertDialog;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_by_time_call);
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
