package com.example.gudiya.moviesdemo.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gudiya.moviesdemo.Model.ResultModel;
import com.example.gudiya.moviesdemo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gudiya on 23/09/2016.
 */
public class MoviesAdapter extends BaseAdapter {
    private Context context;
    private List<ResultModel> result_list=new ArrayList<>();


    public MoviesAdapter(Context context,List<ResultModel> results)
    {
        this.context=context;
        this.result_list=results;
    }
    @Override
    public int getCount() {
        return result_list.size();
    }

    @Override
    public Object getItem(int position) {
        return result_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return result_list.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.activity_up_coming_movies, null);
            holder = new ViewHolder();
            holder.mtextViewtitle = (TextView) convertView.findViewById(R.id.textViewtitle);
            holder.mtextViewDate = (TextView) convertView.findViewById(R.id.textViewDate);
            holder.mtextViewAdult = (TextView) convertView.findViewById(R.id.textViewAdult);

            holder.mImageViewMovie = (ImageView) convertView.findViewById(R.id.imageViewPhoto);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.mtextViewtitle.setText(result_list.get(position).getTitle());
        holder.mtextViewDate.setText(result_list.get(position).getReleaseDate());
        boolean adult=result_list.get(position).getAdult();

        if(adult==true)
        {
            holder.mtextViewAdult.setText("A");

        }
        else
        {
            holder.mtextViewAdult.setText("U/A");

        }

        String path=result_list.get(position).getPosterPath();

        if(path!="")
        {

            Picasso.with(context)
                    .load("http://image.tmdb.org/t/p/w780/" + path)
                    .into(holder.mImageViewMovie);
        }

        return convertView;


    }

    private static class ViewHolder {
        TextView mtextViewDate, mtextViewtitle,mtextViewAdult;
        ImageView mImageViewMovie;

    }

}
