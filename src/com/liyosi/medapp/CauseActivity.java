package com.liyosi.medapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CauseActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cause);
		TextView view=(TextView)findViewById(R.id.cause);
		Bundle b=getIntent().getExtras();
		if(b!=null){
			String cause=b.getString("cause");
			view.setText(cause);
		}
		
	}

	

}
