package bingo;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BingoPilet {
    private char[] tähed = {'B','I','N','G','O'};
    private List<List<Integer>> bingoTabel = new ArrayList<>();

    private Font litSans22 = Font.loadFont(new FileInputStream(new File("LitSansMedium.otf")),25);

    private Color sinine = Color.rgb(41,79, 218);
    private Color punane = Color.rgb(255,38,5);
    private Color roheline = Color.rgb(0,101,2);
    private Color oranz = Color.rgb(255,150,5);
    private List<Color> värvid = Arrays.asList(sinine, punane, roheline, oranz);

    private Random suvaline = new Random();
    private int suvalineVärv = suvaline.nextInt(4);
    private Color värv = värvid.get(suvalineVärv);
    private GridPane ruudustik = new GridPane();

    public BingoPilet() throws FileNotFoundException {
        loosiPilet();
    }

    public List<List<Integer>> loosiPilet() {
        for (char elem: tähed){
            PiletiNumbrid a = new PiletiNumbrid(elem);
            List<Integer> aNumbrid = a.loosiPiletiNumbrid();
            bingoTabel.add(aNumbrid);
        }
        return bingoTabel;
    }

    public List<List<Integer>> getBingoTabel() {
        return bingoTabel;
    }

    public void kontrolli(int loositudNumber){
        for (int i=0; i<bingoTabel.size(); i++){
            for (int j=0; j<bingoTabel.get(i).size(); j++){
                if (bingoTabel.get(i).get(j) == loositudNumber) {
                    this.bingoTabel.get(i).set(j,0);
                    Rectangle rect = new Rectangle(43,43);
                    rect.setFill(värv);
                    rect.setOpacity(0.7);
                    ruudustik.add(rect, i, j);
                }
            }
        }
    }

    public Pane drawBingoPilet() {
        StackPane pane = new StackPane();

        Rectangle taust = new Rectangle(300, 300);
        taust.setFill(värv);

        double numbriSuurus = 43;
        ruudustik.setPrefSize(280,280);

        for (int i=0; i<5; i++){
            ruudustik.getColumnConstraints().add(new ColumnConstraints(numbriSuurus));
            ruudustik.getRowConstraints().add(new RowConstraints(numbriSuurus));
        }
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                int number = getBingoTabel().get(i).get(j);
                ruudustik.add(numbriRuut(numbriSuurus, ""+number),i,j);
            }
        }
        ruudustik.setHgap(9);
        ruudustik.setVgap(9);

        ruudustik.setAlignment(Pos.CENTER);

        pane.getChildren().addAll(taust, ruudustik);

        return pane;
    }
    private Pane numbriRuut(double suurus, String number){
        Pane ruut = new StackPane();
        Rectangle rect = new Rectangle(suurus,suurus);
        rect.setFill(Color.WHITE);
        rect.setOpacity(0.7);

        Text tekst = new Text();
        tekst.setFont(litSans22);
        tekst.setFill(värv);
        tekst.setText(number);

        ruut.getChildren().addAll(rect, tekst);

        return ruut;
    }

    public boolean nurkademäng() {
        boolean nurkVõit = false;
        int nurk1 = bingoTabel.get(0).get(0);
        int nurk2 = bingoTabel.get(0).get(4);
        int nurk3 = bingoTabel.get(4).get(0);
        int nurk4 = bingoTabel.get(4).get(4);

        if (nurk1==0 && nurk2==0 && nurk3==0 && nurk4==0){
            nurkVõit = true;
        }
        return nurkVõit;
    }
    public boolean diagonaalidemäng() {
        boolean diagonaalVõit = true;
        for (int i=0; i<bingoTabel.size(); i++){
            for (int j=0; j<bingoTabel.get(i).size(); j++){
                if (i==j && bingoTabel.get(i).get(j) != 0){
                    diagonaalVõit = false;
                }
                else if (((i==0&&j==4)||(i==1&&j==3)||(i==2&&j==2)||(i==3&&j==1)||(i==4&&j==0))&&bingoTabel.get(i).get(j)!=0){
                    diagonaalVõit = false;
                }
            }
        }
        return diagonaalVõit;
    }
    public boolean täismäng() {
        boolean täismängVõit = true;
        for (int i=0; i<bingoTabel.size(); i++){
            for (int j=0; j<bingoTabel.get(i).size(); j++){
                if (bingoTabel.get(i).get(j) != 0){
                    täismängVõit = false;
                }
            }
        }
        return täismängVõit;
    }
}