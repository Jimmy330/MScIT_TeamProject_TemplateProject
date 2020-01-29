package commandline;

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
	
	public void game() {
		modelObject.loadPlayer();
		modelObject.setNumOfRounds(0);
		while(!modelObject.gameIsOver()) {
			modelObject.setNumOfRounds(modelObject.getNumOfRounds()+1);
			modelObject.drawPhase();
			viewObject.printRoundTitle();
			viewObject.printDrawCard();
			int indexOfCategory=modelObject.selectPhase();
			if(indexOfCategory==-1) {
				viewObject.printSelectCategory();
				indexOfCategory=viewObject.scanInt();
			}
			modelObject.setIndexOfCategory(indexOfCategory);
			modelObject.judgePhase();
			viewObject.printResult();
		}
	}
}
