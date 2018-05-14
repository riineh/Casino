import bingo.BingoGUI;
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
import slot_machine.SlotGUI;

import java.io.*;

public class KasiinoMängGUI extends Application {
    private String logoPath = "file:casinoLogo.jpg";
    private String logoPath2 = "file:casinoLogoIlma.jpg";
    private String bingoLogo = "file:bingo.jpg";
    private String blacjackLogo = "file:blackjack.jpg";
    private String slotMachineLogo = "file:slotMachine2.jpg";

    private Font megrim50 = Font.loadFont(new FileInputStream(new File("Megrim.ttf")), 50);
    private Font megrim35 = Font.loadFont(new FileInputStream(new File("Megrim.ttf")), 35);
    private Font megrim22 = Font.loadFont(new FileInputStream(new File("Megrim.ttf")), 22);

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

        ImageView iv2 = looTaust(logoPath2, stseen);  //siis tekitab uue tausta ilma casino logota

        stage.setScene(stseen);
        stage.setTitle("Casino");
        stage.show();

        //exit nupp
        Pane exitNupp = getSuurNupp("Exit");
        exitNupp.setLayoutX(laius - 200);
        exitNupp.setLayoutY(50);
        exitNupp.setOnMouseClicked(event -> Platform.exit());
        juur.getChildren().add(exitNupp);

        //play nupp
        Pane playNupp = getSuurNupp("Play");
        playNupp.setLayoutX(laius - 200);
        playNupp.setLayoutY(150);
        juur.getChildren().add(playNupp);

        //küsi andmed ring
        Pane ring = getAndmedKüsida();
        ring.setLayoutX(stseen.getWidth() / 2 - laius / 4);
        ring.setLayoutY(stseen.getHeight() / 2 - laius / 4);

        //nime lahter (allpool kontrollib et lahter ei jääks tühjaks)
        Pane nimeTextField = new StackPane();
        TextField tf1 = getTextField("Nimi");
        nimeTextField.getChildren().add(tf1);
        nimeTextField.setLayoutX(stseen.getWidth() / 2 - (225 / 2));
        nimeTextField.setLayoutY(stseen.getHeight() / 2 + 35);

        //vanuse lahter (vanuse kontroll tuleb allpool)
        Pane vanuseTextField = new StackPane();
        TextField tf2 = getTextField("Vanus");
        vanuseTextField.getChildren().add(tf2);
        vanuseTextField.setLayoutX(stseen.getWidth() / 2 - (225 / 2));
        vanuseTextField.setLayoutY(stseen.getHeight() / 2 + 100);

        stseen.setOnMouseClicked(event -> {
            if (tf1.getText().equals("")) {
                tf1.setText("Nimi");
            }
            if (tf2.getText().equals("")) {
                tf2.setText("Vanus");
            }
        });

        //next nupp
        Pane nextNupp = getVäikeNupp("Next");
        nextNupp.setLayoutX((stseen.getWidth() / 2) - (115 / 2));
        nextNupp.setLayoutY(stseen.getHeight() / 2 + 175);

        //back nupp
        Pane backNupp = getSuurNupp("Back");
        backNupp.setLayoutX(50);
        backNupp.setLayoutY(50);

        //menu nupp
        Pane menuNupp = getSuurNupp("Menu");
        menuNupp.setLayoutX(50);
        menuNupp.setLayoutY(50);
        menuNupp.setOnMouseClicked(event1 -> {
            stseen.setRoot(juur);
            juur.getChildren().add(exitNupp);
        });

        //bingo alusta mängu
        Pane bingoNupp = getMänguLogo(bingoLogo, "Bingo");
        bingoNupp.setLayoutX(stseen.getWidth() / 6);
        bingoNupp.setLayoutY(stseen.getHeight() / 2 - 100);
        bingoNupp.setOnMouseClicked(event -> {
            BingoGUI bingoGUI = null;
            try {
                bingoGUI = new BingoGUI(stseen);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Group bingoRoot = bingoGUI.getJuur();
            bingoRoot.getChildren().addAll(exitNupp, menuNupp);
            stseen.setRoot(bingoRoot);
        });

        //blackjack alusta mängu
        Pane blackjackNupp = getMänguLogo(blacjackLogo, "Black-\njack");
        blackjackNupp.setLayoutX((stseen.getWidth() / 6) + 300);
        blackjackNupp.setLayoutY(stseen.getHeight() / 2 - 100);

        //slot mmachine alusta mängu
        Pane slotmachineNupp = getMänguLogo(slotMachineLogo, "Slot\nMachine");
        slotmachineNupp.setLayoutX((stseen.getWidth() / 6) + 600);
        slotmachineNupp.setLayoutY(stseen.getHeight() / 2 - 100);
        slotmachineNupp.setOnMouseClicked(event -> {
            SlotGUI slotMäng = null;
            try {
                slotMäng = new SlotGUI(stseen);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Group slotRoot = slotMäng.start();
            slotRoot.getChildren().addAll(exitNupp, menuNupp);
            stseen.setRoot(slotRoot);
        });

        //mängu interaktiivsus hakkab siit
        playNupp.setOnMouseClicked(event -> {
            juur.getChildren().clear(); //kui play nuppu vajutada, tühjendab juure ja lisab uuesti vajalikud asjad
            juur.getChildren().addAll(iv2, ring, exitNupp, nimeTextField, vanuseTextField, nextNupp);
            nextNupp.setOnMouseClicked(event1 -> {
                //kontrollib vanust ja et nime lahter ei oleks tühi
                if (onPiisavVanus(tf2.getText()) && !tf1.getText().equals("Nimi") && !tf1.getText().equals("") &&
                        !tf1.getText().equals("Palun sisesta nimi!")) {
                    juur.getChildren().clear();
                    juur.getChildren().addAll(iv2, bingoNupp, blackjackNupp, slotmachineNupp, exitNupp, backNupp);
                    backNupp.setOnMouseClicked(event2 -> {
                        juur.getChildren().clear();
                        juur.getChildren().addAll(iv2, ring, exitNupp, nimeTextField, vanuseTextField, nextNupp);
                    });
                } else if (!onPiisavVanus(tf2.getText())) {
                    tf2.setText("Vanusepiirang on 21");  //kui vanus pole piisav siis lahter täitub vastava tekstiga
                } else if (tf1.getText().equals("Nimi") || tf1.getText().equals("") || tf1.getText().equals("Palun sisesta nimi!")) {
                    tf1.setText("Palun sisesta nimi!"); //kui tühi nimelahter siis lahter täitub vastava tekstiga
                }
            });
        });
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

    private Pane getVäikeNupp(String tekst) {
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

    private Pane getSuurNupp(String tekst) {
        Pane pane = new StackPane();
        Rectangle rect = new Rectangle(150, 70);
        rect.setFill(Color.rgb(45, 65, 70));
        rect.setStroke(Color.WHITE);

        Text text = new Text();
        text.setFill(Color.WHITE);
        text.setText(tekst);
        text.setFont(megrim50);

        pane.getChildren().addAll(rect, text);
        pane.setOpacity(0.8);

        pane.setOnMouseEntered(event -> rect.setFill(Color.rgb(53, 69, 73)));
        pane.setOnMouseExited(event -> rect.setFill(Color.rgb(45, 65, 70)));

        return pane;
    }

    private TextField getTextField(String sisu) {
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
        ring.setRadius(laius / 4);

        Text text = new Text();
        text.setFill(Color.WHITE);
        text.setText("Tere tulemast\nkasiinosse U of T!\n\nPalun sisesta enda andmed:\n\n\n\n");
        text.setFont(megrim35);
        text.setTextAlignment(TextAlignment.CENTER);

        pane.setOpacity(0.95);

        pane.getChildren().addAll(ring, text);
        return pane;
    }

    private Pane getMänguLogo(String logoP, String nimi) {
        Pane pane = new StackPane();
        Rectangle rect = new Rectangle(200, 200);
        rect.setFill(Color.rgb(45, 65, 70));
        rect.setOpacity(0.7);
        rect.setStroke(Color.WHITE);

        Image logo = new Image(logoP);
        ImageView iv = new ImageView(logo);
        iv.setFitWidth(200);
        iv.setFitHeight(200);

        Text text = new Text();
        text.setFill(Color.WHITE);
        text.setText(nimi);
        text.setFont(megrim50);
        text.setTextAlignment(TextAlignment.CENTER);

        pane.getChildren().addAll(iv, rect, text);

        pane.setOnMouseEntered(event -> rect.setFill(Color.rgb(53, 69, 73)));
        pane.setOnMouseExited(event -> rect.setFill(Color.rgb(45, 65, 70)));

        return pane;
    }

    private boolean onPiisavVanus(String sõne) {
        int vanus;
        boolean onPiisavVanus = true;
        try {
            vanus = Integer.parseInt(sõne);
            if (vanus < 21) {
                onPiisavVanus = false;
            }
        } catch (Exception e) {
            onPiisavVanus = false;
        }
        return onPiisavVanus;
    }
}