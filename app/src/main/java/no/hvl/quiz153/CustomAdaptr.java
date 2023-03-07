package no.hvl.quiz153;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdaptr extends BaseAdapter {

    MainViewModel mViewModel;
    private Context context;
    private ArrayList<QuizEntry> entryList;
    private LayoutInflater inflater;

    public CustomAdaptr(Context context, ArrayList<QuizEntry> entryList, MainViewModel mvm) {
        this.context = context;
        this.entryList = entryList;
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