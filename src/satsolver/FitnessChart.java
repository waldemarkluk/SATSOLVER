package satsolver;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.DefaultTableXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.util.Rotation;

/**
 * @author Grzegorz Tokarz
 * @author Marcin Długosz
 * @author Michał Suwała
 *         Date: 22.03.14
 *         Time: 11:38
 */

public class FitnessChart extends JFrame {

    private static final long serialVersionUID = 1L;
    public JFreeChart chart;
    public static DefaultCategoryDataset dataset=new DefaultCategoryDataset();
    public FitnessChart(String applicationTitle, String chartTitle) {
        super(applicationTitle);
        // based on the dataset we create the chart
        chart = createChart(dataset, chartTitle);

        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        // add it to our application
        setContentPane(chartPanel);
    }

    /**
     * Creates a chart
     */

    private JFreeChart createChart(CategoryDataset dataset, String title) {

        JFreeChart chart = ChartFactory.createLineChart("Dopasowanie","Iteracje","Dopasowanie",dataset);


        return chart;

    }
}