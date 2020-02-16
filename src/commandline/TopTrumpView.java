package commandline;

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
	/*
	 * set the io objects above
	 */

	public TopTrumpView(TopTrumpModel model) {//constructor, assign the view with model 
		modelObject=model;
	}
	
	public int scanInt() {// get a int from command line, used to get users command
		int res=sc.nextInt();
		//sc.close();
		return res;
	}	
	
	public void printcontinue() {//ask user if he wants to quit
		printstream.print("\n\nDo you want to quit?\n" + 
				"	0: quit\n" + 
				"	other numbers: continue\n" + 
				"Enter the number for your selection:");
	}
	public void printSelectMenu() {//ask for users' command
		printstream.print("\n\nDo you want to see past results or play a game?\n" + 
				"   1: Print Game Statistics\n" + 
				"   2: Play game\n" + 
				"Enter the number for your selection:");
	}
	
	public void printGameStart() {
		printstream.println("Game Start");
	}
	
	public void printRoundTitle() {//print each round's title
		printstream.printf("Round %d\n",modelObject.getNumOfRounds());
		printstream.printf("Round %d: Players have drawn their cards\n",modelObject.getNumOfRounds());
	}
	
	public void printDrawCard() {//print human players hand
		printstream.printf("You drew '%s':\r\n",modelObject.getHumanPlayer().getHand().getName());
		for(int i=1;i<=5;i++) {
			printstream.printf("   > %s: %d\r\n", Card.getCategoryName()[i], modelObject.getHumanPlayer().getHand().getCategory()[i]);
		}
		printstream.printf("There are '%d cards in your deck\n", modelObject.getHumanPlayer().getNumOfCards());
	}
	
	public void printSelectCategory() {//ask human player for index of category 
		printstream.println("It is your turn to select a category, the categories are:");
		for(int i=1;i<=5;i++) {
			printstream.printf("   %d: %s\r\n", i, Card.getCategoryName()[i]);
		}
		printstream.println("Enter the number for your attribute: ");
	}
	
	public void printResult() {//print result of each round
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
	
	public void printWinnerCard() {//print the winner's card
		printstream.printf("The winning card was '%s':\n", modelObject.getWinnerCard().getName());
		for(int i=1;i<=5;i++) {
			printstream.printf("   > %s: %d ", Card.getCategoryName()[i], modelObject.getWinnerCard().getCategory()[i]);
			if(i==modelObject.getIndexOfCategory()) {
				printstream.print("<--");
			}
			printstream.println();
		}
	}
	
	public void printGameEnd() {// print result of a game
		int[] s=new int[5];
		for(int i=0;i<5;i++) {
			s[i]=modelObject.getPlayer()[i].getRoundWin();
		}
		String name=modelObject.getGameWinner().getName();
		printstream.printf("Game End\r\n" + 
				"\r\n" + 
				"	The overall winnder was %s\r\n" + 
				"	Scores:\r\n" + 
				"	   AI Player 1: %d\r\n" + 
				"	   AI Player 2: %d\r\n" + 
				"	   AI Player 3: %d\r\n"+ 
				"	   AI Player 4: %d\r\n" + 
				"	   You: %d\r\n" 
				, name,s[1],s[2],s[3],s[4],s[0]);
	}
	//print game status if user asks
	public void printGameStatus(int numofgame, int numofhumanwin, int numofaiwin, double avedraw, int largeround) {
		printstream.printf("Game Statistics:\r\n" + 
				"   Number of Games: %d\r\n" + 
				"   Number of Human Wins: %d\r\n" + 
				"   Number of AI Wins: %d\r\n" + 
				"   Average number of Draws: %.3f\r\n" + 
				"   Longest Game: %d",numofgame,numofhumanwin,numofaiwin,avedraw,largeround);
	}
}
