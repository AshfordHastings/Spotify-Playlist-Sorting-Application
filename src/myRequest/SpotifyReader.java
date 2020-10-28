package myRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.model_objects.specification.PlaylistTrack;

public class SpotifyReader {
	
	   public static Playlist pullPlaylist(String playlistId) throws Exception {
		   //changes this value
		    if(request.getClientToken() == null) {
		    	request.getClientAuthorization();
		    } //request.getClientAuthorization();
		   
		   String cutFromInput = new String("spotify:playlist:");
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

		public static List<SortableAlbum> getPlaylistAlbums(Playlist inputPlaylist) {
			List<PlaylistTrack> playlistTracks = SpotifyReader.convertToTracklist(inputPlaylist);
			
			ArrayList<SortableAlbum> albumList = new ArrayList<>();
			for(int i = 0; i < playlistTracks.size(); i++) {
				SortableAlbum albumAtIndex = SortableAlbum.convertFrom(playlistTracks.get(i));
				if(!albumList.contains(albumAtIndex)) {
					albumList.add(albumAtIndex);
				}
			}
			return albumList;
		}
	   
	   public static List<PlaylistTrack> convertToTracklist(Playlist userPlaylist) {
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
