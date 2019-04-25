package ua.edu.sumdu.badgroup;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ua.edu.sumdu.badgroup.entities.Point;

import java.io.IOException;

import static java.lang.Thread.sleep;

public class Controller {

    private App app;

    public Controller(App app) {
        this.app = app;
    }

    @FXML
    private Button btnNew;

    @FXML
    private Button btnExit;

    public void pressNew(ActionEvent event) throws IOException {
        setPoints(getMaxPoint(app.getPointsForPlot()));
        Parent homePageParent = app.getParentB();
        Scene homePageScene = new Scene(homePageParent);
        Stage currentStage = (Stage) ( (Node) event.getSource()).getScene().getWindow();
        currentStage.setScene(homePageScene);
        currentStage.show();
        app.getParentA().getScene().setRoot(new Parent() {});
    }

    public void setPoints(double p) {
        Axes axes = new Axes(
                (int)(p*40), (int)(p*40),
                0, (int)p, 1,
                0, (int)p, 1
        );
        Points points = new Points(app.getPointsForPlot(),0, (int)(p*40), 1, axes);
        app.getAnchRight().getChildren().clear();
        app.getAnchRight().getChildren().add(axes);
        app.getAnchRight().getChildren().add(points);
        app.getAnchRight().setPadding(new Insets(10));
        app.getAnchRight().setStyle("-fx-background-color: rgb(35, 39, 50);");
    }

    public double getMaxPoint(ObservableList<Point> p) {
        double result = 10;
        if (p.size() != 0) {

            for (Point point: p) {
                if (point.getArg() > result) {
                    result = point.getArg();
                }
                if (point.getValue() > result) {
                    result = point.getValue();
                }
            }
            return result;
        }
        return result;
    }

    public  void pressExit(ActionEvent event) {
        System.exit(0);
    }
}
