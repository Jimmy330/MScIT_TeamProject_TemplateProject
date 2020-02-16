package online.dwResources;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import commandline.Card;
import commandline.Player;
import commandline.TopTrumpJDBC;
import commandline.TopTrumpModel;
import jersey.repackaged.com.google.common.collect.Maps;

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands to
 * the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create REST API
 * methods in Dropwizard. You will need to replace these with methods that allow
 * a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/**
	 * A Jackson Object writer. It allows us to turn Java objects into JSON strings
	 * easily.
	 */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();

	/**
	 * Contructor method for the REST API. This is called first. It provides a
	 * TopTrumpsJSONConfiguration from which you can get the location of the deck
	 * file and the number of AI players.
	 * 
	 * @param conf
	 */
	private TopTrumpModel model;
	private int num;
	private File deckFile;
	private TopTrumpJDBC dataBase;

	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) throws Exception {

		num = conf.getNumAIPlayers();
		deckFile = new File(conf.getDeckFile());
		model = new TopTrumpModel();
		dataBase = new TopTrumpJDBC(model);
		try {
			dataBase.create();
		}catch(Exception e) {
			
		};
	
	}
	
	@GET
	@Path("/AIplayerNum")	
	public void setPlayerNum(@QueryParam("value")int num){
		
		this.num=num;
		model.setPlayerNum(num+1);
	}
	
	@GET
	@Path("/startGame")
	public String startGame() throws Exception {
		
		model.loadDeck(deckFile);
		model.shuffle(model.getGameDeck(), TopTrumpModel.getNumofcards());
		model.loadPlayer();
		model.setNumOfRounds(0);
		
		return newRound();
	}

	@GET
	@Path("/card")
	public String card() throws IOException {

		ArrayList<Player> playerInfo = new ArrayList<Player>();

		for (int i = 0; i <= num; i++) {
			playerInfo.add(model.getPlayer()[i]);
		}		
		return oWriter.writeValueAsString(playerInfo);
	}

	@GET
	@Path("/roundNo")
	public String getRoundNo() {
		return Integer.toString(model.getNumOfRounds());
	}

	@GET
	@Path("/commonDeck")
	public String getCommonDeck() throws JsonProcessingException {
		return oWriter.writeValueAsString(model.getCardsInCommonDeck());
	}

	@GET
	@Path("/category")
	public String category(@QueryParam("value") int category) throws IOException {
		model.setIndexOfCategory(category);

		HashMap<String, String> selectorInfo = new HashMap<String, String>();
		Player selector = model.getPlayer()[model.getSelector()];
		String categoryName = Card.categoryName[category];

		selectorInfo.put("name", selector.getName());
		selectorInfo.put("type", Integer.toString(selector.getType()));
		selectorInfo.put("category", categoryName);

		return oWriter.writeValueAsString(selectorInfo);

	}

	@GET
	@Path("/selector")
	public String selector() throws IOException {

		int indexOfCategory = model.selectPhase();
		HashMap<String, String> selectorInfo = new HashMap<String, String>();
		Player selector = model.getPlayer()[model.getSelector()];
		String categoryName = "";
		if (indexOfCategory > 0) {
			categoryName = Card.categoryName[indexOfCategory];
			model.setIndexOfCategory(indexOfCategory);
		}
		selectorInfo.put("name", selector.getName());
		selectorInfo.put("type", Integer.toString(selector.getType()));
		selectorInfo.put("category", categoryName);

		return oWriter.writeValueAsString(selectorInfo);

	}

	@GET
	@Path("/roundWinner")
	public String getRoundWinner() throws IOException {
		model.judgePhase();

		if (model.roundWinner() < 0) {
			ArrayList<Integer> list = new ArrayList<>();

			list.add(0);
			list.add(model.getCardsInCommonDeck());
			return oWriter.writeValueAsString(list);
		}
		return oWriter.writeValueAsString(model.getPlayer()[model.roundWinner()]);
	}

	@GET
	@Path("/newRound")
	public String newRound() throws IOException {

		if (!model.gameIsOver()) {
			model.drawPhase();
			model.setNumOfRounds(model.getNumOfRounds() + 1);
			return selector();
		}

		return oWriter.writeValueAsString(false);

	}

	@GET
	@Path("/gameWinner")
	public String getGameWinner() throws Exception {
		List<LinkedHashMap<String, String>> list = new ArrayList<>();

		LinkedHashMap<String, String> gameStatus = Maps.newLinkedHashMap();
		LinkedHashMap<String, String> gameData = Maps.newLinkedHashMap();

		gameStatus.put("gameWinner", model.getGameWinner().getName());
		list.add(gameStatus);

		for (int i = 0; i < num + 1; i++) {
			String playerGameData = Integer.toString(model.getPlayer()[i].getRoundWin());
			String playerName = model.getPlayer()[i].getName();

			gameData.put(playerName, playerGameData);
		}
		list.add(gameData);
		

		try {
			dataBase.initialPlayer();
		}catch(Exception e) {};
		
		int lastgid=dataBase.lastGidbefore();
	
		dataBase.insertGameResult(lastgid+1, model.getNumOfRounds(), model.getNumOfDraws(), model.roundWinner());
		for(int i=0;i<5;i++) {
			dataBase.insertPlayerResult(lastgid+1, i, model.getPlayer()[i].getRoundWin());
		}

		return oWriter.writeValueAsString(list);
	}

	@GET
	@Path("/gameData")
	public String gameData() throws Exception {

		List<Integer> list = new ArrayList<>();
		list.add(dataBase.lastGidbefore());
		list.add(dataBase.numOfHumanWins());
		list.add(dataBase.numOfAIWins());
		list.add((int)dataBase.aveDraw());
		list.add(dataBase.largeRounds());

		return oWriter.writeValueAsString(list);


	}

}
