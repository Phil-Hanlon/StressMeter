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
import java.util.Random;

public class HomeFragment extends Fragment {


    private GalleryViewModel galleryViewModel;

    ArrayList<ImageView> images;
    ArrayList<ImageView> images_16;
    ImagesAdapter imagesAdapter;


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


        get_16_images();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        // Sets the text above the grid of images
        final TextView textView = root.findViewById(R.id.text_home);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText("Touch the image that best captures how stressed you feel right now");
            }
        });



        // Gets 16 new images whenever the "MORE IMAGES" button is clicked
        root.findViewById(R.id.more_images_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                get_16_images();
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        GridView gridView = getActivity().findViewById(R.id.images_grid_view);

        imagesAdapter = new ImagesAdapter(getContext(), images_16);

        gridView.setAdapter(imagesAdapter);
    }


    // Call to replace the current 16 images
    private void get_16_images() {

        // Makes a clone of the list of images from which to pull images without repetition
        ArrayList<ImageView> image_bag = new ArrayList<ImageView>();
        image_bag = (ArrayList)images.clone();

        Random random = new Random();


        images_16.clear();

        // Gets 16 random images (without repeating)
        for(int i = 0; i<16; i++ ) {

            ImageView imageView = image_bag.remove(random.nextInt(image_bag.size()));

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    imageSelected(v);
                }
            });

            images_16.add(imageView);
        }

        if( imagesAdapter != null ) imagesAdapter.notifyDataSetChanged();
    }



    private void imageSelected(View view) {

        
    }
}