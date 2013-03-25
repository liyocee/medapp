package com.liyosi.medapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class SymptomDescActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_symptom_desc);
		TextView view=(TextView)findViewById(R.id.symptom);
		Bundle b=getIntent().getExtras();
		if(b!=null){
			String symp=b.getString("symptoms");
			view.setText(symp);
					
		}
		 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_symptom_desc, menu);
		return true;
	}

}
