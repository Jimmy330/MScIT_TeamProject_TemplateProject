package commandline;

public class Player {
	private int type;// type of player: AI(0) or human(1)
	private String name;// player's name
	private Card[] deck;// player's card deck
	private Card handCard;// the first card of the player's card deck
	private int numOfCards;

	public Player(String name, int type) {// constructor
		this.name = name;
		this.type = type;
		deck = new Card[50];
		numOfCards = 0;
	}

	public boolean isAlive() {// check the status of the player:lose or not
		return numOfCards > 0;
	}

	public void gainCard(Card c) {// add card to player's card deck bottom
		deck[numOfCards++] = c;
	}

    // draw card from player's card deck, the index of remaining cards will be updated respectively
	public void drawCard() {
		handCard = deck[0];
		for (int i = 0; i < numOfCards - 1; i++) {
			deck[i] = deck[i + 1];
		}
		numOfCards--;
	}

	public int getNumOfCards() {
		return numOfCards;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {// getters
		return type;
	}

	public String getName() {
		return name;
	}

	public Card[] getDeck() {
		return deck;
	}

	public Card getHand() {
		return handCard;
	}
}
