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

public class AgeChart{
	private Analytics analytics;
		public AgeChart(Analytics analytics){
			 this.analytics = analytics;
		}
		
		public Vector<Chart> createChart() {
		Vector<Chart> charts = new Vector<Chart>();
		for(int i=0;i<analytics.getAnswerCount().size();i++){
        Chart chart = new Chart(ChartType.PIE);

        Configuration conf = chart.getConfiguration();

        conf.setTitle("Age Distribution of answer");

        PlotOptionsPie plotOptions = new PlotOptionsPie();
        plotOptions.setCursor(Cursor.POINTER);
        DataLabels dataLabels = new DataLabels();
        dataLabels.setEnabled(true);
        dataLabels
                .setFormatter("'<b>'+ this.point.name +'</b>: '+ this.percentage +' %'");
        plotOptions.setDataLabels(dataLabels);
        conf.setPlotOptions(plotOptions);

        final DataSeries series = new DataSeries();
        if(analytics.getAnswerCount().get(i+1)==0){
        	series.add(new DataSeriesItem("All Age Range",100));
        }else{
        series.add(new DataSeriesItem("0-18", 100*analytics.getAgeRange()[i][0]/analytics.getAnswerCount().get(i+1)));
        series.add(new DataSeriesItem("19~25",100*analytics.getAgeRange()[i][1]/analytics.getAnswerCount().get(i+1)));
        series.add(new DataSeriesItem("26~35", 100*analytics.getAgeRange()[i][2]/analytics.getAnswerCount().get(i+1)));
        series.add(new DataSeriesItem("36~50",  100*analytics.getAgeRange()[i][3]/analytics.getAnswerCount().get(i+1)));
        series.add(new DataSeriesItem("50~65", 100*analytics.getAgeRange()[i][4]/analytics.getAnswerCount().get(i+1)));
        series.add(new DataSeriesItem("66 and above", 100*analytics.getAgeRange()[i][5]/analytics.getAnswerCount().get(i+1)));
        }
        conf.setSeries(series);
        
        chart.drawChart(conf);
        charts.add(chart);
		}
        return charts;
    }
}
