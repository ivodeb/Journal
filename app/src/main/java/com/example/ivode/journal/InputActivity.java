package com.example.ivode.journal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.sql.Timestamp;

public class InputActivity extends AppCompatActivity {

    private Mood moodSelected = Mood.HAPPY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    public void addEntry(View v) {
        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());

        EditText titleText = findViewById(R.id.editTitleText);
        EditText entryText = findViewById(R.id.editEntryText);

        String title = titleText.getText().toString();
        String content = entryText.getText().toString();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        JournalEntry entry = new JournalEntry(title, content, moodSelected, timestamp);

        db.insert(entry);

        this.finish();
    }

    public void happyMood(View v) {
        ImageView happy = findViewById(R.id.moodHappy);
        ImageView crying = findViewById(R.id.moodCrying);
        ImageView tired = findViewById(R.id.moodTired);
        ImageView angry = findViewById(R.id.moodAngry);

        moodSelected = Mood.HAPPY;
        happy.setImageResource(R.drawable.happy_selected);
        crying.setImageResource(R.drawable.crying);
        tired.setImageResource(R.drawable.tired);
        angry.setImageResource(R.drawable.angry);
    }
    public void cryingMood(View v) {
        ImageView happy = findViewById(R.id.moodHappy);
        ImageView crying = findViewById(R.id.moodCrying);
        ImageView tired = findViewById(R.id.moodTired);
        ImageView angry = findViewById(R.id.moodAngry);

        moodSelected = Mood.CRYING;
        crying.setImageResource(R.drawable.crying_selected);
        happy.setImageResource(R.drawable.happy);
        tired.setImageResource(R.drawable.tired);
        angry.setImageResource(R.drawable.angry);
    }
    public void tiredMood(View v) {
        ImageView happy = findViewById(R.id.moodHappy);
        ImageView crying = findViewById(R.id.moodCrying);
        ImageView tired = findViewById(R.id.moodTired);
        ImageView angry = findViewById(R.id.moodAngry);

        moodSelected = Mood.TIRED;
        tired.setImageResource(R.drawable.tired_selected);
        happy.setImageResource(R.drawable.happy);
        crying.setImageResource(R.drawable.crying);
        angry.setImageResource(R.drawable.angry);
    }
    public void angryMood(View v) {
        ImageView happy = findViewById(R.id.moodHappy);
        ImageView crying = findViewById(R.id.moodCrying);
        ImageView tired = findViewById(R.id.moodTired);
        ImageView angry = findViewById(R.id.moodAngry);

        moodSelected = Mood.ANGRY;
        angry.setImageResource(R.drawable.angry_selected);
        happy.setImageResource(R.drawable.happy);
        crying.setImageResource(R.drawable.crying);
        tired.setImageResource(R.drawable.tired);
    }
}