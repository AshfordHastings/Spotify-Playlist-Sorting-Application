package myRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.*;

import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;

public class Main extends Application {
	
	private static String playlistId;
	
	public static final Scanner kb = new Scanner(System.in);
	
	public static void main(String args[]) {
		
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) {
		Color spotifyGreen = new Color(0, 0, 1, 1.0).rgb(30,215,96);
		Background spotifyBackground = new Background(new BackgroundFill(spotifyGreen, CornerRadii.EMPTY, Insets.EMPTY));
		Label welcomeLabel1 = new Label("Welcome to the Playlist Sorter.");
		Label welcomeLabel2 = new Label("This application will output the albums contained in a Spotify playlist to the console, sorted by date.");
		Label userPromptLabel = new Label("Please enter your playlist ID:");
		TextField inputField = new TextField();
		Button nextButton = new Button("next");
				
		StackPane pane = new StackPane();
		pane.setPadding(new Insets(11, 12, 13, 14));
		pane.setBackground(spotifyBackground);
		pane.getChildren().addAll(welcomeLabel1, welcomeLabel2, userPromptLabel, inputField, nextButton);
		
		Scene scene = new Scene(pane, 400, 450);
		primaryStage.setTitle("Spotify Playlist Sorter");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		  
		Button button = new Button("Enter");   
		
		
	    /* Scanner connected to keyboard input, or input file */
	    
	    /* Establish connection with Spotify client */
	    if(request.getClientToken() == null) {
	    	request.getClientAuthorization();
	    }
	    
	    /* Set access token for Spotify API */
	    request.setUserToken(request.getClientToken());
	    
	    /* Get input for playlistId */
	    System.out.println("Welcome to the Playlist Sorter.");
	    System.out.println("This application will output the albums contained in a Spotify playlist to the console, sorted by date.");
	    while(true) {
	    /* Retrieves and prints a playlist ordered by date */
	    Playlist userPlaylist = getUserPlaylist();
	    List<PlaylistTrack> trackList = getTracklist(userPlaylist);
	    List<SortableAlbum> albums = SortableAlbum.getAlbumList(trackList);
	    Collections.sort(albums);
	    
	    for(int i = 0; i < albums.size(); i++) {
	    	System.out.println(albums.get(i));
	    }
	    System.out.println('\n' + "Total albums: " + albums.size());
	    }
	    
	}
	
	   public static Playlist getUserPlaylist() {
		   String cutFromInput = new String("spotify:playlist:");
		   System.out.println();
		   while(true) {
			   System.out.println('\n' + "Please enter your playlist ID:");
			   playlistId = kb.nextLine();
			   if(playlistId.substring(0, cutFromInput.length()).equals(cutFromInput)) {
		    		playlistId = playlistId.substring(cutFromInput.length());
		    	}
			   
		    try {
		    	return request.getSpotifyPlaylist(playlistId);
		    }
		    catch (Exception e){
		    	System.out.println("Error encountered while retrieving user playlist. Please try again.");
		    }
		  }
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
			   			.getPlaylistsItems(playlistId)
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
