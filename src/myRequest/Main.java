package myRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.*;

import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;

public class Main extends Application {
	
	private static final Color spotifyGreen = Color.rgb(30,215,96);
	
	
	public static FlowPane pane;
	public static TextField inputField;
	public static Button nextButton;
	
	public static void main(String args[]) {
		
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) {
		
		// TODO: Update, rewrite, revariable, and make this all way cleaner.
		Background spotifyBackground = new Background(new BackgroundFill(spotifyGreen, CornerRadii.EMPTY, Insets.EMPTY));

		Label welcomeLabel1 = new Label("Welcome to the Playlist Sorter.");
		Label welcomeLabel2 = new Label("This application will output the albums contained in a Spotify playlist to the console, sorted by date.");
		
		pane = new FlowPane();
		pane.setPadding(new Insets(11, 12, 13, 14));
		pane.setBackground(spotifyBackground);
		pane.getChildren().addAll(welcomeLabel1, welcomeLabel2);
		
		Scene scene = new Scene(pane, 400, 450);
		primaryStage.setTitle("Spotify Playlist Sorter");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		Controller.setup(pane);
		Playlist userPlaylist = Controller.requestUserInput();
		/*
	    List<PlaylistTrack> trackList = getTracklist(userPlaylist);
	    List<SortableAlbum> albums = SortableAlbum.getAlbumList(trackList);
	    Collections.sort(albums);
	    
	    */
	    
	    
	}

	   public static List<PlaylistTrack> getTracklist(Playlist userPlaylist) {
		   List<PlaylistTrack> trackList = new ArrayList<PlaylistTrack>();
		   int trackIndex = 0;
		   
		   
		   
		   // TODO: Investigate the JSON response limits on playlist
		   Paging<PlaylistTrack> trackPages = userPlaylist.getTracks();
		   
		   while(trackIndex < trackPages.getTotal()) {
			   PlaylistTrack[] tempTrackList = trackPages.getItems();
			   trackList.addAll(Arrays.asList(tempTrackList));
			   trackIndex = trackIndex + trackPages.getLimit();
			   	try {
				   trackPages = request.getSpotifyApi()
			   			.getPlaylistsItems(userPlaylist.getId())
			   			.offset(trackIndex)
			   			.build().execute();
			   	}
			   	catch(Exception e) {
					   break;
				}
			   
		   }
		   
		  // trackList.addAll(Arrays.asList(trackPages.getItems()));
		   return trackList;
		   
	   }
}
