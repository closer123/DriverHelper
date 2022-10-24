package com.example.driverhelper.fragment;

import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.driverhelper.R;
import com.example.driverhelper.activity.DirectBroadcastMainActivity;
import com.example.driverhelper.activity.WarnInfoActivity;
import com.example.driverhelper.viewmodel.DirectBroadcastViewModel;


public class DirectBroadcastFragment extends Fragment implements View.OnClickListener {

    private DirectBroadcastViewModel mViewModel;
    private CardView mDirectCard,mWarnCard;

    public static DirectBroadcastFragment newInstance() {
        return new DirectBroadcastFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_direct_broadcast, container, false);
        mDirectCard=view.findViewById(R.id.dir_card);
        mDirectCard.setOnClickListener(this);
        mWarnCard=view.findViewById(R.id.his_card );
        mWarnCard.setOnClickListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DirectBroadcastViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent();

        switch (v.getId()){
            case R.id.dir_card:

                intent.setClass(getActivity(),DirectBroadcastMainActivity.class);
                startActivity(intent);
                break;
            case R.id.his_card:
                intent.setClass(getActivity(), WarnInfoActivity.class);
                startActivity(intent);
                break;

        }

    }
}