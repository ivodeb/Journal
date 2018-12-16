package com.example.ivode.journal;

import java.io.Serializable;
import java.sql.Timestamp;

/** Journal entry class with title, mood, content and time. */
public class JournalEntry implements Serializable {
    private int id;
    private String title, content;
    private Mood mood;
    private Timestamp timestamp;

    JournalEntry(String title, String content, Mood mood, Timestamp timestamp) {
        this.title = title;
        this.content = content;
        this.mood = mood;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    Mood getMood() {
        return mood;
    }

    Timestamp getTimestamp() {
        return timestamp;
    }
}