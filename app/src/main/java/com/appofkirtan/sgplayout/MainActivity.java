package com.appofkirtan.sgplayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView sign_in,sign_up;
    ViewPager viewPager;
    PagerViewAdepter pagerViewAdepter;
    Button signinbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sign_in=(TextView)findViewById(R.id.SignIn);
        sign_up=(TextView)findViewById(R.id.SignUp);
        viewPager =(ViewPager)findViewById(R.id.fragment_container);
        pagerViewAdepter = new PagerViewAdepter(getSupportFragmentManager());

        viewPager.setAdapter(pagerViewAdepter);

        sign_in.setOnClickListener(new View.OnClickListener() {

          //  @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
                /*signinbtn = (Button)findViewById(R.id.btn_signin);
                signinbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this,demoaAtication.class));
                        finish();
                    }
                });*/
            }

        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });

       /* viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

               onChangeTab(position);
            }

            private void onChangeTab(int position) {
                if(position ==0 ){
                    sign_in.setTextColor(getColor(R.color.Bright_color));
                    sign_up.setTextColor(getColor(R.color.Light_color));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/
    }
}