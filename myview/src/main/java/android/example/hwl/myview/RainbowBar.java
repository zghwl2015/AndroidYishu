package android.example.hwl.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by monlonwong on 2017/3/4.
 */

public class RainbowBar extends View {

    int barColor = Color.parseColor("#1E88E5");
    int wSpace = Utils.dip2px(getContext(), 80);
    int vSpace = Utils.dip2px(getContext(), 4);
    int space = Utils.dip2px(getContext(), 10);

    float startX = 0;
    float delta = 10f;
    Paint mPaint;

    public RainbowBar(Context context) {
        super(context);
    }

    public RainbowBar(Context context, AttributeSet attrs) {

        this(context, attrs, 0);
    }

    public RainbowBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RainbowBar,
                0, 0);
        wSpace = typedArray.getDimensionPixelSize(R.styleable.RainbowBar_rainbowbar_hspace, wSpace);
        vSpace = typedArray.getDimensionPixelSize(R.styleable.RainbowBar_rainbowbar_vspace,
                vSpace);
        barColor = typedArray.getColor(R.styleable.RainbowBar_rainbowbar_color, barColor);
        //recycle TypedArray
        typedArray.recycle();

        mPaint.setColor(barColor);
        mPaint.setStrokeWidth(vSpace);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //get screen width
        float screenWidth = this.getMeasuredWidth();
        if (startX >= screenWidth + (wSpace + space) - (screenWidth % (wSpace + space))){
            startX = 0;
        } else {
            startX += delta;
        }

        float start = startX;
        while (start < screenWidth){
            canvas.drawLine(start, 5, start + wSpace, 5, mPaint);
            start += (wSpace + vSpace);
        }

        start -= (wSpace + vSpace);

        while (start >= -wSpace){
            canvas.drawLine(start, 5, start + wSpace, 5, mPaint);
            start -= (wSpace + vSpace);
        }

        invalidate();
    }
}





