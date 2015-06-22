package dgruenberger.tweberschlaeger.htlgrieskirchen.at.helloworldv2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by dgruenberger on 19.06.2015.
 */
public class CustomDrawableView extends View {

    public CustomDrawableView(Context context){
        super(context);

    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.rgb(178, 228, 92));
        int x = getWidth();
        int y = getHeight();
        int radius = 50;
        canvas.drawCircle(x/4, y/4, radius, paint);
    }
}