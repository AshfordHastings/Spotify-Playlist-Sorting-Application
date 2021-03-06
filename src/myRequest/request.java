package myRequest;

import java.net.URI;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.model_objects.AbstractModelObject;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.model_objects.specification.Album;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Playlist;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import com.wrapper.spotify.requests.data.albums.GetAlbumRequest;
import com.wrapper.spotify.requests.data.playlists.GetPlaylistRequest;

public class request {
	
	private final static String clientID = "e7e24983cfe34bdead4811e9bf763aae";
	private final static String clientSecret = "f3cb685d007a48f0a0a055da6ed23c53";
	private final static URI redirectURI = SpotifyHttpManager.makeUri("https://en.wikipedia.org/wiki/Throbbing_Gristle");
	
	private final static SpotifyApi spotifyApi = new SpotifyApi.Builder()
	.setClientId(clientID)
	.setClientSecret(clientSecret)
	.build();
	
	private static String clientAccessToken = null;
	private static String userAccessToken;
	private static String userRefreshToken;
	
	/**	Obtains authorization through the Client Credentials Flow.
	 * Cannot access user resources or refresh tokens, but does require secret key.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		System.out.println("Hello world!");
	}
	
	public static void getClientAuthorization() {
		
		
		//Gets an access token from Spotify
		 ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
				    .build();
		    try {
		    	// Sets access token for client credentials 
		        ClientCredentials clientCredentials = clientCredentialsRequest.execute();
		        clientAccessToken = clientCredentials.getAccessToken();
		    	System.out.println("Authorization through Client Credentials successful.");
		        System.out.println("Expires in: " + clientCredentials.getExpiresIn());
		      } catch (Exception e) {
		    	 System.out.println(e.toString());
		      }
	}
	
	public static void getUserAuthorization() {
		//Gets an access token from Spotify
		SpotifyApi spotifyApi = new SpotifyApi.Builder()
		.setClientId(clientID)
		.setClientSecret(clientSecret)
		.setRedirectUri(redirectURI)
		.build();
		
		AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode("")
				.build();
		
		try {
			AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();
			
		      // Set access and refresh token for further "spotifyApi" object usage
		    userAccessToken = authorizationCodeCredentials.getAccessToken();
		    userRefreshToken = authorizationCodeCredentials.getRefreshToken();
		    spotifyApi.setAccessToken(userAccessToken);
		    spotifyApi.setRefreshToken(userRefreshToken);
		    userAccessToken = authorizationCodeCredentials.getAccessToken();
	    	System.out.println("Authorization through Authorization Code Flow successful.");
	        System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
		}
		catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	public static Playlist getSpotifyPlaylist(String playlistId) throws Exception {
		GetPlaylistRequest playlistRequest = spotifyApi.getPlaylist(playlistId).build();
		try {
			Playlist playlist = playlistRequest.execute();
			return playlist;
		}
		catch (Exception e) {
			e.toString();
			System.out.println("Exception thrown in getPlaylist.");
			throw new Exception("PlaylistException");
		}
		
	}
	
	public static Album getSpotifyAlbum(String albumId) throws Exception {
		GetAlbumRequest albumRequest = spotifyApi.getAlbum(albumId).build();
		try {
			Album album = albumRequest.execute();
			return album;
		}
		catch (Exception e) {
			e.toString();
			System.out.println("Exception thrown in getSpotifyAlbum.");
			throw new Exception("AlbumException");
		}
		
	}
	
	public static SpotifyApi getSpotifyApi() {
		return spotifyApi;
	}
	public static String getClientToken() {
		return clientAccessToken;
	}
	
	public static String getUserToken() {
		return userAccessToken;
	}
	
	public static SpotifyApi setUserToken(String accessToken) {
		spotifyApi.setAccessToken(accessToken);
		return spotifyApi;
	}
}
