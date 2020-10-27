package myRequest;

import com.wrapper.spotify.model_objects.specification.Playlist;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

public class Controller {
	final private static Label userPromptLabel = new Label("Please enter your playlist ID:");
	final private static Label errorLabel = new Label("Error encountered while retrieving user playlist. Please try again.");
	final private static Button inputReadButton = new Button("next");
	final private static TextField inputField = new TextField();
	
	private static FlowPane pane;
	private static Playlist userPlaylist;
	
	public static void setup(FlowPane pane) {
		Controller.pane = pane;
	}
	
	public static Playlist requestUserInput() {	
		pane.getChildren().addAll(userPromptLabel, inputReadButton, inputField);
		
		userPlaylist = null;
		inputReadButton.setOnAction(event -> {
			try {
				userPlaylist = getUserPlaylist();
			} catch (Exception e){
				pane.getChildren().add(errorLabel);
			}
	});
		
		
/*
		while(userPlaylist == null) {
		inputReadButton.setOnAction(event -> {
				try {
					userPlaylist = getUserPlaylist();
				} catch (Exception e){
					pane.getChildren().add(errorLabel);
				}
		});
		}
		
		*/
		
		return userPlaylist;
	}
	
	   public static Playlist getUserPlaylist() throws Exception {
		    if(request.getClientToken() == null) {
		    	request.getClientAuthorization();
		    } request.getClientAuthorization();
		   
		   String cutFromInput = new String("spotify:playlist:");
			   String playlistId  = inputField.getText();
			   if(playlistId.substring(0, cutFromInput.length()).equals(cutFromInput)) {
		    		playlistId = playlistId.substring(cutFromInput.length());
		    	}
			   
		    try {
		    	return request.getSpotifyPlaylist(playlistId);
		    }
		    catch (Exception e){
		    	throw new Exception("PlaylistInputError");
		    }
	   }
}
