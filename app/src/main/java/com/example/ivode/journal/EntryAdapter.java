package com.example.ivode.journal;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

/** This adapter fills a certain view with data from a database entry. */
public class EntryAdapter extends ResourceCursorAdapter {

    EntryAdapter(Context context, Cursor c) {
        super(context, R.layout.entry_row, c, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // set title
        TextView title = view.findViewById(R.id.journal_title);
        title.setText(cursor.getString(cursor.getColumnIndex("title")));

        // set timestamp
        TextView timestamp = view.findViewById(R.id.journal_time);
        timestamp.setText(cursor.getString(cursor.getColumnIndex("timestamp")).substring(0, 10));

        // set mood
        ImageView moodImage = view.findViewById(R.id.journal_mood);
        Mood mood = Mood.valueOf(cursor.getString(cursor.getColumnIndex("mood")));
        moodImage.setImageResource(mood.getMoodImage());

    }
}