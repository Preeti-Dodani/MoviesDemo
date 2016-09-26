package com.example.gudiya.moviesdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gudiya.moviesdemo.Adapter.MoviesAdapter;
import com.example.gudiya.moviesdemo.Model.ResultModel;
import com.example.gudiya.moviesdemo.Response.ResultResponse;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView mListViewMovies;
    private TextView mtextviewScreenTitle;
    private Gson gson=new Gson();
    public static String base_url="https://api.themoviedb.org/3/movie/";
    public List<ResultModel> result =new ArrayList<>();
    private MoviesAdapter moviesAdapter;
    private String API_KEY="b7cd3340a794e5a2f35e3abb820b497f";
    private ImageButton mimagebuttonInfo;

    private Context mContext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);
        mContext=MainActivity.this;
        mListViewMovies=(ListView)findViewById(R.id.listViewMovies);
        mtextviewScreenTitle=(TextView)findViewById(R.id.textViewScreentitle);
        mtextviewScreenTitle.setText(String.valueOf("UpComing Movies"));
        mimagebuttonInfo=(ImageButton)findViewById(R.id.imagebuttoninfo);
        mimagebuttonInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,InformationScreenActivity.class);
                startActivity(i);

            }
        });
        if (Constants.isInternetWorking(mContext)) {

            fetchData();
            showList();

        }
        else
        {
            Toast.makeText(MainActivity.this,"Please check Internet Connection",Toast.LENGTH_SHORT).show();
        }

    }

    public void fetchData()
    {
        Map<String, String> body = new HashMap<>();
        body.put("api_key", "b7cd3340a794e5a2f35e3abb820b497f");
        RestAdapter restAdapter = new RestAdapter.Builder().
                setEndpoint(base_url).setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
         IRetorfitwebservice iRetrofitWebservice = restAdapter.create(IRetorfitwebservice.class);
        iRetrofitWebservice.getMoviesList(String.valueOf(API_KEY), new Callback<ResultResponse>() {


            @Override
            public void success(ResultResponse resultResponse, Response response) {
                Log.d("response","response"+resultResponse.getPage());

                Log.d("response","response"+response.toString());
                result=resultResponse.getResults();
                showList();

            }

            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();

            }
        });
    }

   /* @Override
    public void success(ResultResponse resultResponse, Response response)
    {
        result=resultResponse.getResults();
        Toast.makeText(MainActivity.this,"poston"+result.size(),Toast.LENGTH_LONG).show();

        showList();

    }

    @Override
    public void failure(RetrofitError error) {
        error.printStackTrace();

    }*/

    public void showList()
    {

        moviesAdapter=new MoviesAdapter(this,result);
        mListViewMovies.setAdapter(moviesAdapter);
        moviesAdapter.notifyDataSetChanged();
        mListViewMovies.setOnItemClickListener(this);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String title = result.get(position).getTitle();
        Intent i=new Intent(MainActivity.this,MoviesActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable)result.get(position));
        i.putExtra("BUNDLE", args);
        startActivity(i);




    }
}
