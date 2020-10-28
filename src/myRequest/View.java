package myRequest;

import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View {
	private static Label welcomeLabel1 = new Label("Welcome to the Playlist Sorter.");
	private static Label welcomeLabel2 = new Label("This application will output the albums contained in a Spotify playlist to the console, sorted by date.");
	private static final Label userPromptLabel = new Label("Please enter your playlist ID:");
	private static final Label userErrorLabel = new Label("Error encountered while retrieving user playlist. Please try again.");
	
	private static final Color spotifyGreen = Color.rgb(30,215,96);
	private static final Background spotifyBackground = new Background(new BackgroundFill(spotifyGreen, CornerRadii.EMPTY, Insets.EMPTY));
	
	private static Stage stage;
	private static FlowPane pane = new FlowPane();
	private static Scene scene = new Scene(pane, 400, 450);

	private static Button playlistInputButton;
	private static TextField spotifyIdInput = new TextField();
	private static TextArea spotifyInfoOutput = new TextArea();

	
	
	public static void showHomeScreen(Stage primaryStage) {
		pane.setPadding(new Insets(11, 12, 13, 14));
		pane.setBackground(spotifyBackground);
		pane.getChildren().addAll(welcomeLabel1, welcomeLabel2);
	
		stage = primaryStage;
		primaryStage.setTitle("Spotify Playlist Sorter");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void showUserOptions(Button button) {
		playlistInputButton = button;
		
		pane.getChildren().addAll(userPromptLabel, spotifyIdInput);
		pane.getChildren().add(playlistInputButton);
	}
	
	public static void clearOutput() {
		pane.getChildren().remove(userErrorLabel);
		spotifyIdInput.clear();
		spotifyInfoOutput.clear();
	}
	
	public static void outputErrorMessage() {
		if(!pane.getChildren().contains(userErrorLabel)) {
			pane.getChildren().add(userErrorLabel);
		}
	}
	
	public static void outputList(List<?> list) {
		pane.getChildren().add(spotifyInfoOutput);
		for(int listIndex = 0; listIndex < list.size(); listIndex++) {
			spotifyInfoOutput.appendText(list.get(listIndex).toString() + '\n');
		}
	}
	
	public static String getUserTextInput() {
		return spotifyIdInput.getText();
	}
}
