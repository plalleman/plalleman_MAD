package com.example.paigealleman.lab7;
//lab7 to update a map with the users location as they move
//thanks to Aileen's tutorial for the required code

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

    //variables to store needed values (location, etc.)
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Location mCurrentLocation;
    private Marker mCurrentLocationMarker;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        buildGoogleApiClient();
        checkLocationPermission();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //set the map type
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        //check for permission
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            mMap.setMyLocationEnabled(true);
        }
    }

    private synchronized void buildGoogleApiClient() {
        //The GoogleApiClient class is the main entry point for integrating with Google Play Services
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this) //connection callbacks are called when the client is connected or disconnected
                .addOnConnectionFailedListener(this) //handles failed attempt of connect client to service
                .addApi(LocationServices.API) //adds the Google Play Service LocationServices API
                .build(); //builds the GoogleApiClient object
        mGoogleApiClient.connect(); //connect client
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);//active location updates interval in milliseconds, so this is one second
        mLocationRequest.setFastestInterval(1000); //fastest interval for location updates
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        //permission check - use the Fused Location API
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        //set the location variable to hold the value of the location that was just passed to us in this function
        mCurrentLocation = location;

        //make an object that will store the location coordinates in latitude and longitude
        LatLng latlng = new LatLng(mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude());

        //marker placement
        if(mCurrentLocationMarker == null){
            //if there isn't one we need to make one
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latlng);
            markerOptions.title("You are here!");
            //put it in the current locaton
            mCurrentLocationMarker = mMap.addMarker(markerOptions);
        }
        else{
            //a marker already exists so we need to change its location
            mCurrentLocationMarker.setPosition(latlng);
        }

        //move camera to be over the marker
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        //test toast for Paige - not necessary since the user could see the dot moving but useful for testing especially
        //using the emulator since you have to send coordinates manually
        Toast.makeText(this, "Location updated", Toast.LENGTH_LONG).show();


    }

    public void checkLocationPermission(){
        //check for permission
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                //no permissions why??? Demand answers from the user
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
                //returns false if the user selected do not ask again
            } else {
                //no explanation needed, request permissions
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled the result arrays are empty
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // Permission was granted
                    if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                        Toast.makeText(this, "permission granted", Toast.LENGTH_LONG).show();
                    }
                } else { // Permission denied
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
