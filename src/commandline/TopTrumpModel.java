package commandline;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class TopTrumpModel {
	private final static int numOfCards=40;
	private Card[] gameDeck=new Card[50];//所有卡片
	private Card[] commonDeck=new Card[50];//游戏平局时产生的公共卡
	private int cardsInCommonDeck=0;
	private Player[] player=new Player[5];
	private int numOfGames=0;
	private int numOfHumanWins=0;
	private int numOfAIWins=0;
	private double aveOfdraws=0;
	private int longestGame=0;//询问时所产生的数据
	private int numOfRounds=0;//回合数
	private Card winnerCard;
	private int indexOfCategory;
	private int selector;
	public TopTrumpModel() {
		File deckFile=new File("deck.txt");
		loadDeck(deckFile);
	}
	
	public void drawPhase() {
		for(int i=0;i<5;i++) {
			if(player[i].isAlive()) {
				player[i].drawCard();
				commonDeck[cardsInCommonDeck++]=player[i].getHand();
			}
		}
	}
	
	public int selectPhase() {
		if(selector==0) return -1;
		Random rd=new Random();
		return (rd.nextInt(5)+1);
	}
	
	public void judgePhase() {
		int winner=roundWinner();
		if(winner>-1) {
			for(int i=0;i<cardsInCommonDeck;i++) {
				player[winner].gainCard(commonDeck[i]);
			}
			cardsInCommonDeck=0;
			selector=winner;
		}
		
	}
	
	public Card winnerCard() {
		int[] cate=new int[5];
		int max=0;
		Card res=null;
		for(int i=0;i<5;i++) {
			if(!player[i].isAlive()) continue;
			cate[i]=player[i].getHand().getCategory()[indexOfCategory];
			if(cate[i]>max) {
				max=cate[i];
				res=player[i].getHand();
			}
		}
		return res;
	}
	
	public int roundWinner() {
		int[] cate=new int[5];
		int max=0;
		int ans=-1;
		for(int i=0;i<5;i++) {
			if(!player[i].isAlive()) continue;
			cate[i]=player[i].getHand().getCategory()[indexOfCategory];
			if(cate[i]>max) {
				max=cate[i];
				ans=i;
				winnerCard=player[i].getHand();
			}else
			if(cate[i]==max) {
				ans=-1;
			}
		}
		return ans;
	}
	
	public void loadDeck(File f) {
		try {
			Scanner input=new Scanner(f);
			String firstLine=input.nextLine();
			String[] firstLineSplit=firstLine.split(" ");
			Card.setCategoryName(firstLineSplit);
			int cardNumber=0;
			while(input.hasNext()) {
				String[] line=input.nextLine().split(" ");
				int[] category=new int[6];
				for(int i=1;i<=5;i++) {
					category[i]=Integer.parseInt(line[i]);
				}
				gameDeck[cardNumber++]=new Card(line[0],category);
			}
			input.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadPlayer() {
		player[0]=new Player("You",1);//人类玩家
		for(int i=1;i<=4;i++) {
			String name=String.format("AI Player %d", i);
			player[i]=new Player(name,0);
		}//4个ai玩家
		int index=0;//发牌指针
		for(int i=0;i<numOfCards;i++) {
			player[index++].gainCard(gameDeck[i]);
			if(index>4) index=0;
		}
	}
	
	public boolean gameIsOver() {
		int surviver=0;
		for(int i=0;i<5;i++) {
			if(player[i].isAlive()) surviver++;
		}
		return surviver==1;
	}
	
	public void printDeck() {
		String[] firstline=Card.getCategoryName();
		for(int i=0;i<6;i++) {
			System.out.printf("%s\t", firstline[i]);
		}
		System.out.println();
		for(int i=0;i<numOfCards;i++) {
			String name=gameDeck[i].getName();
			int[] category=gameDeck[i].getCategory();
			System.out.printf("%s\t", name);
			for(int j=1;j<6;j++) {
				System.out.printf("%d\t", category[j]);
			}
			System.out.println();
		}
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
	
	public int getNumOfGames() {
		return numOfGames;
	}

	public int getNumOfHumanWins() {
		return numOfHumanWins;
	}

	public int getNumOfAIWins() {
		return numOfAIWins;
	}

	public double getAveOfdraws() {
		return aveOfdraws;
	}

	public int getLongestGame() {
		return longestGame;
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

	
}
