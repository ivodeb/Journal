package com.example.ivode.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.database.Cursor;

import java.sql.Timestamp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listViewEntry = findViewById(R.id.entryListView);
        listViewEntry.setOnItemLongClickListener(new OnItemLongClickListener());
        listViewEntry.setOnItemClickListener(new OnItemClickListener());

        updateData();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateData();
    }

    public void updateData() {
        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
        Cursor cursor = db.selectAll();

        ListView listViewEntry = findViewById(R.id.entryListView);
        EntryAdapter entryAdapter = new EntryAdapter(getApplicationContext(), cursor);
        listViewEntry.setAdapter(entryAdapter);
    }


    public void floatingActionButtonClicked(View v) {
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    private class OnItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);

            Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            Mood mood = Mood.valueOf(cursor.getString(cursor.getColumnIndex("mood")));
            Timestamp timestamp = Timestamp.valueOf(cursor.getString(cursor.getColumnIndex("timestamp")));
            JournalEntry entry = new JournalEntry(title, content, mood, timestamp);

            intent.putExtra("entryJournal", entry);
            startActivity(intent);
        }
    }

    private class OnItemLongClickListener implements AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
            Cursor cursor = (Cursor) adapterView.getItemAtPosition(i);
            long id = cursor.getInt(cursor.getColumnIndex("_id"));

            db.deleteEntry(id);
            updateData();
            return false;
        }
    }
}