package commandline;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TopTrumpView {
	private TopTrumpModel modelObject;
	private PrintStream printstream=System.out;
	private Scanner sc;
	public void setPrintstream(PrintStream printstream) {
		this.printstream = printstream;
	}
	
	public void setSc(Scanner sc) {
		this.sc = sc;
	}
	
	public void closeSc() {
		sc.close();
	}

	public TopTrumpView(TopTrumpModel model) {
		modelObject=model;
	}
	
	public int scanInt() {
		int res=sc.nextInt();
		//sc.close();
		return res;
	}	
	
	public void printTitle() {
		printstream.println("--------------------\n" + 
						   "--- Top Trumps   ---\n" + 
						   "--------------------");
	}
	
	public void printSelectMenu() {
		printstream.print("Do you want to see past results or play a game?\n" + 
				"   1: Print Game Statistics\n" + 
				"   2: Play game\n" + 
				"Enter the number for your selection:");
	}
	
	public void printGameStart() {
		printstream.println("Game Start");
	}
	
	public void printRoundTitle() {
		printstream.printf("Round %d\n",modelObject.getNumOfRounds());
		printstream.printf("Round %d: Players have drawn their cards\n",modelObject.getNumOfRounds());
	}
	
	public void printDrawCard() {
		printstream.printf("You drew '%s':\r\n",modelObject.getHumanPlayer().getHand().getName());
		for(int i=1;i<=5;i++) {
			printstream.printf("   > %s: %d\r\n", Card.getCategoryName()[i], modelObject.getHumanPlayer().getHand().getCategory()[i]);
		}
		printstream.printf("There are '%d cards in your deck\n", modelObject.getHumanPlayer().getNumOfCards());
	}
	
	public void printSelectCategory() {
		printstream.println("It is your turn to select a category, the categories are:");
		for(int i=1;i<=5;i++) {
			printstream.printf("   %d: %s\r\n", i, Card.getCategoryName()[i]);
		}
		printstream.println("Enter the number for your attribute: ");
	}
	
	public void printResult() {
		if(modelObject.roundWinner()==-1) {
			printDraw();
		}else {
			printWinner();
			printWinnerCard();
		}
		
	}
	
	public void printDraw() {
		printstream.printf("Round %d: This round was a Draw, common pile now has %d cards\n",modelObject.getNumOfRounds(),modelObject.getCardsInCommonDeck());
	}
	
	public void printWinner() {
		int indexOfWinner=modelObject.roundWinner();
		printstream.printf("Round %d: Player %s won this round\n", modelObject.getNumOfRounds(),modelObject.getPlayer()[indexOfWinner].getName());
	}
	
	public void printWinnerCard() {
		printstream.printf("The winning card was '%s':\n", modelObject.getWinnerCard().getName());
		for(int i=1;i<=5;i++) {
			printstream.printf("   > %s: %d ", Card.getCategoryName()[i], modelObject.getWinnerCard().getCategory()[i]);
			if(i==modelObject.getIndexOfCategory()) {
				printstream.print("<--");
			}
			printstream.println();
		}
	}

	public void printGameStatus(int numofgame, int numofhumanwin, int numofaiwin, double avedraw, int largeround) {
		printstream.printf("Game Statistics:\r\n" + 
				"   Number of Games: %d\r\n" + 
				"   Number of Human Wins: %d\r\n" + 
				"   Number of AI Wins: %d\r\n" + 
				"   Average number of Draws: %.3f\r\n" + 
				"   Longest Game: %d",numofgame,numofhumanwin,numofaiwin,avedraw,largeround);
	}
}
