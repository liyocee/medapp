package com.liyosi.medapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class Splash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		Thread timer = new Thread(){
        	public void run(){
        		try{
        			sleep(1000);
        		}catch (Exception e){
        			e.printStackTrace();
        		}finally{
        			startActivity(new Intent(Splash.this,Dashboard.class));
        			finish();
        		}
        	}
        	
        };
        timer.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_splash, menu);
		return true;
	}

}
