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

public class FirstAidActivity extends ListActivity {
	private ArrayList<String> id;
	private ArrayList<String> name;
	private ArrayList<String> treatment;
	
	ArrayAdapter<String> aidAdapter;
	ListView List;
	private final String mUrl =Url.getUrl()+"/first_aid.php";;
	
	
	public FirstAidActivity(){
		
		id=new ArrayList<String>();
		name=new ArrayList<String>();
		treatment=new ArrayList<String>();
		aidAdapter=null;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v("First Aid ","Starting First Aid Activity....");
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_first_aid);
		setItems();
		
		aidAdapter= new ArrayAdapter<String>(FirstAidActivity.this,
				 android.R.layout.simple_list_item_1,name);
		 setListAdapter(aidAdapter);
		
	}
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_first_aid, menu);
		return true;
	}
	
	private void setItems() {
		// TODO Auto-generated method stub
		String result = "";
		InputStream isp = null;
		try {
			
			HttpClient httpclient = Connect.connect();
			HttpGet httpget = new HttpGet(mUrl); 
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
				name.add(json.getString("name"));	
				treatment.add(json.getString("treatment"));
				id.add(json.getString("id"));	
				
				
				
			}
			
			aidAdapter.notifyDataSetChanged();
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
	
		Intent intent=new Intent(this,FirstAidTreatmentActivity.class);
		intent.putExtra("treatment",treatment.get(position));
		startActivity(intent);
	}//end listener

}
