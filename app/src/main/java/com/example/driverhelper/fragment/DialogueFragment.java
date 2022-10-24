package com.example.driverhelper.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.driverhelper.R;
import com.example.driverhelper.adapter.DirectBroadcastListAdapter;
import com.example.driverhelper.bean.User;
import com.example.driverhelper.viewmodel.DialogueViewModel;

import java.util.ArrayList;
import java.util.List;

public class DialogueFragment extends Fragment {
    private final static String TAG = "xiaohao";
    private boolean isBottom = false;
    private View footView;
    private List<User> users;
    private DialogueViewModel mViewModel;
    private DirectBroadcastListAdapter directBroadcastListAdapter;
    private ListView video_ls;

    public static DialogueFragment newInstance() {
        return new DialogueFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialogue, container, false);
        users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users.add(new User("xiaohao", i));
        }
        video_ls = view.findViewById(R.id.video_ls);
        video_ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "点击了第" + position + "数据", Toast.LENGTH_SHORT).show();

            }
        });
        directBroadcastListAdapter = new DirectBroadcastListAdapter(getActivity(), users);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        video_ls.setAdapter(directBroadcastListAdapter);
        video_ls.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.i(TAG + "滑动状态：", String.valueOf(scrollState));
                if (isBottom && scrollState == SCROLL_STATE_IDLE) {
                    footView = getLayoutInflater().inflate(R.layout.listitem_foot, null);
                    video_ls.addFooterView(footView);
                    addItemView();

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.i(TAG + "滑动离开的：", String.valueOf(firstVisibleItem));
                Log.i(TAG + "当前展示的：", String.valueOf(visibleItemCount));
                Log.i(TAG + "总共的：", String.valueOf(totalItemCount));
                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    isBottom = true;
                } else {
                    isBottom = false;
                }
            }

        });
    }

    private void addItemView() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<User> newUsers = new ArrayList<>();
                for (int i = 0; i < 6; i++) {

                    newUsers.add(new User("小俊" + i, 1 + i));

                }

                if (directBroadcastListAdapter != null) {
                    directBroadcastListAdapter.addAllData(newUsers);
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        video_ls.removeFooterView(footView);
                        directBroadcastListAdapter.notifyDataSetChanged();
                    }
                });

            }
        }).start();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DialogueViewModel.class);
        // TODO: Use the ViewModel
    }

}