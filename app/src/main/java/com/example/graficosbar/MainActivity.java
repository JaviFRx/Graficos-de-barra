package com.example.graficosbar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.renderer.XAxisRenderer;
import com.github.mikephil.charting.utils.MPPointF;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // variable for our bar chart
    BarChart my_barChart;
    ArrayList<Double> valuesList = new ArrayList<Double>();
    ArrayList<BarEntry> entries = new ArrayList<>();
    final ArrayList<String> xAxisLabel = new ArrayList<>();
    boolean meses=false,dias=false,horas=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing variable for bar chart.
        my_barChart = findViewById(R.id.BarChart);

        //showMonthsBars();
        showDayHoursBars();

    }

    private void valores(){
        //input Y data (Day Hours - 24 Values)
        try {
            valuesList.add((double)2000);
            valuesList.add((double)500);
            valuesList.add((double)300);
            valuesList.add((double)450);
            valuesList.add((double)850);
            valuesList.add((double)950);
            valuesList.add((double)800);
            valuesList.add((double)100);
            valuesList.add((double)270);
            valuesList.add((double)430);
            valuesList.add((double)110);
            valuesList.add((double)30);/*
        valuesList.add((double)200);
        valuesList.add((double)180);
        valuesList.add((double)0);
        valuesList.add((double)140);
        valuesList.add((double)160);
        valuesList.add((double)0);
        valuesList.add((double)100);
        valuesList.add((double)0);
        valuesList.add((double)750);
        valuesList.add((double)1200);
        valuesList.add((double)10);
        valuesList.add((double)0);*/
            for (int i = 0; i < valuesList.size(); i++) {
                BarEntry barEntry = new BarEntry(i+1, valuesList.get(i).floatValue()); //start always from x=1 for the first bar
                entries.add(barEntry);
            }
        }catch (Exception e){
        }
    }
    private void titulo_X(){
        //initialize xAxis
        XAxis xAxis = my_barChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(14);
        xAxis.setDrawAxisLine(true);
        xAxis.setAxisLineColor(Color.BLACK);
        xAxis.setDrawGridLines(true);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setAxisMinimum(0 + 0.5f); //to center the bars inside the vertical grid lines we need + 0.5 step
        xAxis.setAxisMaximum(entries.size() + 0.5f); //to center the bars inside the vertical grid lines we need + 0.5 step
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setXOffset(0f); //labels x offset in dps
        xAxis.setYOffset(0f); //labels y offset in dps
        if (meses){
            xAxis.setCenterAxisLabels(true); //don't center the x labels as we are using a custom XAxisRenderer to set the label x, y position
            xAxis.setLabelCount(xAxisLabel.size(), true); //draw x labels for 13 vertical grid lines
        }else if (horas){
            xAxis.setCenterAxisLabels(false); //don't center the x labels as we are using a custom XAxisRenderer to set the label x, y position
            xAxis.setLabelCount(5, true); //show only 5 labels (5 vertical grid lines)
        }
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return xAxisLabel.get((int) value);
            }
        });
    }
    private void titulo_Y(){
        //initialize Y-Right-Axis
        YAxis rightAxis = my_barChart.getAxisRight();
        rightAxis.setTextColor(Color.BLACK);
        rightAxis.setTextSize(20);
        rightAxis.setDrawAxisLine(true);
        rightAxis.setAxisLineColor(Color.BLACK);
        rightAxis.setDrawGridLines(true);
        rightAxis.setGranularity(1f);
        rightAxis.setGranularityEnabled(true);
        rightAxis.setAxisMinimum(0);
        // rightAxis.setAxisMaximum(1500f);
        rightAxis.setLabelCount(6, true); //labels (Y-Values) for 4 horizontal grid lines
        rightAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);

        //initialize Y-Left-Axis
        YAxis leftAxis = my_barChart.getAxisLeft();
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
    private void showMonthsBars(){
        meses=true;
        //input Y data (Months Data - 12 Values)
        valores();
        //initialize x Axis Labels (labels for 13 vertical grid lines)
        xAxisLabel.add("E"); //this label will be mapped to the 1st index of the valuesList
        xAxisLabel.add("F");
        xAxisLabel.add("M");
        xAxisLabel.add("A");
        xAxisLabel.add("M");
        xAxisLabel.add("J");
        xAxisLabel.add("JL");
        xAxisLabel.add("A");
        xAxisLabel.add("S");
        xAxisLabel.add("O");
        xAxisLabel.add("N");
        xAxisLabel.add("D");
        xAxisLabel.add(""); //empty label for the last vertical grid line on Y-Right Axis
        titulo_X();
        titulo_Y();

        //set the BarDataSet
        BarDataSet barDataSet = new BarDataSet(entries, "Months");
        barDataSet.setColor(Color.RED);
        barDataSet.setFormSize(15f);
        barDataSet.setDrawValues(true);
        barDataSet.setValueTextSize(12f);

        //set the BarData to chart
        BarData data = new BarData(barDataSet);
        my_barChart.setData(data);
        my_barChart.setScaleEnabled(true);
        my_barChart.getLegend().setEnabled(false);
        my_barChart.setDrawBarShadow(false);
        my_barChart.getDescription().setEnabled(false);
        my_barChart.setPinchZoom(false);
        my_barChart.setDrawGridBackground(true);
        my_barChart.invalidate();
    }
    private void showDayHoursBars(){
        horas=true;
        valores();
        //initialize x Axis Labels (labels for 25 vertical grid lines)
        for(int i = 0; i < 24; i++){
            switch (i){
                case 0:
                    xAxisLabel.add("12 AM"); //12AM - 5AM
                    break;
                case 6:
                    xAxisLabel.add("6"); //6AM - 11AM
                    break;
                case 12:
                    xAxisLabel.add("12 PM"); //12PM - 5PM
                    break;
                case 18:
                    xAxisLabel.add("6"); //6PM - 11PM
                    break;
                default:
                    xAxisLabel.add(String.format(Locale.US, "%02d", i)+":00");
                    break;
            }
        }
        xAxisLabel.add(""); //empty label for the last vertical grid line on Y-Right Axis

        titulo_X();
        titulo_Y();

        //set the BarDataSet
        BarDataSet barDataSet = new BarDataSet(entries, "Hours");
        barDataSet.setColor(Color.RED);
        barDataSet.setFormSize(15f);
        barDataSet.setDrawValues(true); //mostrar valores
        barDataSet.setValueTextSize(12f);
        barDataSet.setGradientColor(Color.rgb(51, 66, 255),Color.rgb(51, 190, 255));
        //barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLUE);
        barDataSet.setValueTextSize(20f);

        //set the BarData to chart

        BarData data = new BarData(barDataSet);
        my_barChart.setData(data);
        my_barChart.setScaleEnabled(true);
        my_barChart.getLegend().setEnabled(false);
        my_barChart.setDrawBarShadow(false);
        my_barChart.getDescription().setEnabled(false);
        my_barChart.setPinchZoom(true);
        my_barChart.setDrawGridBackground(false); //fondo
        my_barChart.setXAxisRenderer(new XAxisRenderer(my_barChart.getViewPortHandler(), my_barChart.getXAxis(), my_barChart.getTransformer(YAxis.AxisDependency.LEFT)){
            @Override
            protected void drawLabel(Canvas c, String formattedLabel, float x, float y, MPPointF anchor, float angleDegrees) {
                //for 6AM and 6PM set the correct label x position based on your needs
                if(!TextUtils.isEmpty(formattedLabel) && formattedLabel.equals("6"))
                    Utils.drawXAxisValue(c, formattedLabel, x+Utils.convertDpToPixel(5f), y+Utils.convertDpToPixel(1f), mAxisLabelPaint, anchor, angleDegrees);
                    //for 12AM and 12PM set the correct label x position based on your needs
                else
                    Utils.drawXAxisValue(c, formattedLabel, x+Utils.convertDpToPixel(20f), y+Utils.convertDpToPixel(1f), mAxisLabelPaint, anchor, angleDegrees);
            }
        });
        my_barChart.invalidate();
    }


}
