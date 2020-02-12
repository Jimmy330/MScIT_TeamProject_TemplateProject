package commandline;

import java.io.IOException;
import java.util.Scanner;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws Exception {

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection
		
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		TopTrumpModel model=new TopTrumpModel();
		TopTrumpJDBC jdbc=new TopTrumpJDBC(model);
		
		//jdbc.create();
		TopTrumpView view=new TopTrumpView(model);
		
		TestLog log=new TestLog(model,writeGameLogsToFile);
		TopTrumpController controller=new TopTrumpController(model,view,jdbc,log);
		view.setPrintstream(System.out);
		view.setSc(new Scanner(System.in));
		
		while (!userWantsToQuit) {
			
			// ----------------------------------------------------
			// Add your game logic here based on the requirements
			// ----------------------------------------------------
			
			controller.selectMenu();
			// Loop until the user wants to exit the game
			
			userWantsToQuit=true; // use this when the user wants to exit the game
			
		}
		view.closeSc();

	}

}
