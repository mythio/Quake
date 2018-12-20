package com.example.mythio.quake;

public class Earthquake {

    private String mMagnitude;
    private String mLocationOffset;
    private String mLocation;
    private String mDate;
    private String mTime;
    private String mUrl;

    public Earthquake(String mMagnitude, String mLocationOffset, String mLocation, String mDate, String mTime, String mUrl) {
        this.mMagnitude = mMagnitude;
        this.mLocationOffset = mLocationOffset;
        this.mLocation = mLocation;
        this.mDate = mDate;
        this.mTime = mTime;
        this.mUrl = mUrl;
    }

    public String getmMagnitude() {
        return mMagnitude;
    }

    public String getmLocationOffset() {
        return mLocationOffset;
    }

    public String getmLocation() {
        return mLocation;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmTime() {
        return mTime;
    }

    public String getmUrl() {
        return mUrl;
    }
}
