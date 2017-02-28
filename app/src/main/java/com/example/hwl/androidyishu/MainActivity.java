package com.example.hwl.androidyishu;

import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView =(TextView) findViewById(R.id.button);
        TransitionDrawable transitionDrawable =(TransitionDrawable) textView.getBackground();
        transitionDrawable.startTransition(2000);

        View scale =(View) findViewById(R.id.text_view);
        ScaleDrawable scaleDrawable =(ScaleDrawable) scale.getBackground();
        scaleDrawable.setLevel(3000);

        ImageView imageClip =(ImageView) findViewById(R.id.clip);
        ClipDrawable clipDrawable =(ClipDrawable) imageClip.getDrawable();
        clipDrawable.setLevel(9000);
    }
}
