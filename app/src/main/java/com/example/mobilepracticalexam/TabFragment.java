package com.example.mobilepracticalexam;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;

public class TabFragment extends Fragment {

    private static final String ARG_TITLE = "title";

    // Create Fragment Instance
    public static TabFragment newInstance(String title) {

        TabFragment fragment = new TabFragment();

        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);

        fragment.setArguments(args);

        return fragment;
    }

    // Create Fragment View
    @Override
    public View onCreateView(
            android.view.LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        TextView textView = new TextView(requireContext());

        // Get Tab Title
        Bundle args = getArguments();

        String title = args != null
                ? args.getString(ARG_TITLE, "First")
                : "First";

        // Configure TextView
        textView.setText(title);
        textView.setTextSize(28);

        // Dynamic Text Color
        textView.setTextColor(
                ContextCompat.getColor(
                        requireContext(),
                        R.color.primary_text
                )
        );

        textView.setGravity(Gravity.CENTER);

        // Layout Parameters
        textView.setLayoutParams(
                new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
        );

        return textView;
    }
}