package application;
	
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


import java.io.File;
//import java.net.URL;


import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;

public class Main extends Application {
	MediaPlayer mediaPlayer;
	//private Label time;
	Duration duration;
	Button fullScreenButton;
	Scene scene;
	Media media;
	double width;
	double height;
	MediaView mediaView;
	ImageView imageView;
	BorderPane borderPane;
	Image image;
	@Override
	public void start(Stage primaryStage) {
	
		scene = setScene();
		primaryStage.setTitle("Media Player");
		primaryStage.setScene(scene);
		
		primaryStage.show();
	}
	
	private HBox addToolBar() {
		HBox toolBar = new HBox();
		toolBar.setPadding(new Insets(20));
		toolBar.setAlignment(Pos.CENTER);
		toolBar.alignmentProperty().isBound();
		toolBar.setSpacing(5);
		toolBar.setStyle("-fx-background-color: Black");
		try {
			Image playButtonImage = new Image(getClass().getResourceAsStream("play.png"));
			Button playButton = new Button();
			playButton.setGraphic(new ImageView(playButtonImage));
			playButton.setStyle("-fx-background-color: Black");
			
			
			playButton.setOnAction((ActionEvent e) -> {
				try {
					mediaPlayer.play();
				}catch (NullPointerException npe) {}
			});
			
			playButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			playButton.setStyle("-fx-background-color: Black");
			playButton.setStyle("-fx-body-color: Black");
			});
			playButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			playButton.setStyle("-fx-background-color: Black");
			});
		

			Image pausedButtonImage = new Image(getClass().getResourceAsStream("Pause.png"));
			Button pauseButton = new Button();
			pauseButton.setGraphic(new ImageView(pausedButtonImage));
			pauseButton.setStyle("-fx-background-color: Black");

			
			pauseButton.setOnAction((ActionEvent e) -> {
				try {
					mediaPlayer.pause();
				}catch (NullPointerException npe) {}
			});

			pauseButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			pauseButton.setStyle("-fx-background-color: Black");
			pauseButton.setStyle("-fx-body-color: Black");
			});
			pauseButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			pauseButton.setStyle("-fx-background-color: Black");
			});
			
			Image ffwButtonImage = new Image(getClass().getResourceAsStream("Forward.png"));
			Button ffwButton = new Button();
			ffwButton.setGraphic(new ImageView(ffwButtonImage));
			ffwButton.setStyle("-fx-background-color: Black");

			ffwButton.setOnAction((ActionEvent e) -> {
				try {
					mediaPlayer.seek(mediaPlayer.getCurrentTime().multiply(1.5));
				}catch (NullPointerException npe) {}
			});
			ffwButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			ffwButton.setStyle("-fx-background-color: Black");
			ffwButton.setStyle("-fx-body-color: Black");
			});
			ffwButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			ffwButton.setStyle("-fx-background-color: Black");
			});
			
			Image rwdButtonImage = new Image(getClass().getResourceAsStream("Back.png"));
			Button rwdButton = new Button();
			rwdButton.setGraphic(new ImageView(rwdButtonImage));
			rwdButton.setStyle("-fx-background-color: Black");

			rwdButton.setOnAction((ActionEvent e) -> {
				try {
					mediaPlayer.seek(mediaPlayer.getCurrentTime().divide(1.5));
				}catch (NullPointerException npe) {}
			});
			rwdButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			rwdButton.setStyle("-fx-background-color: Black");
			rwdButton.setStyle("-fx-body-color: Black");
			});
			rwdButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			rwdButton.setStyle("-fx-background-color: Black");
			});
			
			Image filesButtonImage = new Image(getClass().getResourceAsStream("Files.png"));
			Button filesButton = new Button();
			filesButton.setGraphic(new ImageView(filesButtonImage));
			filesButton.setStyle("-fx-background-color: Black");

			filesButton.setOnAction((ActionEvent e) -> {
				FileChooser fc = new FileChooser();
				fc.getExtensionFilters().add(new ExtensionFilter("*.mp4", "*.avi", "*.flv", "*.mpeg"));
				File file = fc.showOpenDialog(null);
				String path = file.getAbsolutePath();
				path = path.replace("\\", "/");
				media = new Media(new File(path).toURI().toString());
				
				mediaPlayer = new MediaPlayer(media);
				mediaPlayer.setAutoPlay(true);
				mediaView = new MediaView(mediaPlayer);
				
				borderPane.setCenter(mediaView);
				
				DoubleProperty width = mediaView.fitWidthProperty();
				DoubleProperty height = mediaView.fitHeightProperty();
				width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
				height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
				
			});
			filesButton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
			filesButton.setStyle("-fx-background-color: Black");
			filesButton.setStyle("-fx-body-color: Black");
			});
			filesButton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
			filesButton.setStyle("-fx-background-color: Black");
			});
			
			toolBar.getChildren().addAll(playButton, pauseButton, rwdButton, ffwButton, filesButton);
		} catch (NullPointerException npe) {
			
		} 
		
		return toolBar;
	}
	
	public Scene setScene() {
		
		//DropShadow
		DropShadow dropshadow = new DropShadow();
		dropshadow.setOffsetY(5.0);
		dropshadow.setOffsetX(5.0);
		dropshadow.setColor(Color.WHITE);
		borderPane = new BorderPane();
		borderPane.setStyle("-fx-background-color: Black");
			try{
				
				String path = "src/media/Raindrops_Videvo.mp";
				media = new Media(new File(path).toURI().toString());
				mediaPlayer = new MediaPlayer(media);
				mediaPlayer.setAutoPlay(false);
				mediaView = new MediaView(mediaPlayer);
	
				mediaView.setEffect(dropshadow);
	
				borderPane.setCenter(mediaView);
		
				DoubleProperty width = mediaView.fitWidthProperty();
				DoubleProperty height = mediaView.fitHeightProperty();
				width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
				height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));
				
				
			} catch (Exception e) {
				String path = "src/media/film.png";
				image = new Image(new File(path).toURI().toString());
				imageView = new ImageView(image);
				imageView.setEffect(dropshadow);
				
				borderPane.setCenter(imageView);
						
			}
			borderPane.setBottom(addToolBar());
			scene = new Scene(borderPane, 600, 600);
			scene.setFill(Color.BLACK);
			return scene;
		}
	
	public static void main(String[] args) {
		launch(args);
	}
}
