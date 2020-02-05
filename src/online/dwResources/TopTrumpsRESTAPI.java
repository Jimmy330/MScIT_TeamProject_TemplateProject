package online.dwResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.fasterxml.jackson.databind.util.JSONPObject;

import commandline.Card;
import commandline.TopTrumpController;
import commandline.TopTrumpModel;
import commandline.TopTrumpView;
import commandline.test;
import netscape.javascript.JSObject;

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

	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) throws IOException {
		// ----------------------------------------------------
		// Add relevant initalization here
		// ----------------------------------------------------
		num = conf.getNumAIPlayers();
		model = new TopTrumpModel(false);
		model.loadPlayer();
		model.drawPhase();

	}

	@GET
	@Path("/roundNo")
	public String getRoundNo() {
		return Integer.toString(model.getNumOfRounds()+1);
	}

//	@GET
//	@Path("/nextRound")
//	public String nextRound() {
//		
//	}

	// @GET
	// @Path("/selector")
	// public String getSelector() {
	// 	return model.getPlayer()[model.getSelector()].getName();
	// }

	@GET
	@Path("/select")
	public String selectCategory() throws IOException {
		
		String category = Card.getCategoryName()[model.getSelector()];
		String selector = model.getPlayer()[model.getSelector()].getName();
		

		List<String> list = new ArrayList<>();
		list.add(selector);
		list.add(category);

		return oWriter.writeValueAsString(list);

	}

	@GET
	@Path("/roundWinner")
	public String getRoundWinner() {
		try {
			model.judgePhase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model.getPlayer()[model.roundWinner()].getName();
	}

	@GET
	@Path("/card")
	public String card() throws IOException {
		// TopTrumpModel model = new TopTrumpModel(false);

		String[][] test = new String[10][10];

		for (int i = 0; i < num + 1; i++) {
			test[i][0] = model.getPlayer()[i].getName();
			test[i][1] = Integer.toString(model.getPlayer()[i].getNumOfCards());
			test[i][2] = model.getPlayer()[i].getHand().getName();
			test[i][3] = Integer.toString(model.getPlayer()[i].getHand().getCategory()[0]);
			test[i][4] = Integer.toString(model.getPlayer()[i].getHand().getCategory()[1]);
			test[i][5] = Integer.toString(model.getPlayer()[i].getHand().getCategory()[2]);
			test[i][6] = Integer.toString(model.getPlayer()[i].getHand().getCategory()[3]);
			test[i][7] = Integer.toString(model.getPlayer()[i].getHand().getCategory()[4]);

//			 for(int j =3; j<model.getPlayer()[i].getNumOfCards()+3;j++){
//				 test[i][j]=Integer.toString(model.getPlayer()[i].getHand().getCategory()[j-3]);
//
//			 };

		}
		;

		// List<String> listOfWords = new ArrayList<String>();
		// listOfWords.add(playerName);
		// listOfWords.add(numOfCards);
		// listOfWords.add(categorySize);
		// listOfWords.add(categorySpeed);
		// listOfWords.add(categoryRange);
		// listOfWords.add(categoryFirepower);
		// listOfWords.add(categoryCargo);

		String list = oWriter.writeValueAsString(test);

		// String list= oWriter.writeValueAsString(listOfWords);

		// ObjectMapper map=new ObjectMapper();
		// String list = map.writeValueAsString(card);
		return list;
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
