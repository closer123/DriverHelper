package com.example.driverhelper.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.driverhelper.R;
import com.example.driverhelper.viewmodel.ShortVideoViewModel;

public class ShortVideoFragment extends Fragment {

    private ShortVideoViewModel mViewModel;

    public static ShortVideoFragment newInstance() {
        return new ShortVideoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_short_video, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ShortVideoViewModel.class);
        // TODO: Use the ViewModel
    }

}