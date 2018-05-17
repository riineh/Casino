import bingo.BingoPilet;
import bingo.LoosiNumber;
import javafx.animation.FadeTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BingoGUI {
    private int kõrgus = 700;
    private int laius = 1200;

    private String bingoLogoPath = "file:bingoTaust3.jpg";
    private String bingoLogo = "file:bingoKaart.jpg";

    private Font megrim100 = Font.loadFont(new FileInputStream(new File("Megrim.ttf")),100);
    private Font megrim50 = Font.loadFont(new FileInputStream(new File("Megrim.ttf")),50);
    private Font megrim35 = Font.loadFont(new FileInputStream(new File("Megrim.ttf")),35);
    private Font megrim22 = Font.loadFont(new FileInputStream(new File("Megrim.ttf")),22);

    private Font litSans45 = Font.loadFont(new FileInputStream(new File("LitSans-Medium.otf")),45);
    private Font litSans30 = Font.loadFont(new FileInputStream(new File("LitSans-Medium.otf")),30);

    private Color sinine = Color.rgb(41,79, 218);
    private Color punane = Color.rgb(255,38,5);
    private Color roheline = Color.rgb(0,101,2);
    private Color oranz = Color.rgb(255,150,5);
    private List<Color> värvid = Arrays.asList(sinine, punane, roheline, oranz);

    private Scene stseen;
    private Mängija mängija;
    private List<BingoPilet> piletid;

    public BingoGUI(Scene stseen, Mängija mängija) throws FileNotFoundException {
        this.stseen = stseen;
        this.mängija = mängija;
    }

    public Group getJuur() {
        Group juur = new Group();

        ImageView iv = looTaust(bingoLogoPath, this.stseen);
        juur.getChildren().add(iv);

        //ring koos tuvtustustekstiga
        Pane infoRing = getTextCircle("\n\n" +
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
        Pane reeglidRing = getTextCircle("\nBingo pilet on 25 ruuduga mänguväli, mis on\n" +
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

        Pane startNupp = getSuurNupp("Start");
        startNupp.setLayoutX(stseen.getWidth()/2 - 75);
        startNupp.setLayoutY(50);

        Pane mängijaInfo = mängija.getProfiiliPane();
        mängijaInfo.setLayoutX(50);
        mängijaInfo.setLayoutY(stseen.getHeight()-150);

        juur.getChildren().addAll(infoRing, reeglidNupp, mängimaNupp, mängijaInfo);

        reeglidNupp.setOnMouseClicked(event -> {juur.getChildren().removeAll(infoRing, mängimaNupp, reeglidNupp);
            juur.getChildren().addAll(reeglidRing, tagasiNupp);
        });
        tagasiNupp.setOnMouseClicked(event -> {juur.getChildren().removeAll(reeglidRing, tagasiNupp, ruut1, ruut2, mängijaInfo);
            juur.getChildren().addAll(infoRing, reeglidNupp, mängimaNupp, mängijaInfo);
        });
        mängimaNupp.setOnMouseClicked(event -> {juur.getChildren().removeAll(infoRing, mängimaNupp, reeglidNupp, mängijaInfo);
            juur.getChildren().addAll(tagasiNupp, ruut1, ruut2);
        });

        final Pane[] bingoPilet1 = new Pane[1];
        final Pane[] bingoPilet2 = new Pane[1];
        final Pane[] bingoPilet0 = new Pane[1];

        ruut1.setOnMouseClicked(event -> {juur.getChildren().removeAll(ruut1, ruut2, tagasiNupp);
            mängija.setRaha(mängija.getRaha()-10);
            BingoPilet p1 = null;
            try {
                p1 = new BingoPilet();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            bingoPilet0[0] = p1.drawBingoPilet();
            piletid = new ArrayList<>();
            piletid.add(p1);
            bingoPilet0[0].setLayoutX(100);
            bingoPilet0[0].setLayoutY(stseen.getHeight()/2-150);
            juur.getChildren().addAll(bingoPilet0[0], startNupp);
        });

        ruut2.setOnMouseClicked(event -> {juur.getChildren().removeAll(ruut1, ruut2, tagasiNupp);
            mängija.setRaha(mängija.getRaha()-20);
            BingoPilet p1 = null;
            BingoPilet p2 = null;
            try {
                p1 = new BingoPilet();
                p2 = new BingoPilet();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            bingoPilet1[0] = p1.drawBingoPilet();
            bingoPilet2[0] = p2.drawBingoPilet();
            piletid = new ArrayList<>();
            piletid.add(p1);
            piletid.add(p2);
            bingoPilet1[0].setLayoutX(100);
            bingoPilet1[0].setLayoutY(stseen.getHeight()/2-150);
            bingoPilet2[0].setLayoutX(450);
            bingoPilet2[0].setLayoutY(stseen.getHeight()/2-150);
            juur.getChildren().addAll(bingoPilet1[0], bingoPilet2[0], startNupp);
        });

        startNupp.setOnMouseClicked(event -> {
            juur.getChildren().remove(startNupp);
            List<Integer> loositudArvud = new ArrayList<>();
            LoosiNumber loos = new LoosiNumber(1, 75);
            List<Pane> panes = loosiNumbrid(40, loos, loositudArvud, juur);

            final int[] summa = {0};
            final int[] võit = {0};
            piletid.forEach(bingoPilet -> {
                if (bingoPilet.nurkademäng()){
                    võit[0] += 40;
                    summa[0] += 40;
                }
            });
            Pane nurkademäng = getTextPane("Nurkade-\nmängu võit:\n\n", "\n" + võit[0] + "€");
            nurkademäng.setLayoutX(stseen.getWidth() - 400);
            nurkademäng.setLayoutY(stseen.getHeight()/2-150);
            Pane jätka = getVäikeNupp("Jätka");
            jätka.setLayoutX(stseen.getWidth() - 325);
            jätka.setLayoutY(stseen.getHeight()/2 + 80);
            juur.getChildren().addAll(nurkademäng, jätka);

            jätka.setOnMouseClicked(event1 -> {
                for (Pane p: panes) {
                    juur.getChildren().remove(p);
                }
                juur.getChildren().removeAll(nurkademäng, jätka);
                võit[0] = 0;
                List<Pane> panes2 = loosiNumbrid(17, loos, loositudArvud, juur);
                summa[0] = 0;
                piletid.forEach(bingoPilet ->{
                    if (bingoPilet.diagonaalidemäng()){
                        võit[0] += 100;
                        summa[0] += 100;
                    }
                });
                Pane diagonaalidemäng = getTextPane("Diagonaalide-\nmängu võit:\n\n", "\n" + võit[0] + "€");
                diagonaalidemäng.setLayoutX(stseen.getWidth() - 400);
                diagonaalidemäng.setLayoutY(stseen.getHeight()/2-150);
                Pane jätka2 = getVäikeNupp("Jätka");
                jätka2.setLayoutX(stseen.getWidth() - 325);
                jätka2.setLayoutY(stseen.getHeight()/2 + 80);
                juur.getChildren().addAll(diagonaalidemäng, jätka2);

                jätka2.setOnMouseClicked(event2 -> {
                    võit[0] = 0;
                    List<Pane> panes3 = loosiNumbrid(15, loos, loositudArvud, juur);
                    for (Pane p: panes2) {
                        juur.getChildren().remove(p);
                    }
                    juur.getChildren().removeAll(diagonaalidemäng, jätka2);
                    piletid.forEach(bingoPilet -> {
                        if (bingoPilet.täismäng()){
                            võit[0] += 150;
                            summa[0] += 150;
                        }
                    });
                    Pane täismäng = getTextPane("Täisnmängu\n võit:\n\n", "\n" + võit[0] + "€");
                    täismäng.setLayoutX(stseen.getWidth() - 400);
                    täismäng.setLayoutY(stseen.getHeight()/2-150);
                    Pane jätka3 = getVäikeNupp("Jätka");
                    jätka3.setLayoutX(stseen.getWidth() - 325);
                    jätka3.setLayoutY(stseen.getHeight()/2 + 80);
                    juur.getChildren().addAll(täismäng, jätka3);

                    jätka3.setOnMouseClicked(event3 -> {
                        mängija.setRaha(mängija.getRaha() + summa[0]);
                        for (Pane p: panes3) {
                            juur.getChildren().remove(p);
                        }
                        juur.getChildren().removeAll(täismäng, jätka3, bingoPilet0[0], bingoPilet1[0], bingoPilet2[0]);
                        Pane lõpp = getTextPane("Õnnitlused!\nVõitsite kokku:\n", "\n\n" + summa[0] + "€");
                        lõpp.setLayoutX(stseen.getWidth()/2-150);
                        lõpp.setLayoutY(stseen.getHeight()/2-150);
                        juur.getChildren().add(lõpp);
                    });
                });
            });
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

    private Pane getTextCircle(String tekst, String pk, Font font){
        Pane pane = new StackPane();
        Circle ring = new Circle();
        ring.setFill(Color.rgb(45, 65, 70));
        ring.setRadius(laius/4);
        ring.setOpacity(0.9);

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

    private Pane getTextPane(String tekst, String tekst2) {
        Pane pane = new StackPane();

        Rectangle rect = new Rectangle(300, 300);
        Random suvaline = new Random();
        int suvalineVärv = suvaline.nextInt(4);
        rect.setFill(värvid.get(suvalineVärv));
        rect.setOpacity(0.8);

        Text text = new Text(tekst);
        text.setFont(megrim35);
        text.setFill(Color.WHITE);
        text.setTextAlignment(TextAlignment.CENTER);

        Text text2 = new Text(tekst2);
        text2.setFont(litSans45);
        text2.setFill(Color.WHITE);
        text2.setTextAlignment(TextAlignment.CENTER);

        pane.getChildren().addAll(rect, text, text2);

        FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000));
        fadeTransition.setNode(pane);
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(0.8);
        fadeTransition.play();

        return pane;
    }

    private Pane getSuurNupp(String tekst) {
        Pane pane = new StackPane();
        Rectangle rect = new Rectangle(150, 70);
        rect.setFill(Color.RED);
        rect.setStroke(Color.WHITE);

        Text text = new Text();
        text.setFill(Color.WHITE);
        text.setText(tekst);
        text.setFont(megrim50);

        pane.getChildren().addAll(rect, text);
        pane.setOpacity(0.8);

        pane.setOnMouseEntered(event -> rect.setFill(Color.rgb(255, 72, 72)));
        pane.setOnMouseExited(event -> rect.setFill(Color.RED));

        return pane;
    }

    private Pane getVäikeNupp(String tekst){
        Pane pane = new StackPane();
        Rectangle rect = new Rectangle(160, 50);
        rect.setFill(Color.RED);
        rect.setStroke(Color.WHITE);

        Text text = new Text();
        text.setFill(Color.WHITE);
        text.setText(tekst);
        text.setFont(megrim35);

        pane.getChildren().addAll(rect, text);
        pane.setOpacity(0.9);

        pane.setOnMouseEntered(event -> rect.setFill(Color.rgb(255, 72, 72)));
        pane.setOnMouseExited(event -> rect.setFill(Color.RED));

        return pane;
    }

    private Pane getRuutTekstiga(String arv, String logoP) {
        Pane pane = new StackPane();
        Rectangle rect = new Rectangle(200, 200);

        List<Color> värvid = Arrays.asList(sinine, punane, roheline, oranz);

        Random suvaline = new Random();
        int suvalineVärv = suvaline.nextInt(4);

        rect.setFill(värvid.get(suvalineVärv));
        rect.setStroke(Color.WHITE);
        rect.setOpacity(0.6);

        Image logo = new Image(logoP);
        ImageView iv = new ImageView(logo);
        iv.setFitWidth(200);
        iv.setFitHeight(200);

        Text text = new Text();
        text.setFill(Color.WHITE);
        text.setText(arv);
        text.setFont(litSans45);
        text.setTextAlignment(TextAlignment.CENTER);

        pane.getChildren().addAll(iv, rect, text);

        pane.setOnMouseEntered(event -> rect.setOpacity(0.5));
        pane.setOnMouseExited(event -> rect.setOpacity(0.6));

        return pane;
    }

    private List<Pane> loosiNumbrid(int arv, LoosiNumber loos, List<Integer> loositudArvud, Group juur){
        int loositud;
        List<Pane> panes = new ArrayList<>();
        for (int i=0; i<arv; i++) {
            while (true) {
                int loositudArv = loos.suvalineNumber();
                if (!loositudArvud.contains(loositudArv)) {
                    loositudArvud.add(loositudArv);
                    loositud = loositudArv;
                    break;
                }
            }
            int finalLoositud = loositud;

            Pane pane = new StackPane();
            Circle circle = new Circle(25);
            circle.setFill(Color.WHITE);
            Text tekst = new Text("" + finalLoositud);
            tekst.setFill(Color.BLACK);
            tekst.setFont(litSans30);
            if (i < 20) {
                pane.setLayoutX(50 + i*55);
                pane.setLayoutY(stseen.getHeight()-90);
            }
            else {
                pane.setLayoutX(50 + (-1100 + i*55));
                pane.setLayoutY(stseen.getHeight()-150);
            }
            pane.getChildren().addAll(circle, tekst);
            pane.setOpacity(0.8);
            panes.add(pane);
            juur.getChildren().add(pane);
            FadeTransition fadeTransition = new FadeTransition(Duration.millis(1000));
            fadeTransition.setNode(pane);
            fadeTransition.setFromValue(0.0);
            fadeTransition.setToValue(0.8);
            fadeTransition.play();
            piletid.forEach(pilet -> pilet.kontrolli(finalLoositud));
        }
        return panes;
    }
}