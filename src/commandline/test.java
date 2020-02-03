package commandline;

import java.util.Scanner;

public class test {
	public static void main(String[] args) throws Exception{
		TopTrumpModel model=new TopTrumpModel();
//		TopTrumpJDBC jdbc=new TopTrumpJDBC(model);
//		jdbc.test();
		TopTrumpView view=new TopTrumpView(model);
		TopTrumpController controller=new TopTrumpController(model,view);
		view.setPrintstream(System.out);
		view.setSc(new Scanner(System.in));
		controller.game();
		model.printDeck();
		view.closeSc();
	}
}
