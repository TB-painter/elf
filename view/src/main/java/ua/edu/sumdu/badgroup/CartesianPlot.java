package ua.edu.sumdu.badgroup;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Function;

// Java 8 code
public class CartesianPlot extends Application {

    private CartesianContr controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) throws IOException {
       /* controller = new CartesianContr();
        FXMLLoader loader = new FXMLLoader();
        loader.setController(controller);
        loader.setLocation(Controller.class.getResource("../../../../loadScreen01.fxml"));

        stage.setTitle("y = \u00BC(x+4)(x+1)(x-2)");
        stage.setScene(new Scene(loader.load(), stage.getWidth(), stage.getHeight()));
        stage.show(); */

        HBox hbox = new HBox();

        Button b = new Button("add");
        b.setOnAction(ev -> hbox.getChildren().add(new Label("Test")));

        ScrollPane scrollPane = new ScrollPane(hbox);
        scrollPane.setFitToHeight(true);

        BorderPane root = new BorderPane(scrollPane);
        root.setPadding(new Insets(15));
        root.setTop(b);

        Scene scene = new Scene(root, 400, 400);

        stage.setScene(scene);
        stage.show();
    }


}
