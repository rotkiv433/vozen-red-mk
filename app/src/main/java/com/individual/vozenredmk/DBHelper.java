package com.individual.vozenredmk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final String relations_table = "RELATIONS_TABLE";
    public static final String COLUMN_RELATION_START = "RELATION_START";
    public static final String COLUMN_RELATION_END = "RELATION_END";
    public static final String COLUMN_RELATION_STATION = "RELATION_STATION";
    public static final String COLUMN_RELATION_TIME = "RELATION_TIME";
    public static final String COLUMN_RELATION_COMPANY = "RELATION_COMPANY";
    public static final String COLUMN_RELATION_PRICE = "RELATION_PRICE";
    public static final String COLUMN_ID = "ID";

    public DBHelper(@Nullable Context context) {
        super(context, "relations.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + relations_table + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_RELATION_START + " TEXT, " +
                COLUMN_RELATION_END + " TEXT, " + COLUMN_RELATION_STATION + " TEXT, " + COLUMN_RELATION_TIME + " TEXT, " + COLUMN_RELATION_COMPANY + " TEXT," +
                COLUMN_RELATION_PRICE + " TEXT)";

        db.execSQL(createTableStatement);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(Relation r) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_RELATION_START, r.getStart());
        cv.put(COLUMN_RELATION_END, r.getEnd());
        cv.put(COLUMN_RELATION_STATION, r.getStanica());
        cv.put(COLUMN_RELATION_TIME, r.getVreme());
        cv.put(COLUMN_RELATION_COMPANY, r.getKompanija());
        cv.put(COLUMN_RELATION_PRICE, r.getCena());

        long insert = db.insert(relations_table, null, cv);
        if(insert == -1)
            return false;
        else
            return true;
    }

    public List<Relation> getEveryone() {
        List<Relation> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + relations_table;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()) {
            do {
                int relationID = cursor.getInt(0);
                String relationStart = cursor.getString(1);
                String relationEnd = cursor.getString(2);
                String relationStation = cursor.getString(3);
                String relationTime = cursor.getString(4);
                String relationCompany = cursor.getString(5);
                String relationPrice = cursor.getString(6);

                Relation newRelation =  new Relation(relationStart, relationEnd, relationStation, relationTime, relationCompany, relationPrice);
                returnList.add(newRelation);
            } while(cursor.moveToNext());
        }
        else {

        }

        cursor.close();
        db.close();

        return returnList;
    }
}
