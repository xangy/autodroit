package com.project;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Feedback extends Activity {
	AlertDialog alertDialog;
	EditText name, subject, message;
	String fullname, supportEmail, emailSubject, originalEmail, emailMessage;
	Intent emailIntent, intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
		name = (EditText) findViewById(R.id.name);
		subject = (EditText) findViewById(R.id.subject);
		message = (EditText) findViewById(R.id.message);
		
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
	
	public void sendEmail(View view) {
		Button button = (Button) findViewById(R.id.send_mail);
		button.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	fullname = name.getText().toString();
		    	supportEmail = "Autodroit Support <vishalxkumar@outlook.com>";
		    	emailSubject = subject.getText().toString();
		    	originalEmail = message.getText().toString();
		    	emailMessage = originalEmail + "\nRegards,\n" + fullname;
		    	emailIntent = new Intent(Intent.ACTION_SEND);
		    	emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{ supportEmail });
		    	emailIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);
		    	emailIntent.putExtra(Intent.EXTRA_TEXT, emailMessage);
		    	//need this to prompts email client only
		    	emailIntent.setType("message/rfc822");
		    	startActivity(Intent.createChooser(emailIntent, "Choose an Email client :"));
		    }
		});
	}
	
}
