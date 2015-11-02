package com.mjas.myweatherapp;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
 
import org.json.JSONObject;
 
import android.content.Context;
import android.util.Log;
 
public class WeatherFetch {
 
    
    public static JSONObject getJSON(String strAPI){
        try {
        	       	
        	InputStream inputStream = null;
        	JSONObject result = null;
        	
            URL url = new URL(String.format(strAPI));           
            HttpURLConnection urlConnection = 
                    (HttpURLConnection)url.openConnection();
             
           
       	   
             
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()));
             
            StringBuffer json = new StringBuffer(1024);
            String tmp="";
            while((tmp=reader.readLine())!=null)
                json.append(tmp).append("\n");
            reader.close();
             
            JSONObject data = new JSONObject(json.toString());
            
           
             
            return data;
            
          
        }catch(Exception e){
            return null;
        }
    }

	private static void readStream(InputStream in) {
		// TODO Auto-generated method stub
		
	} 
	
	 
}
