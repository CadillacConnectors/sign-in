package me.joshuajacobson.signinapp.libs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import me.joshuajacobson.signinapp.libs.datatypes.User;

/**
 * Created by joshua on 1/8/17.
 */

public final class DatabaseInteraction {
    private static String profileInfoRows = "id TEXT, firstname TEXT, lastname TEXT, pinhash TEXT, permissions TEXT";
    private static String currentRows = "id TEXT, starttime TEXT";
    private static String captainsLogRows = "id TEXT, time TEXT, body TEXT";
    private static String storageRows = "id TEXT, starttime TEXT, endtime TEXT";

    private static String profileInfo = "profileinfo";
    private static String current = "current";
    private static String captainsLog = "captainslog";
    private static String storage = "storage";

    public void addUser(User user, Context context) {
        DatabaseHelper mDbHelper = new DatabaseHelper(context, profileInfo, profileInfoRows);
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", user.getId());
        values.put("firstname", user.getFirstname());
        values.put("lastname", user.getLastname());
        values.put("pinhash", user.getPin_hashed());
        values.put("permissions", user.getPermissions());
        database.insert(profileInfo, null, values);
        mDbHelper.onCreate(database);
    }

    @Nullable
    public User getUser(String id, Context context) {
        DatabaseHelper mDbHelper = new DatabaseHelper(context, profileInfo, profileInfoRows);
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        mDbHelper.onCreate(database);
        String[] projection = {
                "id",
                "firstname",
                "lastname",
                "pinhash",
                "permissions"
        };
        String selection = "id=?";
        String[] selectionArgs = {
                id
        };
        Cursor cursor = database.query(
                profileInfo,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (cursor.moveToNext()) {
            return new User(cursor.getString(1), cursor.getString(2), cursor.getString(0), cursor.getString(3), cursor.getString(4));
        }
        cursor.close();
        return null;
    }

    public User[] getUsers(Context context) {
        DatabaseHelper mDbHelper = new DatabaseHelper(context, profileInfo, profileInfoRows);
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        mDbHelper.onCreate(database);
        String[] projection = {
                "id",
                "firstname",
                "lastname",
                "pinhash",
                "permissions"
        };
        Cursor cursor = database.query(
                profileInfo,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        User[] users = new User[cursor.getCount()];
        for (int i = 0; cursor.moveToNext(); i++) {
            users[i] = new User(cursor.getString(1), cursor.getString(2), cursor.getString(0), cursor.getString(3), cursor.getString(4));
        }
        cursor.close();
        return users;
    }

    public void removeUser(String id, Context context) {
        //TODO
    }

    public void checkInUser(String id, Context context) {
        DatabaseHelper mDbHelper = new DatabaseHelper(context, current, currentRows);
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("starttime", TimeManager.getTime());
        database.insert(current, null, values);
        mDbHelper.onCreate(database);
    }

    public boolean checkOutUser(String id, Context context) {

        DatabaseHelper currentHelper = new DatabaseHelper(context, current, currentRows);
        SQLiteDatabase current = currentHelper.getWritableDatabase();
        currentHelper.onCreate(current);

        DatabaseHelper storageRowsHelper = new DatabaseHelper(context, storage, storageRows);
        SQLiteDatabase storageRows = storageRowsHelper.getWritableDatabase();
        storageRowsHelper.onCreate(storageRows);

        //Get Session
        String[] projection = {
                "starttime"
        };
        String selection = "id=?";
        String[] selectionArgs = {
                id
        };
        Cursor cursor = current.query(
                DatabaseInteraction.current,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        String starttime;
        if (cursor.moveToNext()) {
            starttime = cursor.getString(0);
        } else {
            return false;
        }
        cursor.close();

        //Save Session


        return true;
    }
}
