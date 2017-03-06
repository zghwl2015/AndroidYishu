package android.example.hwl.myview;

import android.content.Context;

/**
 * Created by monlonwong on 2017/3/4.
 */

public class Utils {

    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
