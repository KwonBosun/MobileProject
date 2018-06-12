package com.media.kbs.mobileproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DiaryActivity extends AppCompatActivity {
    private Button breakButton;
    private Button lunchButton;
    private Button dinnerButton;
    private Button exerButton;
    private final static int LIST_BREAK = 1;
    private final static int LIST_LUNCH = 2;
    private final static int LIST_DINNER = 3;
    private final static int LIST_EXERCISE =4;
    private TextView breakfastText;
    private TextView lunchText;
    private TextView dinnerText;
    private TextView CarbonWeight;
    private TextView ProteinWeight;
    private TextView FatWeight;
    private TextView SodiumWeight;
    private TextView Kcal;
    private TextView ExerName;
    private double calTotal = 0;
    private double carbonTotal = 0;
    private double proteinTotal = 0;
    private double fatTotal = 0;
    private double sodiumTotal = 0;
    private double calBreak = 0;
    private double carbonBreak = 0;
    private double proteinBreak = 0;
    private double fatBreak = 0;
    private double sodiumBreak = 0;
    private double calLunch = 0;
    private double carbonLunch = 0;
    private double proteinLunch = 0;
    private double fatLunch = 0;
    private double sodiumLunch = 0;
    private double calDinner = 0;
    private double carbonDinner = 0;
    private double proteinDinner = 0;
    private double fatDinner = 0;
    private double sodiumDinner = 0;
    private double exerKcal=0;
    private double exerTotal=0;

    private String getTime = "";
    public static String date;
    public static DBHelper dbHelper = null;
    private ListItem listItem_b;
    private ListItem listItem_l;
    private ListItem listItem_d;
    private ListItem listItem_e;
    private String foodname_break = "";
    private String foodname_lunch = "";
    private String foodname_dinner = "";
    private String breakTotal = "";
    private String exerName="";
    String totalSum = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_content);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent getIntent = getIntent();
        date = getIntent.getStringExtra("date");

        Log.d("MAIN", "date :" + date);
        if (dbHelper == null) {
            Log.d("MAIN", "db null");
            dbHelper = new DBHelper(DiaryActivity.this, "food.db", null, 1);
        }
        dbHelper.createDataInfo(date);


        breakfastText = (TextView) findViewById(R.id.breakfast_content);
        lunchText = findViewById(R.id.lunch_content);
        dinnerText = findViewById(R.id.dinner_content);
        CarbonWeight = findViewById(R.id.carbon);
        ProteinWeight = findViewById(R.id.protein);
        FatWeight = findViewById(R.id.fat);
        SodiumWeight = findViewById(R.id.sodium);
        Kcal = findViewById(R.id.calText);
        //lunchText.setText(String.format("%s",listItems.get(1).getCarbonKcal()));
        breakButton = findViewById(R.id.breakfast_btn);
        lunchButton = findViewById(R.id.lunch_btn);
        dinnerButton = findViewById(R.id.dinner_btn);
        exerButton=findViewById(R.id.exercise_btn);
        ExerName=findViewById(R.id.exer_content);

        load();
        breakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(DiaryActivity.this, ListActivity.class), LIST_BREAK);

            }
        });
        lunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(DiaryActivity.this, ListActivity.class), LIST_LUNCH);
            }
        });
        dinnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(DiaryActivity.this, ListActivity.class), LIST_DINNER);
            }
        });
        exerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(DiaryActivity.this, TrainingListActivity.class), LIST_EXERCISE);
            }
        });


    }

    private void load() {
        listItem_b = dbHelper.getDataInfo("breakfast");
        listItem_l = dbHelper.getDataInfo("lunch");
        listItem_d = dbHelper.getDataInfo("dinner");
        listItem_e = dbHelper.getDataInfo("exercise");
        if (listItem_l != null && listItem_d != null && listItem_b != null) {
            carbonTotal = listItem_b.getCarbonKcal() + listItem_l.getCarbonKcal() + listItem_d.getCarbonKcal();
            proteinTotal = listItem_b.getProteinKcal() + listItem_l.getProteinKcal() + listItem_d.getProteinKcal();
            fatTotal = listItem_b.getFatKcal() + listItem_l.getFatKcal() + listItem_d.getFatKcal();
            sodiumTotal = listItem_b.getSodiumKcal() + listItem_l.getSodiumKcal() + listItem_d.getSodiumKcal();
            calTotal = listItem_b.getTotalKcal() + listItem_l.getTotalKcal() + listItem_d.getTotalKcal();

            String breakfast = listItem_b.getNameBreak();
            String lunch = listItem_l.getNameLunch();
            String dinner = listItem_d.getNameDinner();
            breakfastText.setText(breakfast);
            lunchText.setText(lunch);
            dinnerText.setText(dinner);

            Kcal.setText(String.format("%.2f", calTotal) + "kcal");
            CarbonWeight.setText(String.format("%.2f", carbonTotal) + "g");
            ProteinWeight.setText(String.format("%.2f", proteinTotal) + "g");
            FatWeight.setText(String.format("%.2f", fatTotal) + "g");
            SodiumWeight.setText(String.format("%.2f", sodiumTotal) + "mg");
        } else if (listItem_b != null && listItem_l != null) {
            carbonTotal = listItem_b.getCarbonKcal() + listItem_l.getCarbonKcal();
            proteinTotal = listItem_b.getProteinKcal() + listItem_l.getProteinKcal();
            fatTotal = listItem_b.getFatKcal() + listItem_l.getFatKcal();
            sodiumTotal = listItem_b.getSodiumKcal() + listItem_l.getSodiumKcal();
            calTotal = listItem_b.getTotalKcal() + listItem_l.getTotalKcal();

            String breakfast = listItem_b.getNameBreak();
            String lunch = listItem_l.getNameLunch();
            breakfastText.setText(breakfast);
            lunchText.setText(lunch);
            Kcal.setText(String.format("%.2f", calTotal) + "kcal");
            CarbonWeight.setText(String.format("%.2f", carbonTotal) + "g");
            ProteinWeight.setText(String.format("%.2f", proteinTotal) + "g");
            FatWeight.setText(String.format("%.2f", fatTotal) + "g");
            SodiumWeight.setText(String.format("%.2f", sodiumTotal) + "mg");
        } else if (listItem_b != null && listItem_d != null) {
            carbonTotal = listItem_b.getCarbonKcal() + listItem_d.getCarbonKcal();
            proteinTotal = listItem_b.getProteinKcal() + listItem_d.getProteinKcal();
            fatTotal = listItem_b.getFatKcal() + listItem_d.getFatKcal();
            sodiumTotal = listItem_b.getSodiumKcal() + listItem_d.getSodiumKcal();
            calTotal = listItem_b.getTotalKcal() + listItem_d.getTotalKcal();
            String breakfast = listItem_b.getNameBreak();
            String dinner = listItem_d.getNameDinner();

            breakfastText.setText(breakfast);
            dinnerText.setText(dinner);

            Kcal.setText(calTotal + "kcal");
            CarbonWeight.setText(carbonTotal + "g");
            ProteinWeight.setText(proteinTotal + "g");
            FatWeight.setText(fatTotal + "g");
            SodiumWeight.setText(sodiumTotal + "mg");
        } else if (listItem_l != null && listItem_d != null) {
            carbonTotal = listItem_l.getCarbonKcal() + listItem_d.getCarbonKcal();
            proteinTotal = listItem_l.getProteinKcal() + listItem_d.getProteinKcal();
            fatTotal = listItem_l.getFatKcal() + listItem_d.getFatKcal();
            sodiumTotal = listItem_l.getSodiumKcal() + listItem_d.getSodiumKcal();
            calTotal = listItem_l.getTotalKcal() + listItem_l.getTotalKcal();

            String lunch = listItem_l.getNameLunch();
            String dinner = listItem_d.getNameDinner();

            lunchText.setText(lunch);
            dinnerText.setText(dinner);
            Kcal.setText(String.format("%.2f", calTotal) + "kcal");
            CarbonWeight.setText(String.format("%.2f", carbonTotal) + "g");
            ProteinWeight.setText(String.format("%.2f", proteinTotal) + "g");
            FatWeight.setText(String.format("%.2f", fatTotal) + "g");
            SodiumWeight.setText(String.format("%.2f", sodiumTotal) + "mg");
        } else if (listItem_b != null) {
            carbonTotal = listItem_b.getCarbonKcal();
            proteinTotal = listItem_b.getProteinKcal();
            fatTotal = listItem_b.getFatKcal();
            sodiumTotal = listItem_b.getSodiumKcal();
            calTotal = listItem_b.getTotalKcal();

            String breakfast = listItem_b.getNameBreak();
            breakfastText.setText(breakfast);

            Kcal.setText(String.format("%.2f", calTotal) + "kcal");
            CarbonWeight.setText(String.format("%.2f", carbonTotal) + "g");
            ProteinWeight.setText(String.format("%.2f", proteinTotal) + "g");
            FatWeight.setText(String.format("%.2f", fatTotal) + "g");
            SodiumWeight.setText(String.format("%.2f", sodiumTotal) + "mg");
        } else if (listItem_l != null) {
            carbonTotal = listItem_l.getCarbonKcal();
            proteinTotal = listItem_l.getProteinKcal();
            fatTotal = listItem_l.getFatKcal();
            sodiumTotal = listItem_l.getSodiumKcal();
            calTotal = listItem_l.getTotalKcal();

            String lunch = listItem_l.getNameLunch();

            lunchText.setText(lunch);

            Kcal.setText(String.format("%.2f", calTotal) + "kcal");
            CarbonWeight.setText(String.format("%.2f", carbonTotal) + "g");
            ProteinWeight.setText(String.format("%.2f", proteinTotal) + "g");
            FatWeight.setText(String.format("%.2f", fatTotal) + "g");
            SodiumWeight.setText(String.format("%.2f", sodiumTotal) + "mg");
        } else if (listItem_d != null) {
            carbonTotal = listItem_d.getCarbonKcal();
            proteinTotal = listItem_d.getProteinKcal();
            fatTotal = listItem_d.getFatKcal();
            sodiumTotal = listItem_d.getSodiumKcal();
            calTotal = listItem_d.getTotalKcal();

            String dinner = listItem_d.getNameDinner();

            dinnerText.setText(dinner);
            Kcal.setText(String.format("%.2f", calTotal) + "kcal");
            CarbonWeight.setText(String.format("%.2f", carbonTotal) + "g");
            ProteinWeight.setText(String.format("%.2f", proteinTotal) + "g");
            FatWeight.setText(String.format("%.2f", fatTotal) + "g");
            SodiumWeight.setText(String.format("%.2f", sodiumTotal) + "mg");
        }
        if (listItem_l != null && listItem_d != null && listItem_b != null&&listItem_e!=null) {
            carbonTotal = listItem_b.getCarbonKcal() + listItem_l.getCarbonKcal() + listItem_d.getCarbonKcal();
            proteinTotal = listItem_b.getProteinKcal() + listItem_l.getProteinKcal() + listItem_d.getProteinKcal();
            fatTotal = listItem_b.getFatKcal() + listItem_l.getFatKcal() + listItem_d.getFatKcal();
            sodiumTotal = listItem_b.getSodiumKcal() + listItem_l.getSodiumKcal() + listItem_d.getSodiumKcal();
            calTotal = listItem_b.getTotalKcal() + listItem_l.getTotalKcal() + listItem_d.getTotalKcal();
            exerTotal=listItem_e.getExerKcal();


            String breakfast = listItem_b.getNameBreak();
            String lunch = listItem_l.getNameLunch();
            String dinner = listItem_d.getNameDinner();
            String exercise = listItem_e.getNameExer();
            breakfastText.setText(breakfast);
            lunchText.setText(lunch);
            dinnerText.setText(dinner);


            Kcal.setText(String.format("%.2f", calTotal) + "kcal");
            CarbonWeight.setText(String.format("%.2f", carbonTotal) + "g");
            ProteinWeight.setText(String.format("%.2f", proteinTotal) + "g");
            FatWeight.setText(String.format("%.2f", fatTotal) + "g");
            SodiumWeight.setText(String.format("%.2f", sodiumTotal) + "mg");
        } else if (listItem_b != null && listItem_l != null&&listItem_e!=null) {
            carbonTotal = listItem_b.getCarbonKcal() + listItem_l.getCarbonKcal();
            proteinTotal = listItem_b.getProteinKcal() + listItem_l.getProteinKcal();
            fatTotal = listItem_b.getFatKcal() + listItem_l.getFatKcal();
            sodiumTotal = listItem_b.getSodiumKcal() + listItem_l.getSodiumKcal();
            calTotal = listItem_b.getTotalKcal() + listItem_l.getTotalKcal();
            exerTotal=listItem_e.getExerKcal();
            String breakfast = listItem_b.getNameBreak();
            String lunch = listItem_l.getNameLunch();
            String exercise = listItem_e.getNameExer();

            breakfastText.setText(breakfast);
            lunchText.setText(lunch);
            Kcal.setText(String.format("%.2f", calTotal) + "kcal");
            CarbonWeight.setText(String.format("%.2f", carbonTotal) + "g");
            ProteinWeight.setText(String.format("%.2f", proteinTotal) + "g");
            FatWeight.setText(String.format("%.2f", fatTotal) + "g");
            SodiumWeight.setText(String.format("%.2f", sodiumTotal) + "mg");
        } else if (listItem_b != null && listItem_d != null&&listItem_e!=null) {
            carbonTotal = listItem_b.getCarbonKcal() + listItem_d.getCarbonKcal();
            proteinTotal = listItem_b.getProteinKcal() + listItem_d.getProteinKcal();
            fatTotal = listItem_b.getFatKcal() + listItem_d.getFatKcal();
            sodiumTotal = listItem_b.getSodiumKcal() + listItem_d.getSodiumKcal();
            calTotal = listItem_b.getTotalKcal() + listItem_d.getTotalKcal();
            exerTotal=listItem_e.getExerKcal();
            String breakfast = listItem_b.getNameBreak();
            String dinner = listItem_d.getNameDinner();
            String exercise = listItem_e.getNameExer();

            breakfastText.setText(breakfast);
            dinnerText.setText(dinner);
            ExerName.setText(exercise);
            Kcal.setText(String.format("%.2f", calTotal) + "kcal");
            CarbonWeight.setText(String.format("%.2f", carbonTotal) + "g");
            ProteinWeight.setText(String.format("%.2f", proteinTotal) + "g");
            FatWeight.setText(String.format("%.2f", fatTotal) + "g");
            SodiumWeight.setText(String.format("%.2f", sodiumTotal) + "mg");
        } else if (listItem_l != null && listItem_d != null&&listItem_e!=null) {
            carbonTotal = listItem_l.getCarbonKcal() + listItem_d.getCarbonKcal();
            proteinTotal = listItem_l.getProteinKcal() + listItem_d.getProteinKcal();
            fatTotal = listItem_l.getFatKcal() + listItem_d.getFatKcal();
            sodiumTotal = listItem_l.getSodiumKcal() + listItem_d.getSodiumKcal();
            calTotal = listItem_l.getTotalKcal() + listItem_l.getTotalKcal();
            exerTotal=listItem_e.getExerKcal();
            String lunch = listItem_l.getNameLunch();
            String dinner = listItem_d.getNameDinner();
            String exercise = listItem_e.getNameExer();

            ExerName.setText(exercise);
            lunchText.setText(lunch);
            dinnerText.setText(dinner);
            Kcal.setText(String.format("%.2f", calTotal) + "kcal");
            CarbonWeight.setText(String.format("%.2f", carbonTotal) + "g");
            ProteinWeight.setText(String.format("%.2f", proteinTotal) + "g");
            FatWeight.setText(String.format("%.2f", fatTotal) + "g");
            SodiumWeight.setText(String.format("%.2f", sodiumTotal) + "mg");
        } else if (listItem_b != null&&listItem_e!=null) {
            carbonTotal = listItem_b.getCarbonKcal();
            proteinTotal = listItem_b.getProteinKcal();
            fatTotal = listItem_b.getFatKcal();
            sodiumTotal = listItem_b.getSodiumKcal();
            calTotal = listItem_b.getTotalKcal();
            exerTotal=listItem_e.getExerKcal();
            String breakfast = listItem_b.getNameBreak();
            breakfastText.setText(breakfast);
            String exercise = listItem_e.getNameExer();

            ExerName.setText(exercise);
            Kcal.setText(String.format("%.2f", calTotal) + "kcal");
            CarbonWeight.setText(String.format("%.2f", carbonTotal) + "g");
            ProteinWeight.setText(String.format("%.2f", proteinTotal) + "g");
            FatWeight.setText(String.format("%.2f", fatTotal) + "g");
            SodiumWeight.setText(String.format("%.2f", sodiumTotal) + "mg");
        } else if (listItem_l != null&&listItem_e!=null) {
            carbonTotal = listItem_l.getCarbonKcal();
            proteinTotal = listItem_l.getProteinKcal();
            fatTotal = listItem_l.getFatKcal();
            sodiumTotal = listItem_l.getSodiumKcal();
            calTotal = listItem_l.getTotalKcal();
            exerTotal=listItem_e.getExerKcal();
            String lunch = listItem_l.getNameLunch();
            String exercise = listItem_e.getNameExer();

            lunchText.setText(lunch);
            ExerName.setText(exercise);
            Kcal.setText(String.format("%.2f", calTotal) + "kcal");
            CarbonWeight.setText(String.format("%.2f", carbonTotal) + "g");
            ProteinWeight.setText(String.format("%.2f", proteinTotal) + "g");
            FatWeight.setText(String.format("%.2f", fatTotal) + "g");
            SodiumWeight.setText(String.format("%.2f", sodiumTotal) + "mg");
        } else if (listItem_d != null&&listItem_e!=null) {
            carbonTotal = listItem_d.getCarbonKcal();
            proteinTotal = listItem_d.getProteinKcal();
            fatTotal = listItem_d.getFatKcal();
            sodiumTotal = listItem_d.getSodiumKcal();
            calTotal = listItem_d.getTotalKcal();
            exerTotal=listItem_e.getExerKcal();
            String dinner = listItem_d.getNameDinner();
            String exercise = listItem_e.getNameExer();
            ExerName.setText(exercise);
            dinnerText.setText(dinner);
            Kcal.setText(String.format("%.2f", calTotal) + "kcal");
            CarbonWeight.setText(String.format("%.2f", carbonTotal) + "g");
            ProteinWeight.setText(String.format("%.2f", proteinTotal) + "g");
            FatWeight.setText(String.format("%.2f", fatTotal) + "g");
            SodiumWeight.setText(String.format("%.2f", sodiumTotal) + "mg");
        }else if (listItem_e!=null){
            String exercise = listItem_e.getNameExer();
            ExerName.setText(exercise);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        load();
//        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "FoodInfo.db", null, 1);

//
//        listItems = dbHelper.getDataInfo(getTime);
//        lunchText.setText(String.format("%s",listItems.get(1).getCarbonKcal()));


    }

    @Override
    protected void onPause() {
        super.onPause();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        final DBHelper dbHelper = new DBHelper(getApplicationContext(), "FoodInfo.db", null, 1);
        if (requestCode == LIST_BREAK) {
            if (resultCode == RESULT_OK) {

                if (listItem_b == null) {
                    breakTotal += data.getStringExtra("name");
                    calBreak += data.getDoubleExtra("total", 0);
                    carbonBreak += data.getDoubleExtra("carbon", 0);
                    proteinBreak += data.getDoubleExtra("protein", 0);
                    fatBreak += data.getDoubleExtra("fat", 0);
                    sodiumBreak += data.getDoubleExtra("sodium", 0);

                } else {
                    breakTotal = listItem_b.getNameBreak();
                    breakTotal += data.getStringExtra("name") + " ";
                    calBreak = listItem_b.getTotalKcal();
                    calBreak += data.getDoubleExtra("total", 0);
                    carbonBreak=listItem_b.getCarbonKcal();
                    carbonBreak += data.getDoubleExtra("carbon", 0);
                    proteinBreak = listItem_b.getProteinKcal();
                    proteinBreak += data.getDoubleExtra("protein", 0);
                    fatBreak = listItem_b.getFatKcal();
                    fatBreak += data.getDoubleExtra("fat", 0);
                    sodiumBreak = listItem_b.getFatKcal();
                    sodiumBreak += data.getDoubleExtra("sodium", 0);
                }


                dbHelper.insertDataInfo("breakfast", calBreak, carbonBreak, proteinBreak, fatBreak, sodiumBreak, breakTotal, foodname_lunch, foodname_dinner,exerName,exerKcal);

                calTotal += calBreak;
                carbonTotal += carbonBreak;
                proteinTotal += proteinBreak;
                fatTotal += fatBreak;
                sodiumTotal += sodiumBreak;

            }
        }
        if (requestCode == LIST_LUNCH) {
            if (resultCode == RESULT_OK) {
                if (listItem_l == null) {
                    foodname_lunch += data.getStringExtra("name");
                    carbonLunch += data.getDoubleExtra("carbon", 0);
                    calLunch += data.getDoubleExtra("total", 0);
                    proteinLunch += data.getDoubleExtra("protein", 0);
                    fatLunch += data.getDoubleExtra("fat", 0);
                    sodiumLunch += data.getDoubleExtra("sodium", 0);
                } else {
                    foodname_lunch = listItem_l.getNameLunch();
                    foodname_lunch += data.getStringExtra("name") + " ";
                    calLunch = listItem_l.getTotalKcal();
                    calLunch += data.getDoubleExtra("total", 0);
                    carbonLunch=listItem_l.getCarbonKcal();
                    carbonLunch += data.getDoubleExtra("carbon", 0);
                    proteinLunch = listItem_l.getProteinKcal();
                    proteinLunch += data.getDoubleExtra("protein", 0);
                    fatLunch = listItem_l.getFatKcal();
                    fatLunch += data.getDoubleExtra("fat", 0);
                    sodiumLunch = listItem_l.getFatKcal();
                    sodiumLunch += data.getDoubleExtra("sodium", 0);
                }

                    calTotal += calLunch;
                    carbonTotal += carbonLunch;
                    proteinTotal += proteinLunch;
                    fatTotal += fatLunch;
                    sodiumTotal += sodiumLunch;
                    dbHelper.insertDataInfo("lunch", calBreak, carbonBreak, proteinBreak, fatBreak, sodiumBreak, breakTotal, foodname_lunch, foodname_dinner,exerName,exerKcal);



                }
            }
            if (requestCode == LIST_DINNER) {
                if (resultCode == RESULT_OK) {

                    if (listItem_d == null) {
                        foodname_dinner += data.getStringExtra("name");
                        calDinner += data.getDoubleExtra("total", 0);
                        carbonDinner += data.getDoubleExtra("carbon", 0);
                        proteinDinner += data.getDoubleExtra("protein", 0);
                        fatDinner += data.getDoubleExtra("fat", 0);
                        sodiumDinner += data.getDoubleExtra("sodium", 0);
                    } else {
                        foodname_dinner = listItem_d.getNameDinner();
                        foodname_dinner += data.getStringExtra("name") + " ";
                        calDinner = listItem_d.getTotalKcal();
                        calDinner += data.getDoubleExtra("total", 0);
                        carbonDinner = listItem_d.getCarbonKcal();
                        carbonDinner += data.getDoubleExtra("carbon", 0);
                        proteinDinner = listItem_d.getProteinKcal();
                        proteinDinner += data.getDoubleExtra("protein", 0);
                        fatDinner = listItem_d.getFatKcal();
                        fatDinner += data.getDoubleExtra("fat", 0);
                        sodiumDinner = listItem_d.getFatKcal();
                        sodiumDinner += data.getDoubleExtra("sodium", 0);
                    }

                        calTotal += calDinner;
                        carbonTotal += carbonDinner;
                        proteinTotal += proteinDinner;
                        fatTotal += proteinDinner;
                        sodiumTotal += sodiumDinner;
                        dbHelper.insertDataInfo("dinner", calBreak, carbonBreak, proteinBreak, fatBreak, sodiumBreak, breakTotal, foodname_lunch, foodname_dinner,exerName,exerKcal);
//
                }
            }
            if(requestCode==LIST_EXERCISE){
                if(resultCode==RESULT_OK){
                    if(listItem_e==null) {
                        exerName += data.getStringExtra("exername");
                        exerKcal+=data.getDoubleExtra("exerkcal",0);

                    }
                    else {
                        exerName=listItem_e.getNameExer();
                        exerName+=data.getStringExtra("exername");
                        exerKcal=listItem_e.getExerKcal();
                        exerKcal+=data.getDoubleExtra("exerkcal",0);
                    }
                    exerTotal+=exerKcal;
                    dbHelper.insertDataInfo("exercise",0.0, 0.0, 0.0, 0.0, 0.0, " ", " ", " ",exerName,exerKcal);

                }
            }
        }

    }
