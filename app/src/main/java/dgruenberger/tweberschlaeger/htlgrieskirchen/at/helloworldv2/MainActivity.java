package dgruenberger.tweberschlaeger.htlgrieskirchen.at.helloworldv2;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        /*
        //Öffnen des Intro-Logos
        Intent intentMainActivity = getIntent();
        Bundle extras = intentMainActivity.getExtras();

        if(extras==null || !extras.getBoolean("DONT_OPEN_STARTSCREEN_AGAIN"))
        {
            Intent startScreenIntent = new Intent(this, Activity_Startscreen.class);
            startActivity(startScreenIntent);
        }
        */

    }

    //Das Zurückgelangen zur Startscreen-Activity wird verhindert.
    @Override
    public void onBackPressed() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void btnGPSClicked(final View source)
    {
        Intent intentGPS = new Intent(this, MapActivity.class);
        startActivity(intentGPS);
    }

    public void btnNotificationClicked(final View v){
        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Hello World v2 l�uft")
                .setContentText("Die App Hello World v2 l�uft und kann bedient werden.");

        Notification notification = nBuilder.build();
        NotificationManager manager = (NotificationManager)this.getSystemService(NOTIFICATION_SERVICE);
        manager.notify(8, notification);
    }

    public void btnDialogClicked (final View v){
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        Date aktuelleZeit = new Date(System.currentTimeMillis());
        String zeit = simpleDate.format(aktuelleZeit);
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(zeit);
        dialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    public void btnSensorClicked(final View v){
        Intent intent = new Intent(this, SensorActivity.class);
        startActivity(intent);

    }
}