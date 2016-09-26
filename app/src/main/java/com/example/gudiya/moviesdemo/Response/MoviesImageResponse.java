package com.example.gudiya.moviesdemo.Response;

import com.example.gudiya.moviesdemo.Model.BackDropModel;
import com.example.gudiya.moviesdemo.Model.PosterModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gudiya on 24/09/2016.
 */
public class MoviesImageResponse implements Serializable
{
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("backdrops")
    @Expose
    private List<BackDropModel> backdrops = new ArrayList<BackDropModel>();
    @SerializedName("posters")
    @Expose
    private List<PosterModel> posters = new ArrayList<PosterModel>();

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The backdrops
     */
    public List<BackDropModel> getBackdrops() {
        return backdrops;
    }

    /**
     *
     * @param backdrops
     * The backdrops
     */
    public void setBackdrops(List<BackDropModel> backdrops) {
        this.backdrops = backdrops;
    }

    /**
     *
     * @return
     * The posters
     */
    public List<PosterModel> getPosters() {
        return posters;
    }

    /**
     *
     * @param posters
     * The posters
     */
    public void setPosters(List<PosterModel> posters) {
        this.posters = posters;
    }


}
