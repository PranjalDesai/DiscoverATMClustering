package com.pranjal.discoveratmclustering;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

/**
 * Created by Pranjal on 5/19/16.
 */
public class MarkerDetails extends DefaultClusterRenderer<MarkerItem> {

    public MarkerDetails(Context context, GoogleMap map, ClusterManager<MarkerItem> clusterManager)
    {
        super(context, map, clusterManager);
    }

    //Rendering the Marker before Hand
    protected void onBeforeClusterItemRendered(MarkerItem item, MarkerOptions markerOptions) {
        markerOptions.title(item.getTitle());
        super.onBeforeClusterItemRendered(item, markerOptions);
    }
}
