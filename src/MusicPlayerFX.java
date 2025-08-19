import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class MusicPlayerFX extends Application {

    private MediaPlayer player;

    @Override
    public void start(Stage primaryStage) {
        // Load audio file
        File file = new File("src\\Pink Floyd-Hey You.wav");
        Media media = new Media(file.toURI().toString());// Media : wrapper around the audio file.
        player = new MediaPlayer(media); // MediaPlayer : controller to play,pause change volume, track progress

        // UI Elements
        Button playBtn = new Button("Play");
        Button stopBtn = new Button("Stop");
        Button resetBtn = new Button("Reset");

        ProgressBar progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(300);

        Slider volumeSlider = new Slider(0, 1, 0.5); // min, max, initial
        Label volumeLabel = new Label("Volume");

        // Button actions
        playBtn.setOnAction(e -> player.play());
        stopBtn.setOnAction(e -> player.pause());
        resetBtn.setOnAction(e -> player.seek(Duration.ZERO));

        // Volume control
        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            player.setVolume(newVal.doubleValue());
        });

        // Update progress bar
        player.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            progressBar.setProgress(newTime.toMillis() / player.getTotalDuration().toMillis());
        });

        // Layout
        HBox buttons = new HBox(10, playBtn, stopBtn, resetBtn);
        HBox volumeControl = new HBox(10, volumeLabel, volumeSlider);
        VBox root = new VBox(10, buttons, progressBar, volumeControl);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(root, 350, 150);
        primaryStage.setTitle("Music Player");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
