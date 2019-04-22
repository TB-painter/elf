package ua.edu.sumdu.badgroup;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Rectangle;
import ua.edu.sumdu.badgroup.entities.Data;
import ua.edu.sumdu.badgroup.entities.Point;

import java.awt.*;
import java.util.function.Function;

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
                            mapX(p.getArg()-0.01, axes), mapY(p.getValue()-0.1, axes)
                    )
            );

            path.getElements().add(
                    new LineTo(
                            mapX(p.getArg(), axes), mapY(p.getValue()-0.1, axes)
                    )
            );
        }

        setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        setPrefSize(axes.getPrefWidth(), axes.getPrefHeight());
        setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

        getChildren().setAll(path, axes);
    }

    private double mapX(double x, Axes axes) {
        double tx = 20; //axes.getPrefWidth() / 2+100;
        double sx = (axes.getPrefWidth() /
                (axes.getXAxis().getUpperBound() -
                        axes.getXAxis().getLowerBound()))-4;

        return x * sx + tx;
    }

    private double mapY(double y, Axes axes) {
        double ty = axes.getPrefHeight()-23; //axes.getPrefHeight() / 2;
        double sy = (axes.getPrefHeight() /
                (axes.getYAxis().getUpperBound() -
                        axes.getYAxis().getLowerBound()))-4;

        return -y * sy + ty;
    }
}
