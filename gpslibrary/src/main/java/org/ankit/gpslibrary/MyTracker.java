package org.ankit.gpslibrary;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.util.List;
import java.util.Locale;

public class MyTracker implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private ADLocationListener locListener;
    Context ctx;
    public MyTracker(Context ctx, ADLocationListener locListener){
        this.ctx=ctx;
        this.locListener=locListener;
    }
    public void track(){


        mGoogleApiClient = new GoogleApiClient.Builder(ctx)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mGoogleApiClient.connect();

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds


    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i("My Tracker", "Location services connected!.");

        if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        fetchLocation(location);

        if (location == null) {
            if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(ctx, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }

    private void fetchLocation(Location location) {
        List<Address> addresses;
        Geocoder geocoder = new Geocoder(ctx, Locale.getDefault());
        try {
            ADLocation adLocation=new ADLocation();
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            adLocation.address= addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            adLocation.city=addresses.get(0).getLocality();
            adLocation.state= addresses.get(0).getAdminArea();
            adLocation.country = addresses.get(0).getCountryName();
            adLocation.pincode= addresses.get(0).getPostalCode();
            adLocation.lat=location.getLatitude();
            adLocation.longi=location.getLongitude();

            locListener.whereIAM(adLocation);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("My Tracker", "Location services suspended. Please reconnect.");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i("My Tracker", "Location service connection failed.");
    }

    @Override
    public void onLocationChanged(Location location) {
        fetchLocation(location);
    }

    public interface ADLocationListener{
        void whereIAM(ADLocation loc);
    }
}



