package com.mjas.myweatherapp;

public class Day {
    private double mHighTemperature;
    private double mLowTemperature;
    private String mTimeZone;


    public double getHighTemperature() {
        return (int)Math.round(mHighTemperature);
    }

    public void setHighTemperature(double highTemperature) {
        mHighTemperature = highTemperature;
    }

    public double getLowTemperature() {
        return (int)Math.round(mLowTemperature);
    }

    public void setLowTemperature(double lowTemperature) {
        mLowTemperature = lowTemperature;
    }

    public String getTimeZone() {
        return mTimeZone;
    }

    public void setTimeZone(String timeZone) {
        mTimeZone = timeZone;
    }
    
    
    
}

