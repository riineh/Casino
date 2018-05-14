package slot_machine;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class SlotGUI  {

    private int kõrgus = 700;
    private int laius = 1200;

    private String logoPath = "file:casinoLogo.jpg";
    private String logoPath2 = "file:casinoLogoIlma.jpg";
    private Font megrim35 = Font.loadFont(new FileInputStream(new File("Megrim.ttf")),35);
    private Font megrim22 = Font.loadFont(new FileInputStream(new File("Megrim.ttf")),22);
    private Scene stseen;

    public SlotGUI(Scene stseen) throws FileNotFoundException {
        this.stseen = stseen;
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

    public Group start() {
        Group juur = new Group();


        ImageView iv2 = looTaust(logoPath2, stseen);
        juur.getChildren().add(iv2);



        Pane ring = getAndmedKüsida();
        ring.setLayoutX(stseen.getWidth()/2-laius/4);
        ring.setLayoutY(stseen.getHeight()/2-laius/4);
        Pane ruut = taustruut();
        ruut.setLayoutX(100);
        ruut.setLayoutY(100);

        Pane  nimeTextField = new StackPane();
        TextField tf1 = getTextField("[3] või [5]");
        nimeTextField.getChildren().add(tf1);
        nimeTextField.setLayoutX(stseen.getWidth()/2-(225/2));
        nimeTextField.setLayoutY(stseen.getHeight()/2+ 65);
        Pane playNupp = getVäikeNupp("Play");
        playNupp.setLayoutX((stseen.getWidth()/2)-(115/2));
        playNupp.setLayoutY(stseen.getHeight()/2 + 125);

        juur.getChildren().addAll(ring, nimeTextField, playNupp);

        Canvas canvas = new Canvas(1200, 700);

        Image seitse = new Image("http://icons.iconarchive.com/icons/designcontest/casino/96/Seven-icon.png");
        Image kirsid  = new Image("http://icons.iconarchive.com/icons/designcontest/casino/96/Cherry-icon.png");
        Image banaanid = new Image("http://icons.iconarchive.com/icons/designcontest/casino/96/Banana-icon.png");
        Image sidrun = new Image("http://icons.iconarchive.com/icons/designcontest/casino/96/Lemon-icon.png");
        Image ploom = new Image("http://icons.iconarchive.com/icons/designcontest/casino/96/Plum-icon.png");
        Image arbuus = new Image("http://icons.iconarchive.com/icons/designcontest/casino/96/Watermelon-icon.png");


        playNupp.setOnMouseClicked(event -> {
            if (tf1.getText().equals("3")) {
                juur.getChildren().clear();
                juur.getChildren().addAll(iv2, ruut, canvas);
                GraphicsContext gc = canvas.getGraphicsContext2D();

                final long startNanoTime = System.nanoTime();


            } else if (tf1.getText().equals("5")){
                juur.getChildren().clear();
                juur.getChildren().addAll(iv2, ruut);
            } else {
                tf1.setText("Ebasobiv sisend!");
            }
        });
        return juur;
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
        "Sisesta [3] või [5] vastava automaadiga mängimiseks. \n Tee oma valik: \n\n\n\n");
        text.setFont(megrim22);
        text.setTextAlignment(TextAlignment.CENTER);

        pane.setOpacity(0.95);

        pane.getChildren().addAll(ring, text);
        return pane;
    }

    private Pane getVäikeNupp(String tekst){
        Pane pane = new StackPane();
        Rectangle rect = new Rectangle(115, 50);
        rect.setFill(Color.rgb(45, 65, 70));
        rect.setStroke(Color.WHITE);

        Text text = new Text();
        text.setFill(Color.WHITE);
        text.setText(tekst);
        text.setFont(megrim35);

        pane.getChildren().addAll(rect, text);
        pane.setOpacity(0.8);

        pane.setOnMouseEntered(event -> rect.setFill(Color.rgb(53, 69, 73)));
        pane.setOnMouseExited(event -> rect.setFill(Color.rgb(45, 65, 70)));

        return pane;
    }

    private Pane taustruut() {
        Pane pane = new StackPane();
        Rectangle ruut = new Rectangle();
        ruut.setFill(Color.rgb(45, 65, 70));
        ruut.setHeight(500);
        ruut.setWidth(1000);

        pane.setOpacity(0.95);
        pane.getChildren().addAll(ruut);
        return pane;

    }

}

