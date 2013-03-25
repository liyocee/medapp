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

public class Symptom extends ListActivity  {
	
	private ArrayList<String>names;
	
	private ArrayList<SymptomPojo> passList;
	ArrayAdapter<String> symptomsAdapter;
	ListView symptomsList;
	private final String mUrl =Url.getUrl()+"symptom.php";
	
	

	public Symptom(){
		Log.v("url",mUrl);
		names=new ArrayList<String>();
		passList=new ArrayList<SymptomPojo>();
		symptomsAdapter=null;
	}///end constructor
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 Log.v("MAIN", "main starting");
		 setItems();
		 symptomsAdapter= new ArrayAdapter<String>(Symptom.this,
				 android.R.layout.simple_list_item_1,names);
		 setListAdapter(symptomsAdapter);
		
		
	}//end
	
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
				//int id, String name, String cause, String treatment, String symptoms
				SymptomPojo pojo=new SymptomPojo(Integer.parseInt(json.getString("id")),
												json.getString("name"),
												json.getString("cause"),
												json.getString("treatment"),
												json.getString("symptoms"));
				//Log.v("Symptom",json.getString("symptoms"));
				//Log.v("Treatment",json.getString("treatment"));
				
				passList.add(pojo);
				
			}
			
			symptomsAdapter.notifyDataSetChanged();
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
		//Toast.makeText(getApplicationContext(), Integer.toString(position)+" . Item Clicked is "+item, Toast.LENGTH_LONG).show();
		Bundle b=new Bundle();
		b.putParcelable("parse",passList.get(position));
		Intent intent=new Intent(this,SymptomTabActivity.class);
		intent.putExtras(b);
		startActivity(intent);
	}//end listener
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_symptom, menu);
		return true;
	}

}
