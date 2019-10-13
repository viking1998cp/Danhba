package vn.lachongmedia.appnv.customcamera;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

/**
 * Created by HoTung on 1/7/2017.
 */

public class MyView extends View {
    private final Paint drawingPaint;

    private boolean haveTouch;
    private Rect touchArea;

    public MyView(Context context) {
        super(context);
        drawingPaint = new Paint();
        drawingPaint.setColor(Color.GREEN);
        drawingPaint.setStyle(Paint.Style.STROKE);
        drawingPaint.setStrokeWidth(2);
        haveTouch = false;
    }

    public void setHaveTouch(Rect tArea) {
        haveTouch = true;
        touchArea = tArea;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        canvas.drawColor(Color.TRANSPARENT);

        if (haveTouch) {
            drawingPaint.setColor(Color.GREEN);
            canvas.drawRect(
                    touchArea.left, touchArea.top, touchArea.right, touchArea.bottom,
                    drawingPaint);
        }
    }
}
