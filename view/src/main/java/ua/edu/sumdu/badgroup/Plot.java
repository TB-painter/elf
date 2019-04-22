package ua.edu.sumdu.badgroup;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import ua.edu.sumdu.badgroup.math.Formula;
import ua.edu.sumdu.badgroup.math.Formulas;

import java.util.function.Function;

public class Plot extends Pane {
    public Plot(
            Formulas formulas, Formula constants,
            double xMin, double xMax, double xInc,
            Axes axes
    ) {
        Path path = new Path();
        path.setStroke(Color.ORANGE.deriveColor(0, 1, 1, 0.6));
        path.setStrokeWidth(2);

        path.setClip(
                new Rectangle(
                        0, 0,
                        axes.getPrefWidth(),
                        axes.getPrefHeight()
                )
        );

        double x = xMin;
        double y = apply(x, formulas, constants);

        path.getElements().add(
                new MoveTo(
                        mapX(x, axes), mapY(y, axes)
                )
        );

        x += xInc;
        while (x < xMax) {
            y = apply(x, formulas, constants);

            path.getElements().add(
                    new LineTo(
                            mapX(x, axes), mapY(y, axes)
                    )
            );

            x += xInc;
        }

        setMinSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);
        setPrefSize(axes.getPrefWidth(), axes.getPrefHeight());
        setMaxSize(Pane.USE_PREF_SIZE, Pane.USE_PREF_SIZE);

        getChildren().setAll(axes, path);
    }

    private double mapX(double x, Axes axes) {
        double tx = 20;
        double sx = axes.getPrefWidth() /
                (axes.getXAxis().getUpperBound() -
                        axes.getXAxis().getLowerBound());

        return x * sx + tx;
    }

    private double mapY(double y, Axes axes) {
        double ty = axes.getPrefHeight()-15;
        double sy = axes.getPrefHeight() /
                (axes.getYAxis().getUpperBound() -
                        axes.getYAxis().getLowerBound());

        return -y * sy + ty;
    }

    public double apply (double x, Formulas formulas, Formula constants) {
        double result;
        switch (formulas) {
            case LINEAR: result = constants.getFreeCoef() + constants.getArgCoef()*x;
            case LOGARITHMIC: result = constants.getFreeCoef() + constants.getArgCoef()* Math.log(x);
            case INVERSE: result = constants.getFreeCoef() + constants.getArgCoef()/x;
            case EXPONENTIAL: result = constants.getFreeCoef()* Math.pow(constants.getFreeCoef(), x);
            case POWER: result = constants.getFreeCoef()* Math.pow(x, constants.getFreeCoef());
            case INVERSE_EXPONENTIAL: result = Math.exp(constants.getFreeCoef() + constants.getArgCoef()/x);
            case INVERSE_SUM: result = 1 / (constants.getFreeCoef() + constants.getArgCoef()*x);
            case INVERSE_LOG_SUM: result = 1 / (constants.getFreeCoef() + constants.getArgCoef()* Math.log(x));
            case INVERSE_SUM_X: result = x / (constants.getFreeCoef() + constants.getArgCoef()*x);
            default:
                result = result = constants.getFreeCoef() + constants.getArgCoef()*x;
        }
        return result;
    }
}
