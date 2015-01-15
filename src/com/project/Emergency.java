package com.project;



import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Emergency extends Activity {

	Button b,b1,b2;
	AlertDialog alertDialog;
	Intent intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_emergency);
		
b=(Button)findViewById(R.id.button1);
        
        b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				try{
				
				String number = "102";
				String uri="tel:"+ number.trim();
				Intent in=new Intent(Intent.ACTION_CALL);
				in.setData(Uri.parse(uri));
				startActivity(in);
				}
				catch(ActivityNotFoundException e){
					Toast.makeText(getApplicationContext(), "error"+e, Toast.LENGTH_SHORT).show();
				}
			}
		});
        
        b1=(Button)findViewById(R.id.button2);
        b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				try{
				String number = "100";
				String uri="tel:"+ number.trim();
				Intent in1=new Intent(Intent.ACTION_CALL);
				in1.setData(Uri.parse(uri));
				startActivity(in1);
				}catch(ActivityNotFoundException e)
				{
					Toast.makeText(getApplicationContext(), "error"+e, Toast.LENGTH_SHORT).show();
					
				}
				
				}
		});
        
        b2=(Button)findViewById(R.id.button3);
        b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				try{
				
				String number = "101";
				String uri="tel:"+ number.trim();
				Intent in2=new Intent(Intent.ACTION_CALL);
				in2.setData(Uri.parse(uri));
				startActivity(in2);
				}
				catch(ActivityNotFoundException e)
				{
					Toast.makeText(getApplicationContext(), "error"+e, Toast.LENGTH_SHORT).show();
					
				}
				
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
