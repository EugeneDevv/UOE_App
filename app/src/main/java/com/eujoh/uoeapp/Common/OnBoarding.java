package com.eujoh.uoeapp.Common;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.eujoh.uoeapp.HelperClasses.SliderAdapter;
import com.eujoh.uoeapp.R;
import com.eujoh.uoeapp.User.UserDashboard;

public class OnBoarding extends AppCompatActivity {

    //variable
    ViewPager viewPager;
    LinearLayout dotsLayout;
    Button letsGetStartedBtn;
    SliderAdapter sliderAdapter;
    TextView[] dots;
    Animation animation;
    int currentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_on_boarding);

        //Hooks
        viewPager = findViewById(R.id.slider_vp);
        dotsLayout = findViewById(R.id.dots);
        letsGetStartedBtn = findViewById(R.id.get_started_btn);

        //Call adapter
        sliderAdapter = new SliderAdapter(this);
        viewPager.setAdapter(sliderAdapter);

        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }

    public void skip(View view){
        startActivity(new Intent(this, UserDashboard.class));
        finish();
    }
    public void next(View view){
        viewPager.setCurrentItem(currentPosition+1);
    }
    private void addDots(int position){
        dots = new TextView[4];
        dotsLayout.removeAllViews();

        for (int i=0; i <dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);
        }
        if (dots.length > 0 ){
            dots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPosition = position;

            if (position == 0 ) {
                letsGetStartedBtn.setVisibility(View.INVISIBLE);
            } else if (position == 1){
                letsGetStartedBtn.setVisibility(View.INVISIBLE);
            } else if (position == 2){
                letsGetStartedBtn.setVisibility(View.INVISIBLE);
            }else {
                animation = AnimationUtils.loadAnimation(OnBoarding.this, R.anim.bottom_anim);
                letsGetStartedBtn.setAnimation(animation);
                letsGetStartedBtn.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}