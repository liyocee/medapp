package com.liyosi.medapp;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class SymptomTabActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 
		setContentView(R.layout.activity_system_tab);
		Intent prev=getIntent();
		Bundle b=prev.getExtras();
		SymptomPojo pojo=b.getParcelable("parse");
		
		 
		
		TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost); 
		tabHost.setup();
		
		// Cause Tab
	
		Intent intent = new Intent().setClass(this, CauseActivity.class);
		intent.putExtra("cause", pojo.getTreatment());
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
		TabSpec spec = tabHost
		  .newTabSpec("Cause")
		  .setIndicator("Cause", getResources().getDrawable(android.R.drawable.star_on))
				 .setContent(intent);
		tabHost.addTab(spec);
	
		// Systomps Tab
		intent= new Intent().setClass(this, SymptomDescActivity.class);
		intent.putExtra("symptoms", pojo.getSymptoms());
		 spec = tabHost
		  .newTabSpec("Symptom")
		  .setIndicator("Symptom", getResources().getDrawable(android.R.drawable.star_on))
				  .setContent(intent);
		
		tabHost.addTab(spec);
		// Treatment tab
		intent = new Intent().setClass(this, TreatmentActivity.class);
		intent.putExtra("treatment", pojo.getCause());
		 spec= tabHost
		  .newTabSpec("Treatment")
		  .setIndicator("Treatment", getResources().getDrawable(android.R.drawable.star_on))
				  .setContent(intent);
		
		tabHost.addTab(spec);
		tabHost.setCurrentTab(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_system_tab, menu);
		return true;
	}

}
