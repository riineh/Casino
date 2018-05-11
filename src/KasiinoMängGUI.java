import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;
import java.io.*;

public class KasiinoMängGUI extends Application {
    private String logoPath = "file:casinoLogo.jpg";
    private String logoPath2 = "file:casinoLogoIlma.jpg";
    private Font megrim50 = Font.loadFont(new FileInputStream(new File("Megrim.ttf")),50);
    private Font megrim35 = Font.loadFont(new FileInputStream(new File("Megrim.ttf")),35);
    private Font megrim22 = Font.loadFont(new FileInputStream(new File("Megrim.ttf")),22);

    private int kõrgus = 700;
    private int laius = 1200;

    public KasiinoMängGUI() throws FileNotFoundException {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Group juur = new Group();
        Scene stseen = new Scene(juur, laius, kõrgus);

        ImageView iv = looTaust(logoPath, stseen);
        juur.getChildren().add(iv);

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

        stage.setScene(stseen);
        stage.setTitle("Casino");
        stage.show();

        Pane ring = küsiAnmded();
        ring.setLayoutX(stseen.getWidth()/2-laius/4);
        ring.setLayoutY(stseen.getHeight()/2-laius/4);

        playNupp.setOnMouseClicked(event -> {juur.getChildren().clear();
        ImageView iv2 = looTaust(logoPath2, stseen);
        juur.getChildren().addAll(iv2, ring, exitNupp);});
    }

    private Pane getExitNupp(){
        Pane pane = new StackPane();
        Rectangle rect = new Rectangle(150, 70);
        rect.setFill(Color.rgb(45, 65, 70));
        rect.setStroke(Color.WHITE);

        Text text = new Text();
        text.setFill(Color.WHITE);
        text.setText("Exit");
        text.setFont(megrim50);

        pane.getChildren().addAll(rect, text);
        pane.setOpacity(0.8);

        pane.setOnMouseEntered(event -> rect.setFill(Color.rgb(53, 69, 73)));
        pane.setOnMouseExited(event -> rect.setFill(Color.rgb(45, 65, 70)));
        pane.setOnMouseClicked(event -> Platform.exit());

        return pane;
    }

    private ImageView looTaust(String logoP, Scene stseen) {
        Image logo = new Image(logoP);
        ImageView iv = new ImageView(logo);
        //määran pildi suuruse soovitud laiuseks ja kõrguseks
        iv.setFitWidth(stseen.getWidth());
        iv.setFitHeight(stseen.getHeight());

        iv.fitWidthProperty().bind(stseen.widthProperty());
        iv.fitHeightProperty().bind(stseen.heightProperty());
        return iv;
    }

    private Pane getPlayNupp() {
        Pane pane = new StackPane();
        Rectangle rect = new Rectangle(150, 70);
        rect.setFill(Color.rgb(45, 65, 70));
        rect.setStroke(Color.WHITE);

        Text text = new Text();
        text.setFill(Color.WHITE);
        text.setText("Play");
        text.setFont(megrim50);

        pane.getChildren().addAll(rect, text);
        pane.setOpacity(0.8);

        pane.setOnMouseEntered(event -> rect.setFill(Color.rgb(53, 69, 73)));
        pane.setOnMouseExited(event -> rect.setFill(Color.rgb(45, 65, 70)));

        return pane;
    }

    private Pane küsiAnmded() {
        Pane pane = new StackPane();
        Circle ring = new Circle();
        ring.setFill(Color.rgb(45, 65, 70));
        ring.setRadius(laius/4);

        Text text = new Text();
        text.setFill(Color.WHITE);
        text.setText("Tere tulemast\nkasiinosse U of T!\n\nPalun sisesta enda andmed:\n\n\n\n");
        text.setFont(megrim35);
        text.setTextAlignment(TextAlignment.CENTER);

        pane.setOpacity(0.95);

        /*
        TextField tf = new TextField();
        tf.setText("Nimi");
        tf.setFont(megrim22);
        tf.setMaxWidth(200);
        tf.setOpacity(0.7);
        */

        pane.getChildren().addAll(ring, text);
        return pane;
    }
}
