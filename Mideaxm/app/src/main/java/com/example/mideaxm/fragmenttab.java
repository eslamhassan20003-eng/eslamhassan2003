package com.example.mideaxm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragmenttab extends Fragment {

    private static final String ARG_TITLE = "title";

    public static fragmenttab newInstance(String title) {
        fragmenttab fragment = new fragmenttab();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragmenttab, container, false);
        TextView tvTitle = view.findViewById(R.id.tvTabTitle);
        if (getArguments() != null) {
            tvTitle.setText(getArguments().getString(ARG_TITLE));
        }
        return view;
    }
}
