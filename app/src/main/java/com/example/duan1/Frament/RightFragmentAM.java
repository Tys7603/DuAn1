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


public class RightFragmentAM extends Fragment {
    Intent intent;
    ImageView ivNc, ivBanh, ivXoi;
    public RightFragmentAM() {
        // Required empty public constructor
    }

    public static RightFragmentAM newInstance() {
        RightFragmentAM fragment = new RightFragmentAM();

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
        intent = new Intent(getActivity(), DSTAActivityAD.class);
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