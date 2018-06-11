package com.media.kbs.mobileproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    private String date;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("MAIN", "db date :" + date);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS DATEINFO_" + date + ";");
        onCreate(db);
    }

    public void createDataInfo(String date){
        this.date = date;

        SQLiteDatabase db = getWritableDatabase();
        StringBuilder sb = new StringBuilder();
        Log.d("MAIN", "db date :" + date);
        sb.append("CREATE TABLE IF NOT EXISTS DATEINFO_");
        sb.append(date);
        sb.append(" (_id INTEGER PRIMARY KEY AUTOINCREMENT, time TEXT, kcal double, carbon double, protein double, fat double, sodium double" +
                ", breakfastt TEXT, luncht TEXT, dinnert TEXT)");
        db.execSQL(sb.toString());
    }

    public void insertDataInfo(String time, double kcal, double carbon, double protein, double fat, double sodium, String breakfast, String lunch, String dinner){
        SQLiteDatabase db = getWritableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO DATEINFO_");
        sb.append(date);
        sb.append(" (time, kcal, carbon, protein, fat, sodium, breakfastt, luncht, dinnert) VALUES (\"");
        sb.append(time);
        sb.append("\", ");
        sb.append(kcal);
        sb.append(", ");
        sb.append(carbon);
        sb.append(", ");
        sb.append(protein);
        sb.append(", ");
        sb.append(fat);
        sb.append(", ");
        sb.append(sodium);
        sb.append(", \"");
        sb.append(breakfast);
        sb.append("\", ");
        sb.append("\"");
        sb.append(lunch);
        sb.append("\", ");
        sb.append("\"");
        sb.append(dinner);
        sb.append("\"");
        sb.append(")");

        db.execSQL(sb.toString());
    }
    public void upgradeDataInfo(String time, double kcal, double carbon, double protein, double fat, double sodium){
        SQLiteDatabase db = getWritableDatabase();
        StringBuffer sb = new StringBuffer();
        sb.append(" UPDATE DATEINFO_");
        sb.append(date);
        sb.append(" SET kcal = ?, carbon = ?, protein = ?, fat = ?, sodium = ? WHERE time = \"");
        sb.append(time);
        sb.append("\"");

        db.execSQL(sb.toString(),
                new Object[]{
                        kcal, carbon, protein, fat, sodium
                });
    }
    public ListItem getDataInfo(String time){
        SQLiteDatabase db = getReadableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM DATEINFO_");
        sb.append(date);
        sb.append(" WHERE time = \"");
        sb.append(time);
        sb.append("\"");
        Cursor cursor = db.rawQuery(sb.toString(), null);

        ListItem listItem = null;
        while (cursor.moveToNext()){
            listItem = new ListItem();
            listItem.setTotalKcal(cursor.getDouble(2));
            listItem.setCarbonKcal(cursor.getDouble(3));
            listItem.setProteinKcal(cursor.getDouble(4));
            listItem.setFatKcal(cursor.getDouble(5));
            listItem.setSodiumKcal(cursor.getDouble(6));
            listItem.setNameBreak(cursor.getString(7));
            listItem.setNameLunch(cursor.getString(8));
            listItem.setNameDinner(cursor.getString(9));
        }
        return listItem;
    }
}
