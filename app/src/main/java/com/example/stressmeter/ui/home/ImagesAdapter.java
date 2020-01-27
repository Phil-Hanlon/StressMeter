package com.example.stressmeter.ui.home;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ImagesAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<ImageView> images;



    public ImagesAdapter(Context context, ArrayList<ImageView> images ) {

        this.context = context;
        this.images = images;
    }


    @Override
    public int getCount() {

        return images.size();
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

        return images.get(position);
    }
}
