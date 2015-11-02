package com.mjas.myweatherapp;



public class CurrentWeather {
	
	 private double mHumidity;
	 private long mTime;
	 private String mIcon;
	 private int mIconId;
	 private String mSummary;
	 private double mTemperatureH;
	 private double mTemperatureL;
	 private String mTimeZone;
	 private double mWindSpeed;
	 private double mWindBearing;
	 private double mPrecipChance;
	 private String mLocation;
	 
	    public double getHumidity() {
	        return (int)Math.round(mHumidity);
	    }

	    public void setHumidity(double humidity) {
	    	mHumidity = humidity;
	    }

	    public long getTime(){
	    	return (int)Math.round(mTime);
	    }
	    public void setTime(long time) {
	    	mTime = time;
	    }
	    public int getIconId() {
	        return mIconId;
	    }
	    public void setIconId(int iconId) {
	        mIconId = iconId;
	    }
	    public String getIcon() {
	        return mIcon;
	    }

	    public void setIcon(String icon) {
	        mIcon = icon;
	    }
	    
	    public String getSummary() {
	        return mSummary;
	    }

	    public void setSummary(String summary) {
	        mSummary = summary;
	    }
	    
	    public double getTemperatureH() {
	        return (int)Math.round(mTemperatureH);
	    }

	    public void setTemperatureH(double temperature) {
	        mTemperatureH = temperature;
	    }

	    public double getTemperatureL() {
	        return (int)Math.round(mTemperatureL);
	    }

	    public void setTemperatureL(double temperature) {
	        mTemperatureL = temperature;
	    }
	    public String getTimeZone() {
	        return mTimeZone;
	    }

	    public void setTimeZone(String timeZone) {
	        mTimeZone = timeZone;
	    }
	    
	    public double getWindSpeed() {
	        return (int)Math.round(mWindSpeed);
	    }

	    public void setWindSpeed(double windSpeed) {
	        mWindSpeed = windSpeed;
	    }

	    public double getWindBearing() {
	        return (int)Math.round(mWindBearing);
	    }

	    public void setWindBearing(double windBearing) {
	        mWindBearing = windBearing;
	    }

	    public double getPrecipChance() {
	        return (int)Math.round(mPrecipChance);
	    }

	    public void setPrecipChance(double precipChance) {
	        mPrecipChance = precipChance;
	    }
	    /*public void getFormattedTime(){
	    	DateFormat df = DateFormat.getDateTimeInstance();
	    }*/
	    
	    public String getLocation() {
	        return mLocation;
	    }

	    public void setLocation(String location) {
	        mLocation = location;
	    }
	    
}


//currentWeather.setPrecipChance(currently.getDouble("precipProbability"));;

