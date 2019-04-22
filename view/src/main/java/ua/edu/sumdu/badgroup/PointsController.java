package ua.edu.sumdu.badgroup;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import ua.edu.sumdu.badgroup.entities.Data;
import ua.edu.sumdu.badgroup.entities.Point;
import ua.edu.sumdu.badgroup.job.DeviateCalculation;
import ua.edu.sumdu.badgroup.job.GettingApproximatedFormula;
import ua.edu.sumdu.badgroup.math.Formulas;


import java.net.URL;
import java.util.ResourceBundle;

public class PointsController implements Initializable {

    private App app;

    public PointsController(App app) {
        this.app = app;
       // pointsData = app.getPointsForPlot();
    }

    //@FXML
    //private AnchorPane anchRight;

    @FXML
    private SplitPane splitP;

    @FXML
    private TextField amount;

    @FXML
    private AnchorPane paneLeft;

    @FXML
    private TableView<Point> tableViewPoints;

    @FXML
    private Button savePoints;

    @FXML
    private Button openPoints;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnPlot;

    @FXML
    private TableColumn<Point, Double> pointX;

    @FXML
    private TableColumn<Point, Double> pointY;

    @FXML
    private Label errorLabel;

    //private ObservableList<Point> pointsData /*= FXCollections.observableArrayList(new Point())*/;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //anchRight = app.
        boolean flagX, flagY;
        try {
            pointX.setCellValueFactory(new PropertyValueFactory<>("arg"));
            pointX.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            pointX.setOnEditCommit(
                    event -> ((Point) event.getTableView().getItems().get(
                            event.getTablePosition().getRow())
                    ).setArg(event.getNewValue()));

            pointY.setCellValueFactory(new PropertyValueFactory<>("res"));
            pointY.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            pointY.setOnEditCommit(
                    event -> ((Point) event.getTableView().getItems().get(
                            event.getTablePosition().getRow())
                    ).setRes(event.getNewValue()));
        }catch (ParseException ex) {
            errorLabel.setText("INPUT NUMBERS!");
        }finally {
            //errorLabel.setText("");
        }
        //anchRight = new AnchorPane();
        setPlot();
        splitP.getItems().add(app.getAnchRight());
        splitP.setDividerPositions(0.4);

        tableViewPoints.setItems(app.getPointsForPlot());
    }

    @FXML
    public void addSpace() {
        setPlot();
        app.getPointsForPlot().add(new Point());
    }

    public void setPlot() {
        Axes axes = new Axes(
                400, 420,
                0, 10, 1,
                0, 10, 1
        );
        Points points = new Points(app.getPointsForPlot(),0, 10, 1, axes);
        app.getAnchRight().getChildren().clear();
        app.getAnchRight().getChildren().add(axes);
        app.getAnchRight().getChildren().add(points);
        app.getAnchRight().setPadding(new Insets(10));
        app.getAnchRight().setStyle("-fx-background-color: rgb(35, 39, 50);");

    }

    public Formulas getMinDev() {
        double d = Double.valueOf(app.getMapFP().get(Formulas.LINEAR).getProperty("DEVIATE"));
        Formulas result = Formulas.LINEAR;
        if (d > Double.valueOf(app.getMapFP().get(Formulas.LOGARITHMIC).getProperty("DEVIATE"))) {
            d = Double.valueOf(app.getMapFP().get(Formulas.LOGARITHMIC).getProperty("DEVIATE"));
            result = Formulas.LOGARITHMIC;
        }
        if (d > Double.valueOf(app.getMapFP().get(Formulas.INVERSE).getProperty("DEVIATE"))) {
            d = Double.valueOf(app.getMapFP().get(Formulas.INVERSE).getProperty("DEVIATE"));
            result = Formulas.INVERSE;
        }
        if (d > Double.valueOf(app.getMapFP().get(Formulas.EXPONENTIAL).getProperty("DEVIATE"))) {
            d = Double.valueOf(app.getMapFP().get(Formulas.EXPONENTIAL).getProperty("DEVIATE"));
            result = Formulas.EXPONENTIAL;
        }
        if (d > Double.valueOf(app.getMapFP().get(Formulas.POWER).getProperty("DEVIATE"))) {
            d = Double.valueOf(app.getMapFP().get(Formulas.POWER).getProperty("DEVIATE"));
            result = Formulas.POWER;
        }
        if (d > Double.valueOf(app.getMapFP().get(Formulas.INVERSE_EXPONENTIAL).getProperty("DEVIATE"))) {
            d = Double.valueOf(app.getMapFP().get(Formulas.INVERSE_EXPONENTIAL).getProperty("DEVIATE"));
            result = Formulas.INVERSE_EXPONENTIAL;
        }
        if (d > Double.valueOf(app.getMapFP().get(Formulas.INVERSE_SUM).getProperty("DEVIATE"))) {
            d = Double.valueOf(app.getMapFP().get(Formulas.INVERSE_SUM).getProperty("DEVIATE"));
            result = Formulas.INVERSE_SUM;
        }
        if (d > Double.valueOf(app.getMapFP().get(Formulas.INVERSE_LOG_SUM).getProperty("DEVIATE"))) {
            d = Double.valueOf(app.getMapFP().get(Formulas.INVERSE_LOG_SUM).getProperty("DEVIATE"));
            result = Formulas.INVERSE_LOG_SUM;
        }
        if (d > Double.valueOf(app.getMapFP().get(Formulas.INVERSE_SUM_X).getProperty("DEVIATE"))) {
            d = Double.valueOf(app.getMapFP().get(Formulas.INVERSE_SUM_X).getProperty("DEVIATE"));
            result = Formulas.INVERSE_SUM_X;
        }
        return result;
    }

    @FXML
    public void pressPlot(ActionEvent event) {
        //setPlot();
        for (Point p : app.getPointsForPlot()) {
            app.getDataPoints().add(p);
        }
        DeviateCalculation devCalc = new DeviateCalculation(app.getDataPoints());


        app.setMapFP(devCalc.execute());

        GettingApproximatedFormula gaf = new GettingApproximatedFormula(getMinDev(), app.getDataPoints());
        System.out.println(getMinDev().toString());
        Axes axes = new Axes(
                400, 420,
                0, 10, 1,
                0, 10, 1
        );
        Points points = new Points(app.getPointsForPlot(),0, 10, 1, axes);
        Plot plot = new Plot(getMinDev(), gaf.execute(), 0, 10, 0.1, axes);

        app.getAnchRight().getChildren().clear();
        app.getAnchRight().getChildren().add(axes);
        app.getAnchRight().getChildren().add(points);

        app.getAnchRight().getChildren().add(plot);

        app.getAnchRight().setPadding(new Insets(10));
        app.getAnchRight().setStyle("-fx-background-color: rgb(35, 39, 50);");


        Parent homePageParent1 = app.getParentC();
        Scene homePageScene1 = new Scene(homePageParent1);
        Stage currentStage1 = (Stage) ( (Node) event.getSource()).getScene().getWindow();
        currentStage1.setScene(homePageScene1);
        currentStage1.show();
    }
}
