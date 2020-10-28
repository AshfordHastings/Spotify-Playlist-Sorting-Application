package myRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.wrapper.spotify.model_objects.specification.Playlist;

import javafx.scene.control.Button;

public class Controller {
	
	private static Playlist userPlaylist = null;
	
	public static Button playlistInputButton = new Button("next");
	
	public static void requestUserInput() {
		
		View.showUserOptions(playlistInputButton);
		
		playlistInputButton.setOnAction(event -> {
			try {
				String playlistId = View.getUserTextInput();
				userPlaylist = SpotifyReader.pullPlaylist(playlistId);
			    List<SortableAlbum> albums = SpotifyReader.getPlaylistAlbums(userPlaylist);
			    Collections.sort(albums);
			    View.outputList(albums);
			    
			} catch (Exception e){
				View.outputErrorMessage();
			}
	});
	}
	
	public static Playlist returnUserPlaylist() {
		return userPlaylist;
	}
	
	
}