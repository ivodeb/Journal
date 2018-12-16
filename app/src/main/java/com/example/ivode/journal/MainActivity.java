package com.example.ivode.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.database.Cursor;

import java.sql.Timestamp;

/** Shows list of journal entries in the database. */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView entry_list = findViewById(R.id.entry_list);
        entry_list.setOnItemLongClickListener(new OnItemLongClickListener());
        entry_list.setOnItemClickListener(new OnItemClickListener());

        updateData();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateData();
    }

    // update the activity based on last known data
    public void updateData() {
        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
        ListView entry_list = findViewById(R.id.entry_list);
        entry_list.setAdapter(new EntryAdapter(getApplicationContext(), db.selectAll()));
    }


    public void floatingActionButtonClicked(View v) {
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    // on short click, show details of journal entry
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

            intent.putExtra("journal_entry", entry);
            startActivity(intent);
        }
    }

    // on long click, delete journal entry from database and update the activity
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