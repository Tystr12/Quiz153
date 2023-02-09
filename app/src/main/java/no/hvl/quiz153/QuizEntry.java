package no.hvl.quiz153;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

public class QuizEntry implements Parcelable, Comparable<QuizEntry>{
    private String text;
    
    @DrawableRes
    private int img;

    public QuizEntry(String text, int img) {
        this.text = text;
        this.img = img;
    }

    protected QuizEntry(Parcel in) {
        text = in.readString();
        img = in.readInt();
    }

    public static final Creator<QuizEntry> CREATOR = new Creator<QuizEntry>() {
        @Override
        public QuizEntry createFromParcel(Parcel in) {
            return new QuizEntry(in);
        }

        @Override
        public QuizEntry[] newArray(int size) {
            return new QuizEntry[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeInt(img);
    }

    @Override
    public int compareTo(QuizEntry o) {
        return this.text.toLowerCase().compareTo(o.getText().toLowerCase());
    }
}
