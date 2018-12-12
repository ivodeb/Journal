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
    public void bindView(View view, Context context, Cursor cursor) {

        TextView title = view.findViewById(R.id.journalTitle);
        TextView timestamp = view.findViewById(R.id.journalTimestamp);
        ImageView moodImage = view.findViewById(R.id.journalMood);

        title.setText(cursor.getString(cursor.getColumnIndex("title")));
        timestamp.setText(cursor.getString(cursor.getColumnIndex("timestamp")).substring(0, 10));

        Mood mood = Mood.valueOf(cursor.getString(cursor.getColumnIndex("mood")));
        moodImage.setImageResource(mood.getMoodImage());
    }
}