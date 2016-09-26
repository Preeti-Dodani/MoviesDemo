package com.example.gudiya.moviesdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.gudiya.moviesdemo.Adapter.MoviesImageAdapter;
import com.example.gudiya.moviesdemo.Model.PosterModel;
import com.example.gudiya.moviesdemo.Model.ResultModel;
import com.example.gudiya.moviesdemo.Response.MoviesDeatilsResponse;
import com.example.gudiya.moviesdemo.Response.MoviesImageResponse;
import com.example.gudiya.moviesdemo.Response.ResultResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MoviesActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener{

    TextView mtextViewDate, mtextViewtitle, mtextViewOverView;
    ImageView mImageViewMovie;
    RatingBar mRatingBar;
    public static String base_url = "https://api.themoviedb.org/3/";
    private String API_KEY = "b7cd3340a794e5a2f35e3abb820b497f";
    private String mMovie_id;
    private ViewPager mViewPager;
    private MoviesImageAdapter mAdapter;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    private ImageButton mimagebuttonInfo;
    private TextView mtextviewScreenTitle;
    private Context mContext;



    private List<PosterModel> posterModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_details);
        mContext=MoviesActivity.this;


        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        mtextviewScreenTitle=(TextView)findViewById(R.id.textViewScreentitle);
        mtextviewScreenTitle.setText(String.valueOf("Movie Name"));

        mimagebuttonInfo=(ImageButton)findViewById(R.id.imagebuttoninfo);

        mimagebuttonInfo.setVisibility(View.INVISIBLE);

        mtextViewtitle = (TextView) findViewById(R.id.textViewTitle);
        mtextViewOverView = (TextView) findViewById(R.id.textViewOverView);
        mImageViewMovie = (ImageView) findViewById(R.id.imageViewPicture);
        mRatingBar = (RatingBar) findViewById(R.id.rating_bar);


        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        ResultModel object = (ResultModel) args.getSerializable("ARRAYLIST");

        mMovie_id = String.valueOf(object.getId());
        if (Constants.isInternetWorking(mContext)) {

            fetchData();
            fetchImage();

        }
        else
        {
            Toast.makeText(MoviesActivity.this, "Please check Internet Connection", Toast.LENGTH_SHORT).show();
        }



    }

    public void fetchData() {
        String url = base_url;
        Map<String, String> body = new HashMap<>();
        body.put("api_key", "b7cd3340a794e5a2f35e3abb820b497f");
        RestAdapter restAdapter = new RestAdapter.Builder().
                setEndpoint(url).setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        IRetorfitwebservice iRetrofitWebservice = restAdapter.create(IRetorfitwebservice.class);
        iRetrofitWebservice.getMoviesDeatils(String.valueOf(mMovie_id), String.valueOf(API_KEY), new Callback<MoviesDeatilsResponse>() {


            @Override
            public void success(MoviesDeatilsResponse moviesDeatilsResponse, Response response) {
                try {
                    mtextViewtitle.setText(String.valueOf(moviesDeatilsResponse.getTitle()));
                    mtextViewOverView.setText(String.valueOf(moviesDeatilsResponse.getOverview()));


                    Double stars = moviesDeatilsResponse.getPopularity();

                    if (stars > 0) {

                        String rate = String.valueOf(moviesDeatilsResponse.getPopularity());
                        mRatingBar.setRating(Float.parseFloat(rate));


                    } else {
                        mRatingBar.setRating(0.0f);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


            @Override
            public void failure(RetrofitError error) {

                error.printStackTrace();
            }
        });


    }

    public void fetchImage() {
        String url = base_url;
        Map<String, String> body = new HashMap<>();
        body.put("api_key", "b7cd3340a794e5a2f35e3abb820b497f");
        RestAdapter restAdapter = new RestAdapter.Builder().
                setEndpoint(url).setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        IRetorfitwebservice iRetrofitWebservice = restAdapter.create(IRetorfitwebservice.class);
        iRetrofitWebservice.getMoviesImages(String.valueOf(mMovie_id), String.valueOf(API_KEY), new Callback<MoviesImageResponse>() {


            @Override
            public void success(MoviesImageResponse moviesImageResponse, Response response) {

                try {
                    posterModelList = moviesImageResponse.getPosters();
                    showImages();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    public void showImages()
    {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);
        mAdapter = new MoviesImageAdapter(this, posterModelList);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setOffscreenPageLimit(1);
        setPageViewIndicator();


    }

    private void setPageViewIndicator() {

         dotsCount = mAdapter.getCount();
        dots = new ImageView[5];

        for (int i = 0; i < 5; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            final int presentPosition = i;
            dots[presentPosition].setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    mViewPager.setCurrentItem(presentPosition);
                    return true;
                }

            });


            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem));
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


   }

    @Override
    public void onPageSelected(int position) {

        for (int i = 0; i < 5; i++)
        {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem));
        }

        if(position<5) {

            dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem));
        }
        else
        {

        }

        if (position == 5)
        {
            mViewPager.setCurrentItem(0);
        }
        else
        {

        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}