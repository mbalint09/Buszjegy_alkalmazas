package com.example.buszjegy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelperClass extends SQLiteOpenHelper {
    //Database version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "jegyek_database";
    //Database Table name
    private static final String TABLE_NAME = "JEGYEK";
    //Table columns
    public static final String ID = "id";
    public static final String VISZONYLAT = "viszonylat";
    public static final String AR = "ar";
    private SQLiteDatabase sqLiteDatabase;


    //creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME +"("+ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT," + VISZONYLAT + " TEXT NOT NULL,"+AR+" TEXT NOT NULL);";
    //Constructor
    public DatabaseHelperClass (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addJegy(JegyModelClass jegyModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperClass.VISZONYLAT, jegyModelClass.getViszonylat());
        contentValues.put(DatabaseHelperClass.AR, jegyModelClass.getAr());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(DatabaseHelperClass.TABLE_NAME, null,contentValues);
    }

    public List<JegyModelClass> getJegyList(){
        String sql = "select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<JegyModelClass> storeJegy = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String viszonylat = cursor.getString(1);
                String ar = cursor.getString(2);
                storeJegy.add(new JegyModelClass(id,viszonylat,ar));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeJegy;
    }

    public void updateJegy(JegyModelClass jegyModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelperClass.VISZONYLAT,jegyModelClass.getViszonylat());
        contentValues.put(DatabaseHelperClass.AR,jegyModelClass.getAr());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME,contentValues,ID + " = ?" , new String[]
                {String.valueOf(jegyModelClass.getId())});
    }

    public void deleteJegy(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, ID + " = ? ", new String[]
                {String.valueOf(id)});
    }
}
