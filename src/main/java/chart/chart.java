package chart;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.PointClickEvent;
import com.vaadin.addon.charts.PointClickListener;
//import com.vaadin.addon.charts.examples.AbstractVaadinChartExample;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.ui.Component;
//import com.vaadin.ui.Notification;
//import com.vaadin.ui.Window.Notification;

public class chart {
	 
	    public String getDescription() {
	        return  " Chart";
	    }
	   
	    protected Component getChart() {

	        Component ret = createChart();
	        ret.setWidth("100%");
	        ret.setHeight("450px");
	        return ret;
	    }
	    public static Chart createChart() {
	    	return new Chart(ChartType.PIE);
	    }

}
