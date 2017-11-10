package org.ankit.gpslibrary;

/**
 * Created by ankit dubey on 20-06-2017.
 */

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.test.mock.MockPackageManager;
import android.util.Log;


import java.util.List;
import java.util.Locale;

public class MyTracker extends Service implements LocationListener {
        private static final int REQUEST_CODE_PERMISSION = 2;
        private String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 100;
        private static long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;
        boolean canGetLocation;
        boolean isGPSEnabled;
        boolean isNetworkEnabled;
        double latitude;
        Location location;
        protected LocationManager locationManager;
        double longitude;
        private Context mContext;
        public String cityName,address,countryCode,zip,countryName,state,macAddress,ipAddress;




    public MyTracker(Context ctx) {
        mContext=ctx;
            isGPSEnabled = false;
            isNetworkEnabled = false;
            canGetLocation = false;
        getLocation();
        getAddress();
    }


    public Location getLocation() {
        try {

            locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);

            // getting GPS status
            isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!isGPSEnabled && !isNetworkEnabled) {
                // no network provider is enabled
            } else {
                this.canGetLocation = true;
                // First get location from Network Provider
                if (isNetworkEnabled) {
                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER,
                            MIN_TIME_BW_UPDATES,
                            MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                    Log.d("Network", "Network");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }

                // if GPS Enabled get lat/long using GPS Services
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);

                        Log.d("GPS Enabled", "GPS Enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);

                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }
    public double getLatitude() {
        if (this.location != null) {
            this.latitude = this.location.getLatitude();
        }
        return this.latitude;
    }

    public double getLongitude() {
        if (this.location != null) {
            this.longitude = this.location.getLongitude();
        }
        return this.longitude;
    }

    public boolean canGetLocation() {
        return this.canGetLocation;
    }

    public void onLocationChanged(Location location) {
    }

    public void onProviderDisabled(String provider) {
    }

    public void onProviderEnabled(String provider) {
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    public IBinder onBind(Intent arg0) {
        return null;
    }

    public void onCreate() {
        Log.d("OnCreate", "Service OnCreate Start");
    }

    private void getAddress(){
        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);

            address = addresses.get(0).getAddressLine(0);
            zip=addresses.get(0).getPostalCode();
            cityName=addresses.get(0).getLocality();
            countryCode=addresses.get(0).getCountryCode();
            countryName=addresses.get(0).getCountryName();
            state=addresses.get(0).getAdminArea();
            macAddress=IPTracker.getMACAddress("wlan0");
            ipAddress=IPTracker.getIPAddress(true);




        }catch(Exception e){
            System.out.println("------------->Exception");
            e.printStackTrace();
        }

    }


}
