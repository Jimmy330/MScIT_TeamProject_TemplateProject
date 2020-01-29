package commandline;

public class Player {
	private int type;// AI(0) or human(1)
	private String name;//玩家名字
	private Card[] deck;//玩家的卡组
	private Card hand;//玩家的手牌
	private int numOfCards;
	public Player(String name,int type) {//constructor
		this.name=name;
		this.type=type;
		deck=new Card[50];
		numOfCards=0;
	}
	
	public boolean isAlive() {
		return numOfCards>0;
	}
	
	public void gainCard(Card c) {//将一张牌加入玩家deck
		deck[numOfCards++]=c;
	}
	public void drawCard() {//将deck的第一张移到hand
		hand=deck[0];
		for(int i=0;i<numOfCards-1;i++) {
			deck[i]=deck[i+1];
		}
		numOfCards--;
	}
	public int getNumOfCards() {
		return numOfCards;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getType() {//getters
		return type;
	}
	public String getName() {
		return name;
	}
	public Card[] getDeck() {
		return deck;
	}
	public Card getHand() {
		return hand;
	}
}
