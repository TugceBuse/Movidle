package com.example.filmlistesi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;


public class MovieDLE extends Application {

    private static Movie chosen;


    @Override
    public void start(Stage primarystage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MovieDLE.class.getResource("Movie-dle.fxml"));
        AnchorPane Root = fxmlLoader.load();
        Scene scene = new Scene(Root, 1600, 900);
        primarystage.setTitle("MovieDLE");
        primarystage.setScene(scene);
        primarystage.setResizable(false);
        primarystage.show();
    }

    public static Movie getChosen(){
        return chosen;
    }
    public static void Choose_A_Movie()
    {
        Random random = new Random();
        int chosennumber = random.nextInt(250);
        ArrayList<Movie> movielist = ReadText.getMovieList();
        chosen = movielist.get(chosennumber);
        System.out.println(chosen.getName());  //---->>Seçilen filmi gösterir
    }



    public static void main(String[] args) {
        MovieDLEController.MediaPlayer("Music\\audio.mp3", MediaPlayer.INDEFINITE);
        ReadText.readcsv();
        Choose_A_Movie();
        launch();
    }

}