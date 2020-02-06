package commandline;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class TestLog {
	private TopTrumpModel model;
	private FileWriter writer;
	private File testLogFile;
	private String testLog = "";
	private boolean writeLogToFile;

	public TestLog(TopTrumpModel model,boolean writeLogToFile) throws IOException {
		
		if(writeLogToFile) {
		this.model = model;
		testLogFile = new File("toptrumps.log");
		testLogFile.createNewFile();}
		this.writeLogToFile=writeLogToFile;

	}

	public void loadCardLog() throws IOException {
		if(writeLogToFile) {
		String cardDeck = "Load complete card deck\n";
		String[] firstline = Card.getCategoryName();
		for (int i = 0; i < 6; i++) {
			cardDeck += firstline[i] + " ";
		}
		cardDeck += "\n";
		for (int i = 0; i < TopTrumpModel.getNumofcards(); i++) {
			String name = model.getGameDeck()[i].getName();
			int[] category = model.getGameDeck()[i].getCategory();
			cardDeck += name + " ";
			for (int j = 1; j < 6; j++) {
				cardDeck += category[j] + " ";
			}
			cardDeck += "\n";
		}

		writeLog(cardDeck);}

	}

	// log card deck of each player after cards divided
	public void loadPlayerCardLog() {
		
		if(writeLogToFile) {

		String playerLoad = "Each player's card deck\n";

		for (int i = 0; i < model.getPlayer().length; i++) {
			playerLoad += model.getPlayer()[i].getName() + "\n";
			String[] firstline = Card.getCategoryName();
			for (int n = 0; n < 6; n++) {
				playerLoad += firstline[n] + " ";
			}
			playerLoad += "\n";

			String str = "";
			for (int j = 0; j < model.getPlayer()[i].getNumOfCards(); j++) {
				str += model.getPlayer()[i].getDeck()[j].getName()
						+ Arrays.toString(model.getPlayer()[i].getDeck()[j].getCategory()) + "\n";
			}
			playerLoad += str + "\n";
		}
		try {
			writeLog(playerLoad);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

	// log current cards in play
	public void currentCardLog() throws IOException {
		if(writeLogToFile) {

		String currentCard = "Round "+model.getNumOfRounds()+" Current cards in play\n";

		for (int i = 0; i < model.getPlayer().length; i++) {
			if (model.getPlayer()[i].isAlive()) {
				currentCard += model.getPlayer()[i].getName() + "\n";
				String[] firstline = Card.getCategoryName();

				for (int n = 0; n < 6; n++) {
					currentCard += firstline[n] + " ";
				}
				currentCard += "\n";

				String str = "";
				str += model.getPlayer()[i].getHand().getName()
						+ Arrays.toString(model.getPlayer()[i].getHand().getCategory()) + "\n";
				currentCard += str + "\n";
			}
		}
		writeLog(currentCard);
		}

	}
	
	
	/*
	 * log the selector, selected category and value (human player's log code will
	 * be in controller.class)
	 */
	public void selectionLog(int categoryNo) {
	
		if(writeLogToFile) {
		String selectionLog = model.getPlayer()[model.getSelector()].getName();
		
		selectionLog += " selected " + Card.getCategoryName()[categoryNo] + " "
				+ model.getPlayer()[model.getSelector()].getHand().getCategory()[categoryNo];
		try {
			writeLog(selectionLog);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}}

	// check the remaining cards in communal pile
	public void communalPileLog() {
		if(writeLogToFile) {
		String commonDeckStr = "Remaining cards in common deck\n";
		String[] headLine = Card.getCategoryName();
		for (int i = 0; i < 6; i++) {
			commonDeckStr += headLine[i] + " ";
		}
		commonDeckStr += "\n";
		for (int i = 0; i < model.getCardsInCommonDeck(); i++) {
			String name = model.getCommonDeck()[i].getName();
			int[] category = model.getCommonDeck()[i].getCategory();
			commonDeckStr += name + " ";
			for (int j = 1; j < 6; j++) {
				commonDeckStr += category[j] + " ";
			}
			commonDeckStr += "\n";
		}
		try {
			writeLog(commonDeckStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}

	

	// log the winner of the game
	public void gameWinnerLog() {
		if(writeLogToFile) {
		try {
			writeLog("Game Winner is " + model.getGameWinner().getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	}

	// log format helper method
	public void writeLog(String str) throws IOException {
		
		testLog += str + "\n-----------------------------------------\n";
		writer = new FileWriter(testLogFile);
		writer.write(testLog);
		writer.flush();
		writer.close();

	}

}
