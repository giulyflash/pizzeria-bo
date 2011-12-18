package gui;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * 
 * @author minowak
 * Klasa rysujaca wykresy
 */
public class Chart {
	private List<Double> results;
	private String title;
	private JFreeChart chart;
	
	private void generateChart() {
		XYSeries series = new XYSeries("Results");
		
		int i = 0;
		for(Double d : results)
			series.add(i++, d);
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		
		chart = ChartFactory.createXYLineChart(title, "iterations", 
				"result", dataset, PlotOrientation.VERTICAL, true, true, false);
	}
	
	/**
	 *  Konstruktor
	 * @param r - lista wynikow
	 * @param t - tytul
	 */
	public Chart(List<Double> r, String t) {
		results = r;
		title = t;
		generateChart();
	}
	
	/**
	 * Zapisuje wykres do pliku
	 * @param filename - nazwa pliku
	 */
	public void saveChart(String filename) {
		try {
			ChartUtilities.saveChartAsJPEG(new File(filename), chart, 800, 600);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}