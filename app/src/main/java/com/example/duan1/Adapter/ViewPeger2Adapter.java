package com.example.duan1.Adapter;

import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.example.duan1.Frament.RightFragment;
import com.example.duan1.Frament.MainFragment;

public class ViewPeger2Adapter extends FragmentStateAdapter {
    public ViewPeger2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){
            return MainFragment.newInstance();
        }
        return RightFragment.newInstance();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
