package chart;

import java.util.Vector;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.PlotOptionsPie;

import logic.Analytics;

public class GenderChart{
    private Analytics analytics;
        public GenderChart(Analytics analytics){
             this.analytics = analytics;
        }
        
        public Vector<Chart> createChart() {
            Vector<Chart> charts = new Vector<Chart>();
            for(int i=0;i<analytics.getAnswerCount().size();i++){
           Chart chart = new Chart(ChartType.PIE);

           Configuration conf = chart.getConfiguration();

           conf.setTitle("Gender Distribution of answer");

           PlotOptionsPie plotOptions = new PlotOptionsPie();
           plotOptions.setCursor(Cursor.POINTER);
           DataLabels dataLabels = new DataLabels();
           dataLabels.setEnabled(true);
           dataLabels
                   .setFormatter("'<b>'+ this.point.name +'</b>: '+ this.percentage +' %'");
           plotOptions.setDataLabels(dataLabels);
           conf.setPlotOptions(plotOptions);

           final DataSeries series = new DataSeries();
           if(analytics.getAnswerCount().get(1 + i)==0){
               series.add(new DataSeriesItem("All Gender Range",100));
           }else{
           series.add(new DataSeriesItem("Male", 100*analytics.getGender()[i][0]/analytics.getAnswerCount().get(i + 1)));
           series.add(new DataSeriesItem("Female",100*analytics.getGender()[i][1]/analytics.getAnswerCount().get(i + 1)));
           series.add(new DataSeriesItem("Other", 100*analytics.getGender()[i][2]/analytics.getAnswerCount().get(i + 1)));
           }
           conf.setSeries(series);
           
           chart.drawChart(conf);
           charts.add(chart);
            }
           return charts;
       }
}