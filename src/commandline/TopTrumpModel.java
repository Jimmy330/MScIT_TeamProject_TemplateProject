package commandline;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class TopTrumpModel {
	private final static int numOfCards = 40;
	private Card[] gameDeck = new Card[50];//all the card involved in the game 
	private Card[] commonDeck = new Card[50];//common deck when round draws
	private int cardsInCommonDeck = 0;// counter of the cards in common deck
	private int playerNum=5;
	private Player[] player = new Player[playerNum];//players
	private int numOfDraws = 0;
	private int numOfRounds = 0;
	private Card winnerCard;//store the winner card of certain round
	private int indexOfCategory;//the category selected this round
	private int selector;
	private Player gameWinner;


	public TopTrumpModel() {

		 File deckFile = new File("Deck.txt");
		 loadDeck(deckFile);

	}
	
	public void shuffle(Card[] deck,int size) {//shuffle the deck,can be used to shuffle game deck or common deck
		if(size<=1) return;
		Random rd=new Random();
		for(int i=0;i<100;i++) {
			int i1=rd.nextInt(size);
			int i2=rd.nextInt(size);
			Card temp=deck[i1];
			deck[i1]=deck[i2];
			deck[i2]=temp;
		}
	}
	
	public void drawPhase(){// each player draw their card
		for (int i = 0; i < playerNum; i++) {
			if (player[i].isAlive()) {
				player[i].drawCard();
			}
		}

	}

	public int selectPhase() {//selector select the category to compete
		 if (numOfRounds == 1) {
		 	Random rd = new Random();
		 	int n = rd.nextInt(playerNum);
		 	selector = n;
		 }

		if (selector == 0)
			return -1;//return -1 when selector is human, when game controller get -1,controller will ask human player for category
		return player[selector].getGreatCate();//if the selector is ai, return selector's biggest category

	}

	public void judgePhase() {
		
		// round winner gains cards
		int winner = roundWinner();
		
		if (winner > -1) {//there is a winner
			player[winner].win();//increase the winner's roundwin counter
			shuffle(commonDeck,cardsInCommonDeck);
			for (int i = 0; i < cardsInCommonDeck; i++) {
				player[winner].gainCard(commonDeck[i]);
			}//shuffle the common deck then put commondeck into winner's deck
			for (int i = 0; i < playerNum; i++) {
				if (player[i].isAlive()) {
					player[winner].gainCard(player[i].getHand());
				}
			}//winner gets others hand
			cardsInCommonDeck = 0;
			selector = winner;
		}else {//draw
			for (int i = 0; i < playerNum; i++) {
				if (player[i].isAlive()) {
					commonDeck[cardsInCommonDeck++] = player[i].getHand();
				}
			}//cards go to common deck
			numOfDraws++;
		}
		for(int i =0;i<player.length;i++){
			if(player[i].getNumOfCards()==0) player[i].setAlive(false);
		}
	}

	public Card winnerCard() {// return the winner cards
		int[] cate = new int[5];
		int max = 0;
		Card res = null;
		for (int i = 0; i < playerNum; i++) {
			if (!player[i].isAlive())
				continue;
			cate[i] = player[i].getHand().getCategory()[indexOfCategory];
			if (cate[i] > max) {
				max = cate[i];
				res = player[i].getHand();
			}
		}

		return res;
	}

	public int roundWinner() {//return the index of round winner, return -1 if round draws
		int[] categoryValue = new int[5];
		int maxValue = 0;
		int winner = -1;
		for (int i = 0; i < playerNum; i++) {
			if (!player[i].isAlive()){
				continue;
			}//skip losers
			categoryValue[i] = player[i].getHand().getCategory()[indexOfCategory];
			if (categoryValue[i] > maxValue) {
				maxValue = categoryValue[i];
				winner = i;
				winnerCard = player[i].getHand();//if player i get bigger cate value, he wins
			} else if (categoryValue[i] == maxValue) {//if there are two max value, this round draws
				winner = -1;
			}
		}
		return winner;
	}

	public void loadDeck(File f) {//load deck info from File f
		try {
			Scanner input = new Scanner(f);
			String firstLine = input.nextLine();
			String[] firstLineSplit = firstLine.split(" ");
			Card.setCategoryName(firstLineSplit);//get name of categories
			int cardNumber = 0;
			while (input.hasNext()) {
				String[] line = input.nextLine().split(" ");
				int[] category = new int[6];
				for (int i = 1; i <= 5; i++) {
					category[i] = Integer.parseInt(line[i]);
				}
				gameDeck[cardNumber++] = new Card(line[0], category);//load data of one card every loop
			}

			input.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadPlayer(){
		player[0] = new Player("You", 1);// human player
		for (int i = 1; i < playerNum; i++) {
			String name = String.format("AI Player %d", i);
			player[i] = new Player(name, 0);
		} //ai players
		int index = 0;
		for (int i = 0; i < numOfCards; i++) {
			player[index++].gainCard(gameDeck[i]);
			if (index > playerNum-1)
				index = 0;
		}//divide the cards to players
	}

	public boolean gameIsOver() {//return true when only one player survives
		int surviver = 0;
		
		for (int i = 0; i < playerNum; i++) {
			if (player[i].isAlive()) {
				surviver++;
				gameWinner=player[i];//store the winner
			}				
		}
		if(surviver == 1||surviver == 0){
			return true;
		}
		return false;
	}
	



	public void printDeck() {
		String[] firstline = Card.getCategoryName();
		for (int i = 0; i < 6; i++) {
			System.out.printf("%s\t", firstline[i]);
		}
		System.out.println();

	}//it is a test method
	
	public void setPlayerNum(int num) {
		this.playerNum=num;
	}


	public int getIndexOfCategory() {
		return indexOfCategory;
	}

	public void setIndexOfCategory(int indexOfCategory) {
		this.indexOfCategory = indexOfCategory;
	}

	public Card getWinnerCard() {
		return winnerCard;
	}

	public int getCardsInCommonDeck() {
		return cardsInCommonDeck;
	}

	public int getNumOfRounds() {
		return numOfRounds;
	}

	public Player getHumanPlayer() {
		return player[0];
	}

	public Player[] getPlayer() {
		return player;
	}

	public void setNumOfRounds(int numOfRounds) {
		this.numOfRounds = numOfRounds;
	}

	public int getSelector() {
		return selector;
	}

	public void setSelector(int selector) {
		this.selector = selector;
	}


	public int getNumOfDraws() {
		return numOfDraws;
	}

	public void setNumOfDraws(int numOfDraws) {
		this.numOfDraws = numOfDraws;
	}

	public static int getNumofcards() {
		return numOfCards;
	}

	public Card[] getGameDeck() {
		return gameDeck;
	}

	public Card[] getCommonDeck() {
		return commonDeck;
	}

	public Player getGameWinner() {	
		return gameWinner;
	}
	
	
	
}
