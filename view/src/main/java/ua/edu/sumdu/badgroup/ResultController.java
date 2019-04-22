package ua.edu.sumdu.badgroup;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ua.edu.sumdu.badgroup.job.GettingApproximatedFormula;
import ua.edu.sumdu.badgroup.math.Formula;
import ua.edu.sumdu.badgroup.math.Formulas;

import java.net.URL;
import java.util.ResourceBundle;

public class ResultController implements Initializable {

    private App app;

    public ResultController(App app) {
        this.app = app;
    }

    @FXML
    private SplitPane splitP;

    @FXML
    private AnchorPane anchLeft;

    @FXML
    private RadioButton rb1;

    @FXML
    private RadioButton rb2;

    @FXML
    private RadioButton rb3;

    @FXML
    private RadioButton rb4;

    @FXML
    private RadioButton rb5;

    @FXML
    private RadioButton rb6;

    @FXML
    private RadioButton rb7;

    @FXML
    private RadioButton rb8;

    @FXML
    private ImageView pic1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        splitP.getItems().add(app.getAnchRight());
        splitP.setDividerPositions(0.4);
    }

    public void loadData() {

    }



    public void setPlot() {
        Axes axes = new Axes(
                400, 420,
                0, 10, 1,
                0, 10, 1
        );
        Points points = new Points(app.getPointsForPlot(),0, 10, 1, axes);
        System.out.println(app.getPointsForPlot());

        app.getAnchRight().getChildren().clear();
        app.getAnchRight().getChildren().add(axes);
        app.getAnchRight().getChildren().add(points);
        app.getAnchRight().setPadding(new Insets(10));
        app.getAnchRight().setStyle("-fx-background-color: rgb(35, 39, 50);");

    }
}
