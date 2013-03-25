package com.liyosi.medapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class Dashboard extends Activity implements OnItemClickListener  {
	
	static final String EXTRA_MAP = "map";
	Intent nextActivity;
    static final LauncherIcon[] ICONS = {
            new LauncherIcon(R.drawable.symptom, "Symptoms", "symptom.png"),
            new LauncherIcon(R.drawable.firstaid, "First Aid", "firstaid.png"),
            new LauncherIcon(R.drawable.clinic, "Clinics", "clinics.png"),
            new LauncherIcon(R.drawable.contact, "Contacts", "contact.png"),
            new LauncherIcon(R.drawable.about, "About", "about.png"),
            new LauncherIcon(R.drawable.logout, "Exit", "logout.png"),
    };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		GridView gridview = (GridView) findViewById(R.id.dashboard_grid);
	    gridview.setAdapter(new ImageAdapter(this));
	    gridview.setOnItemClickListener(this);
	      
	   // Hack to disable GridView scrolling
	      gridview.setOnTouchListener(new OnTouchListener() {
	            @Override
	            public boolean onTouch(View v, MotionEvent event) {
	                return event.getAction() == MotionEvent.ACTION_MOVE;
	            }
	        });
	}//end
	
	@Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
       
       // intent.putExtra(EXTRA_MAP, ICONS[position].map);
        
        Log.v("Postion",Integer.toString(position));
        if(position==0){
        		Intent intent = new Intent(this, Symptom.class);
        		progressBar(v,intent);
        	
        	
        }
        
        if(position==1){
        	Intent intent = new Intent(this, FirstAidActivity.class);
        	progressBar(v,intent);
        }
        if(position==2){
        	Intent intent = new Intent(this,Clinic.class);
        	progressBar(v,intent);
        }
        
        if(position==3){
        	Intent intent = new Intent(this,DoctorActivity.class);
        	progressBar(v,intent);
        }
        if(position==4){
        	AlertDialog diaBox = makeAndShowDialogBox();	
		    diaBox.show();
        }
        if(position==5){
        	AlertDialog diaBox = makeAndShowDialogBoxExit();	
		    diaBox.show();
        }
    }//end
	
	
	private void progressBar(View v,Intent intent){
		new ProgressDialog(v.getContext());
		nextActivity=intent;
		final ProgressDialog dialog = ProgressDialog.show(v.getContext(),"Please Wait", "Accessing Server.....");
        Thread thread = new Thread(){
	    @Override
	    public void run() {
	    	//if(position==0)
	    	startActivity(nextActivity);  
			runOnUiThread(new Runnable() {
	            public void run() {   
	                dialog.dismiss();
	            }
				});
			
	            }
	    };//emd thread       
	     thread.start();
	}//end progressBar
	
	
	
	private AlertDialog makeAndShowDialogBox(){
    	
        AlertDialog myQuittingDialogBox = 

        	new AlertDialog.Builder(this) 
        	//set message, title, and icon
        	.setTitle("About Medapp") 
        	.setMessage("Medapp is an illness diagnosis applicaton for use by anyone suffering from any " +
        			"illness and want immediate help.") 
        	.setIcon(R.drawable.about)
        	//set three option buttons
        	.setPositiveButton("Ok", new DialogInterface.OnClickListener() { 
        		public void onClick(DialogInterface dialog, int whichButton) { 
        			dialog.dismiss();
        		}              
        	})//create
        	.create();
        	
        	return myQuittingDialogBox;
    }//end
	private AlertDialog makeAndShowDialogBoxExit(){
		
	    AlertDialog myQuittingDialogBox = 
	
	    	new AlertDialog.Builder(this) 
	    	//set message, title, and icon
	    	.setTitle("Exit App?") 
	    	.setMessage("Press OK to Exit MedApp.") 
	    	.setIcon(R.drawable.about)
	    	//set three option buttons
	    	.setPositiveButton("Ok", new DialogInterface.OnClickListener() { 
	    		public void onClick(DialogInterface dialog, int whichButton) { 
	    			Dashboard.this.finish();
	    		}              
	    	}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() { 
	    		public void onClick(DialogInterface dialog, int whichButton) { 
	    			dialog.dismiss();
	    		}              
	    	})//create
	    	.create();
	    	
	    	return myQuittingDialogBox;
	}//end


	public void onBackPressed() {
		   Log.i("Close", "Finishing");
		   AlertDialog diaBox = makeAndShowDialogBoxExit();	
		    diaBox.show();
		   
		 }
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_dashboard, menu);
		return true;
	}
	
	static class LauncherIcon {
        final String text;
        final int imgId;
        final String map;
 
        public LauncherIcon(int imgId, String text, String map) {
            super();
            this.imgId = imgId;
            this.text = text;
            this.map = map;
        }
 
    }//end static class
	
	static class ImageAdapter extends BaseAdapter {
        private Context mContext;
 
        public ImageAdapter(Context c) {
            mContext = c;
        }
 
        @Override
        public int getCount() {
            return ICONS.length;
        }
 
        @Override
        public LauncherIcon getItem(int position) {
            return null;
        }
 
        @Override
        public long getItemId(int position) {
            return 0;
        }
 
        static class ViewHolder {
            public ImageView icon;
            public TextView text;
        }
 
        // Create a new ImageView for each item referenced by the Adapter
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            ViewHolder holder;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater) mContext.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
 
                v = vi.inflate(R.layout.dashboard_icon, null);
                holder = new ViewHolder();
                holder.text = (TextView) v.findViewById(R.id.dashboard_icon_text);
                holder.icon = (ImageView) v.findViewById(R.id.dashboard_icon_img);
                v.setTag(holder);
            } else {
                holder = (ViewHolder) v.getTag();
            }
 
            holder.icon.setImageResource(ICONS[position].imgId);
            holder.text.setText(ICONS[position].text);
 
            return v;
        }
    }//end image adpater

}
