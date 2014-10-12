package com.fatura.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;

import com.google.gson.Gson;

public class CallService{
	private static CallService instance;
	private List<Call> callList;
	private FileOutputStream out;
	private Context ctx;

	private CallService(Context ctx){
		callList = new LinkedList<Call>();
		this.ctx = ctx;
		try {
		  out = ctx.openFileOutput("callLog", Context.MODE_PRIVATE);
		} catch (Exception e)  {
		  e.printStackTrace();
		}
	}

	public static CallService getInstance(Context ctx){
		if(instance == null)
			instance = new CallService(ctx);

		return instance;
	}

	public void enqueue(Call call) throws IOException{
		callList.add(call);
		updateFile();
	}

	public void dequeue(){
		callList.get(0);
	}
	
	public List<Call> getQueue(){
		return callList;
	}

	public void updateFile() throws IOException{
		Gson gson = new Gson();
		String json = gson.toJson(callList);
	    out.write(json.getBytes());
	    out.close();
	}

	private boolean isInsideCurrentBilling(int billingDay, DateTime callDate){
		DateTime currDate = new DateTime();
		DateTime oldBillingDate = currDate.minusMonths(1).withDayOfMonth(billingDay).withTime(0, 0, 0, 0);
		DateTime newBillingDate = currDate.plusMonths(1).withDayOfMonth(billingDay).withTime(0, 0, 0, 0);

		if(callDate.isAfter(oldBillingDate) && callDate.isBefore(newBillingDate))
			return true;

		return false;
	}
	
	private Calendar getOldestDate(int firstDay){
		DateTime currDate = new DateTime();
		
		DateTime oldBillingDate = currDate.minusMonths(1);
		
		DateTime billingDate = new DateTime(currDate.getDayOfYear(), currDate.getDayOfYear(), 26, 12, 0, 0, 0);
		
		return null;
//		Calendar currDate = Calendar.getInstance();
//		Calendar oldestDate = Calendar.getInstance();
//		
//		oldestDate.set(Calendar.YEAR, currDate.get(Calendar.YEAR));
//		oldestDate.set(Calendar.HOUR_OF_DAY, currDate.get(Calendar.HOUR_OF_DAY));
//		oldestDate.set(Calendar.MINUTE, currDate.get(Calendar.MINUTE));
//
//		if(currDate.get(Calendar.DAY_OF_MONTH) > firstDay){
//			oldestDate.set(Calendar.MONTH, currDate.get(Calendar.MONTH));
//			oldestDate.set(Calendar.DAY_OF_MONTH, firstDay);
//		}else{
//			if(currDate.get(Calendar.MONTH) == Calendar.JANUARY){
//				oldestDate.set(Calendar.MONTH, Calendar.DECEMBER);
//				oldestDate.set(Calendar.YEAR, currDate.get(Calendar.YEAR) - 1);
//			}else{
//				oldestDate.set(Calendar.MONTH, currDate.get(Calendar.MONTH) - 1);
//			}
//			oldestDate.set(Calendar.DAY_OF_MONTH, firstDay);
//		}
//
//		return oldestDate;
	}
	
    public List<Call> getCallLog(int billingDay){
        Uri contacts = CallLog.Calls.CONTENT_URI;
        Cursor managedCursor = ctx.getContentResolver().query(
                contacts, null, null, null, null);
        List<Call> callList = new ArrayList<Call>();
        int type;

        while(managedCursor.moveToNext()){
            type = managedCursor.getInt(managedCursor.getColumnIndex(CallLog.Calls.TYPE));

        	if(type == CallLog.Calls.OUTGOING_TYPE){
            	String callDate = managedCursor.getString(managedCursor.getColumnIndex(CallLog.Calls.DATE));

            	if(isInsideCurrentBilling(billingDay, new DateTime(Long.parseLong(callDate)))){
	        		String phNumber = managedCursor.getString(managedCursor.getColumnIndex(CallLog.Calls.NUMBER));
	            	String callDuration = managedCursor.getString(managedCursor.getColumnIndex(CallLog.Calls.DURATION));

	            	Call c = new Call();

	            	c.setDate(Long.parseLong(callDate));
	            	c.setDuration(Integer.parseInt(callDuration));
	            	PhoneNumber phTo = PhoneNumberFactory.createPhoneNumber(phNumber);

	            	if(phTo == null){
	            		phTo = new PhoneNumber();
	            		phTo.setFullNumber(phNumber);
	            		phTo.setFormatNotFound(true);
	            	}

	            	if(phTo.isCollectNumber() || phTo.isFreeBusinessNumber())
	            		continue;

	            	c.setTo(phTo);
	            	c.setFrom(Session.getInstance().getUser().getPhoneNumber());

	            	callList.add(c);
            	}
        	}
        }

        managedCursor.close();

        return callList;
    }
}
