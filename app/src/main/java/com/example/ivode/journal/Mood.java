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
}