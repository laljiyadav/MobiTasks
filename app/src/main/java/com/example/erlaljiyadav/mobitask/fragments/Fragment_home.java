package com.example.erlaljiyadav.mobitask.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.erlaljiyadav.mobitask.R;
import com.example.erlaljiyadav.mobitask.activites.QuizRulesCategories;
import com.example.erlaljiyadav.mobitask.adapters.HomeRecyclerAdapter_TodayTasks;
import com.example.erlaljiyadav.mobitask.configs.Configs;
import com.example.erlaljiyadav.mobitask.modals.TodayTaskModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Fragment_home extends Fragment {
    LinearLayout notask_layout;
    RecyclerView home_recycler;
    SwipeRefreshLayout mainLAyout;
    Button btn_enter_quize;

    public  Fragment_home(){

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_fragment_home, container, false);
        notask_layout=view.findViewById(R.id.notask_layout);
        home_recycler=view.findViewById(R.id.home_recycler);
        mainLAyout=view.findViewById(R.id.mainLAyout);
        btn_enter_quize=view.findViewById(R.id.btn_enter_quize);

        btn_enter_quize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), QuizRulesCategories.class));
                Toast.makeText(getContext(),"Working",Toast.LENGTH_SHORT).show();
            }
        });

        //......................todays task.....................................
        //fetchTodayTask();

        return view;
    }


    ////////////////////fetch todays task//////////////////////////
    private void fetchTodayTask() {
        mainLAyout.setRefreshing(true);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Configs.url_workAssign,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("resp",response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.optString("error").equalsIgnoreCase("true") || jsonObject.optString("error_msg").equalsIgnoreCase("No Task Avaialable !!")) {

                                notask_layout.setVisibility(View.VISIBLE);


                            } else if (jsonObject.optString("error").equalsIgnoreCase("false") || jsonObject.optString("error_msg").equalsIgnoreCase("Task List Available")) {

                                JSONArray jsonArray = jsonObject.getJSONArray("data");

                                ArrayList<TodayTaskModel> list = new ArrayList<>();

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    TodayTaskModel todayTask = new TodayTaskModel();

                                    JSONObject jsonObj = jsonArray.getJSONObject(i);

// For Set data
                                    todayTask.setAmount_Credit(jsonObj.getString("Amount_Credit"));
                                    todayTask.setPackage_id(jsonObj.getString("Package_ID"));
                                    todayTask.setPackage_Name(jsonObj.getString("Package_Name"));
                                    todayTask.setWorkassign_Date(jsonObj.getString("Workassign_Date"));
                                    todayTask.setWorkassign_By(jsonObj.getString("Workassign_By"));
                                    todayTask.setWebUrl(jsonObj.getString("webUrl"));
                                    todayTask.setWork_url(jsonObj.getString("work_url"));
                                    todayTask.setAppSdesc(jsonObj.getString("AppSdesc"));
                                    todayTask.setAppDesc(jsonObj.getString("AppDesc"));
                                    todayTask.setIcon(jsonObj.getString("Icon"));
                                    todayTask.setWorkStatus(jsonObj.getString("WorkStatus"));
                                    todayTask.setClickBLabel(jsonObj.getString("ClickBLabel"));



                                    list.add(todayTask);

                                }
                                // Setup and Handover data to recyclerview

                                HomeRecyclerAdapter_TodayTasks globalAdapter= new HomeRecyclerAdapter_TodayTasks(getActivity(),list);

                                home_recycler.setAdapter(globalAdapter);
                                home_recycler.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                                home_recycler.setHasFixedSize(true);


                            }

                            mainLAyout.setRefreshing(true);

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Server is not Responding !!", Toast.LENGTH_SHORT).show();
                mainLAyout.setRefreshing(false);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
               //params.put(KEY_CUSTOMER_ID, _userId);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

}
