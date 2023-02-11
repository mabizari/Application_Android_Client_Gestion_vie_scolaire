package com.zerophi.gestionvie.enseignantespace.gestion_notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zerophi.gestionvie.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class gestion_notes extends Fragment {
Button btnajouter , btnmodifier, btnvoir;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_gestion_notes,container,false);
        btnmodifier = (Button) view.findViewById(R.id.modifiernotes);



        btnmodifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity().getApplicationContext(),modules2.class));
            }
        });
      return  view;
    }





}
/*
public class gestion_notes extends AppCompatActivity {
Button btnajouter , btnmodifier, btnvoir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_notes);

        btnmodifier = (Button) findViewById(R.id.modifiernotes);
        btnvoir =(Button) findViewById(R.id.voir_notes);

  
        btnmodifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),modules.class));
            }
        });


    }
}
*/