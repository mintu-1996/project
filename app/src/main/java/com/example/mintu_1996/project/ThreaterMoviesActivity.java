package com.example.mintu_1996.project;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Timer;
import java.util.TimerTask;

public class ThreaterMoviesActivity extends AppCompatActivity {
    ViewPager mViewPager;
    CustomPagerAdapter mCustomPagerAdapter;
    int[] mResources = {
            R.drawable.first,
            R.drawable.second,
            R.drawable.third,
            R.drawable.fourth,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threater_movies);
        mCustomPagerAdapter = new CustomPagerAdapter(this);

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);
        Timer time = new Timer();
        time.scheduleAtFixedRate(new MyTimer(),5000,3000);
    }

    public class MyTimer extends TimerTask{

        @Override
        public void run() {
ThreaterMoviesActivity.this.runOnUiThread(new Runnable() {
    @Override
    public void run() {
        if (mViewPager.getCurrentItem() == 0) {
            mViewPager.setCurrentItem(1);
        } else if (mViewPager.getCurrentItem() == 1) {
            mViewPager.setCurrentItem(2);
        }else if (mViewPager.getCurrentItem() == 2) {
            mViewPager.setCurrentItem(3);
        }
    }
});
        }
    }

    class CustomPagerAdapter extends PagerAdapter {

        Context mContext;
        LayoutInflater mLayoutInflater;

        public CustomPagerAdapter(Context context) {
            mContext = context;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mResources.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == ((LinearLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.pager_item, container, false);

            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);
            imageView.setImageResource(mResources[position]);

            container.addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((LinearLayout) object);
        }
    }
}
