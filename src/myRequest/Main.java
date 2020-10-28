package myRequest;



import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static void main(String args[]) {
		launch(args);	
	}
	public void start (Stage primaryStage) { 
	    /* Scanner connected to keyboard input, or input file */
	    
	    /* Establish connection with Spotify client */
	    if(request.getClientToken() == null) {
	    	request.getClientAuthorization();
	    } request.setUserToken(request.getClientToken());
   
	    View.showHomeScreen(primaryStage);
	    Controller.requestUserInput();
	}
}
