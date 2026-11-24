package com.example.mobilepracticalexam;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(FragmentActivity activity) {
        super(activity);
    }

    @Override
    public Fragment createFragment(int position) {

        switch (position) {

            case 0:
                return TabFragment.newInstance("First");

            case 1:
                return TabFragment.newInstance("Second");

            case 2:
                return TabFragment.newInstance("Third");

            default:
                throw new IllegalArgumentException(
                        "Invalid tab position: " + position
                );
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}