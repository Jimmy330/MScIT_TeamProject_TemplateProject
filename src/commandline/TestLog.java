package commandline;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestLog {
	private TopTrumpModel model;
	private FileWriter writer;
	private File testLogFile;
	private String testLog = "";
	private boolean writeLogToFile;

	public TestLog(TopTrumpModel model, boolean writeLogToFile) throws IOException {

		if (writeLogToFile) {
			this.model = model;
			testLogFile = new File("toptrumps.log");
			testLogFile.createNewFile();
		}
		this.writeLogToFile = writeLogToFile;

	}

	// print the whole card information 
	public void loadCardLog() {
		if (writeLogToFile) {
			String cardDeck = "Load complete card deck\n";

			cardDeck += printHead();

			for (int i = 0; i < TopTrumpModel.getNumofcards(); i++) {
				String cardName = model.getGameDeck()[i].getName();
				int[] category = model.getGameDeck()[i].getCategory();

				cardDeck += printCard(cardName, category);
			}
			writeLog(cardDeck);
		}

	}


	// log card deck of each player after cards divided
	public void loadPlayerCardLog() {

		if (writeLogToFile) {

			String playerLoad = "Each player's card deck\n";

			for (int i = 0; i < model.getPlayer().length; i++) {
				playerLoad += model.getPlayer()[i].getName() + "\n";
				playerLoad += printHead();

				for (int j = 0; j < model.getPlayer()[i].getNumOfCards(); j++) {
					String cardName = model.getPlayer()[i].getDeck()[j].getName();
					int[] category = model.getPlayer()[i].getDeck()[j].getCategory();
					playerLoad += printCard(cardName, category);
				}
				playerLoad += "\n";
			}
			writeLog(playerLoad);
		}
	}

	// log current cards in play
	public void currentCardLog() {
		if (writeLogToFile) {

			String currentCard = "Round " + model.getNumOfRounds() + " Current cards in play\n";

			for (int i = 0; i < model.getPlayer().length; i++) {
				if (model.getPlayer()[i].isAlive()) {
					currentCard += model.getPlayer()[i].getName() + "\n";
					currentCard += printHead();

					String cardName = model.getPlayer()[i].getHand().getName();
					int[] category = model.getPlayer()[i].getHand().getCategory();
					currentCard += printCard(cardName, category) + "\n";

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

		if (writeLogToFile) {
			String selectionLog = model.getPlayer()[model.getSelector()].getName();

			selectionLog += " selected " + Card.getCategoryName()[categoryNo] + " "
					+ model.getPlayer()[model.getSelector()].getHand().getCategory()[categoryNo];
			writeLog(selectionLog);
		}
	}

	// check the remaining cards in communal pile
	public void communalPileLog() {
		if (writeLogToFile) {
			String commonDeckStr = "Remaining cards in common deck\n";
			commonDeckStr += printHead();
			
			for (int i = 0; i < model.getCardsInCommonDeck(); i++) {
				String cardName = model.getCommonDeck()[i].getName();
				int[] category = model.getCommonDeck()[i].getCategory();
				commonDeckStr+=printCard(cardName, category);
			}
			writeLog(commonDeckStr);
		}
	}

	// log the winner of the game
	public void gameWinnerLog() {
		if (writeLogToFile) {
			writeLog("Game Winner is " + model.getGameWinner().getName());
		}
	}

	// log format helper method
	public void writeLog(String str) {
		testLog += str + "\n-----------------------------------------\n";
		try {
			writer = new FileWriter(testLogFile);
			writer.write(testLog);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	//helper method to print the header of card information
	public String printHead() {
		String head = "";
		String[] firstline = Card.getCategoryName();
		for (int i = 0; i < 6; i++) {
			head += firstline[i] + " ";
		}
		return head + "\n";
	}

	//helper method to print card name and category values
	public String printCard(String name, int[] category) {
		String card = "";
		card += name + " ";
		for (int i = 1; i < 6; i++) {
			card += category[i] + " ";
		}
		return card + "\n";

	}

}
