package com.pranjal.discoveratmclustering;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by Pranjal on 5/19/16.
 */
public class MarkerItem implements ClusterItem{

    private final LatLng markerPosition;                            //Position of the new marker
                                                                    //  that will be clustered
    public String title;                                            //Title associated with the marker

    public MarkerItem(double lat, double lon, String locationDetails){
        markerPosition = new LatLng(lat, lon);
        title= locationDetails;
    }


    //GETTERS
    @Override
    public LatLng getPosition(){
        return markerPosition;
    }

    public String getTitle(){
        return title;
    }
}
