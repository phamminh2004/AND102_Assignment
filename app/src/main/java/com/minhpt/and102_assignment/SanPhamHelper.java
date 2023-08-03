package com.minhpt.and102_assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SanPhamHelper extends SQLiteOpenHelper {
    public SanPhamHelper(@Nullable Context context) {
        super(context, "SanPhamDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE SanPham(\n" +
                "  masp integer PRIMARY KEY AUTOINCREMENT,\n" +
                "  tensp text,\n" +
                "  giaban integer,\n" +
                "  soluong integer\n" +
                ")";
        db.execSQL(sql);
        String data = "INSERT INTO SanPham VALUES\n" +
                "(1,'Bánh quy bơ LU Pháp',45000,10),\n" +
                "(2,'Snack mực lăn muối ớt',8000,52),\n" +
                "(3,'Snack khoai tây Lays',12000,38),\n" +
                "(4,'Bánh gạo One One',30000,11),\n" +
                "(5,'Kẹo dừa',35000,21),\n" +
                "(6,'Kẹo Socola',25000,30),\n" +
                "(7,'Snack bí đỏ',10000,47),\n" +
                "(8,'Oreo x Blackpink',15000,24)";
        db.execSQL(data);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS SanPham");
            onCreate(db);
        }
    }
}
