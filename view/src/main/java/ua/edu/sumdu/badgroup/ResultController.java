package ua.edu.sumdu.badgroup;


import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ua.edu.sumdu.badgroup.entities.Point;
import ua.edu.sumdu.badgroup.math.Formulas;
import ua.edu.sumdu.badgroup.saving.SaverImpl;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResultController implements Initializable {

    private App app;

    public ResultController(App app) {
        this.app = app;
    }

    @FXML
    private Button screenSave;

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
    private RadioButton rb9;

    @FXML
    private ImageView pic1;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private Label label3;

    @FXML
    private Label label4;

    @FXML
    private Label label5;

    @FXML
    private Label label6;

    @FXML
    private Label label7;

    @FXML
    private Label label8;

    @FXML
    private Label label9;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        splitP.getItems().add(app.getScrollPane());
        splitP.setDividerPositions(0.4);
    }

    public App getApp() {
        return app;
    }

    public Label getLabel1() {
        return label1;
    }

    public Label getLabel2() {
        return label2;
    }

    public Label getLabel3() {
        return label3;
    }

    public Label getLabel4() {
        return label4;
    }

    public Label getLabel5() {
        return label5;
    }

    public Label getLabel6() {
        return label6;
    }

    public Label getLabel7() {
        return label7;
    }

    public Label getLabel8() {
        return label8;
    }

    public Label getLabel9() {
        return label9;
    }

    public void reVisual(Formulas f, double p) {
        Axes axes = new Axes(
                (int)(p*40), (int)(p*40),
                0, (int)p, 1,
                0, (int)p, 1
        );
        Points points = new Points(app.getPointsForPlot(),0, (int)(p*40), 1, axes);
        Plot plot = new Plot(f, 0, (int)(p*40), 0.1, axes, app);
        app.getAnchRight().getChildren().clear();
        app.getAnchRight().getChildren().add(axes);
        app.getAnchRight().getChildren().add(points);
        app.getAnchRight().getChildren().add(plot);
    }

    @FXML
    void exequte1(ActionEvent event) {
        rb2.setSelected(false);
        rb3.setSelected(false);
        rb4.setSelected(false);
        rb5.setSelected(false);
        rb6.setSelected(false);
        rb7.setSelected(false);
        rb8.setSelected(false);
        rb9.setSelected(false);
        reVisual(Formulas.LINEAR, getMaxPoint(app.getPointsForPlot()));

    }

    @FXML
    void exequte2(ActionEvent event) {
        rb1.setSelected(false);
        rb3.setSelected(false);
        rb4.setSelected(false);
        rb5.setSelected(false);
        rb6.setSelected(false);
        rb7.setSelected(false);
        rb8.setSelected(false);
        rb9.setSelected(false);
        reVisual(Formulas.LOGARITHMIC, getMaxPoint(app.getPointsForPlot()));
    }

    @FXML
    void exequte3(ActionEvent event) {
        rb1.setSelected(false);
        rb2.setSelected(false);
        rb4.setSelected(false);
        rb5.setSelected(false);
        rb6.setSelected(false);
        rb7.setSelected(false);
        rb8.setSelected(false);
        rb9.setSelected(false);
        reVisual(Formulas.INVERSE, getMaxPoint(app.getPointsForPlot()));
    }

    @FXML
    void exequte4(ActionEvent event) {
        rb1.setSelected(false);
        rb2.setSelected(false);
        rb3.setSelected(false);
        rb5.setSelected(false);
        rb6.setSelected(false);
        rb7.setSelected(false);
        rb8.setSelected(false);
        rb9.setSelected(false);
        reVisual(Formulas.EXPONENTIAL, getMaxPoint(app.getPointsForPlot()));
    }

    @FXML
    void exequte5(ActionEvent event) {
        rb1.setSelected(false);
        rb2.setSelected(false);
        rb3.setSelected(false);
        rb4.setSelected(false);
        rb6.setSelected(false);
        rb7.setSelected(false);
        rb8.setSelected(false);
        rb9.setSelected(false);
        reVisual(Formulas.POWER, getMaxPoint(app.getPointsForPlot()));
    }

    @FXML
    void exequte6(ActionEvent event) {
        rb1.setSelected(false);
        rb2.setSelected(false);
        rb3.setSelected(false);
        rb4.setSelected(false);
        rb5.setSelected(false);
        rb7.setSelected(false);
        rb8.setSelected(false);
        rb9.setSelected(false);
        reVisual(Formulas.INVERSE_EXPONENTIAL, getMaxPoint(app.getPointsForPlot()));
    }

    @FXML
    void exequte7(ActionEvent event) {
        rb1.setSelected(false);
        rb2.setSelected(false);
        rb3.setSelected(false);
        rb4.setSelected(false);
        rb5.setSelected(false);
        rb6.setSelected(false);
        rb8.setSelected(false);
        rb9.setSelected(false);
        reVisual(Formulas.INVERSE_SUM, getMaxPoint(app.getPointsForPlot()));
    }

    @FXML
    void exequte8(ActionEvent event) {
        rb1.setSelected(false);
        rb2.setSelected(false);
        rb3.setSelected(false);
        rb4.setSelected(false);
        rb5.setSelected(false);
        rb6.setSelected(false);
        rb7.setSelected(false);
        rb9.setSelected(false);
        reVisual(Formulas.INVERSE_LOG_SUM, getMaxPoint(app.getPointsForPlot()));
    }

    @FXML
    void exequte9(ActionEvent event) {
        rb1.setSelected(false);
        rb2.setSelected(false);
        rb3.setSelected(false);
        rb4.setSelected(false);
        rb5.setSelected(false);
        rb6.setSelected(false);
        rb7.setSelected(false);
        rb8.setSelected(false);
        reVisual(Formulas.INVERSE_SUM_X, getMaxPoint(app.getPointsForPlot()));
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

    public void setLabels() {
        label1.setText(app.getMapFP().get(Formulas.LINEAR).getProperty("DEVIATE"));
        label2.setText(app.getMapFP().get(Formulas.LOGARITHMIC).getProperty("DEVIATE"));
        label3.setText(app.getMapFP().get(Formulas.INVERSE).getProperty("DEVIATE"));
        label4.setText(app.getMapFP().get(Formulas.EXPONENTIAL).getProperty("DEVIATE"));
        label5.setText(app.getMapFP().get(Formulas.POWER).getProperty("DEVIATE"));
        label6.setText(app.getMapFP().get(Formulas.INVERSE_EXPONENTIAL).getProperty("DEVIATE"));
        label7.setText(app.getMapFP().get(Formulas.INVERSE_SUM).getProperty("DEVIATE"));
        label8.setText(app.getMapFP().get(Formulas.INVERSE_LOG_SUM).getProperty("DEVIATE"));
        label9.setText(app.getMapFP().get(Formulas.INVERSE_SUM_X).getProperty("DEVIATE"));
    }

    public void activateButton(Formulas i) {
        switch (i) {
            case LINEAR: rb1.setSelected(true); break;
            case LOGARITHMIC: rb2.setSelected(true); break;
            case INVERSE: rb3.setSelected(true); break;
            case EXPONENTIAL: rb4.setSelected(true); break;
            case POWER: rb5.setSelected(true); break;
            case INVERSE_EXPONENTIAL: rb6.setSelected(true); break;
            case INVERSE_SUM: rb7.setSelected(true); break;
            case INVERSE_LOG_SUM: rb8.setSelected(true); break;
            case INVERSE_SUM_X: rb9.setSelected(true); break;
        }
    }
    @FXML
    public void screenShot() {
        WritableImage image = app.getAnchRight().snapshot(new SnapshotParameters(), null);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Input file as save your points");
        Stage stage = (Stage) anchLeft.getScene().getWindow();
        File file = fileChooser.showSaveDialog(stage);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
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

}
