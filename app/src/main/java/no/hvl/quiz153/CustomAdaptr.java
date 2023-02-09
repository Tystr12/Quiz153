package no.hvl.quiz153;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

// provides a ListView over all the elements in the database
// the class ensures that the correct text appears in the correct element
public class CustomAdaptr extends BaseAdapter {

    Context context;
    ArrayList<QuizEntry> entryList;
    LayoutInflater inflater;

    public CustomAdaptr(Context ctx, ArrayList<QuizEntry> entryList) {

        this.context = ctx;
        this.entryList = entryList;
        this.inflater = LayoutInflater.from(ctx);

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
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_custom_list_view,null);
        TextView textView = (TextView) convertView.findViewById(R.id.txt_bar);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.img_icon);
        Button button = (Button) convertView.findViewById(R.id.button_delete);
        final QuizEntry a = getItem(position);
        textView.setText(entryList.get(position).getText());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getCount() > 3 ) {
                    entryList.remove(a);
                    notifyDataSetChanged();
                }
            }

        });
        imageView.setImageResource(entryList.get(position).getImg());
        return convertView;
    }
}
