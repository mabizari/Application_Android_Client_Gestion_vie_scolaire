package com.zerophi.gestionvie;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;
import com.zerophi.gestionvie.fragment.MapViewFragment;
import com.zerophi.gestionvie.help.ActionTabActivity;
import com.zerophi.gestionvie.help.SlidingTabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


public class info extends Fragment {
TextView info1 , info2,info1p,info2p;
   private MapView mMapView;
   private GoogleMap googleMap;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //     return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_info, container, false);
      /*mMapView = (MapView) view.findViewById(R.id.mapVieww);
          mMapView.onCreate(savedInstanceState);
           mMapView.onResume(); // needed to get the map to display immediately

            try {
                MapsInitializer.initialize(getActivity().getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
            }

            mMapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap mMap) {
                    googleMap = mMap;
                    if (ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        mMap.setMyLocationEnabled(true);
                    } else {
                        // Show rationale and request permission.
                    }
                    // For showing a move to my location button
                    googleMap.setMyLocationEnabled(true);

                    // For dropping a marker at a point on the Map
                    LatLng sydney = new LatLng(-34, 151);
                    googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));

                    // For zooming automatically to the location of the marker
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
            });*/
        btnimag = (TextView) view.findViewById(R.id.imag);
        btninfo = (TextView) view.findViewById(R.id.btninto);
        info1 = (TextView) view.findViewById(R.id.info1);
        info2 = (TextView) view.findViewById(R.id.info2);
        info1p = (TextView) view.findViewById(R.id.info1p);
        info2p = (TextView) view.findViewById(R.id.info2p);


        btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          /*  Intent mIntent = new Intent(getActivity().getApplicationContext(),MapsActivity.class);
            startActivity(mIntent);*/
             //   getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_cont_map, new MapViewFragment()).commit();
                if (mMapView.getVisibility() == View.VISIBLE){
                  try {
                      mMapView.setVisibility(View.GONE);
                  }catch (Exception e){

                  }
                }else{
                    mMapView.setVisibility(View.VISIBLE);
                }
            }
        });

        info1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(info1p.getVisibility() == View.VISIBLE){
                    info1p.setVisibility(View.GONE);
                } else{
                    info1p.setVisibility(View.VISIBLE);
                }
            }
        });
        info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(info2p.getVisibility() == View.VISIBLE){
                    info2p.setVisibility(View.GONE);
                } else{
                    info2p.setVisibility(View.VISIBLE);
                }
            }
        });

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        final SlidingTabLayout slidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.tabs);
        viewPager.setAdapter(new TabsPagerAdapter(getActivity().getApplicationContext()));
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
        btnimag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent intent = new Intent(getActivity().getApplicationContext(), ActionTabActivity.class);
                startActivity(intent);*/
                if (slidingTabLayout.getVisibility() == View.VISIBLE && viewPager.getVisibility()==View.VISIBLE){
                    slidingTabLayout.setVisibility(View.GONE);
                    viewPager.setVisibility(View.GONE);
                }else{
                    slidingTabLayout.setVisibility(View.VISIBLE);
                    viewPager.setVisibility(View.VISIBLE);
                }
            }
        });
        return view;
    }

    /*@Override
        public void onResume() {
            super.onResume();
            mMapView.onResume();
        }

        @Override
        public void onPause() {
            super.onPause();
            mMapView.onPause();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            mMapView.onDestroy();
        }

        @Override
        public void onLowMemory() {
            super.onLowMemory();
            mMapView.onLowMemory();
        }*/

    private TextView btnimag;
    private TextView btninfo;

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
                    return " GÉNIE INFORMATIQUE \n TECHNIQUES DU SON ET DE L'IMAGE";
                case 1:
                    return "Génie éléctrique";
                case 2:
                    return "Techniques \n de Commercialisation \net de Communication";

                default:
                    return "Techniques \nde management";
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
                pageView.setImageResource(R.drawable.informatique);
                container.addView(pageView);
                return pageView;}
            else if(position==1){
                ImageView pageView = new ImageView(mContext);
                pageView.setScaleType(ImageView.ScaleType.CENTER);
                pageView.setImageResource(R.drawable.electrique);

                container.addView(pageView);
                return  pageView;
            }else if(position==3){
                ImageView pageView = new ImageView(mContext);
                pageView.setScaleType(ImageView.ScaleType.CENTER);
                pageView.setImageResource(R.drawable.tcc);

                container.addView(pageView);
                return pageView;
            }else{

                ImageView pageView = new ImageView(mContext);
                pageView.setScaleType(ImageView.ScaleType.CENTER);
                pageView.setImageResource(R.drawable.managment);

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