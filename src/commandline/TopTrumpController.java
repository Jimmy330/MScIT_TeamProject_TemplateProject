package commandline;


public class TopTrumpController {
	private TopTrumpModel modelObject;
	private TopTrumpView viewObject;
	private TopTrumpJDBC jdbcObject;
	private TestLog log;
	
	public TopTrumpController(TopTrumpModel model,TopTrumpView view,TopTrumpJDBC jdbc,TestLog log) {
		modelObject=model;
		viewObject=view;
		jdbcObject=jdbc;
		this.log=log;
	}
	
	public void selectMenu() throws Exception{
		viewObject.printSelectMenu();
		int k=viewObject.scanInt();
		if(k==1) {
			this.gameStatus();
		}
		if(k==2) {
			this.game();
		}
		
	}
	public void gameStatus() throws Exception{
		int s1=jdbcObject.lastGidbefore();
		int s2=jdbcObject.numOfHumanWins();
		int s3=jdbcObject.numOfAIWins();
		double s4=jdbcObject.aveDraw();
		int s5=jdbcObject.largeRounds();
		viewObject.printGameStatus(s1,s2,s3,s4,s5);
	}
	public boolean wantsToQuit() {
		viewObject.printcontinue();;
		int k=viewObject.scanInt();
		if(k==0) {
			return true;
		}
			return false;
	}
	public void game() throws Exception {
		

		log.loadCardLog();
		
		modelObject.shuffle(modelObject.getGameDeck(), TopTrumpModel.getNumofcards());		
		log.loadCardLog();
		
		modelObject.loadPlayer();
		log.loadPlayerCardLog();
		

		modelObject.setNumOfRounds(0);	
		
		
		while(!modelObject.gameIsOver()) {
			modelObject.setNumOfRounds(modelObject.getNumOfRounds()+1);
			
			modelObject.drawPhase();			
			log.currentCardLog();
			
			viewObject.printRoundTitle();
			viewObject.printDrawCard();
			
			
			int indexOfCategory=modelObject.selectPhase();
			if(indexOfCategory==-1) {
				viewObject.printSelectCategory();
				indexOfCategory=viewObject.scanInt();
			}
			modelObject.setIndexOfCategory(indexOfCategory);			
			log.selectionLog(indexOfCategory);
			
			modelObject.judgePhase();
			viewObject.printResult();
			log.communalPileLog();
			log.loadPlayerCardLog();
			
		}
		log.gameWinnerLog();
		try {
			jdbcObject.create();
			jdbcObject.initialPlayer();
		}catch(Exception e) {
			
		}
		int lastgid=jdbcObject.lastGidbefore();
		jdbcObject.insertGameResult(lastgid+1, modelObject.getNumOfRounds(), modelObject.getNumOfDraws(), modelObject.roundWinner());
		for(int i=0;i<modelObject.getPlayer().length;i++) {
			jdbcObject.insertPlayerResult(lastgid+1, i, modelObject.getPlayer()[i].getRoundWin());
		}
	}
}
