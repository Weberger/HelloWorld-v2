package dgruenberger.tweberschlaeger.htlgrieskirchen.at.helloworldv2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by dgruenberger on 19.06.2015.
 */
public class Activity_Startscreen extends ActionBarActivity {
    Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_startscreen);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            public void run() {

                Intent startScreenIntent = new Intent(getApplicationContext(), MainActivity.class);
                startScreenIntent.putExtra("DONT_OPEN_STARTSCREEN_AGAIN",true);
                startActivity(startScreenIntent);
            }

        }, 5000);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_startscreen, menu);
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

}