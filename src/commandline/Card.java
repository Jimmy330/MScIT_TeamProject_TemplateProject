package commandline;

public class Card {
	private static String[] categoryName;
	private String name;
	private int[] category;
	public Card(String name, int[] category) {//constructor
		this.name=name;
		this.category=category;
	}
	public String getName() {//一堆游戏中要用的getters和setters
		return name;
	}
	public int[] getCategory() {
		return category;
	}
	public static void setCategoryName(String[] categoryName) {
		Card.categoryName = categoryName;
	}
	public static String[] getCategoryName() {
		return categoryName;
	}
}
