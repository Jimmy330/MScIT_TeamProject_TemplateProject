package commandline;


import java.io.IOException;
import java.util.Random;

public class TopTrumpController {
	private TopTrumpModel modelObject;
	private TopTrumpView viewObject;
	public TopTrumpController(TopTrumpModel model,TopTrumpView view) {
		modelObject=model;
		viewObject=view;
	}
	
	public void selectMenu(int choice) {
		//if(choice==1) {
		//	viewObject.
		//}
	}
	
	public void game() throws IOException {
		
		modelObject.loadPlayer();
		modelObject.setNumOfRounds(0);
		
		while(!modelObject.gameIsOver()) {
			modelObject.setNumOfRounds(modelObject.getNumOfRounds()+1);
			modelObject.drawPhase();
			
			viewObject.printRoundTitle();
			viewObject.printDrawCard();
			
			
			if(modelObject.getNumOfRounds()==1) {
				Random i = new Random();				
				int n=i.nextInt(5);
				modelObject.setSelector(n);
			}
			
			int indexOfCategory=modelObject.selectPhase();
			if(indexOfCategory==-1) {
				viewObject.printSelectCategory();
				indexOfCategory=viewObject.scanInt();
				
				// write logs of human players' category selection
				if(modelObject.getWriteLogsToFile()) {
				String selectionLog = "You selected " + Card.getCategoryName()[indexOfCategory] + " "
						+ modelObject.getPlayer()[0].getHand().getCategory()[indexOfCategory];
				modelObject.writeLog(selectionLog);}
			}
			modelObject.setIndexOfCategory(indexOfCategory);
			modelObject.judgePhase();
			viewObject.printResult();
		}

	}
}
