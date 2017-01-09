package me.joshuajacobson.signinapp.libs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by joshua on 1/8/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private String table;
    private String rows;

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "FeedReader.db";

    public DatabaseHelper(Context context, String table, String rows) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.table = table;
        this.rows = rows;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TEXT_TYPE = " TEXT";
        String COMMA_SEP = ",";
        System.out.println(table);
        String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " + table + " (" +
                BaseColumns._ID + " INTEGER PRIMARY KEY," +
                rows +
                ")";
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + table;
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
