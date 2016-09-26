package com.example.gudiya.moviesdemo.Adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.gudiya.moviesdemo.Model.PosterModel;
import com.example.gudiya.moviesdemo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gudiya on 24/09/2016.
 */
public class MoviesImageAdapter extends PagerAdapter
{
    private Context mContext;
    LayoutInflater mLayoutInflater;

    private List<PosterModel> posterModels=new ArrayList<>();
        public MoviesImageAdapter(Context context,List<PosterModel> posterModelList) {
            this.mContext = context;
            this.posterModels = posterModelList;

            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

         }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View itemView = mLayoutInflater.inflate(R.layout.activity_images_row,container,false);
            ImageView imageView = (ImageView) itemView.findViewById(R.id.imageViewPicture);
            String path=posterModels.get(position).getFilePath();
            if(!path.equals(""))
            {
                Picasso.with(mContext).load("http://image.tmdb.org/t/p/w780/" + path).into(imageView);

            }
            else
            {

                imageView.setImageResource(R.mipmap.ic_launcher);
            }
            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }

        @Override
        public int getCount() {
            return posterModels.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
}
