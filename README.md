# Spotify-Playlist-Sorting-Application
In progress of building developing an application capable of sorting Spotify playlists by various criteria. 

### Progress: 

10/25: Created a Java program able to:
  - Receive a playlist URI through the console
  - Retrieve the playlist's information using Spotify's API, and 
  - Output the albums's that make up the playlist to the console, sorted chronologically by year released.

10/27: 
  - Implemented a basic JavaFX user interface that comes with: 
    - a button to trigger an event to get the user playlist
    - a text input for playlist
    - a scrolling text output 
  - Separated Controller and View functioniality through classes.
  
 ### To-Do: 
  1. Allow ability to sort either tracks or albums on Playlist, or sort by popularity.
    - Implement ComboBox to choose different things
    - Change sortBy method
    - Decide how to sort albums by popularity
    
  2. Make GUI a lot cleaner
    - Add the Spotify logo 
    - Separate everything 
  3. Figure out how to connect to user accounts 
