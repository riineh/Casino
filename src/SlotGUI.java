import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
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
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;


public class SlotGUI  {

    public int panus;
    public int võit = 0;

    private int kõrgus = 700;
    private int laius = 1200;

    private String logoPath = "file:casinoLogo.jpg";
    private String logoPath2 = "file:casinoLogoIlma.jpg";
    private String logoPath3 = "file:slotmachinetaust.jpg";
    private String slotid = "file:Sevenicon.png";
    private String slotid2 = "file:Cherryicon.png";
    private String slotid3 = "file:Bananaicon.png";
    private String slotid4 = "file:Plumicon.png";
    private String slotid5 = "file:Baricon.png";
    private String slotid6 = "file:Lemonicon.png";
    private Font megrim35 = Font.loadFont(new FileInputStream(new File("Megrim.ttf")),35);
    private Font megrim50 = Font.loadFont(new FileInputStream(new File("Megrim.ttf")),50);
    private Font megrim22 = Font.loadFont(new FileInputStream(new File("Megrim.ttf")),22);

    private Scene stseen;
    private Pane exitNupp;
    private Pane menuNupp;
    private Mängija mängija;

    public SlotGUI(Scene stseen, Pane exit, Pane menu, Mängija mängija) throws FileNotFoundException {
        this.stseen = stseen;
        this.exitNupp = exit;
        this.menuNupp = menu;
        this.mängija = mängija;
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

    private ImageView slot(String logoP, Scene stseen) {
        Image logo = new Image(logoP);
        ImageView iv = new ImageView(logo);
        //määran pildi suuruse soovitud laiuseks ja kõrguseks
        iv.setFitWidth(100);
        iv.setFitHeight(100);

        iv.setX(100);
        iv.setY(100);


        return iv;
    }

    public Group start() {
        Group juur = new Group();
        ImageView iv2 = looTaust(logoPath2, stseen);
        ImageView iv3 = looTaust(logoPath3, stseen);

        //esimese sloti ikoonid
        ImageView slot = slot(slotid, stseen);
        slot.setId("seitse");
        ImageView slot2 = slot(slotid2, stseen);
        slot2.setId("kirsid");
        ImageView slot3 = slot(slotid3, stseen);
        slot3.setId("banaan");
        ImageView slot4 = slot(slotid4, stseen);
        slot4.setId("ploom");
        ImageView slot5 = slot(slotid5, stseen);
        slot5.setId("bar");
        ImageView slot6 = slot(slotid6, stseen);
        slot6.setId("sidrun");
        //esimese sloti ikoonide suurused
        slot.setFitWidth(125);
        slot.setFitHeight(125);
        slot2.setFitHeight(125);
        slot2.setFitWidth(125);
        slot3.setFitWidth(125);
        slot3.setFitHeight(125);
        slot4.setFitWidth(125);
        slot4.setFitHeight(125);
        slot5.setFitWidth(125);
        slot5.setFitHeight(125);
        slot6.setFitHeight(125);
        slot6.setFitWidth(125);
        //esimese sloti ikoonide asukohad
        slot2.setLayoutX(235);
        slot2.setLayoutY(190);
        slot.setLayoutY(190);
        slot.setLayoutX(235);
        slot3.setLayoutY(190);
        slot3.setLayoutX(235);
        slot4.setLayoutY(190);
        slot4.setLayoutX(235);
        slot5.setLayoutY(190);
        slot5.setLayoutX(235);
        slot6.setLayoutY(190);
        slot6.setLayoutX(235);
        //esimese sloti ikoonide list
        List<ImageView> list = new ArrayList<>();
        list.add(slot);
        list.add(slot2);
        list.add(slot3);
        list.add(slot4);
        list.add(slot5);
        list.add(slot6);


        //teise sloti ikoonid
        ImageView slot7 = slot(slotid, stseen);
        slot7.setId("seitse");
        ImageView slot8 = slot(slotid2, stseen);
        slot8.setId("kirsid");
        ImageView slot9 = slot(slotid3, stseen);
        slot9.setId("banaan");
        ImageView slot10 = slot(slotid4, stseen);
        slot10.setId("ploom");
        ImageView slot11 = slot(slotid5, stseen);
        slot11.setId("bar");
        ImageView slot12 = slot(slotid6, stseen);
        slot12.setId("sidrun");
        //teise sloti ikoonide suurused
        slot7.setFitWidth(125);
        slot7.setFitHeight(125);
        slot8.setFitHeight(125);
        slot8.setFitWidth(125);
        slot9.setFitWidth(125);
        slot9.setFitHeight(125);
        slot10.setFitWidth(125);
        slot10.setFitHeight(125);
        slot11.setFitWidth(125);
        slot11.setFitHeight(125);
        slot12.setFitHeight(125);
        slot12.setFitWidth(125);
        //teise sloti ikoonide asukohad
        slot7.setLayoutX(435);
        slot7.setLayoutY(190);
        slot8.setLayoutY(190);
        slot8.setLayoutX(435);
        slot9.setLayoutY(190);
        slot9.setLayoutX(435);
        slot10.setLayoutY(190);
        slot10.setLayoutX(435);
        slot11.setLayoutY(190);
        slot11.setLayoutX(435);
        slot12.setLayoutY(190);
        slot12.setLayoutX(435);
        //teise sloti ikoonide list
        List<ImageView> list2 = new ArrayList<>();
        list2.add(slot7);
        list2.add(slot8);
        list2.add(slot9);
        list2.add(slot10);
        list2.add(slot11);
        list2.add(slot12);

        //kolmanda sloti ikoonid
        ImageView slot13 = slot(slotid, stseen);
        slot13.setId("seitse");
        ImageView slot14 = slot(slotid2, stseen);
        slot14.setId("kirsid");
        ImageView slot15 = slot(slotid3, stseen);
        slot15.setId("banaan");
        ImageView slot16 = slot(slotid4, stseen);
        slot16.setId("ploom");
        ImageView slot17 = slot(slotid5, stseen);
        slot17.setId("bar");
        ImageView slot18 = slot(slotid6, stseen);
        slot18.setId("sidrun");
        //kolmanda sloti ikoonide suurused
        slot13.setFitWidth(125);
        slot13.setFitHeight(125);
        slot14.setFitHeight(125);
        slot14.setFitWidth(125);
        slot15.setFitWidth(125);
        slot15.setFitHeight(125);
        slot16.setFitWidth(125);
        slot16.setFitHeight(125);
        slot17.setFitWidth(125);
        slot17.setFitHeight(125);
        slot18.setFitHeight(125);
        slot18.setFitWidth(125);
        //kolmanda sloti ikoonide asukohad
        slot13.setLayoutX(635);
        slot13.setLayoutY(190);
        slot14.setLayoutY(190);
        slot14.setLayoutX(635);
        slot15.setLayoutY(190);
        slot15.setLayoutX(635);
        slot16.setLayoutY(190);
        slot16.setLayoutX(635);
        slot17.setLayoutY(190);
        slot17.setLayoutX(635);
        slot18.setLayoutY(190);
        slot18.setLayoutX(635);
        //kolmanda sloti ikoonide list
        List<ImageView> list3 = new ArrayList<>();
        list3.add(slot13);
        list3.add(slot14);
        list3.add(slot15);
        list3.add(slot16);
        list3.add(slot17);
        list3.add(slot18);

        //neljanda sloti ikoonid
        ImageView slot19 = slot(slotid, stseen);
        slot19.setId("seitse");
        ImageView slot20= slot(slotid2, stseen);
        slot20.setId("kirsid");
        ImageView slot21 = slot(slotid3, stseen);
        slot21.setId("banaan");
        ImageView slot22 = slot(slotid4, stseen);
        slot22.setId("ploom");
        ImageView slot23 = slot(slotid5, stseen);
        slot23.setId("bar");
        ImageView slot24 = slot(slotid6, stseen);
        slot24.setId("sidrun");
        //neljanda sloti ikoonide suurused
        slot19.setFitWidth(125);
        slot19.setFitHeight(125);
        slot20.setFitHeight(125);
        slot20.setFitWidth(125);
        slot21.setFitWidth(125);
        slot21.setFitHeight(125);
        slot22.setFitWidth(125);
        slot22.setFitHeight(125);
        slot23.setFitWidth(125);
        slot23.setFitHeight(125);
        slot24.setFitHeight(125);
        slot24.setFitWidth(125);
        //neljanda sloti ikoonide asukohad
        slot19.setLayoutX(40);
        slot19.setLayoutY(190);
        slot20.setLayoutY(190);
        slot20.setLayoutX(40);
        slot21.setLayoutY(190);
        slot21.setLayoutX(40);
        slot22.setLayoutY(190);
        slot22.setLayoutX(40);
        slot23.setLayoutY(190);
        slot23.setLayoutX(40);
        slot24.setLayoutY(190);
        slot24.setLayoutX(40);
        //neljanda sloti ikoonide list
        List<ImageView> list4 = new ArrayList<>();
        list4.add(slot19);
        list4.add(slot20);
        list4.add(slot21);
        list4.add(slot22);
        list4.add(slot23);
        list4.add(slot24);

        //viienda sloti ikoonid
        ImageView slot25 = slot(slotid, stseen);
        slot25.setId("seitse");
        ImageView slot26= slot(slotid2, stseen);
        slot26.setId("kirsid");
        ImageView slot27 = slot(slotid3, stseen);
        slot27.setId("banaan");
        ImageView slot28 = slot(slotid4, stseen);
        slot28.setId("ploom");
        ImageView slot29 = slot(slotid5, stseen);
        slot29.setId("bar");
        ImageView slot30 = slot(slotid6, stseen);
        slot30.setId("sidrun");
        //viienda sloti ikoonide suurused
        slot25.setFitWidth(125);
        slot25.setFitHeight(125);
        slot26.setFitHeight(125);
        slot26.setFitWidth(125);
        slot27.setFitWidth(125);
        slot27.setFitHeight(125);
        slot28.setFitWidth(125);
        slot28.setFitHeight(125);
        slot29.setFitWidth(125);
        slot29.setFitHeight(125);
        slot30.setFitHeight(125);
        slot30.setFitWidth(125);
        //viienda sloti ikoonide asukohad
        slot25.setLayoutX(835);
        slot25.setLayoutY(190);
        slot26.setLayoutY(190);
        slot26.setLayoutX(835);
        slot27.setLayoutY(190);
        slot27.setLayoutX(835);
        slot28.setLayoutY(190);
        slot28.setLayoutX(835);
        slot29.setLayoutY(190);
        slot29.setLayoutX(835);
        slot30.setLayoutY(190);
        slot30.setLayoutX(835);
        //viienda sloti ikoonide list
        List<ImageView> list5 = new ArrayList<>();
        list5.add(slot25);
        list5.add(slot26);
        list5.add(slot27);
        list5.add(slot28);
        list5.add(slot29);
        list5.add(slot30);

        juur.getChildren().add(iv3);

        Pane ring = getAndmedKüsida();
        ring.setLayoutX(stseen.getWidth()/2-laius/4);
        ring.setLayoutY(stseen.getHeight()/2-laius/4);
        Pane ruut = taustruut();
        ruut.setLayoutX(100);
        ruut.setLayoutY(100);

        Pane slotruut1 = slotruut();
        slotruut1.setLayoutX(300);
        slotruut1.setLayoutY(200);

        Pane slotruut2 = slotruut();
        slotruut2.setLayoutX(500);
        slotruut2.setLayoutY(200);

        Pane slotruut3 = slotruut();
        slotruut3.setLayoutX(700);
        slotruut3.setLayoutY(200);

        Pane slotruut4 = slotruut();
        slotruut4.setLayoutX(105);
        slotruut4.setLayoutY(200);

        Pane slotruut5 = slotruut();
        slotruut5.setLayoutX(305);
        slotruut5.setLayoutY(200);

        Pane slotruut6 = slotruut();
        slotruut6.setLayoutX(505);
        slotruut6.setLayoutY(200);

        Pane slotruut7 = slotruut();
        slotruut7.setLayoutX(705);
        slotruut7.setLayoutY(200);

        Pane slotruut8 = slotruut();
        slotruut8.setLayoutX(905);
        slotruut8.setLayoutY(200);

        Pane startnupp = start("START");
        startnupp.setLayoutY(520);
        startnupp.setLayoutX(525);



        Pane ruut1 = getRuutTekstiga("Kolmene\nautomaat");
        ruut1.setLayoutX(stseen.getWidth()/2-250);
        ruut1.setLayoutY(stseen.getHeight()/2-150);

        Pane ruut2 = getRuutTekstiga("Viiene\nautomaat");
        ruut2.setLayoutX(stseen.getWidth()/2 + 50);
        ruut2.setLayoutY(stseen.getHeight()/2-150);

        Pane nimeTextField = new StackPane();
        TextField tf1 = getTextField("Sisesta panus: ");
        nimeTextField.getChildren().add(tf1);
        nimeTextField.setLayoutX(485);
        nimeTextField.setLayoutY(415);


        Pane playNupp = getVäikeNupp("Play");
        playNupp.setLayoutX((stseen.getWidth()/2)-(115/2));
        playNupp.setLayoutY(stseen.getHeight()/2 + 125);

        juur.getChildren().addAll(ring, playNupp, nimeTextField);

        playNupp.setOnMouseClicked(event -> {
            if (onSobivPanus(tf1.getText())) {
                juur.getChildren().clear();
                juur.getChildren().addAll(iv3, ruut1, ruut2, menuNupp, exitNupp);
            } else {
                tf1.setText("Sisesta õige panus:");
            }

        });

        ruut1.setOnMouseClicked(event -> {juur.getChildren().clear();
            juur.getChildren().addAll(iv3, ruut,slotruut1, slotruut2, slotruut3, startnupp, menuNupp, exitNupp);

            startnupp.setOnMouseClicked(event2 -> {
                try {
                    this.panus = Integer.parseInt(tf1.getText());
                } catch (Exception e){}
                juur.getChildren().clear();
                juur.getChildren().addAll(iv3, ruut,slotruut1, slotruut2, slotruut3, startnupp, menuNupp, exitNupp);
                juur.getChildren().addAll(slot, slot2, slot3, slot4, slot5, slot6,
                        slot7, slot8, slot9, slot10, slot11, slot12, slot13, slot14, slot15, slot16, slot17, slot18);
                Collections.shuffle(list);
                Collections.shuffle(list2);
                Collections.shuffle(list3);


                Set<String> set = new HashSet<>();
                set.add(list.get(5).getId());
                set.add(list2.get(5).getId());
                set.add((list3.get(5).getId()));

                if (set.size() == 1) { //kõik pildid on samad
                    võit = võit + panus*10;
                } else if (set.size() == 2) { //kaks pilti on samad
                    võit = võit + panus*3;
                    System.out.println(panus);
                    System.out.println(võit);
                } else { //ükski ei klapi
                    võit = võit + 0;
                }

                Pane võit1 = võidusumma("Võidusumma");
                võit1.setLayoutX(475);
                võit1.setLayoutY(125);
                juur.getChildren().add(võit1);

                võit1.setOnMouseClicked(event1 -> {
                    juur.getChildren().remove(võit1);
                    Pane võit2 = võidusumma(""+ võit);
                    võit2.setLayoutY(125);
                    võit2.setLayoutX(475);
                    juur.getChildren().add(võit2);
                    mängija.setRaha(mängija.getRaha() + võit);

                });

                for (int i = 0; i < 6; i++) {
                    if (i == 5) {
                        FadeTransition ft = new FadeTransition(Duration.millis(100 *i+1), list.get(i));
                        ft.setFromValue(1.0);  // määratakse algväärtus (1.0 - täiesti selge)
                        ft.setToValue(0.0); // määratakse lõppväärtus (0 - täiesti haihtunud)
                        ft.setCycleCount(8); // lõpmatu tsüklite arv
                        ft.setAutoReverse(true); // lõppu jõudes tagasi, algusest jälle edasi
                        ft.play();

                        FadeTransition ft2 = new FadeTransition(Duration.millis(100 *i+1), list2.get(i));
                        ft2.setFromValue(1.0);  // määratakse algväärtus (1.0 - täiesti selge)
                        ft2.setToValue(0.0); // määratakse lõppväärtus (0 - täiesti haihtunud)
                        ft2.setCycleCount(8); // lõpmatu tsüklite arv
                        ft2.setAutoReverse(true); // lõppu jõudes tagasi, algusest jälle edasi
                        ft2.play();

                        FadeTransition ft3 = new FadeTransition(Duration.millis(100 *i+1), list3.get(i));
                        ft3.setFromValue(1.0);  // määratakse algväärtus (1.0 - täiesti selge)
                        ft3.setToValue(0.0); // määratakse lõppväärtus (0 - täiesti haihtunud)
                        ft3.setCycleCount(8); // lõpmatu tsüklite arv
                        ft3.setAutoReverse(true); // lõppu jõudes tagasi, algusest jälle edasi
                        ft3.play();
                    } else {
                        FadeTransition ft = new FadeTransition(Duration.millis(100 *i+1), list.get(i));
                        ft.setFromValue(1.0);  // määratakse algväärtus (1.0 - täiesti selge)
                        ft.setToValue(0.0); // määratakse lõppväärtus (0 - täiesti haihtunud)
                        ft.setCycleCount(8); // lõpmatu tsüklite arv
                        ft.setAutoReverse(false); // lõppu jõudes tagasi, algusest jälle edasi
                        ft.play();

                        FadeTransition ft2 = new FadeTransition(Duration.millis(100 *i+1), list2.get(i));
                        ft2.setFromValue(1.0);  // määratakse algväärtus (1.0 - täiesti selge)
                        ft2.setToValue(0.0); // määratakse lõppväärtus (0 - täiesti haihtunud)
                        ft2.setCycleCount(8); // lõpmatu tsüklite arv
                        ft2.setAutoReverse(false); // lõppu jõudes tagasi, algusest jälle edasi
                        ft2.play();

                        FadeTransition ft3 = new FadeTransition(Duration.millis(100 *i+1), list3.get(i));
                        ft3.setFromValue(1.0);  // määratakse algväärtus (1.0 - täiesti selge)
                        ft3.setToValue(0.0); // määratakse lõppväärtus (0 - täiesti haihtunud)
                        ft3.setCycleCount(8); // lõpmatu tsüklite arv
                        ft3.setAutoReverse(false); // lõppu jõudes tagasi, algusest jälle edasi
                        ft3.play();

                    }
                }
            });
        });

        ruut2.setOnMouseClicked(event -> { juur.getChildren().clear();
            juur.getChildren().addAll(iv3, ruut,slotruut4, slotruut5, slotruut6, slotruut7, slotruut8, startnupp, menuNupp, exitNupp);

            startnupp.setOnMouseClicked(event2 -> {
                try {
                    this.panus = Integer.parseInt(tf1.getText());
                } catch (Exception e){}
                juur.getChildren().clear();
                juur.getChildren().addAll(iv3, ruut,slotruut4, slotruut5, slotruut6, slotruut7, slotruut8, startnupp, menuNupp, exitNupp);
                juur.getChildren().addAll(slot, slot2, slot3, slot4, slot5, slot6,
                        slot7, slot8, slot9, slot10, slot11, slot12, slot13, slot14, slot15, slot16, slot17, slot18,
                        slot19, slot20, slot21, slot22, slot23, slot24, slot25, slot26, slot27, slot28, slot29, slot30);
                Collections.shuffle(list);
                Collections.shuffle(list2);
                Collections.shuffle(list3);
                Collections.shuffle(list4);
                Collections.shuffle(list5);

                Set<String> set = new HashSet<>();
                set.add(list.get(5).getId());
                set.add(list2.get(5).getId());
                set.add(list3.get(5).getId());
                set.add(list4.get(5).getId());
                set.add(list5.get(5).getId());

                if (set.size() == 1) { //kõik pildid on samad
                    võit = võit + panus*10;
                } else if (set.size() == 2) { //kolmik ja paar või neli ühesugust pilti
                    võit = võit + panus*3;
                } else if (set.size() == 3) { //kolm pilti on samad või kaks paari
                    võit = võit + panus*2;
                } else { //ükski ei klapi
                    võit = võit + 0;
                }

                Pane võit1 = võidusumma("Võidusumma");
                võit1.setLayoutX(475);
                võit1.setLayoutY(125);
                juur.getChildren().add(võit1);

                võit1.setOnMouseClicked(event1 -> {
                    juur.getChildren().remove(võit1);
                    Pane võit2 = võidusumma(""+ võit);
                    võit2.setLayoutY(125);
                    võit2.setLayoutX(475);
                    juur.getChildren().add(võit2);
                    mängija.setRaha(mängija.getRaha() + võit);

                });

                for (int i = 0; i < 6; i++) {
                    if (i == 5) {
                        FadeTransition ft = new FadeTransition(Duration.millis(100 *i+1), list.get(i));
                        ft.setFromValue(1.0);  // määratakse algväärtus (1.0 - täiesti selge)
                        ft.setToValue(0.0); // määratakse lõppväärtus (0 - täiesti haihtunud)
                        ft.setCycleCount(8); // lõpmatu tsüklite arv
                        ft.setAutoReverse(true); // lõppu jõudes tagasi, algusest jälle edasi
                        ft.play();

                        FadeTransition ft2 = new FadeTransition(Duration.millis(100 *i+1), list2.get(i));
                        ft2.setFromValue(1.0);  // määratakse algväärtus (1.0 - täiesti selge)
                        ft2.setToValue(0.0); // määratakse lõppväärtus (0 - täiesti haihtunud)
                        ft2.setCycleCount(8); // lõpmatu tsüklite arv
                        ft2.setAutoReverse(true); // lõppu jõudes tagasi, algusest jälle edasi
                        ft2.play();

                        FadeTransition ft3 = new FadeTransition(Duration.millis(100 *i+1), list3.get(i));
                        ft3.setFromValue(1.0);  // määratakse algväärtus (1.0 - täiesti selge)
                        ft3.setToValue(0.0); // määratakse lõppväärtus (0 - täiesti haihtunud)
                        ft3.setCycleCount(8); // lõpmatu tsüklite arv
                        ft3.setAutoReverse(true); // lõppu jõudes tagasi, algusest jälle edasi
                        ft3.play();

                        FadeTransition ft4 = new FadeTransition(Duration.millis(100 *i+1), list4.get(i));
                        ft4.setFromValue(1.0);  // määratakse algväärtus (1.0 - täiesti selge)
                        ft4.setToValue(0.0); // määratakse lõppväärtus (0 - täiesti haihtunud)
                        ft4.setCycleCount(8); // lõpmatu tsüklite arv
                        ft4.setAutoReverse(true); // lõppu jõudes tagasi, algusest jälle edasi
                        ft4.play();

                        FadeTransition ft5 = new FadeTransition(Duration.millis(100 *i+1), list5.get(i));
                        ft5.setFromValue(1.0);  // määratakse algväärtus (1.0 - täiesti selge)
                        ft5.setToValue(0.0); // määratakse lõppväärtus (0 - täiesti haihtunud)
                        ft5.setCycleCount(8); // lõpmatu tsüklite arv
                        ft5.setAutoReverse(true); // lõppu jõudes tagasi, algusest jälle edasi
                        ft5.play();
                    } else {
                        FadeTransition ft = new FadeTransition(Duration.millis(100 *i+1), list.get(i));
                        ft.setFromValue(1.0);  // määratakse algväärtus (1.0 - täiesti selge)
                        ft.setToValue(0.0); // määratakse lõppväärtus (0 - täiesti haihtunud)
                        ft.setCycleCount(8); // lõpmatu tsüklite arv
                        ft.setAutoReverse(false); // lõppu jõudes tagasi, algusest jälle edasi
                        ft.play();

                        FadeTransition ft2 = new FadeTransition(Duration.millis(100 *i+1), list2.get(i));
                        ft2.setFromValue(1.0);  // määratakse algväärtus (1.0 - täiesti selge)
                        ft2.setToValue(0.0); // määratakse lõppväärtus (0 - täiesti haihtunud)
                        ft2.setCycleCount(8); // lõpmatu tsüklite arv
                        ft2.setAutoReverse(false); // lõppu jõudes tagasi, algusest jälle edasi
                        ft2.play();

                        FadeTransition ft3 = new FadeTransition(Duration.millis(100 *i+1), list3.get(i));
                        ft3.setFromValue(1.0);  // määratakse algväärtus (1.0 - täiesti selge)
                        ft3.setToValue(0.0); // määratakse lõppväärtus (0 - täiesti haihtunud)
                        ft3.setCycleCount(8); // lõpmatu tsüklite arv
                        ft3.setAutoReverse(false); // lõppu jõudes tagasi, algusest jälle edasi
                        ft3.play();

                        FadeTransition ft4 = new FadeTransition(Duration.millis(100 *i+1), list4.get(i));
                        ft4.setFromValue(1.0);  // määratakse algväärtus (1.0 - täiesti selge)
                        ft4.setToValue(0.0); // määratakse lõppväärtus (0 - täiesti haihtunud)
                        ft4.setCycleCount(8); // lõpmatu tsüklite arv
                        ft4.setAutoReverse(false); // lõppu jõudes tagasi, algusest jälle edasi
                        ft4.play();

                        FadeTransition ft5 = new FadeTransition(Duration.millis(100 *i+1), list5.get(i));
                        ft5.setFromValue(1.0);  // määratakse algväärtus (1.0 - täiesti selge)
                        ft5.setToValue(0.0); // määratakse lõppväärtus (0 - täiesti haihtunud)
                        ft5.setCycleCount(8); // lõpmatu tsüklite arv
                        ft5.setAutoReverse(false); // lõppu jõudes tagasi, algusest jälle edasi
                        ft5.play();
                    }
                }
            });
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
        " \n Tee oma panus: \n\n\n\n");
        text.setFont(megrim22);
        text.setTextAlignment(TextAlignment.CENTER);

        pane.setOpacity(0.95);

        pane.getChildren().addAll(ring, text);
        return pane;
    }

    private Pane getVäikeNupp(String tekst){
        Pane pane = new StackPane();
        Rectangle rect = new Rectangle(115, 50);
        rect.setFill(Color.RED);
        rect.setStroke(Color.WHITE);

        Text text = new Text();
        text.setFill(Color.WHITE);
        text.setText(tekst);
        text.setFont(megrim35);

        pane.getChildren().addAll(rect, text);
        pane.setOpacity(0.8);

        pane.setOnMouseEntered(event -> rect.setFill(Color.rgb(225, 72, 72)));
        pane.setOnMouseExited(event -> rect.setFill(Color.RED));

        return pane;
    }

    private Pane võidusumma(String tekst){
        Pane pane = new StackPane();
        Rectangle rect = new Rectangle(250, 50);
        rect.setFill(Color.RED);
        rect.setStroke(Color.WHITE);

        Text text = new Text();
        text.setFill(Color.WHITE);
        text.setText(tekst);
        text.setFont(megrim35);

        pane.getChildren().addAll(rect, text);
        pane.setOpacity(0.8);

        pane.setOnMouseEntered(event -> rect.setFill(Color.rgb(225, 72, 72)));
        pane.setOnMouseExited(event -> rect.setFill(Color.RED));

        return pane;
    }



    private Pane start(String tekst){
        Pane pane = new StackPane();
        Rectangle rect = new Rectangle(140, 60);
        rect.setFill(Color.RED);
        rect.setStroke(Color.WHITE);

        Text text = new Text();
        text.setFill(Color.WHITE);
        text.setText(tekst);
        text.setFont(megrim50);

        pane.getChildren().addAll(rect, text);
        pane.setOpacity(0.95);

        pane.setOnMouseEntered(event -> rect.setFill(Color.rgb(255, 72, 72)));
        pane.setOnMouseExited(event -> rect.setFill(Color.RED));

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

    private Pane slotruut() {
        Pane pane = new StackPane();
        Rectangle ruut = new Rectangle();
        ruut.setFill(Color.WHITE);
        ruut.setHeight(300);
        ruut.setWidth(190);

        pane.getChildren().addAll(ruut);
        return pane;

    }



    private Pane getRuutTekstiga(String arv) {
        Pane pane = new StackPane();
        Rectangle rect = new Rectangle(200, 200);
        rect.setFill(Color.RED);
        rect.setStroke(Color.WHITE);


        Text text = new Text();
        text.setFill(Color.WHITE);
        text.setText(arv);
        text.setFont(megrim35);
        text.setTextAlignment(TextAlignment.CENTER);

        pane.getChildren().addAll(rect, text);
        pane.setOpacity(0.8);

        pane.setOnMouseEntered(event -> rect.setFill(Color.rgb(225, 72, 72)));
        pane.setOnMouseExited(event -> rect.setFill(Color.RED));

        return pane;
    }


    private Pane võiduruut(String arv) {
        Pane pane = new StackPane();
        Rectangle rect = new Rectangle(600, 200);
        rect.setFill(Color.RED);
        rect.setStroke(Color.WHITE);

        Text text = new Text();
        text.setFill(Color.WHITE);
        text.setText(arv);
        text.setFont(megrim35);
        text.setTextAlignment(TextAlignment.CENTER);

        pane.getChildren().addAll(rect, text);
        pane.setOpacity(0.8);

        pane.setOnMouseEntered(event -> rect.setFill(Color.rgb(225, 72, 72)));
        pane.setOnMouseExited(event -> rect.setFill(Color.RED));

        return pane;
    }

    private boolean onSobivPanus(String sõne) {
        boolean onPiisavPanus = true;
        try {
            panus = Integer.parseInt(sõne);
            if (panus < 1) {
                onPiisavPanus = false;
            }
        } catch (Exception e) {
            onPiisavPanus = false;
        }
        return onPiisavPanus;
    }

}

