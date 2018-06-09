package com.media.kbs.mobileproject;

import android.app.ListActivity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class DiaryActivity extends AppCompatActivity{
    private Button breakButton;
    private final static int LIST_REQUEST = 1;
    private TextView breakfastText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_content);

        breakButton= findViewById(R.id.breakfast_btn);
        breakfastText = (TextView) findViewById(R.id.breakfast_content);
        breakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiaryActivity.this, com.media.kbs.mobileproject.ListActivity.class);
                startActivityForResult(intent, LIST_REQUEST);
            }
        });



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == LIST_REQUEST){
            if(resultCode == RESULT_OK){
                breakfastText.setText(data.getStringExtra("name"));
            }
        }
    }
}
