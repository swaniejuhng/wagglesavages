import java.awt.Color;
import java.text.SimpleDateFormat;

import javax.swing.*;
import java.awt.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesDataItem;

@SuppressWarnings("serial")
public class BatteryGraphPanel extends JPanel {

	JFreeChart jfc;
	ChartPanel cp;
	ArrayList<Battery> batteries = new ArrayList<>();

	public BatteryGraphPanel (ArrayList<Battery> batteries) {
		this.batteries = batteries;
		setLayout(new BorderLayout());
		cp = createChartPanel();
		add(cp, BorderLayout.CENTER);
		validate();
	}

	private ChartPanel createChartPanel(){
		jfc = generatedChart();
		ChartPanel chartPanel = new ChartPanel(jfc);
		return chartPanel;
	}

	private JFreeChart generatedChart() {
		final String graphTitle = "Change of Battery & Temperature";
		final XYDataset dataset = createDataset1();

		final JFreeChart chart = ChartFactory.createTimeSeriesChart(
				graphTitle, "Date", "Battery", dataset, true, true, false
				);

		final XYPlot plot = chart.getXYPlot();
		final NumberAxis axis2 = new NumberAxis("Temperature");
		axis2.setAutoRangeIncludesZero(false);
		plot.setRangeAxis(1, axis2);
		plot.setDataset(1, createDataset2());
		plot.mapDatasetToRangeAxis(1, 1);
		final XYItemRenderer renderer = plot.getRenderer();
		renderer.setBaseToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
		if(renderer instanceof StandardXYItemRenderer) {
			final StandardXYItemRenderer rr = (StandardXYItemRenderer)renderer;
			rr.setBaseShapesVisible(true);
			rr.setBaseShapesFilled(true);
		}

		final StandardXYItemRenderer renderer2 = new StandardXYItemRenderer();
		renderer2.setSeriesPaint(0, Color.black);
		//renderer2.setPlotShapes(true);
		renderer2.setBaseShapesVisible(true);
		renderer.setBaseToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());
		plot.setRenderer(1, renderer2);

		final DateAxis axis = (DateAxis)plot.getDomainAxis();
		axis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd HH:mm"));

		return chart;
	}

	private XYDataset createDataset1() {
		String name_ = "Battery (V)";
		String id_ = "ID";
		double temp_ = 0;

		LinkedHashMap<String, TimeSeries> pitTimeSeries
		= new LinkedHashMap<String, TimeSeries>();
		TimeSeries ts = new TimeSeries(name_);
		pitTimeSeries.put(id_, ts);

		TimeSeries timeLine = pitTimeSeries.get(id_);

		//new Minute(minute, hour, day, month, year)

		//2018-07-25 16:57:49
		//0123456789
		for (Battery b : batteries) {
			String ut = b.getUpdated_time();
			int j = ut.indexOf("-");
			int i = ut.indexOf(":");

			int year = Integer.parseInt(ut.substring(0, 4));
			int month = Integer.parseInt(ut.substring(j+1, j+3));
			int day = Integer.parseInt(ut.substring(ut.indexOf("-", j+1)+1, ut.indexOf("-", j+1)+3));
			int hour = Integer.parseInt(ut.substring(i-2, i));
			int minute = Integer.parseInt(ut.substring(i+1, i+3));
			System.out.printf("minute : %d, hour : %d, day : %d, month : %d, year : %d //", minute, hour, day, month, year);

			RegularTimePeriod tm = new Minute(minute, hour, day, month, year);
			TimeSeriesDataItem t = new TimeSeriesDataItem(tm, b.getVoltage());
			timeLine.add(t);
		}


		/*
		for(int i = 0; i < 1440; i++) {
			RegularTimePeriod tm = new Minute(i % 60, i / 60, 8, 8, 2018);
			TimeSeriesDataItem t = new TimeSeriesDataItem(tm, i);
			timeLine.add(t);
		}
		*/

		final TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(timeLine);
		return dataset;
	}

	private XYDataset createDataset2() {
		String name_ = "Temperature (°C)";
		String id_ = "ID";
		double temp_ = 0;

		LinkedHashMap<String, TimeSeries> pitTimeSeries
		= new LinkedHashMap<String, TimeSeries>();
		TimeSeries ts = new TimeSeries(name_);
		pitTimeSeries.put(id_, ts);

		TimeSeries timeLine = pitTimeSeries.get(id_);

		for (Battery b : batteries) {
			String ut = b.getUpdated_time();
			int j = ut.indexOf("-");
			int i = ut.indexOf(":");

			int year = Integer.parseInt(ut.substring(0, 4));
			int month = Integer.parseInt(ut.substring(j+1, j+3));
			int day = Integer.parseInt(ut.substring(ut.indexOf("-", j+1)+1, ut.indexOf("-", j+1)+3));
			int hour = Integer.parseInt(ut.substring(i-2, i));
			int minute = Integer.parseInt(ut.substring(i+1, i+3));
			System.out.printf("minute : %d, hour : %d, day : %d, month : %d, year : %d //", minute, hour, day, month, year);

			RegularTimePeriod tm = new Minute(minute, hour, day, month, year);
			TimeSeriesDataItem t = new TimeSeriesDataItem(tm, b.getTemperature());
			timeLine.add(t);
		}


		/*
		for(int i = 0; i < 1440; i++) {
			RegularTimePeriod tm = new Minute(i % 60, i / 60, 8, 8, 2018);
			TimeSeriesDataItem t = new TimeSeriesDataItem(tm, 288 - i * 0.5);
			timeLine.add(t);
		} */

		final TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(timeLine);
		return dataset;
	}

  public static void main(final String[] args) {
    JFrame f = new JFrame("Waggle Savages");
    f.setSize(900, 900);

    ArrayList<Battery> batteries = WaggleList2.getWeekBatteries("waggle1");
    BatteryGraphPanel bg = new BatteryGraphPanel(batteries);
    f.add(bg);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setVisible(true);
  }

}
