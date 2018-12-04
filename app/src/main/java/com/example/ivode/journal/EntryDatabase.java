package com.example.ivode.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class EntryDatabase extends SQLiteOpenHelper {

    private static final String DBNAME = "myJournalEntries";

    private static EntryDatabase instance;

    // constructor
    private EntryDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static EntryDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new EntryDatabase(context, DBNAME, null, 1);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        sqLiteDatabase.execSQL("create table " + DBNAME + "( _id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "title TEXT NOT NULL, content TEXT NOT NULL, mood TEXT NOT NULL, " +
                "timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP);");

        // add some sample to database
        sqLiteDatabase.execSQL("INSERT INTO " + DBNAME + "(title, content, mood) VALUES " +
                "('day_one', 'Blablabla', 'HAPPY')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DBNAME);
        onCreate(sqLiteDatabase);
    }

    // method to select all database entries
    public Cursor selectAll() {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM "+ DBNAME, null);
    }

    // method to insert an entry
    public void insert(JournalEntry entry) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", entry.getTitle());
        contentValues.put("content", entry.getContent());
        contentValues.put("mood", String.valueOf(entry.getMood()));
        contentValues.put("timestamp", entry.getTimestamp().toString());
        db.insert(DBNAME, null, contentValues);
    }

    public void deleteEntry(long id) {
        SQLiteDatabase db = getWritableDatabase();
        String delete = "DELETE from " + DBNAME + " WHERE _id= " + id;
        db.execSQL(delete);
    }
}