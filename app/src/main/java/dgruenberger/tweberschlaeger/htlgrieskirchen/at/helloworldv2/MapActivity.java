package dgruenberger.tweberschlaeger.htlgrieskirchen.at.helloworldv2;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import org.osmdroid.DefaultResourceProxyImpl;
import org.osmdroid.api.IMapController;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dgruenberger on 19.06.2015.
 */
public class MapActivity extends ActionBarActivity implements LocationListener {

    protected MapView mapView;
    protected IMapController mapController;

    private static LocationManager locMan = null;
    Location actualLocation;
    private GeoPoint shownPoint;
    private int zoom = 16;

    private String TOAST_ACTUAL_LOCATION = "Sie befinden sich hier!";

    private String NAME = "Max";
    private String FULL_NAME = "Max Mustermann";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mapactivity);
        mapView = (MapView) findViewById(R.id.map);
        locMan = (LocationManager) getSystemService(LOCATION_SERVICE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void addMarkers()
    {
        OverlayItem pointWithIcon = new OverlayItem(NAME, FULL_NAME, shownPoint);
        Drawable marker = getResources().getDrawable(R.drawable.marker_green_3);
        pointWithIcon.setMarker(marker);
        OverlayItem[] items = new OverlayItem[]{
                pointWithIcon
        };
        ItemizedOverlayWithFocus<OverlayItem> itemList = new ItemizedOverlayWithFocus<OverlayItem>(
                Arrays.asList(items),
                null,
                new DefaultResourceProxyImpl(this)
        );
        itemList.setFocusItemsOnTap(true);
        mapView.getOverlays().add(itemList);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        onBackPressed();
        return true;
    }

    private void addOverlay() {
        MapOverlay overlay = new MapOverlay(this,shownPoint);
        List<Overlay> overlays = mapView.getOverlays();
        overlays.add(overlay);
        mapView.postInvalidate();
    }

    private void initMap()
    {
        mapController = mapView.getController();
        mapView.setMultiTouchControls(false);
        mapView.setBuiltInZoomControls(true);
        mapView.setUseDataConnection(true);

        mapController.setZoom(zoom);
        mapController.setCenter(shownPoint);

        Toast.makeText(this, TOAST_ACTUAL_LOCATION, Toast.LENGTH_LONG).show();
    }

    private GeoPoint getPointFromLocation (Location loc)
    {
        double latitude = loc.getLatitude();
        double longitude = loc.getLongitude();
        return new GeoPoint(latitude,longitude);
    }

    @Override
    protected void onResume() {
        super.onResume();
        locMan.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        locMan.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        if(location==null) return;
        actualLocation = location;
        Log.d("Seas", actualLocation.getLatitude() + "");
        shownPoint = getPointFromLocation(actualLocation);
        initMap();
        addOverlay();
        addMarkers();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}