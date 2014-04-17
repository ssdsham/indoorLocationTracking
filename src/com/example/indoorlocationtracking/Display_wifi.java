package com.example.indoorlocationtracking;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class Display_wifi extends Activity {
	
	TextView tv1;
	StringBuilder sb;
	  @Override  
	  public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);  
		    setContentView(R.layout.wifi_details);  
		    sb=new StringBuilder(100);
		  tv1=(TextView)findViewById(R.id.tv1);
		  tv1.setMovementMethod(new ScrollingMovementMethod());
		  
			SQLHelper db = new SQLHelper(getApplicationContext());
			
			List<Wifi_class> w= db.getAllWifi();
			for(Wifi_class s:w)
			{
				sb.append(s.getId()+ " "+s.getDestination()+ " "+s.getDetails()+" "+s.getTime()+"\n");
			}
		  tv1.setText(sb.toString());
		  
		  
	  }
	  

}
