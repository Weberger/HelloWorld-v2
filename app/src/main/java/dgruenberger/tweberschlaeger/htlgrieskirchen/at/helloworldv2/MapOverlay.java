package dgruenberger.tweberschlaeger.htlgrieskirchen.at.helloworldv2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Overlay;

import java.util.ArrayList;

/**
 * Created by dgruenberger on 19.06.2015.
 */
public class MapOverlay extends Overlay {

    private Paint paintPoint = new Paint();
    Point p = new Point();
    ArrayList<GeoPoint> points = new ArrayList<GeoPoint>();
    private GeoPoint actualPoint;

    public MapOverlay(Context ctx, GeoPoint point) {
        super(ctx);
        paintPoint.setARGB(255, 255, 0, 0);
        paintPoint.setStyle(Paint.Style.FILL_AND_STROKE);
        actualPoint = point;

        initPoints();
    }

    private void initPoints() {
        points.add(actualPoint);
    }

    @Override
    protected void draw(Canvas canvas, MapView mapView, boolean b) {
        Path path = new Path();
        boolean isFirst = true;
        for (GeoPoint pos : points)
        {
            mapView.getProjection().toPixels(pos, p);
            canvas.drawCircle(p.x, p.y, 5, paintPoint);
        }
    }
}
