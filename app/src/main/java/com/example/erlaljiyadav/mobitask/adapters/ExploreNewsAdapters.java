package com.example.erlaljiyadav.mobitask.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.erlaljiyadav.mobitask.R;
import com.example.erlaljiyadav.mobitask.activites.NewsActivity;
import com.example.erlaljiyadav.mobitask.modals.ExploreNewsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ErLalji on 3/16/2018.
 */

public class ExploreNewsAdapters extends RecyclerView.Adapter<ExploreNewsAdapters.ViewHolder> {
    private ArrayList<ExploreNewsModel> exploreNewsModels=new ArrayList<>();
    private Context context;

    public ExploreNewsAdapters(Context context,ArrayList<ExploreNewsModel> exploreNewsModels)
    {
        this.context=context;
        this.exploreNewsModels=exploreNewsModels;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.content_explore_news,parent,false);
        return new ExploreNewsAdapters.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        final ExploreNewsModel position=exploreNewsModels.get(i);
        holder.author=position.getAuthor();
        holder.description=position.getDescription();
        holder.publishedAt=position.getPublishedAt();
        holder.title=position.getTitle();
        holder.url=position.getUrl();
        holder.urlToImage=position.getUrlToImage();
        Log.e("ViralImg",position.getUrlToImage());
        holder.txt_news_title.setText(holder.title);
        holder.txt_news_headlines.setText(holder.description);
        holder.txt_published_at.setText(holder.publishedAt);

        if (position.getUrlToImage().isEmpty())
        {
            Picasso.with(context)
                    .load(R.drawable.movi_task_logo)
                    .placeholder(R.drawable.movi_task_logo)
                    .error(R.drawable.movi_task_logo)
                    .into(holder.img_news); //this is your ImageView
        }else {
            Picasso.with(context)
                    .load(holder.urlToImage)
                    .placeholder(R.drawable.movi_task_logo)
                    .error(R.drawable.movi_task_logo)
                    .into(holder.img_news); //this is your ImageView
        }
    }

    @Override
    public int getItemCount() {
        return exploreNewsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img_news,img_news_share;
        TextView txt_news_headlines,txt_news_title,txt_published_at,txt_readmore;
        String author,title,description,url,urlToImage,publishedAt;

        public ViewHolder(View itemView) {
            super(itemView);
            img_news=itemView.findViewById(R.id.img_news);
            txt_news_headlines=itemView.findViewById(R.id.txt_news_headlines);
            txt_news_title=itemView.findViewById(R.id.txt_news_title);
            txt_published_at=itemView.findViewById(R.id.txt_published_at);
            img_news_share=itemView.findViewById(R.id.img_news_share);
            txt_readmore=itemView.findViewById(R.id.txt_readmore);
            txt_readmore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startNewsActivity();
                }
            });

            img_news.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startNewsActivity();
                }
            });



            img_news_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    share_news();
                }
            });
        }
//....................This method for share news..........................................

        public void share_news()
        {
            Intent i=new Intent(android.content.Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(android.content.Intent.EXTRA_SUBJECT,title);
            i.putExtra(android.content.Intent.EXTRA_TEXT, title+"\n"+description+"\n"+url);
            context.startActivity(Intent.createChooser(i,"Share via"));
        }

        public void startNewsActivity()
        {
            Intent intent=new Intent(context, NewsActivity.class);
            intent.putExtra("position",getAdapterPosition());
            intent.putExtra("virallist",exploreNewsModels);
            context.startActivity(intent);
        }



    }

}
