package gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.pizzeria.Result;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.data.xy.XYDataItem;
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
		XYSeries minimum = new XYSeries("Minimum");
		
		
		int i = 0;
		for(Double d : results)
			series.add(i++, d);
		
		i = 0;
		int tmpInd = 0;
		double tmpMin = results.get(tmpInd);
		
		for(Double d : results)
			if (d < tmpMin) {
				tmpMin = d;
				tmpInd = results.indexOf(d);
			}
		
		minimum.add(tmpInd, tmpMin);
		series.remove(tmpInd);
		
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		dataset.addSeries(minimum);
		
		chart = ChartFactory.createScatterPlot(title, "iterations", 
				"result", dataset, PlotOrientation.VERTICAL, true, true, false);
	}
	
	/**
	 *  Konstruktor
	 * @param r - lista wynikow
	 * @param t - tytul
	 */
	public Chart(Result wynik, String t, boolean gen) {
		if (gen && wynik.getExtendedIterationResult().size()>0)
		{
			results = wynik.getExtendedIterationResult().get(0);
			for(int i=0; i<results.size(); i++)
			{
				System.out.print(results.get(i)+ " ");
			}
		} else
		{
			results = wynik.getIterationResults();
			for(int i=0; i<results.size(); i++)
			{
				System.out.print(results.get(i)+ " ");
			}
		}
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
