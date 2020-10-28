package myRequest;

import java.util.*;

import com.wrapper.spotify.model_objects.specification.*;


public class SortableAlbum implements Comparable<SortableAlbum> {
	private AlbumSimplified album;
	protected ReleaseDate releaseDate;
	
	SortableAlbum(PlaylistTrack playlistTrack) {
		Track track = (Track)playlistTrack.getTrack();
		album = track.getAlbum();
		releaseDate = new ReleaseDate(album.getReleaseDate());
	}
	
	SortableAlbum(Track track) {
		album = track.getAlbum();
	}
	
	SortableAlbum(AlbumSimplified album) {
		this.album = album;
	}
	
	
	public AlbumSimplified getAlbum() {
		return album;
	}
	
	public String getAlbumId() {
		return album.getId();
	}

	@Override
	public String toString() {
		return new String("'" + album.getName() + "' by " + album.getArtists()[0].getName() + ", released in " + releaseDate);
	}
	
	@Override
	public int compareTo(SortableAlbum o) {
		return this.releaseDate.compareTo(o.releaseDate);
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == (SortableAlbum) o) {
			return true;
		}
		
		if (((SortableAlbum) o).getAlbumId().equals(this.getAlbumId())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static SortableAlbum convertFrom(PlaylistTrack track) {
		return new SortableAlbum(track);
	}
	
	public static List<SortableAlbum> getAlbumList(List<PlaylistTrack> playlistTracks) {
		ArrayList<SortableAlbum> albumArray = new ArrayList<>();
		for(int i = 0; i < playlistTracks.size(); i++) {
			SortableAlbum albumAtIndex = convertFrom(playlistTracks.get(i));
			if(!albumArray.contains(albumAtIndex)) {
				albumArray.add(albumAtIndex);
			}
		}
		return albumArray;
	}


}
