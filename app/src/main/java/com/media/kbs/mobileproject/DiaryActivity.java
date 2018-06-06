package com.media.kbs.mobileproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class DiaryActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_content);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        //버튼 클릭시 리스트뷰로 이동
        Button breakfast_btn = (Button) findViewById(R.id.breakfast_btn) ;
        breakfast_btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DB에 식단선택과 식단종류 전달
                Intent intent = new Intent(DiaryActivity.this, FoodListActivity.class) ;
                startActivity(intent) ;
            }
        });
        Button lunch_btn = (Button) findViewById(R.id.lunch_btn) ;
        lunch_btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DB에 식단선택과 식단종류 전달
                Intent intent = new Intent(DiaryActivity.this, FoodListActivity.class) ;
                startActivity(intent) ;
            }
        });
        Button dinner_btn = (Button) findViewById(R.id.dinner_btn) ;
        dinner_btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DB에 식단선택과 식단종류 전달
                Intent intent = new Intent(DiaryActivity.this, FoodListActivity.class) ;
                startActivity(intent) ;
            }
        });
        Button exercise_btn = (Button) findViewById(R.id.exercise_btn) ;
        exercise_btn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DB에 운동선택과 운동종류 전달
                Intent intent = new Intent(DiaryActivity.this, TrainingListActivity.class) ;
                startActivity(intent) ;
            }
        });

    }
}
