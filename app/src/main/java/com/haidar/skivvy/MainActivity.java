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
    private CoordinatorLayout   mCoordinatorLayout;
    private ImageButton         mFullscreenButton;
    private ImageButton         mKeyboardButton;
    private ImageButton         mMicButton;
    private ImageButton         mSmallMicButton;
    private EditText            mEntryText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindLayoutElements();
        setListeners();
        slideanim();
        gestures();

    }

    private void setListeners() {
        mFullscreenButton.setOnClickListener(View -> marginChange());
        mKeyboardButton.setOnClickListener(View ->setKeyboardbtn());
        mSmallMicButton.setOnClickListener(View -> setSmallMicbtn());

    }

    private void bindLayoutElements(){
        mFullscreenButton   = (ImageButton) findViewById(R.id.expandbtn);
        mKeyboardButton     = (ImageButton)findViewById(R.id.keyboardButton);
        mMicButton          = (ImageButton)findViewById(R.id.micButton);
        mSmallMicButton     = (ImageButton)findViewById(R.id.small_mic) ;
        mEntryText          = (EditText)findViewById(R.id.textentry);
        mCoordinatorLayout  = (CoordinatorLayout) findViewById(R.id.coordinator_layout);

    }


    public void marginChange(){
        //margin change from 180dp to zero

        /*ViewGroup.MarginLayoutParams params=(ViewGroup.MarginLayoutParams)ll.getLayoutParams();
        params.topMargin=0;
        ll.setLayoutParams(params);*/
        mFullscreenButton.setVisibility(View.GONE);

    }
    private void slideanim(){
        //layout upward animation
        Animation slideanim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide);
        mCoordinatorLayout.startAnimation(slideanim);
        //button animation
        Animation fadeanim=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        mFullscreenButton.startAnimation(fadeanim);
    }
    private void gestures(){
        mCoordinatorLayout.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this){
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
    private void setKeyboardbtn(){
        mMicButton.setVisibility(View.GONE);
        mKeyboardButton.setVisibility(View.GONE);
        mEntryText.setVisibility(View.VISIBLE);
        mSmallMicButton.setVisibility(View.VISIBLE);
        mEntryText.requestFocus();
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(mEntryText, InputMethodManager.SHOW_IMPLICIT);
        }, 500);

        marginChange();

    }

    private void setSmallMicbtn(){
        mKeyboardButton.setVisibility(View.VISIBLE);
        mEntryText.setVisibility(View.GONE);
        mMicButton.setVisibility(View.VISIBLE);
        mSmallMicButton.setVisibility(View.GONE);
    }
}
