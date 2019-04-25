package ua.edu.sumdu.badgroup;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import ua.edu.sumdu.badgroup.entities.Data;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

public class CartesianContr implements Initializable {

    @FXML
    private SplitPane splitP;

    @FXML
    private AnchorPane anchRight;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Axes axes = new Axes(
                400, 420,
                -8, 8, 1,
                -6, 6, 1
        );

       /* Plot plot = new Plot(
                x -> .25 * (x + 4) * (x + 1) * (x - 2),
                -8, 8, 0.1,
                axes
        );*/
        //Points points = new Points(-8, 8, 1, axes);

        anchRight = new AnchorPane();
        anchRight.getChildren().clear();
       // anchRight.getChildren().add(plot);
       // anchRight.getChildren().add(points);
        anchRight.setPadding(new Insets(10));
        anchRight.setStyle("-fx-background-color: rgb(35, 39, 50);");
        splitP.getItems().add(anchRight);
        splitP.setDividerPositions(0.4);
    }

}
