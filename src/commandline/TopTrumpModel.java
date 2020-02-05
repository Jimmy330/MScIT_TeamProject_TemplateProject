package commandline;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TopTrumpModel {
	private final static int numOfCards = 40;
	private Card[] gameDeck = new Card[50];// ���п�Ƭ
	private Card[] commonDeck = new Card[50];// ��Ϸƽ��ʱ�����Ĺ�����
	private int cardsInCommonDeck = 0;
	private Player[] player = new Player[5];
	private int numOfDraws = 0;
	private int numOfRounds = 0;// �غ���
	private Card winnerCard;
	private int indexOfCategory;
	private int selector;
	private String testLog = "";
	private FileWriter writer;
	private File testLogFile =new File("testlog.log");
	private Boolean writeLogsToFile=false;

	public TopTrumpModel(Boolean writeLogsToFile) throws IOException {
		this.writeLogsToFile=writeLogsToFile;
		if(writeLogsToFile) {
		testLogFile = new File("testlog.log");
		testLogFile.createNewFile();}

		File deckFile = new File("StarCitizenDeck.txt");
		loadDeck(deckFile);

	}
	
	public void shuffle(Card[] deck,int size) {
		Random rd=new Random();
		for(int i=0;i<100;i++) {
			int i1=rd.nextInt(size);
			int i2=rd.nextInt(size);
			Card temp=deck[i1];
			deck[i1]=deck[i2];
			deck[i2]=temp;
		}
	}
	
	public void drawPhase() throws IOException {
		for (int i = 0; i < 5; i++) {
			if (player[i].isAlive()) {
				player[i].drawCard();
				commonDeck[cardsInCommonDeck++] = player[i].getHand();
			}
		}

		// log current cards in play
		if(writeLogsToFile) {
		String currentCard = "Curret cards in play\n";

		for (int i = 0; i < player.length; i++) {
			if(player[i].isAlive()) {
			currentCard += player[i].getName() + "\n";
			String[] firstline = Card.getCategoryName();

			for (int n = 0; n < 6; n++) {
				currentCard += firstline[n] + " ";
			}
			currentCard += "\n";

			String str = "";
			str += player[i].getHand().getName() + Arrays.toString(player[i].getHand().getCategory()) + "\n";
			currentCard += str + "\n";
			}
		}
		writeLog(currentCard);

		// common deck log
		String commonDeckStr = "Draw cards to common deck\n";
		String[] firstline = Card.getCategoryName();
		for (int i = 0; i < 6; i++) {
			commonDeckStr += firstline[i] + " ";
		}
		commonDeckStr += "\n";
		for (int i = 0; i < cardsInCommonDeck; i++) {
			String name = commonDeck[i].getName();
			int[] category = commonDeck[i].getCategory();
			commonDeckStr += name + " ";
			for (int j = 1; j < 6; j++) {
				commonDeckStr += category[j] + " ";
			}
			commonDeckStr += "\n";
		}
		writeLog(commonDeckStr);}
	}

	public int selectPhase() throws IOException {
		if (numOfRounds == 1) {
			Random rd = new Random();
			int n = rd.nextInt(5);
			selector = n;
		}

		if (selector == 0)
			return -1;
		int categoryNo = player[selector].getGreatCate();

		/*
		 * log the selector, selected category and value (human player's log code will
		 * be in controller.class)
		 */
		
		if(writeLogsToFile) {

		String selectionLog = player[selector].getName();
		
		selectionLog += " selected " + Card.getCategoryName()[categoryNo] + " "
				+ player[selector].getHand().getCategory()[categoryNo];
		writeLog(selectionLog);}

		return (categoryNo);

	}

	public void judgePhase() throws IOException {
		int winner = roundWinner();
		if (winner > -1) {
			player[winner].win();
			shuffle(commonDeck,cardsInCommonDeck);
			for (int i = 0; i < cardsInCommonDeck; i++) {
				player[winner].gainCard(commonDeck[i]);
			}
			cardsInCommonDeck = 0;
			selector = winner;
		}else {
			numOfDraws++;
		}

		// check the remaining cards in communal pile
		if(writeLogsToFile) {
		String commonDeckStr = "Remaining cards in common deck\n";
		String[] headLine = Card.getCategoryName();
		for (int i = 0; i < 6; i++) {
			commonDeckStr += headLine[i] + " ";
		}
		commonDeckStr += "\n";
		for (int i = 0; i < cardsInCommonDeck; i++) {
			String name = commonDeck[i].getName();
			int[] category = commonDeck[i].getCategory();
			commonDeckStr += name + " ";
			for (int j = 1; j < 6; j++) {
				commonDeckStr += category[j] + " ";
			}
			commonDeckStr += "\n";
		}
		writeLog(commonDeckStr);
		
		// log each player's deck when a round is over

		String roundOver = "End of Round " + numOfRounds + "\n";

		for (int i = 0; i < player.length; i++) {
			roundOver += player[i].getName() + "\n";
			String[] firstline = Card.getCategoryName();
			for (int n = 0; n < 6; n++) {
				roundOver += firstline[n] + " ";
			}
			roundOver += "\n";

			String str = "";
			for (int j = 0; j < player[i].getNumOfCards(); j++) {
				str += player[i].getDeck()[j].getName() + Arrays.toString(player[i].getDeck()[j].getCategory()) + "\n";
			}
			roundOver += str + "\n";
		}
		writeLog(roundOver);}

	}

	public Card winnerCard() {
		int[] cate = new int[5];
		int max = 0;
		Card res = null;
		for (int i = 0; i < 5; i++) {
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

	public int roundWinner() {
		int[] cate = new int[5];
		int max = 0;
		int ans = -1;
		for (int i = 0; i < 5; i++) {
			if (!player[i].isAlive())
				continue;
			cate[i] = player[i].getHand().getCategory()[indexOfCategory];
			if (cate[i] > max) {
				max = cate[i];
				ans = i;
				winnerCard = player[i].getHand();
			} else if (cate[i] == max) {
				ans = -1;
			}
		}
		return ans;
	}

	public void loadDeck(File f) {
		try {
			Scanner input = new Scanner(f);
			String firstLine = input.nextLine();
			String[] firstLineSplit = firstLine.split(" ");
			Card.setCategoryName(firstLineSplit);
			int cardNumber = 0;
			while (input.hasNext()) {
				String[] line = input.nextLine().split(" ");
				int[] category = new int[6];
				for (int i = 1; i <= 5; i++) {
					category[i] = Integer.parseInt(line[i]);
				}
				gameDeck[cardNumber++] = new Card(line[0], category);
			}
			shuffle(gameDeck,numOfCards);
			input.close();
			
			//write card load logs
			
			if(writeLogsToFile) {
			String cardDeck = "Load complete card deck\n";
			String[] firstline = Card.getCategoryName();
			for (int i = 0; i < 6; i++) {
				cardDeck += firstline[i] + " ";
			}
			cardDeck += "\n";
			for (int i = 0; i < numOfCards; i++) {
				String name = gameDeck[i].getName();
				int[] category = gameDeck[i].getCategory();
				cardDeck += name + " ";
				for (int j = 1; j < 6; j++) {
					cardDeck += category[j] + " ";
				}
				cardDeck += "\n";
			}
			writeLog(cardDeck);}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadPlayer() throws IOException {
		player[0] = new Player("You", 1);// �������
		for (int i = 1; i <= 4; i++) {
			String name = String.format("AI Player %d", i);
			player[i] = new Player(name, 0);
		} // 4��ai���
		int index = 0;// ����ָ��
		for (int i = 0; i < numOfCards; i++) {
			player[index++].gainCard(gameDeck[i]);
			if (index > 4)
				index = 0;
		}

		// log card deck of each player after cards divided
		if(writeLogsToFile) {
		String playerLoad = "Allocate cards to players\n";

		for (int i = 0; i < player.length; i++) {
			playerLoad += player[i].getName() + "\n";
			String[] firstline = Card.getCategoryName();
			for (int n = 0; n < 6; n++) {
				playerLoad += firstline[n] + " ";
			}
			playerLoad += "\n";

			String str = "";
			for (int j = 0; j < player[i].getNumOfCards(); j++) {
				str += player[i].getDeck()[j].getName() + Arrays.toString(player[i].getDeck()[j].getCategory()) + "\n";
			}
			playerLoad += str + "\n";
		}
		writeLog(playerLoad);}

	}

	public boolean gameIsOver() throws IOException {
		int surviver = 0;
		Player gameWinner = null;
		for (int i = 0; i < 5; i++) {
			if (player[i].isAlive()) {
				surviver++;
				gameWinner=player[i];
			}				
		}
		boolean gameOver = surviver == 1;
		
		//log the winner of the game
		if(writeLogsToFile) {
		if(gameOver) writeLog("Game Winner is "+gameWinner.getName());}
		
		return gameOver;
	}

	public void printDeck() {
		String[] firstline = Card.getCategoryName();
		for (int i = 0; i < 6; i++) {
			System.out.printf("%s\t", firstline[i]);
			testLog += firstline[i] + " ";
		}
		System.out.println();

		testLog = testLog + "\n";
		for (int i = 0; i < numOfCards; i++) {
			String name = gameDeck[i].getName();
			int[] category = gameDeck[i].getCategory();
			System.out.printf("%s\t", name);
			testLog = testLog + name;
			for (int j = 1; j < 6; j++) {
				System.out.printf("%d\t", category[j]);
			}
			System.out.println();
			testLog = testLog + "\n";
			System.out.println(testLog);
		}
	}

	public void writeLog(String str) throws IOException {
		testLog += str + "\n----\n";
		writer = new FileWriter(testLogFile);
		writer.write(testLog);
		writer.flush();
		writer.close();

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

	public Boolean getWriteLogsToFile() {
		return writeLogsToFile;
	}

	public int getNumOfDraws() {
		return numOfDraws;
	}

	public void setNumOfDraws(int numOfDraws) {
		this.numOfDraws = numOfDraws;
	}
	
}
