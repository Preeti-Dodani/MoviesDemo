package com.example.gudiya.moviesdemo.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Gudiya on 23/09/2016.
 */
public class DatesModel implements Serializable {

    @SerializedName("maximum")
    @Expose
    private String maximum;
    @SerializedName("minimum")
    @Expose
    private String minimum;

    /**
     *
     * @return
     * The maximum
     */
    public String getMaximum() {
        return maximum;
    }

    /**
     *
     * @param maximum
     * The maximum
     */
    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    /**
     *
     * @return
     * The minimum
     */
    public String getMinimum() {
        return minimum;
    }

    /**
     *
     * @param minimum
     * The minimum
     */
    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

}
