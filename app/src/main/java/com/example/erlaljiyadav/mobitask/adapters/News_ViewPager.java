package com.example.erlaljiyadav.mobitask.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.erlaljiyadav.mobitask.R;
import com.example.erlaljiyadav.mobitask.modals.ExploreNewsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by android on 30/12/17.
 */

public class News_ViewPager extends PagerAdapter {
    private ArrayList<ExploreNewsModel> explorenewsLists;
    private LayoutInflater inflater;
    Context context;
    public News_ViewPager(Context context, ArrayList<ExploreNewsModel> explorenewsLists){
        this.context=context;
        this.explorenewsLists=explorenewsLists;
    }

    @Override
    public int getCount() {
        return explorenewsLists.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View viewLayout = inflater.inflate(R.layout.news_layout, container,false);

        ImageView viralimg=viewLayout.findViewById(R.id.expanded_image);
        TextView viraltext=viewLayout.findViewById(R.id.expanded_text);
        TextView title=viewLayout.findViewById(R.id.expanded_title);
        Button fullNews=viewLayout.findViewById(R.id.fullNews);
        TextView auther=viewLayout.findViewById(R.id.author);
        TextView postDate=viewLayout.findViewById(R.id.date);
        final WebView news_web=viewLayout.findViewById(R.id.news_webView);
        final ExploreNewsModel exploreNewsModel =explorenewsLists.get(position);




//////////////////////////setting data to views////////////////
        Picasso.with(context).load(exploreNewsModel.getUrlToImage()).into(viralimg);
        title.setText(exploreNewsModel.getTitle());
        viraltext.setText(exploreNewsModel.getDescription());
        postDate.setText("Post Date: "+exploreNewsModel.getPublishedAt().substring(0,10).replace("T"," ").replace("+00:00",""));

        if (!exploreNewsModel.getAuthor().equalsIgnoreCase("null")){
            auther.setText("Posted by: "+exploreNewsModel.getAuthor());

        }
        else {
            auther.setText("Posted by: ");

        }


        news_web.setWebViewClient(new WebViewClient());

        news_web.loadUrl(exploreNewsModel.getUrl());
        news_web.getSettings().setJavaScriptEnabled(true);
        news_web.setHorizontalScrollBarEnabled(false);
//

        fullNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                news_web.setVisibility(View.VISIBLE);
                viewLayout.findViewById(R.id.linear_layout).setVisibility(View.GONE);
            }
        });


        (container).addView(viewLayout);

        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {


        ((ViewPager)container).removeView((View)object);
    }

}
