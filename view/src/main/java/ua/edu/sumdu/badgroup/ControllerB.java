package ua.edu.sumdu.badgroup;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import ua.edu.sumdu.badgroup.entities.Data;
import ua.edu.sumdu.badgroup.entities.Point;
import ua.edu.sumdu.badgroup.job.DeviateCalculation;
import ua.edu.sumdu.badgroup.math.Formulas;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ControllerB implements Initializable {


    private App app;

    public ControllerB(App app) {
        this.app = app;
    }

    @FXML
    private Button btnFinish;
    private LinkedList<Point> dataList;
    @FXML
    private TextField xInput;

    @FXML
    private TextField yInput;

    @FXML
    private Button btnOKInput;

    @FXML
    private volatile Label errorLabel;

    @FXML
    private ChoiceBox chBox;

    private Map<Formulas, Properties> getDevCalculate;

    public void setErrorLabel (String s) {
        errorLabel.setText(s);
    }

    public String getErrorLabel() {

        return errorLabel.getText();
    }

    @FXML
    public void pressFinalOK(javafx.event.ActionEvent event) throws IOException {
        Data dataValues = new Data(dataList);
        DeviateCalculation devCalc = new DeviateCalculation(dataValues);
        app.setDataPoints(dataValues);
        app.setMapFP(devCalc.execute());
        System.out.println("OK");
        Parent homePageParent1 = app.getParentC();
        Scene homePageScene1 = new Scene(homePageParent1);
        Stage currentStage1 = (Stage) ( (Node) event.getSource()).getScene().getWindow();
        currentStage1.setScene(homePageScene1);
        currentStage1.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dataList = new LinkedList<>();
        //getDevCalculate = new HashMap<>();
        uploadCoiseValues();
    }

    public void uploadCoiseValues() {
        ObservableList list  = FXCollections.observableList(new LinkedList());
        list.add("y=a0+a1*X  0.066");
        list.add("y=a0+a1*ln(X)  0.342");
        list.add("y=a0+(a1/X)   0.655");
        list.add("y=a0*a1^X   0.103");
        list.add("y=a0*X^a1   0.129");
        list.add("y=e^(a0+a1/X)   0.392");
        list.add("y=1/a0+a1*X   0.228");
        list.add("y=1/(a0+a1*ln(X))   0.028");
        list.add("y=X/(a0+a1*X)   0.198");
        chBox.setItems(list);
    }

    @FXML
    public void addPoint(ActionEvent event) {
        double a,b;
        try {
            a = Double.parseDouble(String.valueOf(Double.parseDouble(xInput.getText())));
            b = Double.parseDouble(String.valueOf(Double.parseDouble(yInput.getText())));
            Point p = new Point(a,b);
            dataList.add(p);
            errorLabel.setText("");
           // System.out.println("X: " + a + " Y: " + b);
        }
        catch (NullPointerException | NumberFormatException ex) {
            errorLabel.setText("INCORRECT FORMATS OF VALUES");
        }
        finally {
            xInput.setText("");
            yInput.setText("");
        }
    }


    public void pressChBtn(ActionEvent event) {
        if (chBox.isDisable()) {
            chBox.setDisable(false);
        }
        else {
            chBox.setValue("");
            chBox.setDisable(true);
        }
    }


}
