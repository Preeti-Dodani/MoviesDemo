package com.example.gudiya.moviesdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Gudiya on 24/09/2016.
 */
public class InformationScreenActivity extends AppCompatActivity {

    private ImageButton mimagebuttonInfo;
    private TextView mtextviewScreenTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inforamtion_screen);
        mtextviewScreenTitle=(TextView)findViewById(R.id.textViewScreentitle);
        mtextviewScreenTitle.setText(String.valueOf("Information"));

        mimagebuttonInfo=(ImageButton)findViewById(R.id.imagebuttoninfo);

        mimagebuttonInfo.setVisibility(View.INVISIBLE);


    }
}
