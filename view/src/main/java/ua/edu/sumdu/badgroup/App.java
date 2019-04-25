package ua.edu.sumdu.badgroup;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.*;
import ua.edu.sumdu.badgroup.entities.Data;
import ua.edu.sumdu.badgroup.entities.Point;
import ua.edu.sumdu.badgroup.job.GettingApproximatedFormula;
import ua.edu.sumdu.badgroup.math.Formulas;

import java.io.IOException;
import java.util.*;

public class App extends Application
{
    private Controller controllerA;
    private PointsController controllerB;
    private ResultController controllerC;

    private AnchorPane anchRight;
    ScrollPane scrollPane;
    private Parent parentA;
    private Parent parentB;
    private Parent parentC;

    private Map<Formulas, Properties> mapFP;
    private Data dataPoints;
    private ObservableList<Point> pointsForPlot;
    private GettingApproximatedFormula gaf;
    private Stage primaryStage;

    private FXMLLoader loaderA = new FXMLLoader();
    private FXMLLoader loaderB = new FXMLLoader();
    private FXMLLoader loaderC = new FXMLLoader();

    public static void main(String[] args )
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        anchRight = new AnchorPane();
        scrollPane = new ScrollPane(anchRight);
        scrollPane.setFitToHeight(true);
        pointsForPlot = FXCollections.observableArrayList();
        mapFP = new HashMap<>();
        dataPoints = new Data(new LinkedList<Point>());
        gaf = new GettingApproximatedFormula(Formulas.INVERSE, dataPoints);


        controllerA = new Controller(this);
        loaderA.setController(controllerA);
        loaderA.setLocation(Controller.class.getResource("../../../../a.fxml"));
        parentA = loaderA.load();

        controllerB = new PointsController(this);
        loaderB.setController(controllerB);
        loaderB.setLocation(ControllerB.class.getResource("../../../../loadScreen01.fxml"));
        parentB = loaderB.load();

        controllerC = new ResultController(this);
        loaderC.setController(controllerC);
        loaderC.setLocation(ControllerC.class.getResource("../../../../loadScreen02.fxml"));
        parentC = loaderC.load();

        primaryStage.setTitle("Test");
        primaryStage.setScene(new Scene(parentA, primaryStage.getWidth(), primaryStage.getHeight()));
        primaryStage.show();
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public ObservableList<Point> getPointsForPlot() {
        return pointsForPlot;
    }

    public Parent getParentA() {
        return parentA;
    }

    public Parent getParentB() {
        return parentB;
    }

    public Parent getParentC() {
        return parentC;
    }

    public AnchorPane getAnchRight() {
        return anchRight;
    }

    public void setAnchRight(AnchorPane anchRight) {
        this.anchRight = anchRight;
    }

    public void setPointsForPlot(ObservableList<Point> pointsForPlot) {
        this.pointsForPlot = pointsForPlot;
    }

    public ScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(ScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public GettingApproximatedFormula getGaf() {
        return gaf;
    }

    public void setGaf(GettingApproximatedFormula gaf) {
        this.gaf = gaf;
    }

    public ResultController getControllerC() {
        return controllerC;
    }

    public Controller getControllerA() {
        return controllerA;
    }

    public PointsController getControllerB() {
        return controllerB;
    }

    public Data getDataPoints() {
        return dataPoints;
    }

    public void setDataPoints(Data dataPoints) {
        this.dataPoints = dataPoints;
    }

    public Map<Formulas, Properties> getMapFP() {
        return mapFP;
    }

    public void setMapFP(Map<Formulas, Properties> mapFP) {
        this.mapFP = mapFP;
    }

}
