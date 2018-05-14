package bingo;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class BingoMäng {
    private int kõrgus = 700;
    private int laius = 1200;

    private String bingoLogoPath = "file:bingoTaust3.jpg";
    private String bingoLogo = "file:bingoKaart.jpg";
    private Font megrim100 = Font.loadFont(new FileInputStream(new File("Megrim.ttf")),100);
    private Font megrim50 = Font.loadFont(new FileInputStream(new File("Megrim.ttf")),50);
    private Font megrim35 = Font.loadFont(new FileInputStream(new File("Megrim.ttf")),35);
    private Font megrim22 = Font.loadFont(new FileInputStream(new File("Megrim.ttf")),22);

    private Group juur;
    private Scene stseen;

    //hoiab bingopileti andmeid
    private BingoPilet bingo;

    public BingoMäng(Scene stseen) throws FileNotFoundException {
        this.stseen = stseen;
    }

    public Group getJuur() {
        juur = new Group();

        ImageView iv = looTaust(bingoLogoPath, this.stseen);
        juur.getChildren().add(iv);

        //ring koos tuvtustustekstiga
        Pane infoRing = getTextPane("\n\n" +
                "1 mänguväli maksab 10€.\n\n" +
                "Enne alustamist palun lugeda\nläbi mängureeglid.\n\n" +
                "Head mänguõnne!\n\n", "Bingo\n\n\n\n\n\n", megrim35);
        infoRing.setLayoutX(stseen.getWidth()/2-(laius/4));
        infoRing.setLayoutY(stseen.getHeight()/2-(laius/4));

        //reeglite nupp
        Pane reeglidNupp = getVäikeNupp("Reeglid");
        reeglidNupp.setLayoutX((stseen.getWidth()/2)-180);
        reeglidNupp.setLayoutY(stseen.getHeight()/2 + 150);

        //mängu alustamise nupp
        Pane mängimaNupp = getVäikeNupp("Mängima");
        mängimaNupp.setLayoutX(stseen.getWidth()/2+20);
        mängimaNupp.setLayoutY(stseen.getHeight()/2+150);

        //Reeglite ring
        Pane reeglidRing = getTextPane("\nBingo pilet on 25 ruuduga mänguväli, mis on\n" +
                "täidetud juhuslikult valitud numbritega vahemikus\n1–75. Mäng toimub kolmes osas:\n" +
                "1. esimeses osas tuleb täis saada nurgaruudud\n" +
                "2. teises osas tuleb täis saada mõlemad diagonaalid\n" +
                "3. kolmandas osas tuleb täis saada kõik ruudud.\n\n" +
                "Nurkademängus loositakse 40 numbrit\n(võidusumma 20€).\n" +
                "Diagonaalidemängus 17 numbrit (võidusumma 50€).\n" +
                "Täismängus 15 numbrit (võidusumma 150€).\n\n" +
                "Võimalik on valida ühe- ja kahe-\nmänguväljaga pileti vahel.","Reeglid:\n\n\n\n\n\n\n", megrim22);
        reeglidRing.setLayoutX(stseen.getWidth()/2-(laius/4));
        reeglidRing.setLayoutY(stseen.getHeight()/2-(laius/4));

        Pane tagasiNupp = getVäikeNupp("Tagasi");
        tagasiNupp.setLayoutX((stseen.getWidth()/2)-(160/2));
        tagasiNupp.setLayoutY(stseen.getHeight()/2 + 215);

        Pane ruut1 = getRuutTekstiga("1\nmängu-\nväli", bingoLogo);
        ruut1.setLayoutX(stseen.getWidth()/2-250);
        ruut1.setLayoutY(stseen.getHeight()/2-150);

        Pane ruut2 = getRuutTekstiga("2\nmängu-\nvälja", bingoLogo);
        ruut2.setLayoutX(stseen.getWidth()/2 + 50);
        ruut2.setLayoutY(stseen.getHeight()/2-150);

        juur.getChildren().addAll(infoRing, reeglidNupp, mängimaNupp);

        reeglidNupp.setOnMouseClicked(event -> {juur.getChildren().removeAll(infoRing, mängimaNupp, reeglidNupp);
            juur.getChildren().addAll(reeglidRing, tagasiNupp);
        });
        tagasiNupp.setOnMouseClicked(event -> {juur.getChildren().removeAll(reeglidRing, tagasiNupp, ruut1, ruut2);
            juur.getChildren().addAll(infoRing, reeglidNupp, mängimaNupp);
        });
        mängimaNupp.setOnMouseClicked(event -> {juur.getChildren().removeAll(infoRing, mängimaNupp, reeglidNupp);
            juur.getChildren().addAll(tagasiNupp, ruut1, ruut2);
        });

        return juur;
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

    private Pane getTextPane(String tekst, String pk, Font font){
        Pane pane = new StackPane();
        Circle ring = new Circle();
        ring.setFill(Color.rgb(45, 65, 70));
        ring.setRadius(laius/4);
        ring.setOpacity(0.8);

        Text text = new Text();
        text.setFill(Color.WHITE);
        text.setText(tekst);
        text.setFont(font);
        text.setTextAlignment(TextAlignment.CENTER);

        Text pealkiri = new Text();
        pealkiri.setFill(Color.WHITE);
        pealkiri.setText(pk);
        pealkiri.setFont(megrim50);
        pealkiri.setTextAlignment(TextAlignment.CENTER);

        pane.getChildren().addAll(ring, pealkiri, text);
        return pane;
    }

    private Pane getVäikeNupp(String tekst){
        Pane pane = new StackPane();
        Rectangle rect = new Rectangle(160, 50);
        rect.setFill(Color.rgb(45, 65, 70));
        rect.setStroke(Color.WHITE);

        Text text = new Text();
        text.setFill(Color.WHITE);
        text.setText(tekst);
        text.setFont(megrim35);

        pane.getChildren().addAll(rect, text);
        pane.setOpacity(0.9);

        pane.setOnMouseEntered(event -> rect.setFill(Color.rgb(53, 69, 73)));
        pane.setOnMouseExited(event -> rect.setFill(Color.rgb(45, 65, 70)));

        return pane;
    }

    private Pane getRuutTekstiga(String arv, String logoP) {
        Pane pane = new StackPane();
        Rectangle rect = new Rectangle(200, 200);
        rect.setFill(Color.rgb(45, 65, 70));
        rect.setStroke(Color.WHITE);

        Image logo = new Image(logoP);
        ImageView iv = new ImageView(logo);
        iv.setFitWidth(200);
        iv.setFitHeight(200);

        Text text = new Text();
        text.setFill(Color.WHITE);
        text.setText(arv);
        text.setFont(megrim50);
        text.setTextAlignment(TextAlignment.CENTER);

        pane.getChildren().addAll(rect, text);
        pane.setOpacity(0.8);

        pane.setOnMouseEntered(event -> rect.setFill(Color.rgb(53, 69, 73)));
        pane.setOnMouseExited(event -> rect.setFill(Color.rgb(45, 65, 70)));

        return pane;
    }
}
