package com.example.ivode.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.annotation.Nullable;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/** Database for journal entries with insert, delete, select functions. */
public class EntryDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "myJournalEntries";
    private static EntryDatabase instance;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DB_NAME + "( _id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "title TEXT NOT NULL, content TEXT NOT NULL, mood TEXT NOT NULL, " +
                "timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int a, int b) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(db);
    }

    // constructor
    private EntryDatabase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    static EntryDatabase getInstance(Context context) {
        if (instance == null) {
            instance = new EntryDatabase(context, DB_NAME, null, 1);
        }
        return instance;
    }

    // select all entries and store them
    Cursor selectAll() {
        SQLiteDatabase db = getWritableDatabase();
        return db.rawQuery("SELECT * FROM "+ DB_NAME, null);
    }

    // insert a new entry
    void insert(JournalEntry entry) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", entry.getTitle());
        values.put("content", entry.getContent());
        values.put("mood", String.valueOf(entry.getMood()));
        values.put("timestamp", entry.getTimestamp().toString());
        db.insert(DB_NAME, null, values);
    }

    // delete an existing entry
    void deleteEntry(long id) {
        SQLiteDatabase db = getWritableDatabase();
        String delete = "DELETE from " + DB_NAME + " WHERE _id= " + id;
        db.execSQL(delete);
    }
}