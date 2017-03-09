package com.example.hwl.androidyishu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by monlonwong on 2017/2/28.
 */

public class MyTextView1 extends AppCompatTextView {

    private Paint mPaint;

    private String mText;
    private int mTextColor;
    private int mTextSize;
    private Rect mBound;




    public MyTextView1(Context context) {
        super(context);
    }

    public MyTextView1(Context context, AttributeSet attrs) {

        this(context, attrs, 0);

    }

    public MyTextView1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        mTextColor = Color.BLUE;
//        mTextSize = 10;
//        mText = "Custom Text";
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.MyTextView1);
        mTextColor = typedArray.getColor(R.styleable.MyTextView1_textColor, Color.BLACK);
        mTextSize = typedArray.getDimensionPixelSize(R.styleable.MyTextView1_textSize, 100);
        mText = typedArray.getString(R.styleable.MyTextView1_text);
        typedArray.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);

        //text bound
        mBound = new Rect();
        mPaint.getTextBounds(mText, 0, mText.length(), mBound);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        canvas.drawText(mText, (getWidth() - mBound.width()) / 2,
                getHeight() / 2, mPaint);


    }
}
