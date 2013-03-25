package com.liyosi.medapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Vector;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Symptom extends ListActivity  {
	private Vector<String> items;
	private Vector<String> id;
	private ArrayList<String>names;
	ArrayAdapter<String> symptomsAdapter;
	ListView symptomsList;
	private final String mUrl ="http://10.4.48.47/medapp/symptom.php";

	public Symptom(){
		items=new Vector<String>();
		id=new Vector<String>();
		names=new ArrayList<String>();
	}///end constructor
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 Log.v("MAIN", "main starting");
		 //setContentView(R.layout.activity_symptom);
		 /*SymptomListAdapter adapter = new SymptomListAdapter(this);
         setListAdapter(adapter);                                    

         LoadSymptoms loadData = new LoadSymptoms(adapter);
         loadData.execute();    */
		 
		 setItems();
		 ArrayAdapter<String> a = new ArrayAdapter<String>(Symptom.this,
				 android.R.layout.simple_list_item_1);
		 Enumeration<String> en = items.elements();
		 while (en.hasMoreElements())
				a.add(en.nextElement());
		 setListAdapter(a);
		
		
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
				items.add(json.getString("name"));
				

			}
		} catch (Exception e) {
			Log.e("Log.tag", "Error parsing Data" + e.toString());
		}
	}
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_symptom, menu);
		return true;
	}

}
