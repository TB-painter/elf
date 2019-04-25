package ua.edu.sumdu.badgroup;

import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import ua.edu.sumdu.badgroup.entities.Point;

public class Points extends Pane {
    public Points(
            ObservableList<Point> data,
            double xMin, double xMax, double xInc,
            Axes axes
    ) {
        Path path = new Path();
        path.setStroke(Color.GREENYELLOW.deriveColor(0, 1, 1, 0.6));
        path.setStrokeWidth(5);

        path.setClip(
                new Rectangle(
                        0, 0,
                        axes.getPrefWidth(),
                        axes.getPrefHeight()
                )
        );

        double x = xMin;
        double y = Math.cos(x)*3;

        int i = 0;
        path.getElements().add(new MoveTo(mapX(0 , axes), mapY(0, axes)));

        for (Point p:data) {

            path.getElements().add(
                    new MoveTo(
                            mapX(p.getArg()-0.01, axes), mapY(p.getValue(), axes)
                    )
            );

            path.getElements().add(
                    new LineTo(
                            mapX(p.getArg()+0.01, axes), mapY(p.getValue(), axes)
                    )
            );
        }

        setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        setPrefSize(axes.getPrefWidth(), axes.getPrefHeight());
        setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

        getChildren().setAll(path, axes);
    }

    private double mapX(double x, Axes axes) {
        double tx = 20;
        double sx = (axes.getPrefWidth() /
                (axes.getXAxis().getUpperBound() -
                        axes.getXAxis().getLowerBound()));

        return x * sx + tx;
    }

    private double mapY(double y, Axes axes) {
        double ty = axes.getPrefHeight();
        double sy = (axes.getPrefHeight() /
                (axes.getYAxis().getUpperBound() -
                        axes.getYAxis().getLowerBound()));

        return -y * sy + ty;
    }
}
