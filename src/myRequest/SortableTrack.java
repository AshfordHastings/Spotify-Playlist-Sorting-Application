package myRequest;

import com.wrapper.spotify.model_objects.specification.PlaylistTrack;
import com.wrapper.spotify.model_objects.specification.Track;

public class SortableTrack extends SortableAlbum{
	private Track track;
	
	SortableTrack(PlaylistTrack playlistTrack) {
		super(playlistTrack);
		track = (Track)playlistTrack.getTrack();
	}
	
	@Override
	public String toString() {
		return new String("'" + track.getName() + "' by " + track.getArtists()[0].getName() + ", released in " + releaseDate);
	}
	
	public static SortableTrack convertToSortableTrack(PlaylistTrack track) {
		return new SortableTrack(track);
	}
}
