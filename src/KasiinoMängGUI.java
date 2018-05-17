import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
import java.util.ArrayList;
import java.util.List;

public class KasiinoMängGUI extends Application {
    private String logoPath = "file:casinoLogo.jpg";
    private String logoPath2 = "file:casinoLogoIlma.jpg";
    private String bingoLogo = "file:bingo.jpg";
    private String slotMachineLogo = "file:slotMachine2.jpg";

    private Font megrim65 = Font.loadFont(new FileInputStream(new File("Megrim.ttf")),65);
    private Font megrim50 = Font.loadFont(new FileInputStream(new File("Megrim.ttf")), 50);
    private Font megrim35 = Font.loadFont(new FileInputStream(new File("Megrim.ttf")), 35);
    private Font megrim22 = Font.loadFont(new FileInputStream(new File("Megrim.ttf")), 22);

    private int kõrgus = 700;
    private int laius = 1200;

    private Mängija mängija;
    private List<Mängija> kõikMängijad = loeAndmed();

    public KasiinoMängGUI() throws IOException {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Group juur = new Group();
        Scene stseen = new Scene(juur, laius, kõrgus);

        ImageView iv = looTaust(logoPath, stseen);  //see on mängu taust logoga
        juur.getChildren().add(iv);

        ImageView iv2 = looTaust(logoPath2, stseen);  //see on mängu taust ilma casino logota

        stage.setScene(stseen);
        stage.setTitle("Casino");
        stage.show();

        //exit nupp salvestuseta
        Pane exitNupp = getSuurNupp("Exit");
        exitNupp.setLayoutX(laius - 200);
        exitNupp.setLayoutY(50);
        exitNupp.setOnMouseClicked(event -> Platform.exit());
        juur.getChildren().add(exitNupp);

        //exit nupp salvestusega
        Pane exitNupp2 = getSuurNupp("Exit");
        exitNupp2.setLayoutX(laius - 200);
        exitNupp2.setLayoutY(50);
        exitNupp2.setOnMouseClicked(event -> {Platform.exit();
            try {
                salvestaAndmed(mängija);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        //play nupp
        Pane playNupp = getSuurNupp("Play");
        playNupp.setLayoutX(laius - 200);
        playNupp.setLayoutY(150);
        juur.getChildren().add(playNupp);

        //küsi andmed ring
        Pane ring = getAndmedKüsida("Tere tulemast\nkasiinosse U of T!\n\nPalun sisesta enda andmed:\n\n\n\n");
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

        //vanad kasutajad nupp
        Pane vanadKasutajadNupp = getVäikeNupp("Vali");
        vanadKasutajadNupp.setLayoutX(stseen.getWidth() / 2 - 255);
        vanadKasutajadNupp.setLayoutY(stseen.getHeight() / 2 + 60);

        //back nupp
        Pane backNupp = getSuurNupp("Back");
        backNupp.setLayoutX(50);
        backNupp.setLayoutY(50);

        //menu nupp
        Pane menuNupp = getSuurNupp("Menu");
        menuNupp.setLayoutX(50);
        menuNupp.setLayoutY(50);

        //bingo alusta mängu
        Pane bingoNupp = getMänguLogo(bingoLogo, "Bingo");
        bingoNupp.setLayoutX(stseen.getWidth() / 2 - 300);
        bingoNupp.setLayoutY(stseen.getHeight() / 2 - 100);

        //slot machine alusta mängu
        Pane slotmachineNupp = getMänguLogo(slotMachineLogo, "Slot\nMachine");
        slotmachineNupp.setLayoutX((stseen.getWidth() / 2 + 50));
        slotmachineNupp.setLayoutY(stseen.getHeight() / 2 - 100);

        //mängu interaktiivsus hakkab siit
        backNupp.setOnMouseClicked(event -> {
            juur.getChildren().clear();
            juur.getChildren().addAll(iv2, ring, exitNupp, nimeTextField, vanuseTextField, nextNupp, vanadKasutajadNupp);
            try {
                salvestaAndmed(mängija);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        menuNupp.setOnMouseClicked(event -> {
            stseen.setRoot(juur);
            juur.getChildren().clear();
            Pane profiil = mängija.getProfiiliPane();
            profiil.setLayoutX(50);
            profiil.setLayoutY(stseen.getHeight()-150);
            juur.getChildren().addAll(iv2, exitNupp2, profiil, slotmachineNupp, bingoNupp, backNupp);
        });

        vanadKasutajadNupp.setOnMouseClicked(event -> {
            juur.getChildren().clear();
            Rectangle ruut = new Rectangle(600, 600);
            ruut.setFill(Color.rgb(45, 65, 70));
            ruut.setOpacity(0.7);
            ruut.setLayoutX(stseen.getWidth()/2 - 300);
            ruut.setLayoutY(stseen.getHeight()/2 - 300);
            juur.getChildren().addAll(iv2, exitNupp, backNupp, ruut);

            GridPane grid = new GridPane();
            grid.setLayoutX(stseen.getWidth()/2-255);
            grid.setLayoutY(stseen.getHeight()/2 - (95*2.5));
            grid.getColumnConstraints().add(new ColumnConstraints(255));
            grid.getRowConstraints().add(new RowConstraints(95));
            grid.setVgap(5);
            grid.setHgap(5);
            for (Mängija m: kõikMängijad){
                Pane pane = m.getProfiiliPane();
                pane.setOnMouseEntered(event1 -> pane.setOpacity(0.6));
                pane.setOnMouseExited(event1 -> pane.setOpacity(0.9));
                pane.setOnMouseClicked(event1 -> {
                    mängija = m;
                    kõikMängijad.remove(m);
                    Pane profiil = mängija.getProfiiliPane();
                    profiil.setLayoutX(50);
                    profiil.setLayoutY(stseen.getHeight()-150);
                    juur.getChildren().clear();
                    juur.getChildren().addAll(iv2, bingoNupp, slotmachineNupp, exitNupp2, backNupp, profiil);
                });
                if (kõikMängijad.indexOf(m)<5){
                    grid.add(pane, 0, kõikMängijad.indexOf(m));
                }
                else if (kõikMängijad.indexOf(m)<10 && kõikMängijad.indexOf(m) >= 5) {
                    grid.add(pane, 1, (-5 + kõikMängijad.indexOf(m)));
                }
            }
            juur.getChildren().add(grid);
        });

        playNupp.setOnMouseClicked(event -> {
            juur.getChildren().clear(); //kui play nuppu vajutada, tühjendab juure ja lisab uuesti vajalikud asjad
            juur.getChildren().addAll(iv2, ring, exitNupp, nimeTextField, vanuseTextField, nextNupp, vanadKasutajadNupp);
            nextNupp.setOnMouseClicked(event1 -> {
                //kontrollib vanust ja et nime lahter ei oleks tühi
                if (onPiisavVanus(tf2.getText()) && !tf1.getText().equals("Nimi") && !tf1.getText().equals("") &&
                        !tf1.getText().equals("Palun sisesta nimi!") && tf1.getText().length()<=12) {
                    try {
                        mängija = new Mängija(tf1.getText(), Integer.parseInt(tf2.getText()), 200);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Pane profiil = mängija.getProfiiliPane();
                    profiil.setLayoutX(50);
                    profiil.setLayoutY(stseen.getHeight()-150);
                    juur.getChildren().clear();
                    juur.getChildren().addAll(iv2, bingoNupp, slotmachineNupp, exitNupp2, backNupp, profiil);
                } else if (!onPiisavVanus(tf2.getText())) {
                    tf2.setText("Vanusepiirang on 21");  //kui vanus pole piisav siis lahter täitub vastava tekstiga
                } else if (tf1.getText().equals("Nimi") || tf1.getText().equals("") ||
                        tf1.getText().equals("Palun sisesta nimi!") || tf1.getText().length()>12) {
                    if (tf1.getText().equals("Nimi") || tf1.getText().equals("") ||
                            tf1.getText().equals("Palun sisesta nimi!")){
                        tf1.setText("Palun sisesta nimi!"); //kui tühi nimelahter siis lahter täitub vastava tekstiga
                    }
                    else if (tf1.getText().length()>12) { //lubatud kuni 12 tähemärki
                        tf1.setText("Max 12 tähemärki!");
                    }
                }
            });
        });

        bingoNupp.setOnMouseClicked(event -> {
            BingoGUI bingoGUI = null;
            try {
                bingoGUI = new BingoGUI(stseen, mängija);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Group bingoRoot = bingoGUI.getJuur();
            bingoRoot.getChildren().addAll(exitNupp2, menuNupp);
            stseen.setRoot(bingoRoot);
        });

        slotmachineNupp.setOnMouseClicked(event -> {
            SlotGUI slotMäng = null;
            try {
                slotMäng = new SlotGUI(stseen);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Group slotRoot = slotMäng.start();
            slotRoot.getChildren().addAll(exitNupp2, menuNupp);
            stseen.setRoot(slotRoot);
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

    private Pane getAndmedKüsida(String tekst) {
        Pane pane = new StackPane();
        Circle ring = new Circle();
        ring.setFill(Color.rgb(45, 65, 70));
        ring.setRadius(laius / 4);

        Text text = new Text();
        text.setFill(Color.WHITE);
        text.setText(tekst);
        text.setFont(megrim35);
        text.setTextAlignment(TextAlignment.CENTER);

        pane.setOpacity(0.95);

        pane.getChildren().addAll(ring, text);
        return pane;
    }

    private Pane getMänguLogo(String logoP, String nimi) {
        Pane pane = new StackPane();
        Rectangle rect = new Rectangle(250, 250);
        rect.setFill(Color.rgb(45, 65, 70));
        rect.setOpacity(0.7);
        rect.setStroke(Color.WHITE);

        Image logo = new Image(logoP);
        ImageView iv = new ImageView(logo);
        iv.setFitWidth(250);
        iv.setFitHeight(250);

        Text text = new Text();
        text.setFill(Color.WHITE);
        text.setText(nimi);
        text.setFont(megrim65);
        text.setTextAlignment(TextAlignment.CENTER);

        pane.getChildren().addAll(iv, rect, text);

        pane.setOnMouseEntered(event -> rect.setOpacity(0.3));
        pane.setOnMouseExited(event -> rect.setOpacity(0.7));

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

    private void salvestaAndmed(Mängija mängija) throws IOException {
        List<Mängija> mängijad = kõikMängijad;
        mängijad.add(mängija);
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("andmed.txt")))){
            for (Mängija m: mängijad) {
                bw.write(m.getNimi() + " " + m.getVanus() + " " + m.getRaha() + "\n");
            }
        }
    }

    private List<Mängija> loeAndmed() throws IOException {
        List<Mängija> mängijad = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("andmed.txt"), "UTF-8"))){
            String rida = reader.readLine();
            while (true) {
                try {
                    String[] jupid = rida.split(" ");
                    mängijad.add(new Mängija(jupid[0], Integer.parseInt(jupid[1]), Integer.parseInt(jupid[2])));
                    rida = reader.readLine();
                } catch (Exception e){
                    break;
                }
            }
        }
        return mängijad;
    }
}