package com.liyosi.medapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class DoctorDetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_details);
		TextView view=(TextView)findViewById(R.id.doctordetails);
	
		Bundle b=getIntent().getExtras();
		DoctorPojo doc=b.getParcelable("parsedoctor");
		if(b!=null){
			StringBuilder build=new StringBuilder();
			build.append("Name  : "+doc.getName());
			build.append("\n\nLocation : "+doc.getLocation());
			build.append("\n\nPhone Number: "+doc.getPhone_number());
			
			view.setText(build.toString());
			
			
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_doctor_details, menu);
		return true;
	}

}
