package com.liyosi.medapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Clinic extends ListActivity {
	private ArrayList<String> id;
	private ArrayList<String>names;
	private ArrayList<ClinicPojo> passList;
	
	ArrayAdapter<String> clinicsAdapter;
	ListView clinicsList;
	private final String mUrl =Url.getUrl()+"clinics.php";
	
	public Clinic(){
		id=new ArrayList<String>();
		names=new ArrayList<String>();
		passList=new ArrayList<ClinicPojo>();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_clinic);
		 setItems();
		clinicsAdapter= new ArrayAdapter<String>(Clinic.this,
				 android.R.layout.simple_list_item_1,names);
		 setListAdapter(clinicsAdapter);
	}
	
	private void setItems() {
		// TODO Auto-generated method stub
		String result = "";
		InputStream isp = null;
		try {
			//HttpClient httpclient = new DefaultHttpClient();
			HttpClient httpclient = Connect.connect();
			 HttpGet httpget = new HttpGet(mUrl); 
			
			//HttpPost httppost = new HttpPost(sb.toString());
			//HttpResponse response = httpclient.execute(httppost);
			HttpResponse response=httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			isp = entity.getContent();
		} catch (Exception e) {
			Log.e("Log.tag", "Error in http connection" + e.toString());
		}
		// convert response to string
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					isp, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			isp.close();
			result = sb.toString();
		} catch (Exception e) {
			Log.e("Log.tag", "Error converting result " + e.toString());
		}

		// parse json date
		try {
			JSONArray jArray = new JSONArray(result);
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject json = jArray.getJSONObject(i);
				names.add(json.getString("name"));	
				id.add(json.getString("id"));	
				
				// int id,String name,String description,String address,String phone,String website lat lom
				ClinicPojo clinic=new ClinicPojo(Integer.parseInt(json.getString("id")),
															json.getString("name"),
															json.getString("description"),
															json.getString("address"),
															json.getString("phone"),
															json.getString("website"),
															json.getString("lat"),
															json.getString("lon")
						);
				passList.add(clinic);
				
			}
			
			clinicsAdapter.notifyDataSetChanged();
		} catch (Exception e) {
			Log.e("Log.tag", "Error parsing Data" + e.toString());
		}
	}//end
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		//String item=((TextView) v).getText().toString();
		//Log.v("List",item);
		
		Bundle b=new Bundle();
		b.putParcelable("parseclinic", passList.get(position));
		Intent intent = new Intent(this,ClinicDetailsActivity.class);
		intent.putExtras(b);
		startActivity(intent);
		//Toast.makeText(getApplicationContext(), Integer.toString(position)+" . Item Clicked is "+item, Toast.LENGTH_LONG).show();
	
		
	}//end listener
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_clinic, menu);
		return true;
	}

}
