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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import ua.edu.sumdu.badgroup.entities.Data;
import ua.edu.sumdu.badgroup.entities.Point;
import ua.edu.sumdu.badgroup.job.DeviateCalculation;
import ua.edu.sumdu.badgroup.job.GettingApproximatedFormula;
import ua.edu.sumdu.badgroup.math.Formulas;
import ua.edu.sumdu.badgroup.saving.SaverImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PointsController implements Initializable {

    private App app;

    public PointsController(App app) {
        this.app = app;
    }
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

    public SplitPane getSplitP() {
        return splitP;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

        }

        setPoints(getMaxPoint(app.getPointsForPlot()));
        splitP.getItems().add(app.getScrollPane());
        splitP.setDividerPositions(0.4);

        tableViewPoints.setItems(app.getPointsForPlot());
    }

    @FXML
    public void addSpace() {
        setPoints(getMaxPoint(app.getPointsForPlot()));
        app.getPointsForPlot().add(new Point());
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

    public void setGrafic(double p) {
        Axes axes = new Axes(
                (int)(p*40), (int)(p*40),
                0, (int)p, 1,
                0, (int)p, 1
        );
        Points points = new Points(app.getPointsForPlot(),0, (int)(p*40), 1, axes);
        Plot plot = new Plot(getMinDev(), 0, (int)(p*40), 0.1, axes, app);
        app.getAnchRight().getChildren().clear();
        app.getAnchRight().getChildren().add(axes);
        app.getAnchRight().getChildren().add(points);
        app.getAnchRight().getChildren().add(plot);
        app.getAnchRight().setPadding(new Insets(10));
        app.getAnchRight().setStyle("-fx-background-color: rgb(35, 39, 50);");
    }

    public Formulas getingFormula(int i) {
        Formulas f;
        switch (i) {
            case 0: f = Formulas.LINEAR;
                break;
            case 1: f = Formulas.LOGARITHMIC;
                break;
            case 2: f = Formulas.INVERSE;
                break;
            case 3: f = Formulas.EXPONENTIAL;
                break;
            case 4: f = Formulas.POWER;
                break;
            case 5: f = Formulas.INVERSE_EXPONENTIAL;
                break;
            case 6: f = Formulas.INVERSE_SUM;
                break;
            case 7: f = Formulas.INVERSE_LOG_SUM;
                break;
            case 8: f = Formulas.INVERSE_SUM_X;
                break;
                default: f = Formulas.LINEAR;
        }
        return f;
    }

    public Formulas getMinDev() {
        ArrayList<Double>  list = new ArrayList<>();
        list.add(Double.valueOf(app.getMapFP().get(Formulas.LINEAR).getProperty("DEVIATE")));
        list.add(Double.valueOf(app.getMapFP().get(Formulas.LOGARITHMIC).getProperty("DEVIATE")));
        list.add(Double.valueOf(app.getMapFP().get(Formulas.INVERSE).getProperty("DEVIATE")));
        list.add(Double.valueOf(app.getMapFP().get(Formulas.EXPONENTIAL).getProperty("DEVIATE")));
        list.add(Double.valueOf(app.getMapFP().get(Formulas.POWER).getProperty("DEVIATE")));
        list.add(Double.valueOf(app.getMapFP().get(Formulas.INVERSE_EXPONENTIAL).getProperty("DEVIATE")));
        list.add(Double.valueOf(app.getMapFP().get(Formulas.INVERSE_SUM).getProperty("DEVIATE")));
        list.add(Double.valueOf(app.getMapFP().get(Formulas.INVERSE_LOG_SUM).getProperty("DEVIATE")));
        list.add(Double.valueOf(app.getMapFP().get(Formulas.INVERSE_SUM_X).getProperty("DEVIATE")));
        double min = list.get(0);
        Formulas result = getingFormula(0);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) <= min) {
                min = list.get(i);
                result = getingFormula(i);
            }
        }
        return result;
    }

    @FXML
    public void pressPlot(ActionEvent event) {
        for (Point p : app.getPointsForPlot()) {
            app.getDataPoints().add(p);
        }
        DeviateCalculation devCalc = new DeviateCalculation(app.getDataPoints());
        app.setMapFP(devCalc.execute());
        app.setGaf(new GettingApproximatedFormula(getMinDev(), app.getDataPoints()));
        setGrafic(getMaxPoint(app.getPointsForPlot()));

        app.getControllerC().setLabels();
        app.getControllerC().activateButton(getMinDev());

        Parent homePageParent1 = app.getParentC();
        Scene homePageScene1 = new Scene(homePageParent1);
        Stage currentStage1 = (Stage) ( (Node) event.getSource()).getScene().getWindow();
        currentStage1.setScene(homePageScene1);
        currentStage1.show();
        app.getParentB().getScene().setRoot(new Parent() {});
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

    @FXML
    void pressOpen(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Input file as save your points");
        Stage stage = (Stage) paneLeft.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        try {
            SaverImpl save = new SaverImpl();
            app.setPointsForPlot(FXCollections.observableArrayList(save.parse(file)));
            tableViewPoints.setItems(app.getPointsForPlot());
            setPoints(getMaxPoint(app.getPointsForPlot()));
        } catch (FileNotFoundException e) {
            alert.setTitle("File not found");
            alert.setHeaderText(null);
            alert.setContentText("File not found. Please try again.");
            alert.showAndWait();
        } catch (IOException e) {
            alert.setTitle("Input Output Exception");
            alert.setHeaderText(null);
            alert.setContentText("IO exception. Please try again.");
            alert.showAndWait();
        }
    }

    @FXML
    void pressSave(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Input file as save your points");
        Stage stage = (Stage) paneLeft.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);
        try {
            SaverImpl save = new SaverImpl();
            save.save(app.getPointsForPlot(), file);
        }catch (FileNotFoundException e) {
            alert.setTitle("File not found");
            alert.setHeaderText(null);
            alert.setContentText("File not found. Please try again.");
            alert.showAndWait();
        } catch (IOException e) {
            alert.setTitle("Input Output Exception");
            alert.setHeaderText(null);
            alert.setContentText("IO exception. Please try again.");
            alert.showAndWait();
        }
    }

    @FXML
    void pressBack(ActionEvent event) throws Exception {
        app.setMapFP(new HashMap<>());
        app.setDataPoints(new Data(new ArrayList<>()));
        app.getPointsForPlot().clear();
        app.setGaf(new GettingApproximatedFormula(Formulas.INVERSE, app.getDataPoints()));
        Parent homePageParent1 = app.getParentA();
        Scene homePageScene1 = new Scene(homePageParent1);
        Stage currentStage1 = (Stage) ( (Node) event.getSource()).getScene().getWindow();
        currentStage1.setScene(homePageScene1);
        currentStage1.show();
        app.getParentB().getScene().setRoot(new Parent() {});
    }

}
