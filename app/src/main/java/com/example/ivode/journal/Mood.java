package com.example.ivode.journal;

public enum Mood {
    CRYING,
    TIRED,
    HAPPY,
    ANGRY;

    public int getMoodImage() {
        switch (this) {
            case CRYING:
                return R.drawable.crying;
            case TIRED:
                return R.drawable.tired;
            case HAPPY:
                return R.drawable.happy;
            case ANGRY:
                return R.drawable.angry;
            default:
                return R.drawable.happy;
        }
    }

    public int getMoodImageView() {
        switch (this) {
            case CRYING:
                return R.id.moodCrying;
            case TIRED:
                return R.id.moodTired;
            case HAPPY:
                return R.id.moodHappy;
            case ANGRY:
                return R.id.moodAngry;
            default:
                return R.id.moodHappy;
        }
    }

    public Mood getMood() {
        switch (this) {
            case CRYING:
                return CRYING;
            case TIRED:
                return TIRED;
            case HAPPY:
                return HAPPY;
            case ANGRY:
                return ANGRY;
            default:
                return HAPPY;
        }
    }
}