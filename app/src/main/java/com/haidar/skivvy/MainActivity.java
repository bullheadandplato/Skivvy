package com.haidar.skivvy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout ll;
    ImageButton fullscreenbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         fullscreenbtn=(ImageButton) findViewById(R.id.expandbtn);
        ll=(LinearLayout)findViewById(R.id.main_linear_layout);


    }
    @Override
    public void onStart(){
        super.onStart();
        fullscreenbtn.setOnClickListener(View -> marginChange());
        slideanim();
        gestures();


    }

    public void marginChange(){
        //margin change from 180dp to zero

        /*ViewGroup.MarginLayoutParams params=(ViewGroup.MarginLayoutParams)ll.getLayoutParams();
        params.topMargin=0;
        ll.setLayoutParams(params);*/
        fullscreenbtn.setVisibility(View.GONE);

    }
    public void slideanim(){
        //layout upward animation
        Animation slideanim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide);
        ll.startAnimation(slideanim);
        //button animation
        Animation fadeanim=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        fullscreenbtn.startAnimation(fadeanim);
    }
    public void gestures(){
        ll.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this){
            @Override
            public void onClick(){
                super.onClick();
                // your on click here
            }
            @Override
            public void onDoubleClick() {
                super.onDoubleClick();
                // your on onDoubleClick here
            }

            @Override
            public void onLongClick() {
                super.onLongClick();
                // your on onLongClick here
            }

            @Override
            public void onSwipeUp() {
                super.onSwipeUp();
                //full screen on swipe up
                marginChange();

            }

            @Override
            public void onSwipeDown() {
                super.onSwipeDown();
                // your swipe down here.
            }

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                // your swipe left here.
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                // your swipe right here.
            }
        });
    }
}
