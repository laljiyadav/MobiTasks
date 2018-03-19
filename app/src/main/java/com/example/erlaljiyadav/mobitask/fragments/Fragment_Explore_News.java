package com.example.erlaljiyadav.mobitask.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.erlaljiyadav.mobitask.R;
import com.example.erlaljiyadav.mobitask.adapters.ExploreNewsAdapters;
import com.example.erlaljiyadav.mobitask.modals.ExploreNewsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment_Explore_News extends Fragment {
    RecyclerView recyclerview_explore;


    public Fragment_Explore_News() {
        // Required empty public constructor
    }

    public static Fragment_Explore_News newInstance() {

        return new Fragment_Explore_News();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_fragment__explore__news, container, false);
        recyclerview_explore=view.findViewById(R.id.recyclerview_explore);

        loadExploreNews();

        return view;
    }
    //////////////////////load explore news..........................//
    private void loadExploreNews(){
//        /////////////id for created wte.jrappdeveloper@gmail.com////////////////////////////////////
        String news_API="https://newsapi.org/v2/top-headlines?sources="+"medical-news-today"+"&apiKey=28e9c0851efd456abf782dc9a37e33e2";
        Log.e("url",news_API);
//      String news_API2="https://newsapi.org/v2/top-headlines?sources=medical-news-today&apiKey=28e9c0851efd456abf782dc9a37e33e2";
//      String news_API3="https://newsapi.org/v2/top-headlines?sources=espn-cric-info&apiKey=28e9c0851efd456abf782dc9a37e33e2";
//      String entertain="https://newsapi.org/v2/top-headlines?sources=buzzfeed&apiKey=28e9c0851efd456abf782dc9a37e33e2";





        StringRequest stringRequest = new StringRequest(Request.Method.GET, news_API,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<ExploreNewsModel> virallist = new ArrayList<>();


                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Log.e("news",response);
                            if (jsonObject.get("status").equals("ok")){

                                JSONArray article=jsonObject.getJSONArray("articles");

                                for (int i=0;i<article.length();i++){

                                    ExploreNewsModel viralNewsModel=new ExploreNewsModel();

                                    viralNewsModel.setAuthor(article.getJSONObject(i).getString("author"));
                                    viralNewsModel.setTitle(article.getJSONObject(i).getString("title"));
                                    viralNewsModel.setDescription(article.getJSONObject(i).getString("description"));
                                    viralNewsModel.setUrl(article.getJSONObject(i).getString("url"));
                                    viralNewsModel.setUrlToImage(article.getJSONObject(i).getString("urlToImage"));
                                    viralNewsModel.setPublishedAt(article.getJSONObject(i).getString("publishedAt"));
                                    virallist.add(viralNewsModel);

                                }
                            }

                            Log.e("virallist",String.valueOf(virallist.size()));

////////////.............................Setup and Handover data to recyclerview

                            ExploreNewsAdapters globalAdapter= new ExploreNewsAdapters(getActivity(),virallist);

                            recyclerview_explore.setAdapter(globalAdapter);
                            recyclerview_explore.setLayoutManager(new LinearLayoutManager(getActivity()));
                            recyclerview_explore.setHasFixedSize(true);


                        } catch (JSONException e) {
                            Log.e("error_news",e.toString());

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));


    }

}
