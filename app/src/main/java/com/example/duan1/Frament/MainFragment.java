
package com.example.duan1.Frament;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.duan1.Activity.DSTAActivity;
import com.example.duan1.R;


public class MainFragment extends Fragment {

    ImageView iv_pizza, iv_hum, iv_my;
    Intent intent;
    public MainFragment() {
    }

    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
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
        intent = new Intent(getActivity(),DSTAActivity.class);

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