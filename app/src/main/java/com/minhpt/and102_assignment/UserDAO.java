package com.minhpt.and102_assignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDAO {
    private UserHelper userHelper;
    private SQLiteDatabase database;
    private Context context;

    public UserDAO(Context context) {
        this.context = context;
        userHelper = new UserHelper(context);
        database = userHelper.getWritableDatabase();
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
