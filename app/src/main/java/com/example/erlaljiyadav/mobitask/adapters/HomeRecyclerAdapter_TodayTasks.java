package com.example.erlaljiyadav.mobitask.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.erlaljiyadav.mobitask.R;
import com.example.erlaljiyadav.mobitask.modals.TodayTaskModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by android on 26/12/17.
 */

public class HomeRecyclerAdapter_TodayTasks extends RecyclerView.Adapter<HomeRecyclerAdapter_TodayTasks.Holder> {


    private ArrayList<TodayTaskModel> listTodayTask;
    private Context context;


    public HomeRecyclerAdapter_TodayTasks(Context context, ArrayList<TodayTaskModel> listTodayTask){
        this.listTodayTask=listTodayTask;
        this.context=context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_today_task,parent,false);

        return new HomeRecyclerAdapter_TodayTasks.Holder(view);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(Holder holder, final int position) {

//.........................set random colors.................//

        List<String> colors;

        colors=new ArrayList<String>();
        colors.clear();

        colors.add("#66BB6A");
        colors.add("#359FF3");
        colors.add("#F87C2C");

        try {
            Random r = new Random();
            int i1 = r.nextInt(3- 0) + 0;
            GradientDrawable draw = new GradientDrawable();
            draw.setColor(Color.parseColor(colors.get(i1)));
            draw.setCornerRadius(3);
            holder.executeTask.setBackground(draw);
        }catch (Exception e){

        }

        final TodayTaskModel i = listTodayTask.get(position);
//        holder.txt_Amz.setText(i.getPackage_Name());
        holder.button_text = i.getPackage_Name();
        holder.work_url = i.getWork_url();
        holder.package_id = i.getPackage_id();
        holder.url_webView = i.getWebUrl();
        holder.desc.setText(i.getAppDesc());
//        holder.txt_subDesc.setText(i.getAppSdesc());
//        holder.txt_amt_crdt.setText(i.getAmount_Credit());
        holder.str_txt_credit_amt=i.getAmount_Credit();
        holder.url_icons = i.getIcon();
        holder.show_point=i.getShowPoint();
        holder.btn_install.setText(i.getShowPoint());
        holder.ClickBLabel=i.getClickBLabel();
        holder.btn_install.setText(holder.button_text);
        holder.full_desc=i.getAppDesc();

        Log.e("taskdec",holder.url_webView);



        if (holder.url_icons.isEmpty()) { //url.isEmpty()
            Picasso.with(context)
                    .load(R.drawable.movi_task_logo)
                    .placeholder(R.drawable.movi_task_logo)
                    .error(R.drawable.movi_task_logo)
                    .into(holder.image);

        } else {
            Picasso.with(context)
                    .load(listTodayTask.get(position).getIcon())
                    .placeholder(R.drawable.movi_task_logo)
                    .error(R.drawable.movi_task_logo)
                    .into(holder.image); //this is your ImageView
        }

    }

    @Override
    public int getItemCount() {
        return listTodayTask.size();
    }



    public class Holder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView desc;
        String button_text, package_id;
        String show_point;

        String work_url, str_txt_credit_amt;
        String url_icons;
        String url_webView;
        String ClickBLabel;
        TextView btn_install;
        LinearLayout executeTask;
        RelativeLayout card;
        private String full_desc;

        public Holder(final View itemView) {
            super(itemView);


            executeTask = itemView.findViewById(R.id.executetask_home_recycler);
            image = itemView.findViewById(R.id.home_rec_image);
            desc = itemView.findViewById(R.id.home_rec_text);
            btn_install = itemView.findViewById(R.id.home_rec_button_text);
            card = itemView.findViewById(R.id.home_card);
        }

    }
}
