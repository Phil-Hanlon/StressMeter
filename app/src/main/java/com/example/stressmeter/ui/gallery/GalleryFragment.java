package com.example.stressmeter.ui.gallery;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.stressmeter.R;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;

import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.LineChartView;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setRetainInstance(true);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);


        /*final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/



        ArrayList<PointValue> points = new ArrayList<PointValue>();



        TableLayout table = root.findViewById(R.id.results_table);

        try {

            CSVReader reader = new CSVReader(new FileReader(new File(getActivity().getExternalFilesDir(null), "stress_timestamp.csv")));

            String[] entry;
            while( (entry = reader.readNext()) != null ) {

                TableRow tableRow = new TableRow(getContext());

                TextView entry_num = new TextView(getContext());
                entry_num.setText(entry[0]);

                TextView stress_level = new TextView(getContext());
                stress_level.setText(entry[1]);
                stress_level.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);

                tableRow.addView(entry_num);
                tableRow.addView(stress_level);

                table.addView(tableRow);


                points.add(new PointValue(Integer.parseInt(entry[0]), Integer.parseInt(entry[1])));
            }

            reader.close();
        } catch( Exception e ) {}




        LineChartView chartView = root.findViewById(R.id.chart);

        LineChartData data = new LineChartData();

        ArrayList<Line> lines = new ArrayList<>();


        points.sort(new pointComparator());


        Line line = new Line(points).setColor(Color.BLUE);

        lines.add(line);

        data.setLines(lines);

        chartView.setLineChartData(data);

        return root;
    }



    private class pointComparator implements Comparator<PointValue> {

        @Override
        public int compare(PointValue o1, PointValue o2) {

            return (int) (o1.getX() - o2.getX());
        }
    }
}