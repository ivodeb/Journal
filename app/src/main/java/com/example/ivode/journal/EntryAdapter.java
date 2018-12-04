package com.example.ivode.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import java.sql.Timestamp;

public class EntryAdapter extends ResourceCursorAdapter {

    public EntryAdapter(Context context, Cursor c) {
        super(context, R.layout.entry_row, c, false);
    }


    @Override
    public Object getItem(int position) {

        Cursor cursor = (Cursor) super.getItem(position);

        int id = cursor.getInt(cursor.getColumnIndex("_id"));
        String title = cursor.getString(cursor.getColumnIndex("title"));
        String content = cursor.getString(cursor.getColumnIndex("content"));
        Mood mood = Mood.valueOf(cursor.getString(cursor.getColumnIndex("mood")));
        Timestamp timestamp = Timestamp.valueOf(cursor.getString(cursor.getColumnIndex("timestamp")));

        return new JournalEntry(id,title,content,mood,timestamp);
    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView title = view.findViewById(R.id.journalTitle);
        TextView timestamp = view.findViewById(R.id.journalTimestamp);
        ImageView moodImage = view.findViewById(R.id.journalMood);

        title.setText(cursor.getString(cursor.getColumnIndex("title")));
        timestamp.setText(cursor.getString(cursor.getColumnIndex("timestamp")));

        Mood mood = Mood.valueOf(cursor.getString(cursor.getColumnIndex("mood")));
        moodImage.setImageResource(mood.getMoodImage());
    }
}