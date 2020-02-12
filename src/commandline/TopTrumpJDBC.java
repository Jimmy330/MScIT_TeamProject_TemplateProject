package commandline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TopTrumpJDBC {
	private String url="jdbc:postgresql://localhost:5433/postgres";
	private String user = "postgres";
	private String password = "123456";
	
//	private String url = "jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/m_19_2453447x";
//	private String user = "m_19_2453447x";
//	private String password = "2453447x";
	
	private Connection conn;
	private TopTrumpModel modelObject;
	
	public TopTrumpJDBC(TopTrumpModel model) throws Exception{
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection(url, user, password);
		modelObject=model;
	}
	public int lastGidbefore() throws Exception{
		String sql ="select gid from game_result\r\n" + 
				"order by gid desc\r\n" + 
				"limit 1";
		Statement state=conn.createStatement();
		ResultSet rs=state.executeQuery(sql);
		int res=0;
		if(rs.next()){
			res=rs.getInt("gid");
		}
		return res;
	}
	public int lastPidbefore() throws Exception{
		String sql ="select playerid from player_detail\r\n" + 
				"order by playerid desc\r\n" + 
				"limit 1";
		Statement state=conn.createStatement();
		ResultSet rs=state.executeQuery(sql);
		int res=0;
		if(rs.next()){
			res=rs.getInt("playerid");
		}
		return res;
	}
	public int numOfAIWins() throws Exception{
		String sql ="SELECT COUNT (*) AS NGames_AIs_Win\r\n" + 
				"FROM GAME_RESULT\r\n" + 
				"WHERE winnerid IN (\r\n" + 
				"		SELECT playerid\r\n" + 
				"		FROM PLAYER_DETAIL\r\n" + 
				"		WHERE type = '0');";
		Statement state=conn.createStatement();
		ResultSet rs=state.executeQuery(sql);
		int res=0;
		if(rs.next()){
			res=rs.getInt("NGames_AIs_Win");
		}
		return res;
	}
	public int numOfHumanWins() throws Exception{
		String sql ="SELECT COUNT (*) AS NGames_Human_Win\r\n" + 
				"FROM GAME_RESULT\r\n" + 
				"WHERE winnerid IN (\r\n" + 
				"		SELECT playerid\r\n" + 
				"		FROM PLAYER_DETAIL\r\n" + 
				"		WHERE type = '1');";
		Statement state=conn.createStatement();
		ResultSet rs=state.executeQuery(sql);
		int res=0;
		if(rs.next()){
			res=rs.getInt("NGames_Human_Win");
		}
		return res;
	}
	public double aveDraw() throws Exception{
		String sql ="SELECT AVG(TotalDraws) AS Avg_Draws\r\n" + 
				"FROM GAME_RESULT;";
		Statement state=conn.createStatement();
		ResultSet rs=state.executeQuery(sql);
		double res=0;
		if(rs.next()){
			res=rs.getDouble("Avg_Draws");
		}
		return res;
	}
	public int largeRounds() throws Exception{
		String sql ="SELECT MAX(TotalRounds) AS max_Rounds\r\n" + 
				"FROM GAME_RESULT;\r\n" ;
		Statement state=conn.createStatement();
		ResultSet rs=state.executeQuery(sql);
		int res=0;
		if(rs.next()){
			res=rs.getInt("max_Rounds");
		}
		return res;
	}
	public void insertGameResult(int gid,int tround,int tdraw, int winnerid) throws Exception{
		String sql ="INSERT INTO GAME_RESULT(GID,TotalRounds,TotalDraws,WinnerID) " + 
					"VALUES(?, ?, ?, ?);";
		PreparedStatement state = conn.prepareStatement(sql);
		state.setInt(1, gid);
		state.setInt(2, tround);
		state.setInt(3, tdraw);
		state.setInt(4, winnerid);
		state.executeUpdate();
	}
	
	public void insertPlayerDetail(int playerid, String name,int type) throws Exception{
		String sql ="INSERT INTO PLAYER_DETAIL(PlayerID, Name, Type) " + 
				"VALUES(?, ?, ?);";
		PreparedStatement state = conn.prepareStatement(sql);
		state.setInt(1, playerid);
		state.setString(2, name);
		state.setInt(3, type);
		state.executeUpdate();
	}
	
	public void insertPlayerResult(int gid, int playerid, int numofRoundWin)throws Exception {
		String sql ="INSERT INTO PLAYER_RESULT(GID, PlayerID, NumOfRoundsWin) " + 
				"VALUES(?, ?, ?);";
		PreparedStatement state = conn.prepareStatement(sql);
		state.setInt(1, gid);
		state.setInt(2, playerid);
		state.setInt(3, numofRoundWin);
		state.executeUpdate();
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
				"NumOfRoundsWin integer," + 
				"Primary Key (GID, PlayerID)," + 
				"Foreign Key (GID) references GAME_RESULT(GID)" + 
				"on delete cascade on update cascade," + 
				"Foreign Key (PlayerID) references PLAYER_DETAIL(PlayerID)" + 
				"on delete cascade on update cascade);";
		Statement state = conn.createStatement(); 
		state.executeUpdate(sql);
		
	}
	
	public void initialPlayer() throws Exception {
		for(int i=0;i<5;i++) {
			this.insertPlayerDetail(i, modelObject.getPlayer()[i].getName(), modelObject.getPlayer()[i].getType());
		}
	}
	
}
