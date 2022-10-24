package com.example.driverhelper.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.driverhelper.R;
import com.example.driverhelper.bean.User;

import java.util.List;

public class DirectBroadcastListAdapter extends BaseAdapter {
private Context context;
private TextView tv1;
    private TextView tv2;
private List<User> userList;
private LayoutInflater layoutInflater;

    public DirectBroadcastListAdapter(Context context,List<User> users) {
        this.userList=users;
        this.context=context;
        layoutInflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
userList=users;

    }
    public void addAllData(List<User> users) {
        if(userList!=null&&users!=null){
            userList.addAll(users);
        }

    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=layoutInflater.inflate(R.layout.direct_item,null);
        tv1=view.findViewById(R.id.item_tv);
        tv2=view .findViewById(R.id.item_tv2);
       User user=userList.get(position);
       tv1.setText(user.getName());
       tv2.setText(user.getAge()+"");
        return view;
    }
}
