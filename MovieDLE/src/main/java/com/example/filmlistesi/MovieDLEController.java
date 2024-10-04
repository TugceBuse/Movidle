package com.example.filmlistesi;

import javafx.animation.FillTransition;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MovieDLEController {

    @FXML
    private TextField tfield;

    @FXML
    private ListView<String> autoview;
    @FXML
    private Pane pane1, pane2, pane3, pane4, pane5, infopane;
    @FXML
    private Label life,lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8, lbl9, lbl10, lbl11, lbl12, lbl13, lbl14, lbl15, lbl16, lbl17, lbl18, lbl19, lbl20, lbl21, lbl22, lbl23, lbl24, lbl25, lbl26, lbl27, lbl28, lbl29, lbl30;
    @FXML
    private Rectangle r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30;

    @FXML
    private ImageView img1, img2, img3, img4, img5,poster,poster1;


    private int btnclckcounter = 0;

    @FXML
    protected void AutoCompleteTextf(){
        if(tfield.getText()!=null)
        {
            autoview.setMouseTransparent(false);
            String text = tfield.getText().toLowerCase();
            String[] names = Movie.getAllNames();
            autoview.getItems().clear();
            autoview.setVisible(true);
            boolean notfound=true;
            for(String name : names){
                if(!text.equals("") && name.toLowerCase().startsWith(text)){
                    autoview.getItems().add(name);
                    notfound=false;
                }
            }
            if(notfound){
                autoview.getItems().add("No Movie Found");
                autoview.setMouseTransparent(true);
            }
            if(text.equals("")){
                autoview.setVisible(false);
            }
        }
    }

    @FXML protected void AutoCompleteview(){
        //if(autoview.getSelectionModel().getSelectedItem()!=null)
        tfield.setText(autoview.getSelectionModel().getSelectedItem());
    }


    protected void playerWon() throws IOException {
        MediaPlayer("Music\\won.mp3",1);
        ButtonType restart = new ButtonType("Restart");
        ButtonType quit = new ButtonType("Quit");
        Optional<ButtonType> option = showAlert("CONGRATULATIONS","You Won The MovieDLE Game!",restart,quit).showAndWait();

        if(option.isPresent()) {
            if (option.get() == restart) {
                RestartGame();
            } else if (option.get() == quit) {
                Platform.exit();
            }
        }
    }

    public static void MediaPlayer(String path,int i){
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setCycleCount(i);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.play();
    }

    protected void RestartGame() {
        pane1.setVisible(false);pane2.setVisible(false);pane3.setVisible(false);pane4.setVisible(false);pane5.setVisible(false);
        infopane.setVisible(false);
        poster.setImage(null);poster1.setImage(null);
        img1.setImage(null);img2.setImage(null);img3.setImage(null);img4.setImage(null);img5.setImage(null);
        tfield.clear();
        MovieDLE.Choose_A_Movie();
        btnclckcounter = 0;
        life.setText("Remaining Guess : "+(5-btnclckcounter));
    }


    private Movie chosen;

    public void Transition(Rectangle r, Color a, Color b) {
        FillTransition tr = new FillTransition(Duration.seconds(3), a, b);
        tr.setShape(r);
        tr.setAutoReverse(false);
        tr.play();
    }

    public void RightGuessTransition(Rectangle r, Color a, Color b) {
        FillTransition tr = new FillTransition(Duration.seconds(1), a, b);
        tr.setShape(r);
        tr.setAutoReverse(false);
        tr.play();
    }

    protected void Guesscontroller(Movie movie, Pane p, Label l1, Label l2, Label l3, Label l4, Label l5, Label l6,
                                   Rectangle R1, Rectangle R2, Rectangle R3, Rectangle R4, Rectangle R5, Rectangle R6, ImageView img) {
        p.setVisible(true);

        //********************WIN********************
        if (movie.getName().equalsIgnoreCase(chosen.getName())) {
            l1.setText(movie.getName());
            l2.setText(movie.getGenre());
            l3.setText(movie.getOrigin());
            l4.setText(movie.getDirector());
            l5.setText(movie.getStar());
            l6.setText(movie.getYear());
            life.setText("Remaining Guess : "+(5-btnclckcounter));

            RightGuessTransition(R1, Color.WHITE, Color.rgb(133, 178, 36));
            RightGuessTransition(R2, Color.WHITE, Color.rgb(133, 178, 36));
            RightGuessTransition(R3, Color.WHITE, Color.rgb(133, 178, 36));
            RightGuessTransition(R4, Color.WHITE, Color.rgb(133, 178, 36));
            RightGuessTransition(R5, Color.WHITE, Color.rgb(133, 178, 36));
            RightGuessTransition(R6, Color.WHITE, Color.rgb(133, 178, 36));
            try {
                playerWon();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        //**************************WRONG GUESS***********************
        else {
            life.setText("Remaining Guess : "+(5-btnclckcounter));
            int yeardiff = Integer.parseInt(chosen.getYear()) - Integer.parseInt(movie.getYear());
            l1.setText(movie.getName());
            l2.setText(movie.getGenre());
            l3.setText(movie.getOrigin());
            l4.setText(movie.getDirector());
            l5.setText(movie.getStar());
            l6.setText(movie.getYear());
            Transition(R1, Color.WHITE, Color.rgb(201, 43, 43));
            if (movie.getGenre().equalsIgnoreCase(chosen.getGenre()))
                Transition(R2, Color.WHITE, Color.rgb(133, 178, 36));
            else
                Transition(R2, Color.WHITE, Color.rgb(201, 43, 43));
            if (movie.getOrigin().equalsIgnoreCase(chosen.getOrigin()))
                Transition(R3, Color.WHITE, Color.rgb(133, 178, 36));
            else
                Transition(R3, Color.WHITE, Color.rgb(201, 43, 43));
            if (movie.getDirector().equalsIgnoreCase(chosen.getDirector()))
                Transition(R4, Color.WHITE, Color.rgb(133, 178, 36));
            else
                Transition(R4, Color.WHITE, Color.rgb(201, 43, 43));
            if (movie.getStar().equalsIgnoreCase(chosen.getStar()))
                Transition(R5, Color.WHITE, Color.rgb(133, 178, 36));
            else
                Transition(R5, Color.WHITE, Color.rgb(201, 43, 43));
            if (movie.getYear().equalsIgnoreCase(chosen.getYear())) {
                Transition(R6, Color.WHITE, Color.rgb(133, 178, 36));
            } else {
                Transition(R6, Color.WHITE, Color.rgb(201, 43, 43));
            }
            if (yeardiff > 0) {
                File file = new File("images\\arrow-up-64.png");
                Image image = new Image(file.toURI().toString());
                img.setImage(image);
            } else if (yeardiff < 0) {
                File file = new File("images\\arrow-down-64.png");
                Image image = new Image(file.toURI().toString());
                img.setImage(image);
            }
            //***********************GAMEOVER********************************
            if(btnclckcounter==5){
                MediaPlayer("Music\\lose.mp3",1);
                ButtonType restart = new ButtonType("RESTART");
                ButtonType quit = new ButtonType("QUIT");

                Optional<ButtonType> option = showAlert("GAME OVER","YOU COULDNT FIND THE MOVIE,YOU LOST!",restart,quit).showAndWait();

                if(option.isPresent()) {
                    if (option.get() == restart) {
                        RestartGame();
                    } else if (option.get() == quit) {
                        Platform.exit();
                    }
                }
            }

        }

    }


    @FXML
    protected void GuessClick() {
        chosen = MovieDLE.getChosen();
        String Guess = tfield.getText();
        tfield.setText("");
        autoview.setVisible(false);
        int k=0;
        for (Movie movie : ReadText.getMovieList()) {
            k++;
            if (movie.getName().equalsIgnoreCase(Guess)) {
                btnclckcounter++;
                flagposter(movie);
                if (btnclckcounter == 1) {
                    infopane.setVisible(true);
                    Guesscontroller(movie, pane1, lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, r1, r2, r3, r4, r5, r6, img1);
                } else if (btnclckcounter == 2) {
                    Guesscontroller(movie, pane2, lbl7, lbl8, lbl9, lbl10, lbl11, lbl12, r7, r8, r9, r10, r11, r12, img2);
                } else if (btnclckcounter == 3) {
                    Guesscontroller(movie, pane3, lbl13, lbl14, lbl15, lbl16, lbl17, lbl18, r13, r14, r15, r16, r17, r18, img3);
                } else if (btnclckcounter == 4) {
                    Guesscontroller(movie, pane4, lbl19, lbl20, lbl21, lbl22, lbl23, lbl24, r19, r20, r21, r22, r23, r24, img4);
                } else if (btnclckcounter == 5) {
                    Guesscontroller(movie, pane5, lbl25, lbl26, lbl27, lbl28, lbl29, lbl30, r25, r26, r27, r28, r29, r30, img5);
                }
                break;
            }
            else if(k==250){
                ButtonType OK = new ButtonType("OK");
                Optional<ButtonType> option = showAlert(null,"The movie title you entered does not exist in first 250 imdb movies",OK).showAndWait();

                if(option.isPresent()) {
                    if (option.get() == OK) {
                        tfield.clear();
                    }
                }
            }
        }
    }

    protected void flagposter(Movie movie){
        File file = new File(movie.getPoster());
        Image image = new Image(file.toURI().toString());
        poster.setImage(image);
        poster1.setImage(image);
        FadeTransition t = new FadeTransition(Duration.millis(1000), poster);
        FadeTransition t1 = new FadeTransition(Duration.millis(1000), poster1);
        t.setFromValue(0.5);
        t1.setFromValue(0.5);
        t.setToValue(1);
        t1.setToValue(1);
        t.setCycleCount(3);
        t1.setCycleCount(3);
        t.setAutoReverse(true);
        t1.setAutoReverse(true);
        t.play();
        t1.play();
    }


    protected Alert showAlert(String header,String message,ButtonType btn,ButtonType btn1){
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle(null);

        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.setResizable(false);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(btn, btn1);
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.getDialogPane().setStyle("-fx-border-color: black");
        alert.getDialogPane().setPrefSize(300, 150);

        return alert;
    }


    protected Alert showAlert(String header,String message,ButtonType btn){
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle(null);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.setResizable(false);
        alert.getButtonTypes().clear();
        alert.getButtonTypes().add(btn);
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.getDialogPane().setStyle("-fx-border-color: black");
        alert.getDialogPane().setPrefSize(300, 150);

        return alert;
    }
}