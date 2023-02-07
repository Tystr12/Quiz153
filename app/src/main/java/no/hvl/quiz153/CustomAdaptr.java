package no.hvl.quiz153;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import no.hvl.quiz153.R;

import java.util.List;

public class CustomAdaptr extends BaseAdapter {
    Context context;
    List<String> txtList;
    LayoutInflater inflater;

    public CustomAdaptr(Context ctx, List<String> txtList) {

        this.context = ctx;
        this.txtList = txtList;
        this.inflater = LayoutInflater.from(ctx);

    }

    @Override
    public int getCount() {
        return txtList.size();
    }

    @Override
    public String getItem(int position) {
        return txtList.get(position);
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
        textView.setText(txtList.get(position));
        final String a = getItem(position);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtList.remove(a);
                notifyDataSetChanged();
            }
        });
        imageView.setImageResource(R.drawable.cat);
        return convertView;
    }
}
