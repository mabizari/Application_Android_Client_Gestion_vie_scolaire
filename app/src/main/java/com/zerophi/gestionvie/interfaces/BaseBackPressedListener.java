package com.zerophi.gestionvie.interfaces;

import android.content.Intent;


import com.zerophi.gestionvie.Main2Activity;
import com.zerophi.gestionvie.R;
import com.zerophi.gestionvie.fragment.compte_rec;
import com.zerophi.gestionvie.info;
import com.zerophi.gestionvie.interfaces.OnBackPressedListener;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class BaseBackPressedListener implements OnBackPressedListener {


    private final FragmentActivity activity;
    public BaseBackPressedListener(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void doBack() {
        activity.getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
      activity.startActivity(new Intent(activity,Main2Activity.class));
      //  activity.getSupportFragmentManager().beginTransaction().replace(R.id.main_cont,new info()).addToBackStack(null).commit();
    }
}
