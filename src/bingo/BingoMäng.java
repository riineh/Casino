package bingo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class BingoMäng extends Application {
    private int kõrgus = 700;
    private int laius = 1200;

    private String logoPath = "file:\\C:\\Users\\Kärt\\IdeaProjects\\Casino\\casinoLogo.jpg";

    //hoiab bingopileti andmeid
    private BingoPilet bingo;

    public static void main(String[] args) { launch(args);
    }

    @Override
    public void start(Stage stage) {
        
    }
}
