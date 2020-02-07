package commandline;

import java.util.Scanner;

public class test {
	public static void main(String[] args) throws Exception{
		boolean testLog=true;
		TopTrumpModel model=new TopTrumpModel();
		TopTrumpJDBC jdbc=new TopTrumpJDBC(model);
		//jdbc.create();
		TopTrumpView view=new TopTrumpView(model);
		
		TestLog log=new TestLog(model,testLog);
		TopTrumpController controller=new TopTrumpController(model,view,jdbc,log);
		view.setPrintstream(System.out);
		view.setSc(new Scanner(System.in));
		controller.game();
		//controller.gameStatus();
		
		//model.printDeck();
		view.closeSc();
	}
}
