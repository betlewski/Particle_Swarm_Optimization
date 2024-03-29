package sample.controller;

import com.orsoncharts.Chart3D;
import com.orsoncharts.Chart3DFactory;
import com.orsoncharts.Range;
import com.orsoncharts.axis.ValueAxis3D;
import com.orsoncharts.data.function.Function3D;
import com.orsoncharts.fx.Chart3DViewer;
import com.orsoncharts.graphics3d.Dimension3D;
import com.orsoncharts.graphics3d.ViewPoint3D;
import com.orsoncharts.legend.LegendAnchor;
import com.orsoncharts.plot.XYZPlot;
import com.orsoncharts.renderer.RainbowScale;
import com.orsoncharts.renderer.xyz.SurfaceRenderer;
import com.orsoncharts.util.Orientation;
import javafx.scene.Node;
import sample.pso.Function;
import sample.pso.FunctionType;
import sample.pso.Swarm;

public class SurfaceChart {

    public Node createChartNode(FunctionType functionType) {
        Chart3D chart = createChart(functionType);
        Chart3DViewer viewer = new Chart3DViewer(chart);
        return viewer;
    }

    private Chart3D createChart(FunctionType functionType) {
        Function3D function = Function::getValueInPoint;
        Chart3D chart = Chart3DFactory.createSurfaceChart(
                functionType.toString(),
                "",
                function, "X", "Y", "Z");
        XYZPlot plot = (XYZPlot) chart.getPlot();
        plot.setDimensions(new Dimension3D(10, 5, 10));
        ValueAxis3D xAxis = plot.getXAxis();
        xAxis.setRange(Swarm.DEFAULT_BEGIN_RANGE, Swarm.DEFAULT_END_RANGE);
        ValueAxis3D zAxis = plot.getZAxis();
        zAxis.setRange(Swarm.DEFAULT_BEGIN_RANGE, Swarm.DEFAULT_END_RANGE);
        SurfaceRenderer renderer = (SurfaceRenderer) plot.getRenderer();
        renderer.setColorScale(new RainbowScale(new Range(0, 10.0)));
        renderer.setDrawFaceOutlines(false);
        chart.setLegendPosition(LegendAnchor.BOTTOM_RIGHT, Orientation.VERTICAL);
        chart.setViewPoint(ViewPoint3D.createAboveLeftViewPoint(35));
        return chart;
    }

}
