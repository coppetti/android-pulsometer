package com.matheus.pulsometro;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;


public class PulsometroView extends View {

    private static final Matrix matrix = new Matrix();
    private static final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private static Bitmap greenBitmap = null;
    private static Bitmap redBitmap = null;

    private static int parentWidth = 0;
    private static int parentHeight = 0;

    public PulsometroView(Context c, AttributeSet attr) {
        super(c, attr);

        greenBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.green_icon);
        redBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.red_icon);
    }

    public PulsometroView(Context c) {
        super(c);

        greenBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.green_icon);
        redBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.red_icon);
    }
    

    protected void onDraw(Canvas canvas) {
        if (canvas == null){
        	throw new NullPointerException();
        }

        Bitmap bitmap = null;
        //n detecta batimento
        if (MyVars.getCurrent() == MyVars.TYPE.beatOFF){
        	bitmap = greenBitmap;
        }
        //se detecta batimento
        else{
        	bitmap = redBitmap;
        }
        
        int horizontal_center = (parentWidth / 2) - (bitmap.getWidth() / 2);
        int vertical_center = (parentHeight / 2) - (bitmap.getHeight() / 2);

        matrix.reset();
        matrix.postTranslate(horizontal_center, vertical_center);
        canvas.drawBitmap(bitmap, matrix, paint);
    }
    
    protected void onMeasure(int width_size, int height_size) {
        super.onMeasure(width_size, height_size);

        parentWidth = MeasureSpec.getSize(width_size);
        parentHeight = MeasureSpec.getSize(height_size);
        setMeasuredDimension(parentWidth, parentHeight);
    }
}
