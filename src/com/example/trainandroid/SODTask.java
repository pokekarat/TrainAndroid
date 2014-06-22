package com.example.trainandroid;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.view.WindowManager;

class SODTask extends AsyncTask<Integer, Integer, Integer>
{
	Ui view;
	WindowManager.LayoutParams lp;
	public SODTask(Ui u){
		
		view = u;
		lp = view._act.getWindow().getAttributes();
	}
	
	@Override
	protected void onPreExecute()
	{    		 			
		FileMgr.status = "SOD-based working";
	}
	
	
	boolean isToggle = true;
	@Override
	protected Integer doInBackground(Integer... params) 
	{
		
		//Wait if battery
    	/*while(Battery.getBatteryLevel() > 95)
    	{
    		SystemClock.sleep(200);
    	}*/
    	
    	//Start
 		while(Battery.getBatteryLevel() <= 100 && Battery.getBatteryLevel() >= 0)
    	{
 	    	publishProgress(Config.sample);
 	    	
 	    	/*if(Sample.sample == 0)
 	    		ht.execute();*/
 	    	if(isToggle)
 	    	{
 	    		isToggle = false;
 	    		lp.screenBrightness = 30;
 	    		view._act.getWindow().setAttributes(lp);
 	    	}
 	    	else
 	    	{
 	    		isToggle = true;
 	    		lp.screenBrightness = 255;
 	    		view._act.getWindow().setAttributes(lp);
 	    	}
 	    	
 	    	SystemClock.sleep(5000); //sleep every 5 mins   	    	
 	    	++Config.sample;
 		}
 		
 		//End
 		publishProgress(-1);
 		
		return null;
	}
	
	double vData = 0;
	double uData = 0;
	double fData = 0;
	double tData = 0;
	
	String result = "";
	@Override
	protected void onProgressUpdate(Integer... arg1)
	{    		
		    FileMgr.updateResults();
		    
		    int loop = arg1[0];
	        
		    /*if(loop != -1){
		    	
		    	int index = Sample.sample % 1020;
		    	
		    	if(index >= 0 && index <= 60 )
		    	{
		    		vData += voltData;
		    	    uData += cpuUtil;
		    		fData += cpuFreqData;
		    		
		    		if(index == 60){
		    			vData = vData / 60;
		    			uData = uData / 60;
		    			fData = fData / 60;
		    			
		    			result += "Voltage="+ vData + " Util=" + uData + " Freq=" + fData + " Battery=" + Battery.getBatteryLevel() + "\n";
		    		}
		    	}
		    	 
		    	if(index >= 960 && index <= 1019)
		    	{
		    		vData += voltData;
		    	    uData += cpuUtil;
		    		fData += cpuFreqData;
		    		
		    		if(index == 60){
		    			vData = vData / 60;
		    			uData = uData / 60;
		    			fData = fData / 60;
		    			
		    			result += "Voltage="+ vData + " Util=" + uData + " Freq=" + fData + " Battery=" + Battery.getBatteryLevel() + "\n";
		    		} 
		    	}
		    }*/
		    
		    //if(loop != -1)
		    //{	
		    	result = loop + " voltage="+ FileMgr.voltData + " Util=" + FileMgr.cpuUtilData + " Freq=" + FileMgr.cpuFreqData + " Bright="+FileMgr.brightData+" Battery=" + Battery.getBatteryLevel() + " Temp="+FileMgr.tempData+"\n";	
		    	FileMgr.saveSDCard("SOD_"+ loop, result);
		    //}
		    
		    //if(loop == -1){
		    	
		    //	FileMgr.saveSDCard("SOD",result);
		    	//Screen.SetBrightness(255);
		    //}
		    
		    
		   view.showData();
	    	
	    	
	}
	
	@Override
	protected void onPostExecute(Integer result)
	{
	}
	
	@Override
    protected void onCancelled() 
	{
    }
	
}
