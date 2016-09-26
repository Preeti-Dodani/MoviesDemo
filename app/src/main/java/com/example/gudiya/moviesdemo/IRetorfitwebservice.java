package com.example.gudiya.moviesdemo;

import com.example.gudiya.moviesdemo.Response.MoviesDeatilsResponse;
import com.example.gudiya.moviesdemo.Response.MoviesImageResponse;
import com.example.gudiya.moviesdemo.Response.ResultResponse;

import java.util.Map;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Gudiya on 23/09/2016.
 */
public interface IRetorfitwebservice {
    @GET("/upcoming")
    public void getMoviesList(@Query("api_key") String api_key,
                                    Callback<ResultResponse> callback);

    @GET("/movie/{id}")
    public void getMoviesDeatils(@Path("id") String groupId,@Query("api_key") String api_key,
                              Callback<MoviesDeatilsResponse> callback);


    @GET("/movie/{id}/images")
    public void getMoviesImages(@Path("id") String groupId,@Query("api_key") String api_key,
                                 Callback<MoviesImageResponse> callback);


   /* @GET("/upcoming?api_key=b7cd3340a794e5a2f35e3abb820b497f")
    public void getMoviesList(Callback<ResultResponse> callback);
*/
}
