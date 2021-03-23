package sample.controller;

import com.orsoncharts.Chart3D;
import com.orsoncharts.Chart3DFactory;
import com.orsoncharts.Colors;
import com.orsoncharts.axis.ValueAxis3D;
import com.orsoncharts.data.xyz.XYZDataset;
import com.orsoncharts.fx.Chart3DViewer;
import com.orsoncharts.graphics3d.Dimension3D;
import com.orsoncharts.graphics3d.ViewPoint3D;
import com.orsoncharts.label.StandardXYZLabelGenerator;
import com.orsoncharts.plot.XYZPlot;
import com.orsoncharts.renderer.xyz.ScatterXYZRenderer;
import javafx.scene.Node;

public class ScatterPlotChart {

    public Node createChartNode(XYZDataset dataset, double maxYRange) {
        Chart3D chart = createChart(dataset, maxYRange);
        Chart3DViewer viewer = new Chart3DViewer(chart);
        return viewer;
    }

    private Chart3D createChart(XYZDataset dataset, double maxYRange) {
        Chart3D chart = Chart3DFactory.createScatterChart("Optimization",
                "", dataset, "X", "Y", "Z");
        chart.setViewPoint(ViewPoint3D.createAboveLeftViewPoint(25));
        XYZPlot plot = (XYZPlot) chart.getPlot();
        plot.setDimensions(new Dimension3D(10.0, 4.0, 4.0));
        plot.setLegendLabelGenerator(new StandardXYZLabelGenerator(
                StandardXYZLabelGenerator.COUNT_TEMPLATE));
        ValueAxis3D xAxis = plot.getXAxis();
        xAxis.setRange(-5, 5);
        ValueAxis3D yAxis = plot.getYAxis();
        yAxis.setRange(0, maxYRange);
        ValueAxis3D zAxis = plot.getZAxis();
        zAxis.setRange(-5, 5);
        ScatterXYZRenderer renderer = (ScatterXYZRenderer) plot.getRenderer();
        renderer.setSize(0.15);
        renderer.setColors(Colors.createIntenseColors());
        return chart;
    }

}
