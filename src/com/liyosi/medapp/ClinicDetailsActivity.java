package com.liyosi.medapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class ClinicDetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clinic_details);
		TextView view=(TextView)findViewById(R.id.clinicdetails);
		Bundle b=getIntent().getExtras();
		ClinicPojo pojo=b.getParcelable("parseclinic");
		
		if(b!=null){
			StringBuilder build=new StringBuilder();
			build.append("Name : "+pojo.getName());
			build.append("\n\nDescription  : "+pojo.getDescription());
			build.append("\n\nAddress : "+pojo.getAddress());
			build.append("\n\nPhone Number : "+pojo.getPhone_number());
			build.append("\n\nWebsite : "+pojo.getWebsite());
			view.setText(build.toString());
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_clinic_details, menu);
		return true;
	}

}
