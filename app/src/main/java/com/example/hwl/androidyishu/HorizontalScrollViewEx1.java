package com.example.hwl.androidyishu;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by hwl on 2017/2/16.
 */

public class HorizontalScrollViewEx1 extends ViewGroup {
    private static  final String TAG = "HorizontalScrollViewEx";
    //和测量、布局有关的变量
    private int mChildrenSize;
    private int mChildrenWidth;
    private int mChildrenIndex;

    //和滑动有关的变量
    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;
    //记录上次滑动的坐标
    private int mLastX = 0;
    private int mLastY = 0;
    //拦截事件中的坐标
    private int mLastXIntercept = 0;
    private int mLastYIntercept = 0;

    public HorizontalScrollViewEx1(Context context) {

        super(context);
        init();
    }

    public HorizontalScrollViewEx1(Context context, AttributeSet attrs) {

        super(context, attrs);
        init();
    }

    public HorizontalScrollViewEx1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        if (mScroller == null){
            mScroller = new Scroller(getContext());
            mVelocityTracker = VelocityTracker.obtain();
        }

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                scrollBy(-deltaX, 0);
                break;
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                //设定滑动计算时间
                mVelocityTracker.computeCurrentVelocity(1000);
                float xVelocity = mVelocityTracker.getXVelocity();
                if (Math.abs(xVelocity) >= 50){
                    mChildrenIndex = xVelocity >0 ? mChildrenIndex - 1 : mChildrenIndex + 1;
                } else {
                    mChildrenIndex = (scrollX + mChildrenWidth /2) / mChildrenWidth;
                }

                mChildrenIndex = Math.max(0, Math.min(mChildrenIndex, mChildrenSize -1));
                int dx = mChildrenIndex * mChildrenWidth - scrollX;
                smoothScrollBy(dx, 0);
                mVelocityTracker.clear();
                break;
            default:
                break;
        }

        mLastX = x;
        mLastY = y;

        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                intercepted = false;
                //继续滑动
                if (!mScroller.isFinished()){
                    mScroller.abortAnimation();
                    intercepted = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastXIntercept;
                int deltaY = y - mLastYIntercept;
                //水平距离大于数值距离时拦截
                if (Math.abs(deltaX) > Math.abs(deltaY)){
                    intercepted = true;
                } else{
                    intercepted =false;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercepted = false;
                break;
            default:
                break;

        }
        Log.d(TAG, "intercepted = " + intercepted);

        mLastX = x;
        mLastY = y;
        mLastXIntercept = x;
        mLastYIntercept = y;

        return intercepted;
    }

    @Override
    protected void onDetachedFromWindow() {
        mVelocityTracker.recycle();
        super.onDetachedFromWindow();
    }

    //重写onMeasure\onLayout方法
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measuredWidth = 0;
        int measuredHeight = 0;
        final int childCount = getChildCount();
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthSpaceSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpaceSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);

        if (childCount == 0){
            setMeasuredDimension(0, 0);
        } else if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST){
            final View childView = getChildAt(0);
            measuredWidth = childView.getMeasuredWidth() * childCount;
            measuredHeight = childView.getMeasuredHeight();
            setMeasuredDimension(measuredWidth, measuredHeight);
        } else if (heightSpecMode == MeasureSpec.AT_MOST){
            final View childView = getChildAt(0);
            measuredHeight = childView.getMeasuredHeight();
            setMeasuredDimension(widthSpaceSize, childView.getMeasuredHeight());
        } else if (widthSpecMode == MeasureSpec.AT_MOST){
            final View childView = getChildAt(0);
            measuredWidth = childView.getMeasuredWidth() * childCount;
            setMeasuredDimension(childView.getMeasuredWidth(), heightSpaceSize);
        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childLeft = 0;
        final int childCount = getChildCount();
        mChildrenSize = childCount;

        for (int i = 0; i < childCount; i++){
            final View childView = getChildAt(i);
            if (childView.getVisibility() != GONE){
                final int childWidth = childView.getMeasuredWidth();
                mChildrenWidth = childWidth;
                childView.layout(childLeft, 0, childLeft + childWidth, childView.getMeasuredHeight());
                childLeft += childWidth;
            }
        }
    }

    private void  smoothScrollBy(int dx, int dy){
        mScroller.startScroll(getScrollX(), 0, dx, 0, 500);
        invalidate();
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()){
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}















