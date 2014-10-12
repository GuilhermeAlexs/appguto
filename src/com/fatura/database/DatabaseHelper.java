package com.fatura.database;

import com.fatura.model.Carrier;
import com.fatura.model.PhoneNumberFactory;
import com.fatura.model.User;
import com.fatura.model.Session;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "faturaDB";
 
    // Table Names
    private static final String TABLE_USER = "user";
	
    // Column names
    private static final String USER_ID = "id";
    private static final String USER_NAME = "name";
    private static final String USER_EMAIL = "email";
    private static final String USER_PHONE = "phone";
    private static final String USER_CARRIER = "carrier";
    private static final String USER_PAYMENTDAY = "paymentDay";
    
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
    		+ TABLE_USER + "(" +
    		USER_ID + " INT PRIMARY KEY NOT NULL," +
    		USER_NAME + " TEXT," +
    		USER_EMAIL + " TEXT," +
    		USER_CARRIER + " TEXT," +
    		USER_PAYMENTDAY + " INTEGER," +
    		USER_PHONE + " TEXT" + ")";
    
    public DatabaseHelper (Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
    
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE_USER);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
		onCreate(db);
	}
	
	public boolean checkForSession(){
		SQLiteDatabase db = this.getWritableDatabase();
		boolean hasTables = false;

        Cursor cursor = db.rawQuery("SELECT * FROM user", null);

        if(cursor.getCount() == 0){
            hasTables=false;
        }
        else if(cursor.getCount() > 0){
            hasTables=true;
        }

        cursor.close();
        db.close();
        return hasTables;
    }
	
	public void updateSession() {
		SQLiteDatabase db = this.getWritableDatabase();
		
		Cursor c = db.rawQuery("SELECT * FROM user", null);
		
		if (c.moveToFirst()) {
		     Session.getInstance().getUser().setEmail(c.getString(c.getColumnIndex(USER_EMAIL)));  
			 Session.getInstance().getUser().setName(c.getString(c.getColumnIndex(USER_NAME)));
			 Session.getInstance().getUser().setPhoneNumber(PhoneNumberFactory.createPhoneNumber(c.getString(c.getColumnIndex(USER_PHONE))));
			 Session.getInstance().getUser().getPhoneNumber().setCarrier(new Carrier(c.getString(c.getColumnIndex(USER_CARRIER))));
			 Session.getInstance().setPaymentDay(c.getInt(c.getColumnIndex(USER_PAYMENTDAY)));
			 c.close();
			 db.close();
		 }
	}
	
	public String createSession(User user, int paymentDay) {
		
		SQLiteDatabase db = this.getWritableDatabase();

		String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE " + USER_PHONE + " = ?";
		String[] args={user.getPhoneNumber().getFullNumber()};
		 
		 Cursor c = db.rawQuery(selectQuery, args);
		 
		 if (c.moveToFirst()) {
		       Log.i("User storage", "User already in database");
		       //updateUser(user);
		       return null;
		 }
		
		ContentValues values = new ContentValues();
		values.put(USER_ID, user.getId());
		values.put(USER_NAME, user.getName());
		values.put(USER_EMAIL, user.getEmail());
		values.put(USER_PHONE, user.getPhoneNumber().getFullNumber());
		values.put(USER_CARRIER, user.getPhoneNumber().getCarrier().getName().toUpperCase());
		values.put(USER_PAYMENTDAY, paymentDay);
		
		db.insert(TABLE_USER, null, values);
		c.close();
		db.close();

		updateSession();
		return user.getEmail();
	}
	
//	public User getUserByEmail(String userEmail) {
//		 SQLiteDatabase db = this.getReadableDatabase();
//		 
//		 String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE " + USER_EMAIL + " = ?";
//		 String[] args={userEmail};
//		 
//		 Cursor c = db.rawQuery(selectQuery, args);
//		 
//		 if (c.moveToFirst()) {
//		       
//			 User user = new User();
//			 PhoneNumber phoneNumber = new PhoneNumber();
//			 user.setPhoneNumber(phoneNumber);
//			 int id = c.getInt(c.getColumnIndex(USER_ID));					
//			 
//			 
//			 user.setId(id);
//			 user.setName(c.getString(c.getColumnIndex(USER_NAME)));
//			 user.getPhoneNumber().setNumber(c.getString(c.getColumnIndex(USER_PHONE)));
//			 user.setEmail(c.getString(c.getColumnIndex(USER_EMAIL)));
//			 
//			 c.close();
//			 db.close();
//			 return user;
//		 }
//		 c.close();
//		 db.close();
//		 return null;
//	}

}
