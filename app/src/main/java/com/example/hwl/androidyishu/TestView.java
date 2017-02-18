package com.example.hwl.androidyishu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hwl on 2017/2/17.
 */

public class TestView extends View {

    private Paint paint;


    public TestView(Context context) {
        super(context);
        init();
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.YELLOW);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLUE);


        Path path = new Path();
        path.moveTo(50, 500);
        path.lineTo(550, 600);
        path.lineTo(50, 900);
        path.lineTo(650, 1200);
        path.lineTo(1000, 200);

        canvas.drawPath(path, paint);

        canvas.drawTextOnPath("This is a test我的小测试", path, 800, 150, paint);


        Paint mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        canvas.drawRect(new RectF(50, 50, 200, 200), mPaint);

        canvas.translate(200, 100);
        canvas.drawRect(new Rect(-100, -100, 0, 0), new Paint());//缩放了
        canvas.scale(0.6f, 0.6f);
        canvas.drawRect(new Rect(0, 0, 100, 100), new Paint());

        canvas.rotate(-30);
        canvas.drawRect(new Rect(0, 100, 200, 300), new Paint());

    }
}
