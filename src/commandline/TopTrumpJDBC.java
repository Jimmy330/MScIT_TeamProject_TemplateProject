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

	public void test() throws Exception{
		String sql = "CREATE TABLE student(" + 
				"sno character(9) PRIMARY KEY," + 
				"sname character(20) UNIQUE," + 
				"ssex character(2)," + 
				"sage smallint," + 
				"sdept character(20)" + 
			");";
		
		int count = state.executeUpdate(sql);

		System.out.println(count);

	}
}
