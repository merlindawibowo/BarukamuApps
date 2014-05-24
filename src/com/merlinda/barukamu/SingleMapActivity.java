package com.merlinda.barukamu;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.ActionBar;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.MapFragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.Toast;
/**
 * Created by Merlinda Wibowo on 02/04/14.
 */
public class SingleMapActivity extends FragmentActivity {
	private GoogleMap map;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.peta);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setSubtitle("Tampilan Peta");
        
        String lng = getIntent().getExtras().getString("LNG");
        String lat = getIntent().getExtras().getString("LAT");
        String nama = getIntent().getExtras().getString("NAMA");
        String alamat = getIntent().getExtras().getString("ALAMAT");
        
        LatLng latLng = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
        
        //my location
        /* Use the LocationManager class to obtain GPS locations */
//        LocationManager mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
//
//        LocationListener mlocListener = new MyLocationListener();
//        mlocManager.requestLocationUpdates( LocationManager.GPS_PROVIDER, 0, 0, (android.location.LocationListener) mlocListener);
        
        if (map == null) {
        	map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        	map.setMyLocationEnabled(true);
		} 
        map.addMarker(new MarkerOptions().position(latLng).title(nama).snippet(alamat));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
        map.animateCamera(CameraUpdateFactory.zoomTo(14), 2000, null);
      
    }  
    
    /* Class My Location Listener */
    public class MyLocationListener implements LocationListener
    {

      @Override
      public void onLocationChanged(Location loc)
      {

        loc.getLatitude();
        loc.getLongitude();

        String Text = "My current location is: " +
        "Latitud = " + loc.getLatitude() +
        "Longitud = " + loc.getLongitude();

        Toast.makeText( getApplicationContext(), Text, Toast.LENGTH_SHORT).show();
      }

      public void onProviderDisabled(String provider)
      {
        Toast.makeText( getApplicationContext(), "Gps Disabled", Toast.LENGTH_SHORT ).show();
      }

      public void onProviderEnabled(String provider)
      {
        Toast.makeText( getApplicationContext(), "Gps Enabled", Toast.LENGTH_SHORT).show();
      }

      public void onStatusChanged(String provider, int status, Bundle extras)
      {

      }
    }

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
            	NavUtils.navigateUpFromSameTask(this);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
