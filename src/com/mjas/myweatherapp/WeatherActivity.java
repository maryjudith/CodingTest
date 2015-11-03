/* Developed by Mary Sukumar  for Continum recruitment*/

package com.mjas.myweatherapp;

import android.support.v7.app.AppCompatActivity;


import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Sampler.Value;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.util.Log;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class WeatherActivity extends AppCompatActivity {

	 public static final String TAG = WeatherActivity.class.getSimpleName();

	    private CurrentWeather mCurrentWeather;
	    private Day[] mDailyWeather;

		private TextView mTimeLabel;

		//private TextView mTempuratureLabelH;
		private TextView mTempuratureLabel;

		private TextView mHumidityValue;

		private TextView mPrecipValue;

		private TextView mSummaryLabel;

		private ImageView mIconImageView;

		private TextView mWindValue;

		private ImageView mRefreshImageView;

		private ProgressBar mProgressBar;

		private TextView mHighTemperature;

		private TextView mLowTemperature;
		private TextView mLocation;

		
		
		
		


	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_weather);
	       // ButterKnife.inject(this);  //allows us to use a shortcut to link layout elements
	        final double longitude;
			final double latitude;
			
	        mTimeLabel = (TextView)findViewById(R.id.timeLabel);
	       // mTempuratureLabelH = (TextView) findViewById(R.id.temperatureLabelH);
	        mTempuratureLabel = (TextView) findViewById(R.id.temperatureLabel);
	        mHumidityValue= (TextView) findViewById(R.id.humidityValue);
	        mPrecipValue= (TextView) findViewById(R.id.precipValue);
	        mSummaryLabel= (TextView) findViewById(R.id.summaryLabel);
	        mIconImageView = (ImageView) findViewById(R.id.imageID);
	        
	       // mIconImageView6 = (ImageView) findViewById(R.id.imgthunder);
	        
	        
	        mWindValue= (TextView) findViewById(R.id.windValue);
	      
	        mHighTemperature= (TextView) findViewById(R.id.highTemperature);
	        mLowTemperature= (TextView) findViewById(R.id.lowTemperature);
	        mLocation=(TextView) findViewById(R.id.locationLabel);
	        LocationManager locationManager;
	        Log.d(TAG, "Checking!");
	        
	     
	     
	        
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
		    	 latitude = -33.8650;
		         longitude = 151.2094;
		         
		    }
		    	
		    
	     

	        getForecast(latitude, longitude);
	        Log.d(TAG, "Main UI code is running!");

	    }

	    private void getForecast  (double latitude, double longitude) {
	        String apiKey = "5155bcaef2541fb458365e160c45d1b0";

	        final String forecastURL = "https://api.forecast.io/forecast/" + apiKey + "/" + latitude + "," + longitude;
	        
	        if (isNetworkAvailable()) {
	        	final Handler handler = new Handler();
	           // toggleRefresh();
	        	//updateWeatherData(forecastURL);
	        	new Thread(){
	    	        @SuppressWarnings("null")
	    	        @Override
					public void run(){
	    	            final JSONObject json = WeatherFetch.getJSON(forecastURL);
	    	           
						if(json == null){
							 
	    	                handler.post(new Runnable(){
	    	                	@Override
	    	                    public void run(){
	    	                    	 Log.v(TAG, "Place Not Found");
	    	                                
	    	                       
	    	                    }
	    	                });
	    	            } else {
	    	            	
	    	                
	    	            	 try {
	    	            	 mCurrentWeather = currentWeather(json);
	                            runOnUiThread(new Runnable() {
	                                @Override
	                                public void run() {
	                                    updateDisplay1();
	                                }

	                            });

	                           
									mDailyWeather = dailyForecast(json);
								
	                            runOnUiThread(new Runnable() {
	                                @Override
	                                public void run() {
	                                    updateDisplay2();
	                                }

	                            });
	    	            	 } catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
	    	            	
	    	            }               
	    	        }
	    	    }.start();
	        	
	        }	
	    }
	           
	               

	        	
	    
	  
	    
	    
    	private CurrentWeather currentWeather(JSONObject forecast){
    		
    		 CurrentWeather currentWeather = new CurrentWeather();
            try {
            	
            	double fahrenheit;
    	        String timezone = forecast.getString("timezone");
    	        Log.i(TAG, "From JSON: " + timezone);

    	        JSONObject currently = forecast.getJSONObject("currently");

    	        

    	        currentWeather.setHumidity(currently.getDouble("humidity"));
    	        currentWeather.setTime(currently.getLong("time"));
    	        
    	        currentWeather.setIcon("Sunny");
    	        currentWeather.setPrecipChance(currently.getDouble("precipProbability"));;
    	        currentWeather.setSummary(currently.getString("summary"));
    	       
    	        fahrenheit=(currently.getDouble("temperature")-32)*5/9;
    	        currentWeather.setTemperatureH(fahrenheit);
    	        currentWeather.setTemperature(fahrenheit);
    	        //currentWeather.setTemperatureL(currently.getDouble("temperature"));
    	        currentWeather.setTimeZone(timezone);
    	        currentWeather.setWindSpeed(currently.getDouble("windSpeed"));
    	        currentWeather.setWindBearing(currently.getInt("windBearing"));

    	           	   
    	       
    	        
                 
            }catch(Exception e){
                Log.e("SimpleWeather", "One or more fields not found in the JSON data");
            }
			return currentWeather;
        }
    	
    	
	   

	     private void updateDisplay1() {
	    	 
	    	
	    	 
	    	//DateFormat df = DateFormat.getDateTimeInstance();
	    	 String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

	    	 DateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd");
	    	 dateFormatter.setLenient(false);
	    	 Date today = new Date();
	    	 String dt = dateFormatter.format(today);
	    	String strSummary="";
	    	
	    	mLocation.setText(mCurrentWeather.getTimeZone()); 
	    	
	    	//mTimeLabel.setText(mCurrentWeather.getTime()+"");
	    	mTimeLabel.setText(currentDateTimeString);
	    	//mTimeLabel.setText(dt);
	        //mTempuratureLabelH.setText(mCurrentWeather.getTemperatureH() + "C");
	    	
	        mTempuratureLabel.setText(mCurrentWeather.getTemperature() + "℃");
	        mHumidityValue.setText( mCurrentWeather.getHumidity() + "%");
	        mPrecipValue.setText(mCurrentWeather.getPrecipChance() + "%");
	        mWindValue.setText(mCurrentWeather.getWindSpeed()+ " MPH");
	       
	        mSummaryLabel.setText(mCurrentWeather.getSummary());
	      
	       strSummary=mCurrentWeather.getSummary().toString();
	        
	        if (strSummary.equals("Rain"))
	        {
	        	mIconImageView.setImageResource(R.drawable.rainy);
	        }
	        else
	        	 if (strSummary.equals("Partly Cloudy"))
	 	        {
	        		 mIconImageView.setImageResource(R.drawable.sunandcloudy);
	 	        }
	        	 else
	        		 if (strSummary.equals("Heavy rain"))
	     	        {
	        			 mIconImageView.setImageResource(R.drawable.thunder);
	     	        }
	        		 else
	        			 if  (strSummary.equals("Drizzle"))
	 	     	        {
	 	        			 mIconImageView.setImageResource(R.drawable.snowy);
	 	     	        }
	        			 else
	        			 mIconImageView.setImageResource(R.drawable.cloudy);
	      
	    }
	     
	     
	     private void updateDisplay2() {
		    	//DateFormat df = DateFormat.getDateTimeInstance();
		    	 
		        
		        mHighTemperature.setText(mDailyWeather[0].getHighTemperature() + "");
		        mLowTemperature.setText(mDailyWeather[0].getLowTemperature() + "");
		       

		        //Drawable drawable = getResources().getDrawable(mCurrentWeather.getIconId());
		       // mIconImageView.setImageDrawable(drawable);
		    }


	    private Day[] dailyForecast(JSONObject forecast) throws JSONException {
	       
	        String timezone = forecast.getString("timezone");

	        JSONObject daily = forecast.getJSONObject("daily");
	        JSONArray data = forecast.getJSONArray("data");

	        Day[] days = new Day[data.length()];

	        

	        for (int i=0; i < data.length(); i++) {
	            JSONObject jsonDay = data.getJSONObject(i);
	            Day day = new Day();
	            day.setTimeZone(timezone);
	            day.setHighTemperature(jsonDay.getDouble("temperatureMax"));
	            day.setLowTemperature(jsonDay.getDouble("temperatureMin"));

	            days[i] = day;

	            Log.d(TAG, String.valueOf(day.getHighTemperature() + " degrees F"));
	            Log.d(TAG, String.valueOf(day.getLowTemperature() + " degrees F"));
	        }
	        return days;
	    }

	    private boolean isNetworkAvailable() {
	        ConnectivityManager manager = (ConnectivityManager)
	                getSystemService(Context.CONNECTIVITY_SERVICE);
	        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
	        boolean isAvailable = false;
	        if(networkInfo != null && networkInfo.isConnected()){
	            isAvailable = true;
	        }
	        else{
	            Toast.makeText(this, getString(R.string.hello_world),
	                    Toast.LENGTH_LONG).show();
	        }
	        return isAvailable;
	    }

	   
	    private void alertUserAboutError() {
	        AlertDialogFragment dialog = new AlertDialogFragment();
	       // dialog.show(getFragmentManager(),"error_dialog");
	    }
	     }
