package com.example.erlaljiyadav.mobitask.activites;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.erlaljiyadav.mobitask.R;
import com.example.erlaljiyadav.mobitask.adapters.News_ViewPager;
import com.example.erlaljiyadav.mobitask.modals.ExploreNewsModel;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {
    ViewPager viewPager;
    ImageView settings,img_back_arrow;
    public static ImageView rightarrow,leftarrow;
    ArrayList<ExploreNewsModel> exploreNewsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        img_back_arrow=findViewById(R.id.img_back_arrow);

        rightarrow=findViewById(R.id.right_arrow);
        leftarrow=findViewById(R.id.left_arrow);
        img_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
       // settings=findViewById(R.id.setting_news);

        exploreNewsList= (ArrayList<ExploreNewsModel>) getIntent().getSerializableExtra("virallist");
        viewPager=findViewById(R.id.news_pager);
        viewPager.setAdapter(new News_ViewPager(NewsActivity.this,exploreNewsList));
        viewPager.setCurrentItem(getIntent().getExtras().getInt("position"));

    }
}
