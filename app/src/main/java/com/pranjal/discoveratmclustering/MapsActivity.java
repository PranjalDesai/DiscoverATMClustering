package com.pranjal.discoveratmclustering;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.MarkerManager;
import com.google.maps.android.clustering.ClusterManager;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ClusterManager<MarkerItem> mClusterManager;                 //manages the clusters
    LatLng discover = new LatLng(42.1567620,-87.8901100);               //using as current location
    LatLng markedPlaces[] = new LatLng[10];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Button x= (Button) findViewById(R.id.button);
        //Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveCamera(0);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setUpCluster();                                               //setting Up Clusters
        /*try{
            Thread.sleep(5000);
        }catch (Exception e)
        {

        }*/
        //moveCamera(0);
    }

    private void setUpCluster() {


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(discover, 10));//Position the map.
        mClusterManager = new ClusterManager<MarkerItem>(this, mMap); //cluster manager of MarkerItems(different lat longs)

        //click changes on a cluster
        mMap.setOnCameraChangeListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);


        //allows MarkerOptions on a Cluster by creating a render before anything is being added
        mClusterManager.setRenderer(new MarkerDetails(getApplicationContext(),mMap,mClusterManager));


        //FOR TESTING
        //Add cluster items (markers) to the cluster manager.
        addItems();
    }


    //Strictly for Testing
    private void addItems() {

        double lat = 42.1567620;
        double lng = -87.8901100;
        String title;

        //creating fake Markers with fake titles
        for (int i = 0; i < 10; i++) {
            double offset = i / 700d;
            lat = lat + offset;
            lng = lng + offset;
            title= Integer.toString(i);
            MarkerItem offsetItem = new MarkerItem(lat, lng, title);
            markedPlaces[i]= new LatLng(lat,lng);
            mClusterManager.addItem(offsetItem);
        }
    }

    public void moveCamera(final int i){

        if(i<10)
        {
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(markedPlaces[i])
                    .zoom(15)
                    .build();
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition), 3000, new GoogleMap.CancelableCallback(){
                @Override
                public void onFinish(){
                    moveCamera(i+1);
                }

                @Override
                public void onCancel(){

                }
            });

        }
    }



}