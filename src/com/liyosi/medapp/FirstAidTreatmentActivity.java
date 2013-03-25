package com.liyosi.medapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class FirstAidTreatmentActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first_aid_treatment);
		TextView view=(TextView)findViewById(R.id.firstaidtreat);
		Bundle b=getIntent().getExtras();
		if(b!=null){
			String treat=b.getString("treatment");
			view.setText(treat);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_first_aid_treatment, menu);
		return true;
	}

}
