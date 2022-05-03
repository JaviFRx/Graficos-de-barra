package com.example.graficosbar;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // variable for our bar chart
    BarChart my_barchart;

    // variable for our bar data.
    BarData barData;

    // variable for our bar data set.
    BarDataSet barDataSet;

    // array list for storing entries.
    ArrayList barEntriesArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing variable for bar chart.
        my_barchart = findViewById(R.id.idBarChart);

        // calling method to get bar entries.
        getBarEntries();

        // creating a new bar data set.
        barDataSet = new BarDataSet(barEntriesArrayList, "Geeks for Geeks");


        // creating a new bar data and
        // passing our bar data set.
        barData = new BarData(barDataSet);

        // below line is to set data
        // to our bar chart.
        my_barchart.setData(barData);

        // adding color to our bar data set.
        //barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setGradientColor(Color.rgb(51, 66, 255),Color.rgb(51, 190, 255));
        //barDataSet.setColor(Color.rgb(51, 187, 255));
        titulos_X();
        //titulos_Y();

        // setting text color.
        barDataSet.setValueTextColor(Color.BLUE);

        // setting text size
        barDataSet.setValueTextSize(20f);
        my_barchart.getDescription().setEnabled(false);
    }

    private void getBarEntries() {
        // creating a new array list
        barEntriesArrayList = new ArrayList<>();
        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntriesArrayList.add(new BarEntry(1f, 10));
        barEntriesArrayList.add(new BarEntry(2f, 6));
        barEntriesArrayList.add(new BarEntry(3f, 8));
        barEntriesArrayList.add(new BarEntry(4f, 2));
        barEntriesArrayList.add(new BarEntry(5f, 4));
        barEntriesArrayList.add(new BarEntry(6f, 1));
        barEntriesArrayList.add(new BarEntry(7f, 8));
        barEntriesArrayList.add(new BarEntry(8f, 6));
        barEntriesArrayList.add(new BarEntry(9f, 8));/*
        barEntriesArrayList.add(new BarEntry(10f, 2));
        barEntriesArrayList.add(new BarEntry(11f, 4));
        barEntriesArrayList.add(new BarEntry(12f, 1));
        barEntriesArrayList.add(new BarEntry(13f, 8));
        barEntriesArrayList.add(new BarEntry(14f, 6));
        barEntriesArrayList.add(new BarEntry(15f, 8));
        barEntriesArrayList.add(new BarEntry(16f, 2));
        barEntriesArrayList.add(new BarEntry(17f, 4));
        barEntriesArrayList.add(new BarEntry(18f, 1));
        barEntriesArrayList.add(new BarEntry(19f, 8));
        barEntriesArrayList.add(new BarEntry(20f, 6));
        barEntriesArrayList.add(new BarEntry(21f, 8));
        barEntriesArrayList.add(new BarEntry(22f, 2));
        barEntriesArrayList.add(new BarEntry(23f, 4));
        barEntriesArrayList.add(new BarEntry(24f, 1));
        barEntriesArrayList.add(new BarEntry(25f, 8));
        barEntriesArrayList.add(new BarEntry(26f, 6));
        barEntriesArrayList.add(new BarEntry(27f, 8));
        barEntriesArrayList.add(new BarEntry(28f, 2));
        barEntriesArrayList.add(new BarEntry(29f, 4));
        barEntriesArrayList.add(new BarEntry(30f, 1));
        barEntriesArrayList.add(new BarEntry(31f, 1));*/

    }
    private void titulos_X(){
        final ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add("J"); //this label will be mapped to the 1st index of the valuesList
        xAxisLabel.add("F");
        xAxisLabel.add("M");
        xAxisLabel.add("A");
        xAxisLabel.add("M");
        xAxisLabel.add("J");
        xAxisLabel.add("J");
        xAxisLabel.add("A");
        xAxisLabel.add("S");
        xAxisLabel.add("O");
        xAxisLabel.add("N");
        xAxisLabel.add("D");
        xAxisLabel.add(""); //empty label for the last vertical grid line on Y-Right Axis
        XAxis xAxis = my_barchart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(14);
        xAxis.setDrawAxisLine(true);
        xAxis.setAxisLineColor(Color.BLACK);
        xAxis.setDrawGridLines(true);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setAxisMinimum(0 + 0.5f); //to center the bars inside the vertical grid lines we need + 0.5 step
        xAxis.setAxisMaximum(barEntriesArrayList.size() + 0.5f); //to center the bars inside the vertical grid lines we need + 0.5 step
        xAxis.setLabelCount(xAxisLabel.size(), true); //draw x labels for 13 vertical grid lines
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setXOffset(0f); //labels x offset in dps
        xAxis.setYOffset(0f); //labels y offset in dps
        xAxis.setCenterAxisLabels(true);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return xAxisLabel.get((int) value);
            }
        });
    }
    private void titulos_Y(){
        //initialize Y-Right-Axis
        YAxis rightAxis = my_barchart.getAxisRight();
        rightAxis.setTextColor(Color.BLACK);
        rightAxis.setTextSize(14);
        rightAxis.setDrawAxisLine(true);
        rightAxis.setAxisLineColor(Color.BLACK);
        rightAxis.setDrawGridLines(true);
        rightAxis.setGranularity(1f);
        rightAxis.setGranularityEnabled(true);
        rightAxis.setAxisMinimum(0);
        rightAxis.setAxisMaximum(6000f);
        rightAxis.setLabelCount(4, true); //draw y labels (Y-Values) for 4 horizontal grid lines starting from 0 to 6000f (step=2000)
        rightAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);

        //initialize Y-Left-Axis
        YAxis leftAxis = my_barchart.getAxisLeft();
        leftAxis.setAxisMinimum(0);
        leftAxis.setDrawAxisLine(true);
        leftAxis.setLabelCount(0, true);
        leftAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return "";
            }
        });
    }
}
