package ua.edu.sumdu.badgroup;

import javafx.beans.binding.Bindings;
import javafx.geometry.Side;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.Pane;

public class Axes extends Pane {
    private NumberAxis xAxis;
    private NumberAxis yAxis;

    public Axes(
            int width, int height,
            double xLow, double xHi, double xTickUnit,
            double yLow, double yHi, double yTickUnit
    ) {
        setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        setPrefSize(width, height);
        setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

        xAxis = new NumberAxis(xLow, xHi, xTickUnit);
        xAxis.setSide(Side.BOTTOM);

        xAxis.setMaxWidth(width);

        xAxis.setMinorTickVisible(false);
        xAxis.setPrefWidth(width);
        xAxis.setLayoutY(height);

        xAxis.setLayoutX(21);

        yAxis = new NumberAxis(yLow, yHi, yTickUnit);
       // yAxis.setMaxHeight(height-40);
        yAxis.setSide(Side.LEFT);
        yAxis.setMinorTickVisible(false);
        yAxis.setPrefHeight(height);
        yAxis.setLayoutY(0);


        getChildren().setAll(xAxis, yAxis);
    }

    public NumberAxis getXAxis() {
        return xAxis;
    }

    public NumberAxis getYAxis() {
        return yAxis;
    }
}
