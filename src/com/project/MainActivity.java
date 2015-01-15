package com.project;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
//import android.view.Menu;
import android.widget.SeekBar;

public class MainActivity extends Activity {

	int prog;
	SeekBar SeekBar;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final SeekBar sb = (SeekBar) findViewById(R.id.sk);
		final Handler handler = new Handler();
		Timer timer = new Timer();
		TimerTask progress = new TimerTask() {
			public void run() {
				handler.post(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						prog=prog+10;
						sb.setProgress(prog);					       
					}
				});
				if(prog==100) {
					Intent it=new Intent(MainActivity.this, HomePageActivity.class);
					startActivity(it);
				}        
			}
		};
		timer.schedule(progress, 0, 500);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_menu, menu);
		return true;
	}*/

}
