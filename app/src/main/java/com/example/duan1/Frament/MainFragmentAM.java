
package com.example.duan1.Frament;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.duan1.Activity.Admin.DSTAActivityAD;
import com.example.duan1.Activity.DSTAActivity;
import com.example.duan1.R;


public class MainFragmentAM extends Fragment {

    ImageView iv_pizza, iv_hum, iv_my;
    Intent intent;
    public MainFragmentAM() {
    }

    // TODO: Rename and change types and number of parameters
    public static MainFragmentAM newInstance() {
        MainFragmentAM fragment = new MainFragmentAM();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iv_pizza = view.findViewById(R.id.iv_pizza);
        iv_hum = view.findViewById(R.id.iv_hamburger);
        iv_my = view.findViewById(R.id.iv_my);
        intent = new Intent(getActivity(), DSTAActivityAD.class);

        iv_pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("id","1");
                startActivity(intent);
            }
        });

        iv_hum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("id","2");
                startActivity(intent);
            }
        });

        iv_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("id","3");
                startActivity(intent);
            }
        });
    }

}