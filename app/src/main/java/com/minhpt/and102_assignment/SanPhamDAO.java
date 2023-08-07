package com.minhpt.and102_assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class SanPhamDAO {
    private SanPhamHelper sanPhamHelper;
    private SQLiteDatabase database;
    private Context context;

    public SanPhamDAO(Context context) {
        this.context = context;
        sanPhamHelper = new SanPhamHelper(context);
        database = sanPhamHelper.getWritableDatabase();
    }

    public ArrayList<SanPham> getListSanPham() {
        ArrayList<SanPham> list = new ArrayList<>();
        try {
            Cursor cursor = database.rawQuery("SELECT * FROM SanPham", null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    list.add(new SanPham(cursor.getInt(0)
                            , cursor.getString(1)
                            , cursor.getInt(2)
                            , cursor.getInt(3)));
                } while (cursor.moveToNext());
            }

        } catch (Exception e) {
            Log.e("TAG", e.getMessage());
        }
        return list;
    }

    public boolean addSanPham(SanPham sanPham) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensp", sanPham.tensp);
        contentValues.put("giaban", sanPham.giaban);
        contentValues.put("soluong", sanPham.soluong);
        long check = database.insert("SanPham", null, contentValues);
        return check != -1;
    }

    public boolean updateSanPham(SanPham sanPham) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tensp", sanPham.tensp);
        contentValues.put("giaban", sanPham.giaban);
        contentValues.put("soluong", sanPham.soluong);
        int check = database.update("SanPham", contentValues, "masp=?", new String[]{String.valueOf(sanPham.masp)});
        return check != -1;
    }

    public boolean deleteSanPham(int masp) {
        int row = database.delete("SanPham", "masp=?", new String[]{String.valueOf(masp)});
        return row != -1;
    }
}
