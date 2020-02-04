package commandline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TopTrumpJDBC {
	
	private String url = "jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/m_19_2453447x";
	private String user = "m_19_2453447x";
	private String password = "2453447x";
	
	private Connection conn;
	private TopTrumpModel modelObject;
	private Statement state; 
	
	public TopTrumpJDBC(TopTrumpModel model) throws Exception{
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection(url, user, password);
		modelObject=model;
		state = conn.createStatement();
	}
	public void create() throws Exception{
		String sql = "create table PLAYER_DETAIL(" + 
				"PlayerID integer NOT NULL," + 
				"Name varchar(32) NOT NULL," + 
				"Type varchar(5) NOT NULL," + 
				"Primary Key (PlayerID));" + 
				"create table GAME_RESULT(" + 
				"GID integer NOT NULL," + 
				"TotalRounds integer NOT NULL," + 
				"TotalDraws integer," + 
				"WinnerID integer NOT NULL," + 
				"Primary Key (GID)," + 
				"Foreign Key (WinnerID) references PLAYER_DETAIL(PlayerID)" + 
				"on delete set null on update cascade);" + 
				"create table PLAYER_RESULT(" + 
				"GID integer NOT NULL," + 
				"PlayerID integer NOT NULL," + 
				"NumOfRoungsWin integer," + 
				"Primary Key (GID, PlayerID)," + 
				"Foreign Key (GID) references GAME_RESULT(GID)" + 
				"on delete cascade on update cascade," + 
				"Foreign Key (PlayerID) references PLAYER_DETAIL(PlayerID)" + 
				"on delete cascade on update cascade);";
		
		state.executeUpdate(sql);
	}
	
	
	
}
