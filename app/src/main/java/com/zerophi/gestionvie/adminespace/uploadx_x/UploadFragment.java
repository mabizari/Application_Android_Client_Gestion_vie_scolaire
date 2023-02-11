package com.zerophi.gestionvie.adminespace.uploadx_x;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zerophi.gestionvie.Globalurls;
import com.zerophi.gestionvie.R;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import uk.co.senab.photoview.PhotoView;


public class UploadFragment extends Fragment implements AdapterView.OnItemSelectedListener {
TextView mTextView ;
    private int pos;
    private String par;
      PhotoView mImageView;

    public UploadFragment() {
        // Required empty public constructor
    }

Spinner spinner ;
    Button mButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view  = inflater.inflate(R.layout.fragment_upload, container, false);
       mTextView = (TextView) view.findViewById(R.id.el);

       mImageView = (PhotoView) view.findViewById(R.id.emploiuploadimage);
spinner= (Spinner) view.findViewById(R.id.uploademplois_spinner);
mButton = (Button) view.findViewById(R.id.editeremplois);
ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getApplicationContext(),
        R.array.emplois_array, android.R.layout.simple_spinner_item);
adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
spinner.setAdapter(adapter);
spinner.setOnItemSelectedListener(this);

mButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      Intent intent = new Intent(getActivity().getApplicationContext(),uploadx.class);
    intent.putExtra("pos",pos+1);
        intent.putExtra("emploi",par);
    startActivity(intent);

    }
});
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Glide.with(getActivity().getApplicationContext()).load(/*"https://gestionviescolaire.000webhostapp.com/uploadEmploiNew/images/"*/Globalurls.emploiimages +(pos+1)+".jpg").diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(mImageView);

            }
        });
        return view;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        pos = position;
         par = parent.getItemAtPosition(position).toString();
       // Toast.makeText(getActivity().getApplicationContext(),position + " " + par+"",Toast.LENGTH_LONG).show();
     Glide.with(getActivity().getApplicationContext()).load(/*"https://gestionviescolaire.000webhostapp.com/uploadEmploiNew/images/"*/Globalurls.emploiimages+(pos+1)+".jpg").diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(mImageView);

        
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}