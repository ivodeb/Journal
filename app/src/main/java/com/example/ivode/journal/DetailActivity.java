package com.example.ivode.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/** Shows the details of a journal entry, including mood, content and timestamp accurate to seconds.*/
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        JournalEntry entry = (JournalEntry) intent.getSerializableExtra("journal_entry");

        TextView title = findViewById(R.id.journal_title);
        TextView timestamp = findViewById(R.id.journal_time);
        TextView content = findViewById(R.id.journal_description);
        ImageView moodImage = findViewById(R.id.journal_mood);

        title.setText(entry.getTitle());
        timestamp.setText(entry.getTimestamp().toString().substring(0, 19));
        content.setText(entry.getContent());
        Mood mood = entry.getMood();
        moodImage.setImageResource(mood.getMoodImage());
    }
}