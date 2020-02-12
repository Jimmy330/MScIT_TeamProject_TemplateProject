package commandline;

public class Card {
	public static String[] categoryName;//store the category name
	private String name;//store the card name
	private int[] category;//store the value of category
	public Card(String name, int[] category) {//constructor
		this.name=name;
		this.category=category;
	}
	public String getName() {//getters and setters
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
