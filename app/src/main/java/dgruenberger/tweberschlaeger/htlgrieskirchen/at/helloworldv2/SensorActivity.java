package dgruenberger.tweberschlaeger.htlgrieskirchen.at.helloworldv2;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.drawable.ShapeDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Display;

/**
 * Created by dgruenberger on 19.06.2015.
 */
public class SensorActivity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    CustomDrawableView customView = null;
    ShapeDrawable shapeDrawable = new ShapeDrawable();
    public float xPos, xAcceleration, xVelocity = 0.0f;
    public float yPos, yAcceleration, yVelocity = 0.0f;
    public float xmax, ymax;
    public float frameTime = 0.666f;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        //Referenz auf Sensormanager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);

        customView = new CustomDrawableView(this);
        setContentView(customView);

        //Grenze des Bildschirms berechnen
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        int width = size.x;
        int height = size.y;
        xmax = width - 20;
        ymax = height - 20;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            xAcceleration = event.values[2];
            yAcceleration = event.values[1];
            updateBall();
        }
    }

    private void updateBall(){
        //Geschwindigkeit berechnen
        xVelocity += (xAcceleration * frameTime);
        yVelocity += (yAcceleration * frameTime);

        //Strecke berechnen die in der Zeit zurückgelegt wurde
        float xDistance = (xVelocity/2)*frameTime;
        float yDistance = (yVelocity/2)*frameTime;

        //Aus dem Sensor ausgelesener Wert = Gegenteil von benötigtem Wert, daher -= xDist/yDist
        xPos -= xDistance;
        yPos -= yDistance;

        //Kugel gerät somit nicht außerhalb des Bildschirms
        if(xPos > xmax){
            xPos = xmax;
        }else if(xPos < 0){
            xPos = 0;
        }
        if(xPos > ymax){
            yPos = ymax;
        } else if (yPos < 0){
            yPos = 0;
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        //Sensor wird wieder definiert
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause(){
        sensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
