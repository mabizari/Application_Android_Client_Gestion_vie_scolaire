package com.zerophi.gestionvie.etudiantespace.emplois.emploitemp;


import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.etudiantespace.emplois.emploitemp.adapters.FragmentsTabAdapter;

import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class gestionemploi2 extends AppCompatActivity {

    private FragmentsTabAdapter adapter;
    private ViewPager viewPager;
    private boolean switch7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionemploi2);
        emp();
    }
    private void emp() {

        setupFragments();
        setupCustomDialog();
        setupSevenDaysPref();
        if(switch7) changeFragments(true);

        setDailyAlarm();

    }

    private void setDailyAlarm() {
    }

    private void changeFragments(boolean b) {
    }


    private void setupFragments() {

        adapter = new FragmentsTabAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
       /* adapter.addFragment(new MondayFragment(), getResources().getString(R.string.monday));
        adapter.addFragment(new TuesdayFragment(), getResources().getString(R.string.tuesday));
        adapter.addFragment(new WednesdayFragment(), getResources().getString(R.string.wednesday));
        adapter.addFragment(new ThursdayFragment(), getResources().getString(R.string.thursday));
        adapter.addFragment(new FridayFragment(), getResources().getString(R.string.friday));*/
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(day == 1 ? 6 : day-2, true);
        tabLayout.setupWithViewPager(viewPager);
    }
    private void setupSevenDaysPref() {
    }
    private void setupCustomDialog() {
    }

}
