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
import sample.pso.Swarm;

public class ScatterPlotChart {

    public Node createChartNode(XYZDataset dataset, double minYRange, double maxYRange) {
        Chart3D chart = createChart(dataset, minYRange, maxYRange);
        Chart3DViewer viewer = new Chart3DViewer(chart);
        return viewer;
    }

    private Chart3D createChart(XYZDataset dataset, double minYRange, double maxYRange) {
        Chart3D chart = Chart3DFactory.createScatterChart("Optimization",
                "", dataset, "X", "Y", "Z");
        chart.setViewPoint(ViewPoint3D.createAboveLeftViewPoint(35));
        XYZPlot plot = (XYZPlot) chart.getPlot();
        plot.setDimensions(new Dimension3D(10, 5, 10));
        plot.setLegendLabelGenerator(new StandardXYZLabelGenerator(
                StandardXYZLabelGenerator.COUNT_TEMPLATE));
        ValueAxis3D xAxis = plot.getXAxis();
        xAxis.setRange(Swarm.DEFAULT_BEGIN_RANGE, Swarm.DEFAULT_END_RANGE);
        ValueAxis3D yAxis = plot.getYAxis();
        yAxis.setRange(minYRange, maxYRange);
        ValueAxis3D zAxis = plot.getZAxis();
        zAxis.setRange(Swarm.DEFAULT_BEGIN_RANGE, Swarm.DEFAULT_END_RANGE);
        ScatterXYZRenderer renderer = (ScatterXYZRenderer) plot.getRenderer();
        renderer.setSize(0.15);
        renderer.setColors(Colors.createIntenseColors());
        return chart;
    }

}
