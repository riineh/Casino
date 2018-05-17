import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Mängija {
    private String nimi;
    private int vanus;
    private int raha;

    private String profiil = "file:profiil2.png";
    private Font megrim27 = Font.loadFont(new FileInputStream(new File("Megrim.ttf")),27);

    public Mängija(String nimi, int vanus, int raha) throws FileNotFoundException {
        this.nimi = nimi;
        this.vanus = vanus;
        this.raha = raha;
    }

    @Override
    public String toString() {
        return nimi + ": " + raha + "€";
    }

    public Pane getProfiiliPane() {
        Pane pane = new StackPane();
        pane.setPrefSize(250,90);

        Image logo = new Image(profiil);
        ImageView iv = new ImageView(logo);
        iv.setFitWidth(250);
        iv.setFitHeight(90);

        Text text = new Text();
        text.setFill(Color.BLACK);
        text.setText("      " + nimi + "\n" + raha + "€");
        text.setFont(megrim27);
        text.setTextAlignment(TextAlignment.RIGHT);

        pane.getChildren().addAll(iv, text);
        pane.setOpacity(0.9);

        return pane;
    }

    public String getNimi() {
        return nimi;
    }

    public int getRaha() {
        return raha;
    }

    public int getVanus() { return vanus; }

    public void setRaha(int raha) {  //selleks, et määrata mängijale alguses kindel raha summa
        this.raha = raha;
    }
}
