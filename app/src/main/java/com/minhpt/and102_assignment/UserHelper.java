package com.minhpt.and102_assignment;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;


public class UserHelper extends SQLiteOpenHelper {
    SQLiteDatabase database = this.getWritableDatabase();

    public UserHelper(Context context) {
        super(context, "UserDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE User(\n" +
                "  username text primary key,\n" +
                "  password text\n" +
                "  )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS User");
            onCreate(db);
        }
    }

    public boolean addUser(User user) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user.username);
        contentValues.put("password", user.password);
        long check = database.insert("User", null, contentValues);
        return check != 1;
    }

    public User checkAcc(User user) {
        Cursor cursor = database.query("User", null, "username like?", new String[]{String.valueOf(user.username)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            User user1 = new User(cursor.getString(0), cursor.getString(1));
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }
        return null;
    }

    public boolean isUsernameExists(String username) {
        Cursor cursor = database.query("User", null, "username like?", new String[]{String.valueOf(username)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            return true;
        }
        return false;
    }

}
