/*package com.mjas.myweatherapp;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.graphics.Typeface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;




@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public class WeatherFragment extends Fragment  {
	
	
    
	Typeface weatherFont;
     
    TextView cityField;
    TextView updatedField;
    TextView detailsField;
    TextView currentTemperatureField;
    TextView weatherIcon;
     
    Handler handler;
    
   
  	  
 
    public WeatherFragment(Location location){   
        handler = new Handler();
    }
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  
        weatherFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/weather.ttf");     
        //updateWeatherData(new LocationPreference(getActivity()).getCity());
        updateWeatherData(new LocationPreference(getActivity()).getCity());  
        
        final double longitude;
		final double latitude;
		
	 LocationManager locationManager;
	 
	// Get the location manager
	    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    // Define the criteria how to select the locatioin provider -> use
	    // default
	    Criteria criteria = new Criteria();
	    String provider = locationManager.getBestProvider(criteria, false);
	    Location location = locationManager.getLastKnownLocation(provider);

	    if (location != null) {
	    	
	          latitude = location.getLatitude();
	          longitude = location.getLongitude();
	      
	    } 
	    else{
	    	 latitude = 45.5233;
	         longitude = -122.6762;
	    }
	   
    }
    
    

	

	private void updateWeatherData(final String city){
    new Thread(){
        public void run(){
            final JSONObject json = WeatherFetch.getJSON(getActivity(), city);
            if(json == null){
                handler.post(new Runnable(){
                    public void run(){
                        Toast.makeText(getActivity(), 
                                getActivity().getString(R.string.place_not_found), 
                                Toast.LENGTH_LONG).show(); 
                    }
                });
            } else {
                handler.post(new Runnable(){
                    public void run(){
                        renderWeather(json);
                    }
				
                });
            }               
        }
    }.start();
}
    private void renderWeather(JSONObject json){
        try {
            cityField.setText(json.getString("name").toUpperCase(Locale.US) + 
                    ", " + 
                    json.getJSONObject("sys").getString("country"));
             
            JSONObject details = json.getJSONArray("weather").getJSONObject(0);
            JSONObject main = json.getJSONObject("main");
            detailsField.setText(
                    details.getString("description").toUpperCase(Locale.US) +
                    "\n" + "Humidity: " + main.getString("humidity") + "%" +
                    "\n" + "Pressure: " + main.getString("pressure") + " hPa");
             
            currentTemperatureField.setText(
                        String.format("%.2f", main.getDouble("temp"))+ " ℃");
     
            DateFormat df = DateFormat.getDateTimeInstance();
            String updatedOn = df.format(new Date(json.getLong("dt")*1000));
            updatedField.setText("Last update: " + updatedOn);
     
            setWeatherIcon(details.getInt("id"),
                    json.getJSONObject("sys").getLong("sunrise") * 1000,
                    json.getJSONObject("sys").getLong("sunset") * 1000);
             
        }catch(Exception e){
            Log.e("SimpleWeather", "One or more fields not found in the JSON data");
        }
    }
    private void setWeatherIcon(int actualId, long sunrise, long sunset){
        int id = actualId / 100;
        String icon = "";
        if(actualId == 800){
            long currentTime = new Date().getTime();
            if(currentTime>=sunrise && currentTime<sunset) {
                icon = getActivity().getString(R.string.weather_sunny);
            } else {
                icon = getActivity().getString(R.string.weather_clear_night);
            }
        } else {
            switch(id) {
            case 2 : icon = getActivity().getString(R.string.weather_thunder);
                     break;         
            case 3 : icon = getActivity().getString(R.string.weather_drizzle);
                     break;     
            case 7 : icon = getActivity().getString(R.string.weather_foggy);
                     break;
            case 8 : icon = getActivity().getString(R.string.weather_cloudy);
                     break;
            case 6 : icon = getActivity().getString(R.string.weather_snowy);
                     break;
            case 5 : icon = getActivity().getString(R.string.weather_rainy);
                     break;
            }
        }
        weatherIcon.setText(icon);
    }
    
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.weather_fragment, container, false);
        cityField = (TextView)rootView.findViewById(R.id.city_field);
        updatedField = (TextView)rootView.findViewById(R.id.updated_field);
        detailsField = (TextView)rootView.findViewById(R.id.details_field);
        currentTemperatureField = (TextView)rootView.findViewById(R.id.current_temperature_field);
        weatherIcon = (TextView)rootView.findViewById(R.id.weather_icon);
         
        //latituteField = (TextView)rootView.findViewById(R.id.TextView02);
	    //longitudeField = (TextView) rootView.findViewById(R.id.TextView04);
        weatherIcon.setTypeface(weatherFont);
        return rootView; 
    }
	
	public void changeCity(String city){
	    updateWeatherData(city);
	}

	
}*/