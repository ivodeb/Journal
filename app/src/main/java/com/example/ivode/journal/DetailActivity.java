package com.example.ivode.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        JournalEntry entry = (JournalEntry) intent.getSerializableExtra("entryJournal");

        TextView title = findViewById(R.id.journalTitle);
        TextView timestamp = findViewById(R.id.journalTimestamp);
        TextView entryText = findViewById(R.id.journalEntry);
        ImageView moodImage = findViewById(R.id.journalMood);

        title.setText(entry.getTitle());
        timestamp.setText(entry.getTimestamp().toString().substring(0, 19));
        entryText.setText(entry.getContent());
        Mood mood = entry.getMood();
        moodImage.setImageResource(mood.getMoodImage());
    }
}