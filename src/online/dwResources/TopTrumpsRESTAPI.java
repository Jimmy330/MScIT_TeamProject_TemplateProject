package online.dwResources;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import commandline.TopTrumpModel;

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

	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) throws IOException {
		// ----------------------------------------------------
		// Add relevant initalization here
		// ----------------------------------------------------
		num = conf.getNumAIPlayers();
		deckFile = new File(conf.getDeckFile());

	}

	@GET
	@Path("/initalize")
	public String initalize() throws IOException {

		model = new TopTrumpModel();
		model.loadDeck(deckFile);
		model.loadPlayer();
		model.drawPhase();
		return card();
	}

	@GET
	@Path("/card")
	public String card() throws IOException {

		ArrayList<Player> playerInfo = new ArrayList<Player>();

		for (int i = 0; i <= num; i++) {
			if(model.getPlayer()[i].isAlive())
			playerInfo.add(model.getPlayer()[i]);
		}
		String list = oWriter.writeValueAsString(playerInfo);
		return list;
	}

	@GET
	@Path("/roundNo")
	public String getRoundNo() {
		return Integer.toString(model.getNumOfRounds() + 1);
	}

	@GET
	@Path("commondeck")
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

		// return selectCategory();
	}

	@GET
	@Path("/selector")
	public String selector() throws IOException {

		int indexOfCategory = model.selectPhase();

		HashMap<String, String> selectorInfo = new HashMap<String, String>();
		Player selector = model.getPlayer()[model.getSelector()];
		String categoryName = Card.categoryName[indexOfCategory + 1];

		selectorInfo.put("name", selector.getName());
		selectorInfo.put("type", Integer.toString(selector.getType()));
		selectorInfo.put("category", categoryName);

		return oWriter.writeValueAsString(selectorInfo);

	}

	@GET
	@Path("/roundWinner")
	public String getRoundWinner() throws IOException {

		if(model.roundWinner()<0) {
			ArrayList<String> list= new ArrayList<String>();
			
			String str = "Opps~! This round is a draw! \n";
			
			str+=model.getCardsInCommonDeck()+" cards moved into communal pile";

			list.add("0");
			list.add(str);
			return oWriter.writeValueAsString(list);
		}
		return oWriter.writeValueAsString(model.getPlayer()[model.roundWinner()]);
	}

	@GET
	@Path("/roundstart")
	public String roundStart() throws IOException {
		model.judgePhase();
		model.drawPhase();
		model.setNumOfRounds(model.getNumOfRounds()+1);
		return selector();
	}

	@GET
	@Path("/gamewinner")
	public String getGameWinner() throws JsonProcessingException {
		if(model.gameIsOver()){

			HashMap<String,Integer> list = new HashMap<String,Integer>();
			for(int i =0;i<num+1;i++){
			int gameData= model.getPlayer()[i].getRoundWin();	
			String playerName=model.getPlayer()[i].getName();
			list.put(playerName, gameData);
			
			}
			return oWriter.writeValueAsString(list);		
		}
		return oWriter.writeValueAsString(false);
	}

	

	@GET
	@Path("/helloJSONList")
	/**
	 * Here is an example of a simple REST get request that returns a String. We
	 * also illustrate here how we can convert Java objects to JSON strings.
	 * 
	 * @return - List of words as JSON
	 * @throws IOException
	 */
	public String helloJSONList() throws IOException {

		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add("Hello");
		listOfWords.add("World!");

		// We can turn arbatory Java objects directly into JSON strings using
		// Jackson seralization, assuming that the Java objects are not too complex.
		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		return listAsJSONString;
	}

	@GET
	@Path("/helloWord")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * 
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String helloWord(@QueryParam("Word") String Word) throws IOException {
		return "Hello " + Word;
	}

}
