package com.example.duan1.Frament;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duan1.Activity.DSTAActivity;
import com.example.duan1.R;


public class RightFragment extends Fragment {
    Intent intent;
    ImageView ivNc, ivBanh, ivXoi;
    public RightFragment() {
        // Required empty public constructor
    }

    public static RightFragment newInstance() {
        RightFragment fragment = new RightFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_right, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ivNc =view.findViewById(R.id.iv_drink);
        ivBanh = view.findViewById(R.id.iv_banh);
        ivXoi =  view.findViewById(R.id.iv_xoi);
        intent = new Intent(getActivity(), DSTAActivity.class);
        ivNc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("id","4");
                startActivity(intent);
            }
        });

        ivBanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("id","5");
                startActivity(intent);
            }
        });

        ivXoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.putExtra("id","6");
                startActivity(intent);
            }
        });
    }
}