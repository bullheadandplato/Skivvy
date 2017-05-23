package com.haidar.skivvy;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    ImageButton fullscreenbtn;
    ImageButton keyboardbtn;
    ImageButton micbtn;
    ImageButton small_micbtn;
    EditText entrytxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         fullscreenbtn=(ImageButton) findViewById(R.id.expandbtn);
        keyboardbtn=(ImageButton)findViewById(R.id.keyboardButton);
        micbtn=(ImageButton)findViewById(R.id.micButton);
        small_micbtn=(ImageButton)findViewById(R.id.small_mic) ;
        entrytxt=(EditText)findViewById(R.id.textentry);
        coordinatorLayout=(CoordinatorLayout) findViewById(R.id.coordinator_layout);


    }
    @Override
    public void onStart(){
        super.onStart();
        fullscreenbtn.setOnClickListener(View -> marginChange());
        keyboardbtn.setOnClickListener(View ->setKeyboardbtn());
        small_micbtn.setOnClickListener(View -> setSmall_micbtn());

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
        coordinatorLayout.startAnimation(slideanim);
        //button animation
        Animation fadeanim=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        fullscreenbtn.startAnimation(fadeanim);
    }
    public void gestures(){
        coordinatorLayout.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this){
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
    public void setKeyboardbtn(){
        micbtn.setVisibility(View.GONE);
        keyboardbtn.setVisibility(View.GONE);
        entrytxt.setVisibility(View.VISIBLE);
        small_micbtn.setVisibility(View.VISIBLE);
        entrytxt.requestFocus();
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(entrytxt, InputMethodManager.SHOW_IMPLICIT);
        }, 500);

        marginChange();

    }
    public void setSmall_micbtn(){
        keyboardbtn.setVisibility(View.VISIBLE);
        entrytxt.setVisibility(View.GONE);
      micbtn.setVisibility(View.VISIBLE);
        small_micbtn.setVisibility(View.GONE);
    }
}
