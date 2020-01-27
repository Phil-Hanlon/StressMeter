package com.example.stressmeter.ui.home;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ImagesAdapter extends BaseAdapter {

    private final Context context;
    private Image[] images;



    public ImagesAdapter(Context context) {

        this.context = context;
    }


    @Override
    public int getCount() {

        return images.length;
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public Object getItem(int position) {
        return null;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        TextView textView = new TextView(context);
        textView.setText(String.valueOf(position));
        return textView;
    }
}
