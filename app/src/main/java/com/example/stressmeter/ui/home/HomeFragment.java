package com.example.stressmeter.ui.home;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.stressmeter.R;
import com.example.stressmeter.ui.gallery.GalleryViewModel;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class HomeFragment extends Fragment {


    private GalleryViewModel galleryViewModel;

    ArrayList<ImageView> images;
    ArrayList<ImageView> images_16;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetainInstance(true);

        images = new ArrayList<>();
        images_16 = new ArrayList<>();

        Resources resources = getContext().getResources();

        for(Field drawables_field :  R.drawable.class.getFields() ) {

            if (drawables_field.getName().startsWith("psm")) {


                try {

                    ImageView imageView = new ImageView(getActivity());


                    imageView.setImageDrawable(resources.getDrawable(drawables_field.getInt(null)));

                    images.add(imageView);
                }

            catch(Exception e){}
            }
        }


        ArrayList<ImageView> image_bag = images;

        for(int i = 0; i<16; i++ ) {

            images_16.add(image_bag.remove())
        }

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView textView = root.findViewById(R.id.text_home);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText("Touch the image that best captures how stressed you feel right now");
            }
        });


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GridView gridView = getActivity().findViewById(R.id.images_grid_view);

        ArrayList<ImageView> images_16 = (ArrayList<ImageView>)images.subList(0, 15);

        ImagesAdapter imagesAdapter = new ImagesAdapter(getContext(), images_16);
        gridView.setAdapter(imagesAdapter);
    }
}