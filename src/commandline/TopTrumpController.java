package commandline;


import java.io.IOException;
import java.util.Random;

public class TopTrumpController {
	private TopTrumpModel modelObject;
	private TopTrumpView viewObject;
	private TopTrumpJDBC jdbcObject;
	public TopTrumpController(TopTrumpModel model,TopTrumpView view,TopTrumpJDBC jdbc) {
		modelObject=model;
		viewObject=view;
		jdbcObject=jdbc;
	}
	
	public void selectMenu(int choice) {
		//if(choice==1) {
		//	viewObject.
		//}
	}
	public void gameStatus() throws Exception{
		int s1=jdbcObject.lastGidbefore();
		int s2=jdbcObject.numOfHumanWins();
		int s3=jdbcObject.numOfAIWins();
		double s4=jdbcObject.aveDraw();
		int s5=jdbcObject.largeRounds();
		viewObject.printGameStatus(s1,s2,s3,s4,s5);
	}
	public void game() throws Exception {
		int lastgid=jdbcObject.lastGidbefore();
		int lastpid=jdbcObject.lastPidbefore()+1;
		modelObject.loadPlayer();
		for(int i=0;i<5;i++) {
			jdbcObject.insertPlayerDetail(i+lastpid, modelObject.getPlayer()[i].getName(), modelObject.getPlayer()[i].getType());
		}
		modelObject.setNumOfRounds(0);
		
		while(!modelObject.gameIsOver()) {
			modelObject.setNumOfRounds(modelObject.getNumOfRounds()+1);
			modelObject.drawPhase();
			
			viewObject.printRoundTitle();
			viewObject.printDrawCard();
			
			
//			if(modelObject.getNumOfRounds()==1) {
//				Random i = new Random();				
//				int n=i.nextInt(5);
//				modelObject.setSelector(n);
//			}
			
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
		jdbcObject.insertGameResult(lastgid+1, modelObject.getNumOfRounds(), modelObject.getNumOfDraws(), modelObject.roundWinner());
		for(int i=0;i<5;i++) {
			jdbcObject.insertPlayerResult(lastgid+1, i+lastpid, modelObject.getPlayer()[i].getRoundWin());
		}
	}
}
