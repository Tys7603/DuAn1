package com.example.duan1.Activity.AdapterAdmin;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.duan1.Frament.MainFragment;
import com.example.duan1.Frament.MainFragmentAM;
import com.example.duan1.Frament.RightFragment;
import com.example.duan1.Frament.RightFragmentAM;

public class ViewPeger2AdapterAD extends FragmentStateAdapter {
    public ViewPeger2AdapterAD(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0){
            return MainFragmentAM.newInstance();
        }
        return RightFragmentAM.newInstance();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
