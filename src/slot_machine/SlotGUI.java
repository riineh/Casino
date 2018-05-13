package slot_machine;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static javafx.application.Application.launch;

public class SlotGUI extends Application {

    private int kõrgus = 700;
    private int laius = 1200;

    private String logoPath = "file:casinoLogo.jpg";
    private String logoPath2 = "file:casinoLogoIlma.jpg";
    private Font megrim35 = Font.loadFont(new FileInputStream(new File("Megrim.ttf")),35);
    private Font megrim22 = Font.loadFont(new FileInputStream(new File("Megrim.ttf")),22);

    public SlotGUI() throws FileNotFoundException {
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

    public void start(Stage stage) {
        Group juur = new Group();
        Scene stseen = new Scene(juur, laius, kõrgus);

        ImageView iv2 = looTaust(logoPath2, stseen);
        juur.getChildren().add(iv2);

        stage.setScene(stseen);
        stage.setTitle("Casino");
        stage.show();

        //küsi andmed ring
        Pane ring = getAndmedKüsida();
        ring.setLayoutX(stseen.getWidth()/2-laius/4);
        ring.setLayoutY(stseen.getHeight()/2-laius/4);

        Pane  nimeTextField = new StackPane();
        TextField tf1 = getTextField("[3] või [5]");
        nimeTextField.getChildren().add(tf1);
        nimeTextField.setLayoutX(stseen.getWidth()/2-(225/2));
        nimeTextField.setLayoutY(stseen.getHeight()/2+ 105);

        juur.getChildren().addAll(ring, nimeTextField);
    }

    private TextField getTextField(String sisu){
        TextField tf = new TextField();
        tf.setText(sisu);
        tf.setFont(megrim22);
        tf.setMaxWidth(225);
        tf.setOpacity(0.7);
        tf.setOnMouseClicked(event -> tf.deleteText(0, tf.getText().length()));

        return tf;
    }

    private Pane getAndmedKüsida() {
        Pane pane = new StackPane();
        Circle ring = new Circle();
        ring.setFill(Color.rgb(45, 65, 70));
        ring.setRadius(laius/4);

        Text text = new Text();
        text.setFill(Color.WHITE);
        text.setText("Tere tulemast!\n\n" + "Kas soovid mängida " +
                "kolmese või viiese automaadiga? \n" +
                "Kolmesega mängides on võiduvõimalused väiksemad, \n aga võidusummad suuremad, kui viiese mänguautomaadiga \n mängides. \n" +
        "Sisesta [3] või [5] vastava automaadiga mängimiseks. \n Tee oma valik: \n");
        text.setFont(megrim22);
        text.setTextAlignment(TextAlignment.CENTER);

        pane.setOpacity(0.95);

        pane.getChildren().addAll(ring, text);
        return pane;
    }
}

