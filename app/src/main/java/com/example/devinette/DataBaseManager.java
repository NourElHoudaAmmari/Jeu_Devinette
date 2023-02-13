package com.example.devinette;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataBaseManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="Game.DB";
    private static int DATABASE_VERSION =2;
    private static final DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
    public DataBaseManager(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
String strSql = "create table T_Scores("
        +"   idScore integer primary key autoincrement,"
        +"   name text not null,"
        +"   score integer not null,"
        +"    when_ integer not null"
        +")";
db.execSQL(strSql);
        Log.i("DATABASE","onCreate invoked");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
String strSql="drop table T_Scores";
db.execSQL(strSql);
this.onCreate(db);
Log.i("DATABASE","onUpgrade invoked");
    }
    public void insertScore( String name ,int score){
        name =name.replace("'","''");
        String strSql ="insert into T_Scores(name,score,when_) values('"
            + name+"'," + score + ", "+ new Date().getTime() +")";
        this.getWritableDatabase().execSQL(strSql);
        Log.i("DATABASE","insertScore invoked");

    }
    public List<ScoreData> readTop10(){
        List<ScoreData>scores = new ArrayList<>();
        String strSql="select * from T_Scores order by score desc limit 10";
        Cursor cursor =this.getReadableDatabase().rawQuery(strSql,null);
        cursor.moveToFirst();
        while(cursor.isAfterLast()){
            ScoreData score = new ScoreData(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),new Date(cursor.getInt(3 )));
            scores.add(score);
            cursor.moveToNext();
        }
        cursor.close();
        return scores;
    }
}
