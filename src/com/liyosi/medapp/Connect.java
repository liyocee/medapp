package com.liyosi.medapp;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class Connect extends Activity {
	/* Proxy Host */
    private static final String PROXY = "proxy.uonbi.ac.ke";
    private static  HttpHost PROXY_HOST=null;
    
    
    public static DefaultHttpClient  connect1(){
    	
    	PROXY_HOST = new HttpHost(PROXY, 80); 	 
	    HttpParams httpParameters = new BasicHttpParams();
	    DefaultHttpClient httpclient = new DefaultHttpClient(httpParameters);
	    httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, PROXY_HOST);
	    
	    httpclient.getCredentialsProvider().setCredentials(
                new AuthScope("proxy.uonbi.ac.ke", 80),
                new UsernamePasswordCredentials("kscdip", "kscdip"));
	    return httpclient;
	 
    	
    }///end
    
    public static HttpClient  connect(){
    	HttpClient httpclient = new DefaultHttpClient();
    	return httpclient;
    	
    }
    
    public  boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        // if no network is available networkInfo will be null
        // otherwise check if we are connected
        Log.v("Network",networkInfo.toString());
        if (networkInfo != null && networkInfo.isConnected()) {
        	
            return true;
        }
        return false;
    } 
    
    

}
