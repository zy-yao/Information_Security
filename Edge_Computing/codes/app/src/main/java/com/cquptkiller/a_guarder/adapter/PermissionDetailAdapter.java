package com.cquptkiller.a_guarder.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cquptkiller.a_guarder.R;
import com.cquptkiller.a_guarder.data.AppInfo;
import com.cquptkiller.a_guarder.view.MainActivity;

import java.util.ArrayList;

/**
 * Created by yao on 2018/12/22.
 */
public class PermissionDetailAdapter extends RecyclerView.Adapter<PermissionDetailAdapter.MeViewHolder> {
    int o_position;
    ArrayList<AppInfo> appList;
    public static final String TAG="PermissionDetailAdapter";
    public PermissionDetailAdapter(int position, ArrayList<AppInfo> appList){
        o_position=position;
        this.appList=appList;
        Log.d("889", "PermissionDetailAdapter: "+position);
    }
    @Override
    public MeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("PermissionDetailAdapter", "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pda_activity, parent, false);
        MeViewHolder holder = new MeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MeViewHolder holder, int position) {
        Log.d("PermissionDetailAdapter", "onBindViewHolder: ");
        try {
            holder.label.setText(appList.get(o_position).getPermissionLabel()[position]);
            holder.name.setText(appList.get(o_position).getMyPermission()[position]);
            //holder.label.setText(MainActivity.appList.get(o_position).getAppName());
        }catch (Exception e){
            Log.d(TAG, "onBindViewHolder: 出错了："+position+">>>>"+e.getMessage());
        }
        }

    @Override
    public int getItemCount() {
        try{
            return MainActivity.appList.get(o_position).getMyPermission().length;
        }catch (Exception e){
            Log.d(TAG, "getItemCount: rerr-->"+e.getMessage());
        }
        return 0;
    }

    class MeViewHolder extends RecyclerView.ViewHolder {
        TextView label;
        TextView name;
        public MeViewHolder(View itemView) {
            super(itemView);
            label= (TextView) itemView.findViewById(R.id.tv_pda_permission_label);
            name= (TextView) itemView.findViewById(R.id.tv_pda_permission_name);
        }
    }
}
