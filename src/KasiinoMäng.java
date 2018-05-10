import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.canvas.*;

import java.awt.*;
import java.io.*;

public class KasiinoMäng extends Application {
    private String logoPath = "file:casinoLogo.jpg";
    private Font megrim = Font.loadFont(new FileInputStream(new File("Megrim.ttf")),50);

    private int kõrgus = 700;
    private int laius = 1200;

    public KasiinoMäng() throws FileNotFoundException {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Group juur = new Group();
        StackPane stackPane = new StackPane();

        //tekitan logost pildi ja et seda näidata saaks ka ImageView
        Image logo = new Image(logoPath);
        ImageView iv = new ImageView(logo);
        //määran pildi suuruse soovitud laiuseks ja kõrguseks
        iv.setFitWidth(laius);
        iv.setFitHeight(kõrgus);
        stackPane.getChildren().add(iv);

        //Selleks, et ekraani muutes tuleks pilt kaasa
        stackPane.heightProperty().addListener((observable, oldValue, newValue) -> {iv.setFitHeight((double) newValue);});
        stackPane.widthProperty().addListener((observable, oldValue, newValue) -> {iv.setFitWidth((double) newValue);});

        juur.getChildren().add(stackPane);

        //exit nupp
        Pane exitNupp = getExitNupp();
        exitNupp.setLayoutX(laius-200);
        exitNupp.setLayoutY(50);
        juur.getChildren().add(exitNupp);

        //play nupp
        Pane playNupp = getPlayNupp();
        playNupp.setLayoutX(laius-200);
        playNupp.setLayoutY(150);
        juur.getChildren().add(playNupp);

        Scene stseen = new Scene(juur, laius, kõrgus);
        stage.setScene(stseen);
        stage.setTitle("Casino");
        stage.show();
    }

    private Pane getExitNupp(){
        Pane pane = new StackPane();
        Rectangle rect = new Rectangle(150, 70);
        rect.setFill(Color.rgb(45, 65, 70));
        rect.setStroke(Color.WHITE);

        Text text = new Text();
        text.setFill(Color.WHITE);
        text.setText("Exit");
        text.setFont(megrim);

        pane.getChildren().addAll(rect, text);

        pane.setOnMouseEntered(event -> rect.setFill(Color.rgb(53, 69, 73)));
        pane.setOnMouseExited(event -> rect.setFill(Color.rgb(45, 65, 70)));
        pane.setOnMouseClicked(event -> Platform.exit());

        return pane;
    }

    private Pane getPlayNupp() {
        Pane pane = new StackPane();
        Rectangle rect = new Rectangle(150, 70);
        rect.setFill(Color.rgb(45, 65, 70));
        rect.setStroke(Color.WHITE);

        Text text = new Text();
        text.setFill(Color.WHITE);
        text.setText("Play");
        text.setFont(megrim);

        pane.getChildren().addAll(rect, text);

        pane.setOnMouseEntered(event -> rect.setFill(Color.rgb(53, 69, 73)));
        pane.setOnMouseExited(event -> rect.setFill(Color.rgb(45, 65, 70)));

        return pane;
    }
}
