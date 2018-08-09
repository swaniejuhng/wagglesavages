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
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import java.util.LinkedHashMap;
import org.jfree.data.time.Minute;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesDataItem;

public class BatteryGraphPanel extends JPanel {
  JFreeChart jfc;
  ChartPanel cp;

  public BatteryGraphPanel() {
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
    final String graphTitle = "Battery & Temperature Change";
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
    String name_ = "Battery";
    String id_ = "ID";
    double temp_ = 0;

    LinkedHashMap<String, TimeSeries> pitTimeSeries
      = new LinkedHashMap<String, TimeSeries>();
    TimeSeries ts = new TimeSeries(name_);
    pitTimeSeries.put(id_, ts);

    TimeSeries timeLine = pitTimeSeries.get(id_);

    for(int i = 0; i < 1440; i++) {
      RegularTimePeriod tm = new Minute(i % 60, i / 60, 8, 8, 2018);
      TimeSeriesDataItem t = new TimeSeriesDataItem(tm, i);
      timeLine.add(t);
    }

    final TimeSeriesCollection dataset = new TimeSeriesCollection();
    dataset.addSeries(timeLine);
    return dataset;
  }

  private XYDataset createDataset2() {
    String name_ = "Temperature";
    String id_ = "ID";
    double temp_ = 0;

    LinkedHashMap<String, TimeSeries> pitTimeSeries
      = new LinkedHashMap<String, TimeSeries>();
    TimeSeries ts = new TimeSeries(name_);
    pitTimeSeries.put(id_, ts);

    TimeSeries timeLine = pitTimeSeries.get(id_);

    for(int i = 0; i < 1440; i++) {
      RegularTimePeriod tm = new Minute(i % 60, i / 60, 8, 8, 2018);
      TimeSeriesDataItem t = new TimeSeriesDataItem(tm, 288 - i * 0.5);
      timeLine.add(t);
    }

    final TimeSeriesCollection dataset = new TimeSeriesCollection();
    dataset.addSeries(timeLine);
    return dataset;
  }

  public static void main(final String[] args) {
    JFrame f = new JFrame("Waggle Savages");
    f.setSize(900, 900);

    BatteryGraphPanel bg = new BatteryGraphPanel();
    f.add(bg);
    f.setVisible(true);
  }
}
