package com.cquptkiller.a_guarder.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cquptkiller.a_guarder.R;
import com.cquptkiller.a_guarder.data.AppInfo;
import com.cquptkiller.a_guarder.view.MainActivity;

import java.util.ArrayList;

/**
 * Created by yao on 2018/12/22.
 */
public class PermissionAdapter extends RecyclerView.Adapter<PermissionAdapter.MyViewHolder> implements View.OnClickListener {
    ArrayList<AppInfo> appList;
    public interface RecyclerViewListener{
        void onClick(View view, int position);
    }
    public RecyclerViewListener listener=null;

    public void setRecyclerViewListener(RecyclerViewListener listener){
        this.listener=listener;
    }
    @Override
    public void onClick(View v) {
        if(listener!=null){
            listener.onClick(v, (Integer) v.getTag());
        }

    }
    public PermissionAdapter(ArrayList<AppInfo> appList){
        this.appList=appList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview,parent,false);
        MyViewHolder holder=new MyViewHolder(view);

        view.setOnClickListener(this);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.appName.setText("名称："+MainActivity.appList.get(position).getAppName());
        holder.packageName.setText("包名："+MainActivity.appList.get(position).getPackageName());
        holder.appIcon.setImageDrawable(MainActivity.appList.get(position).getAppIcon());
        if(MainActivity.appList.get(position).getMyPermission()!=null){
        holder.permissionCount.setText("权限数量"+MainActivity.appList.get(position).getMyPermission().length+"条");
        }else {
            holder.permissionCount.setText("权限数量：0条");
        }
        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return MainActivity.appList.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView appIcon;
        TextView appName;
        TextView packageName;
        TextView permissionCount;


        public MyViewHolder(View itemView) {
            super(itemView);
            appIcon= (ImageView) itemView.findViewById(R.id.item_app_icon);
            appName= (TextView) itemView.findViewById(R.id.item_app_name);
            packageName= (TextView) itemView.findViewById(R.id.item_package_name);
            permissionCount= (TextView) itemView.findViewById(R.id.item_permission_counts);

        }
    }
}
