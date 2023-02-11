package com.zerophi.gestionvie.help;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;

import android.os.Bundle;
import android.widget.RadioGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SupportErrorDialogFragment;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.zerophi.gestionvie.R;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

public class basicmapactivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener, OnMapReadyCallback {

    private SupportMapFragment mMapFragment;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basicmapactivity);


        //Verify Play Services is active and up-to-date
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        switch (resultCode) {
            case ConnectionResult.SUCCESS:
                break;
            default:
                showPlayServicesError(resultCode);
                return;
        }

        mMapFragment = (SupportMapFragment)  getSupportFragmentManager().findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);
//Quickly see if our last-known user location is valid, and center
// the map around that point. If not, use a default location.
    LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        LatLng mapCenter;
        if(location != null) {
            mapCenter = new LatLng(location.getLatitude(),
                    location.getLongitude());
        } else {
//Use a default location
            mapCenter = new LatLng(37.4218, -122.0840);
        }
//Center and zoom the map simultaneously
        CameraUpdate newCamera =
                CameraUpdateFactory.newLatLngZoom(mapCenter, 13);
        mMap.moveCamera(newCamera);
// Wire up the map type selector UI
        RadioGroup typeSelect = (RadioGroup) findViewById(R.id.group_maptype);
        typeSelect.setOnCheckedChangeListener(this);
        typeSelect.check(R.id.type_normal);


    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMap != null) {
//Enable user location display on the map
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mMap.setMyLocationEnabled(true);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        super.onResume();
        if (mMap != null) {
//Disable user location when not visible
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mMap.setMyLocationEnabled(false);
        }
    }


    /*
     * When Play Services is missing or at the wrong version, the client
     * library will assist with a dialog to help the user update.
     */
    private void showPlayServicesError(int errorCode) {
// Get the error dialog from Google Play Services
        Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(
                errorCode,
                this,
                1000 /* RequestCode */);
// If Google Play Services can provide an error dialog
        if (errorDialog != null) {
// Create a new DialogFragment for the error dialog
            SupportErrorDialogFragment errorFragment =
                    SupportErrorDialogFragment.newInstance(errorDialog);
// Show the error dialog in the DialogFragment
            errorFragment.show(
                    getSupportFragmentManager(),
                    "Google Maps");
        }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.type_satellite:
//Show the satellite map view
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.type_normal:
            default:
//Show the normal map view
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }
}
