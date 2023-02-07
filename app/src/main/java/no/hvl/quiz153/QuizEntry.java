package no.hvl.quiz153;

import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;

public class QuizEntry {
    private String text;
    
    @DrawableRes
    private int img;

    public QuizEntry(String text, int img) {
        this.text = text;
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "QuizEntry{" +
                "text='" + text + '\'' +
                ", img=" + img +
                '}';
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
