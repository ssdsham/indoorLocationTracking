package com.example.indoorlocationtracking;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;








import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.DateFormat;
import android.util.Log;

public class SQLHelper extends SQLiteOpenHelper {

  


  private static final String DATABASE_NAME = "sportmix.db";
  private static final int DATABASE_VERSION = 1;

	private static final String WIFI_TABLE = "create table wifi_table (id integer primary key autoincrement,"
	  		+ "destination text,details text,time text)";
	
	
  public SQLHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
	
    db.execSQL(WIFI_TABLE);

    clear("wifi_table",db);
    
   
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(SQLHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    /*db.execSQL("DROP TABLE IF EXISTS Preference");
    db.execSQL("DROP TABLE IF EXISTS Score");
    db.execSQL("DROP TABLE IF EXISTS team");
    onCreate(db);*/
    //insertTeam(new Team("Real Madrid","Football"),db);
    
  }
 
  public void closeDB() {
      SQLiteDatabase db = this.getReadableDatabase();
      if (db != null && db.isOpen())
          db.close();
  }

  public void insertWifi(Wifi_class s) {
	  SQLiteDatabase  database=this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("destination", s.getDestination());
    values.put("details", s.getDetails());
    values.put("time",s.getTime()); 
    
    
    
    Log.d("Data:",s.getDestination()+s.getDetails());
    database.insert("wifi_table",null,values);
    database.close();
  }

  private void insertWifi(Wifi_class s,SQLiteDatabase database) {
	  ContentValues values = new ContentValues();
	    values.put("destination", s.getDestination());
	    values.put("details", s.getDetails());
	
	    values.put("time",s.getTime());    
	    
	    Log.d("Data:",s.getDestination()+s.getDetails());
	    database.insert("wifi_table",null,values);
    
  }

  public void clear(String tname)
  {
	  SQLiteDatabase db = this.getWritableDatabase();
	    db.delete(tname,null,null);
	    db.close();
  }
  private void clear(String tname,SQLiteDatabase db)
  {
	 
	    db.delete(tname,null,null);
	    
  }
  public List<Wifi_class> getAllWifi() {
	  List<Wifi_class> contactList = new ArrayList<Wifi_class>();
	    // Select All Query
	    String selectQuery = "SELECT  * FROM wifi_table ;";
	 
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	 
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	            Wifi_class contact = new Wifi_class();
	            contact.setId(Integer.parseInt(cursor.getString(0)));
	            contact.setDestination(cursor.getString(1));
	            contact.setDetails(cursor.getString(2));
	            contact.setTime(cursor.getString(3));

	           
	            // Adding contact to list
	            contactList.add(contact);
	        } while (cursor.moveToNext());
	    }
	    //db.delete("score",null,null);
	    db.close();
	    // return contact list
	    return contactList;
  }
 
}
/*

  */

