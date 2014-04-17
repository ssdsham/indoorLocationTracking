package com.example.indoorlocationtracking;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Timer;

import android.content.Intent;
import android.net.Uri;
//import com.example.indoorlocationtracking.ScanTask;
//import com.iDocent.ScanResultReceiver;


import android.util.Log;
import android.view.View.OnClickListener;



import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
















import com.example.indoorlocationtracking.R;
import com.example.indoorlocationtracking.SQLHelper;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.net.wifi.ScanResult;

import java.util.List;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends Activity  {
	 private ListView mainListView ;  
	  private ArrayAdapter<String> listAdapter ;  
	  WifiManager wifi;
	  Button button;
	  Button button1;
	  Button button2;
	  FileWriter writer;
	  File file;
	  private EditText edittext;
		//Timer object
		Timer timer;
		//ScanTask scanner;
		private int networkID=-1;
		private static final String FILENAME = "myFile.txt";
		StringBuilder sb;
	
		
	  /** Called when the activity is first created. */  
	  @Override  
	  public void onCreate(Bundle savedInstanceState) {  
	    super.onCreate(savedInstanceState);  
	    setContentView(R.layout.activity_main);  
	      
	    // Find the ListView resource.   
	    mainListView = (ListView) findViewById( R.id.mainListView );  
	    wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		
		//Start the timer running the scanner
		//timer = new Timer();	
		//scanner = new ScanTask(wifi, this);	
	    addKeyListener();
	    addListenerOnButton();
	  
		//Set up to capture Wi-Fi scan results ready event
        //SRR = new ScanResultReceiver(this);
		IntentFilter i = new IntentFilter();
		sb=new StringBuilder(100);
		i.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
		
        List<ScanResult> apList = wifi.getScanResults();
        
int j=0;
        ArrayList<String> values = new ArrayList<String>() ;
        while(j<apList.size())
        {
        values.add(apList.get(j).SSID+"  "+apList.get(j).BSSID+"  "+apList.get(j).level);
        sb.append(apList.get(j).SSID+"  "+apList.get(j).BSSID+"  "+apList.get(j).level+"\n");
        j++;
        	
        }
        
        
        String textToSaveString = "Hello Android";
        
        writeToFile(textToSaveString);
        
        
        String[] planets = new String[] { "Mercury", "Venus", "Earth", "Mars",  
                "Jupiter", "Saturn", "Uranus", "Neptune"};    
ArrayList<String> planetList = new ArrayList<String>();  
planetList.addAll( Arrays.asList(planets) );  

// Create ArrayAdapter using the planet list.  
listAdapter = new ArrayAdapter<String>(this, R.layout.list_item, values);  
	    // Set the ArrayAdapter as the ListView's adapter.  
	    mainListView.setAdapter( listAdapter );      
	    
	  }  
	  
	  public void addKeyListener() {
		  
			// get edittext component
			edittext = (EditText) findViewById(R.id.editText);
		 
			// add a keylistener to keep track user input
			edittext.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
		 
				// if keydown and "enter" is pressed
				if ((event.getAction() == KeyEvent.ACTION_DOWN)
					&& (keyCode == KeyEvent.KEYCODE_ENTER)) {
		 
					// display a floating message
					Toast.makeText(MainActivity.this,
						edittext.getText(), Toast.LENGTH_LONG).show();
					return true;
		 
				} else if ((event.getAction() == KeyEvent.ACTION_DOWN)
					&& (keyCode == KeyEvent.KEYCODE_9)) {
		 
					// display a floating message
					Toast.makeText(MainActivity.this,
						"Number 9 is pressed!", Toast.LENGTH_LONG).show();
					return true;
				}
		 
				return false;
			}
		 });
		}
	  
	
	  
	  
	  private void writeToFile(String data) {
	        try {
	            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(FILENAME, Context.MODE_PRIVATE));
	            outputStreamWriter.write(data);
	            outputStreamWriter.close();
	        }
	        catch (IOException e) {
	        	final String TAG = MainActivity.class.getName();
	            Log.e(TAG, "File write failed: " + e.toString());
	        } 
	         
	    }
	  public void addListenerOnButton() {
		  
		  
			button = (Button) findViewById(R.id.button1);
	 
			button.setOnClickListener(new OnClickListener() {
	 
				@Override
				public void onClick(View arg0) {
	 
					SQLHelper db = new SQLHelper(getApplicationContext());
					Wifi_class w1=new Wifi_class("","");
					w1.setDestination(edittext.getText().toString());
					w1.setDetails(sb.toString());
				    SimpleDateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
				    String date = df.format(Calendar.getInstance().getTime());
				   w1.setTime(date);
					db.insertWifi(w1);
					
				}
	 
			});
	 
			button1 = (Button) findViewById(R.id.button2);
			 
			button1.setOnClickListener(new OnClickListener() {
	 
				@Override
				public void onClick(View arg0) {
	 
					Intent i = new Intent(MainActivity.this, Display_wifi.class);
            		startActivity(i);  
					
				}
	 
			});
			
			button2 = (Button) findViewById(R.id.button3);
			button2.setOnClickListener(new OnClickListener() {
				 
				@Override
				public void onClick(View arg0) {
	 
					 mainListView = (ListView) findViewById( R.id.mainListView );  
					    wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
						
						//Start the timer running the scanner
						//timer = new Timer();	
						//scanner = new ScanTask(wifi, this);	
					    addKeyListener();
					    addListenerOnButton();
					 
						//Set up to capture Wi-Fi scan results ready event
				        //SRR = new ScanResultReceiver(this);
						IntentFilter i = new IntentFilter();
						sb=new StringBuilder(100);
						i.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
						
				        List<ScanResult> apList = wifi.getScanResults();
				        
				int j=0;
				        ArrayList<String> values = new ArrayList<String>() ;
				        while(j<apList.size())
				        {
				        values.add(apList.get(j).SSID+"  "+apList.get(j).BSSID+"  "+apList.get(j).level);
				        sb.append(apList.get(j).SSID+"  "+apList.get(j).BSSID+"  "+apList.get(j).level+"\n");
				        j++;
				        	
				        }
				        /*
				        file = new File( this.getFilesDir().getPath().toString() +"/FileWriterTest.txt");
				        try {
				        	j=0;
							writer = new FileWriter(file ,true);
							  while(j<apList.size())
						        {
						        writer.write(apList.get(j).SSID+"  "+apList.get(j).BSSID+"  "+apList.get(j).level);
						        writer.write(System.getProperty("line.separator"));
						        j++;
						        	
						        }
							  writer.flush();
							  writer.close();
							
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						*/
				        
				        					
				}
	 
			});
			
			
			
			
			
		}
	
}
