package no.hvl.quiz153;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "quiz_database")
public class QuizEntry implements Parcelable, Comparable<QuizEntry> {

    @ColumnInfo(name = "entry_id")
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int eId;

    private String text;
    private Uri img;

    public QuizEntry(String text, Uri img) {
        this.text = text;
        this.img = img;
        this.eId = eId;
    }

    public QuizEntry(String text, int imgResourceId) {
        this.text = text;
        this.img = Uri.parse("android.resource://no.hvl.quiz153/" + imgResourceId);
        this.eId = eId;
    }

    protected QuizEntry(Parcel in) {
        text = in.readString();
        img = in.readParcelable(Uri.class.getClassLoader());
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
    public int getEId() {
        return eId;
    }

    public void setEId(int eId) {
        this.eId = eId;
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

    public Uri getImg() {
        return img;
    }

    public void setImg(Uri img) {
        this.img = img;
    }

    public void setImg(int imgResourceId) {
        this.img = Uri.parse("android.resource://no.hvl.quiz153/" + imgResourceId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeParcelable(img, flags);
    }

    @Override
    public int compareTo(QuizEntry o) {
        return this.text.toLowerCase().compareTo(o.getText().toLowerCase());
    }
}
