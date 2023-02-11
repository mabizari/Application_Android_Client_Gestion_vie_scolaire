package com.zerophi.gestionvie.help;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.connection.isConnectedInternet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ActionTabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs);
        viewPager.setAdapter(new TabsPagerAdapter(this));
        /*
         * SlidingTabLayout is bound to ViewPager, both for tab titles
         * and scroll tracking behavior.
         */

        slidingTabLayout.setViewPager(viewPager);
        slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
//Color to show underneath each tab position
                return Color.WHITE;
            }
            @Override
            public int getDividerColor(int position) {
//Transparent to hide dividers
                return 0;
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }



    private class TabsPagerAdapter extends PagerAdapter {
    Context mContext;

        public TabsPagerAdapter(Context context) {
            mContext = context;
        }
        /*
         * SlidingTabLayout requires this method to define the
         * text that each tab will display.
         */
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "information sur departement informatique";
                case 1:
                    return "localisation est meknes";
                case 2:
                    return "autre information";

                default:
                    return "";
            }
        }



        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            if(position==0){
            ImageView pageView = new ImageView(mContext);
            pageView.setScaleType(ImageView.ScaleType.CENTER);
            pageView.setImageResource(R.drawable.ic_gps_fixed_black_24dp);

            container.addView(pageView);
            return pageView;}
            else if(position==1){
                    ImageView pageView = new ImageView(mContext);
            pageView.setScaleType(ImageView.ScaleType.CENTER);
            pageView.setImageResource(R.drawable.logo_est);

            container.addView(pageView);
            return  pageView;
            }else if(position==3){
                  ImageView pageView = new ImageView(mContext);
            pageView.setScaleType(ImageView.ScaleType.CENTER);
            pageView.setImageResource(R.drawable.ic_gps_fixed_black_24dp);

            container.addView(pageView);
            return pageView;
            }else{

   ImageView pageView = new ImageView(mContext);
            pageView.setScaleType(ImageView.ScaleType.CENTER);
            pageView.setImageResource(R.drawable.logo_est);

            container.addView(pageView);
            return  pageView;

            }
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }


    }
}
