package no.hvl.quiz153;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import java.util.ArrayList;
import java.util.List;

public class CustomAdaptr extends BaseAdapter {

    MainViewModel mViewModel;
    private Context context;
    private ArrayList<QuizEntry> entryList = new ArrayList<>();
    private LayoutInflater inflater;

    public CustomAdaptr(Context context, MainViewModel mvm, LifecycleOwner owner) {
        this.context = context;
        mvm.getAllQuizEntrys().observe(owner, quizEntries -> {
            // Add the new data to the names ArrayList
            entryList.clear();
            entryList.addAll(quizEntries);
            notifyDataSetChanged();

        });
        this.inflater = LayoutInflater.from(context);
        this.mViewModel = mvm;
    }

    @Override
    public int getCount() {
        return entryList.size();
    }

    @Override
    public QuizEntry getItem(int position) {
        return entryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return entryList.get(position).getEId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_custom_list_view, parent, false);
            holder = new ViewHolder();
            holder.textView = convertView.findViewById(R.id.txt_bar);
            holder.imageView = convertView.findViewById(R.id.img_icon);
            holder.deleteButton = convertView.findViewById(R.id.button_delete);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        QuizEntry quizEntry = entryList.get(position);

        holder.textView.setText(quizEntry.getText());
        Uri imageUri = quizEntry.getImg();
        if (imageUri != null) {
            Uri uri = MediaStore.Images.Media.getContentUri(imageUri.toString());
            holder.imageView.setImageURI(imageUri);
        } else {
            holder.imageView.setImageResource(R.drawable.ic_launcher_background);
        }

        holder.deleteButton.setOnClickListener(v -> {
            if (getCount() > 3) {
                mViewModel.deleteQuizEntry(quizEntry);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    static class ViewHolder {
        TextView textView;
        ImageView imageView;
        Button deleteButton;
    }
}