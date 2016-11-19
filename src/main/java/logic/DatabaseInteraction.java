package logic;
import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseInteraction {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private String connectionString;
	
	public DatabaseInteraction(){
		connectionString = "jdbc:mysql://localhost/UserAndPollDatabase?user=root&password=JLjinling@97&useSSL=false";
		conn = null;
		ps = null;
		rs = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch (ClassNotFoundException cnfe) {
			System.out.println ("ClassNotFoundException: " + cnfe.getMessage());
		}
		
	}
	
	
	//Login Methods
	public boolean userExist(String email){
		try{
			conn = DriverManager.getConnection(connectionString);		
			ps = conn.prepareStatement("SELECT 1 FROM Users WHERE Email = '" + email + "'");
			rs = ps.executeQuery();
			if(rs.first() == false){
				return false;
			}
			else{
				return true;
			}
			
		} catch (SQLException sqle) {
		System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
				}
			}
		return false;
	
	}
	
	
	public boolean loginCorrect(String email, String password){
		ps = null;
		rs = null;
		conn = null;
		try{
			conn = DriverManager.getConnection(connectionString);		
			ps = conn.prepareStatement("SELECT 1 FROM Users WHERE Email = '" + email + "'" + " AND Pword = '" + password + "'");
			rs = ps.executeQuery();
			if(rs.first() == false){
				return false;
			} 
			else{
				return true;
			}
			
			} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return true;
	}
	
	
	
	//
	//User Methods
	//
	public void addUser(String email, String password, String city, int ageRange, int gender, int race, int education, String username){

		ps = null;
		rs = null;
		conn = null;
		try{
			conn = DriverManager.getConnection(connectionString);
			ps = conn.prepareStatement("INSERT INTO Users (Email, pword, City, AgeRange, Gender, Race, Education, Points, Username) VALUES ('"+ email +"', '"+password+"', '"+city+"', '"+ageRange+"', '"+gender+"', '"+race+"', '"+education+"', '0', '"+username+"');");
			ps.executeUpdate();
			} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
	

	}

	//Checks if userID is following followID
	public boolean followExist(int userID, int followID){
		ps = null;
		rs = null;
		conn = null;
		try{
			conn = DriverManager.getConnection(connectionString);		
			ps = conn.prepareStatement("SELECT 1 FROM Followers WHERE UserID = '" + userID + "'" + " AND FollowID = '" + followID + "'");
			rs = ps.executeQuery();
			if(rs.first() == false){
				return false;
			} 
			else{
				return true;
			}
			
			} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return true;
	}
	
	public int getUserID(String email){
		ps = null;
		rs = null;
		conn = null;
		try{
			conn = DriverManager.getConnection(connectionString);
			ps = conn.prepareStatement("SELECT * FROM Users WHERE Email = '" + email+"'");

			rs = ps.executeQuery();
			rs.next();
			int id = rs.getInt("UserID");
			return id;
			} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return -1;
	}
	
	//Has userID follow followID
	public void addFollow(int userID, int followID){
		ps = null;
		rs = null;
		conn = null;
		try{
			conn = DriverManager.getConnection(connectionString);
			ps = conn.prepareStatement("INSERT INTO Followers (UserID, FollowID) VALUES ('"+ userID +"', '"+followID+"');");
			ps.executeUpdate();
			} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
	}
	
	//Gets the total number of registered users
	public int getNumberOfUsers(){
		ps = null;
		rs = null;
		conn = null;
		try{
			conn = DriverManager.getConnection(connectionString);
			ps = conn.prepareStatement("SELECT * FROM Users ORDER BY UserID DESC LIMIT 1");
			rs = ps.executeQuery();

			rs.next();

			int num = rs.getInt(1);

			return num;
			} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return -1;
	}

	//Gets all the people that a user follows
	public ArrayList<Integer> getFollows(int UserID){
		ArrayList<Integer> follows = new ArrayList<Integer>();
		ps = null;
		rs = null;
		conn = null;
		try{
			conn = DriverManager.getConnection(connectionString);		
			ps = conn.prepareStatement("SELECT * from Followers where UserID = " + UserID + ";");
			rs = ps.executeQuery();
			while(rs.next()){
				follows.add(rs.getInt(2));
			}
			
			} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		
		
		
		return follows;
	}
	
	//User(String email,String password, String city, int ageRange, int gender, int race, int education, String username){
	public User getUser(String email){
		PreparedStatement ps1 = null;
		ResultSet rs1 = null;
		Connection conn1 = null;
		try{
			System.out.println("here1");

			conn1 = DriverManager.getConnection(connectionString);
			System.out.println("here2");

			ps1 = conn1.prepareStatement("SELECT * FROM Users WHERE Email = '" + email+"'");
			System.out.println("here3");

			rs1 = ps1.executeQuery();		
			System.out.println("here4");

			rs1.next();

			int userID = rs1.getInt("UserID");

			String city = rs1.getString("City");
			String password = rs1.getString("Pword");
			int ageRange = rs1.getInt("AgeRange");
			int gender = rs1.getInt("Gender");
			int race = rs1.getInt("Race");
			int education = rs1.getInt("Education");
			int points = rs1.getInt("Points");
			String username = rs1.getString("Username");
			System.out.println(username);

			User temp = new User(email, password, city, ageRange, gender, race, education, username);
			return temp;
			} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (rs1 != null) {
					rs1.close();
				}
				
				if (ps1 != null) {
					ps1.close();
				}
				if (conn1 != null) {
					conn1.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return null;
	
	}
	
	public User getUser(int userID){
		ps = null;
		rs = null;
		conn = null;
		try{
			conn = DriverManager.getConnection(connectionString);
			ps = conn.prepareStatement("SELECT * FROM Users WHERE UserID = " + userID);

			rs = ps.executeQuery();
			rs.next();
			String email = rs.getString("Email");
			String city = rs.getString("City");
			String password = rs.getString("Pword");
			int ageRange = rs.getInt("AgeRange");
			int gender = rs.getInt("Gender");
			int race = rs.getInt("Race");
			int education = rs.getInt("Education");
			int points = rs.getInt("Points");
			String username = rs.getString("Username");
			User temp = new User(email, password, city, ageRange, gender, race, education, username);
			return temp;
			} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return null;
	}
	
	public ArrayList<User> getAllUsers(){
		int totalUsers = getNumberOfUsers();
		ArrayList<User> allUsers = new ArrayList<User>();
		for(int i = 1; i <= totalUsers; i++){
			User temp = getUser(i);
			allUsers.add(temp);
		}
		return allUsers;
	}
	//Gets all the people that follow a user
	//Returns ArrayList<UserID>
 	public ArrayList<Integer> getFollowers(int followID){
		ArrayList<Integer> followers = new ArrayList<Integer>();
		ps = null;
		rs = null;
		conn = null;
		try{
			conn = DriverManager.getConnection(connectionString);		
			ps = conn.prepareStatement("SELECT * from Followers where FollowID = " + followID + ";");
			rs = ps.executeQuery();
			while(rs.next()){
				followers.add(rs.getInt(1));
			}
			
			} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return followers;
	}
	
 	private void addQuestionWords(String question){
 		ps = null;
		rs = null;
		conn = null;
		question = question.replace("?", "");
		question = question.replace(",", "");
		question = question.replace(".", "");
		question = question.toLowerCase();
		String[] questionWords = question.split(" ");
		
		
		try{
			int pollID = getNumberOfPolls();
			conn = DriverManager.getConnection(connectionString);
			ps = conn.prepareStatement("SELECT * from Words;");
			rs = ps.executeQuery();
			System.out.println("length: " + questionWords.length);
			ResultSetMetaData rsmd = rs.getMetaData();
			for(int j = 0; j < questionWords.length; j++){
				System.out.println(questionWords[j]);
				boolean columnExist = false;
				for(int i = 1; i <= rsmd.getColumnCount(); i++){
					String buffer = rsmd.getColumnName(i);
					if(buffer.equalsIgnoreCase(questionWords[j])){
						//add PollID to the column if it exists
						columnExist = true;
						PreparedStatement ps1 = conn.prepareStatement("INSERT INTO Words (`"+buffer+"`) VALUES ('"+ pollID +"');");
						ps1.executeUpdate();
					}
					
					
				}
				if(!columnExist){
					//Add column and add pollID to that column
					System.out.println("Creating New Column");
					PreparedStatement ps2 = conn.prepareStatement("ALTER TABLE Words ADD `"+questionWords[j]+"` int(11)"+";");
					ps2.executeUpdate();
					PreparedStatement ps3 = conn.prepareStatement("INSERT INTO Words (`"+questionWords[j]+"`) VALUES ('"+ pollID +"');");
					ps3.executeUpdate();
				}
			}
			} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
 	}
 	
 	private void addCreatePoll(int userID){
 		int pollID = getNumberOfPolls();
 		ps = null;
		rs = null;
		conn = null;
		try{
			conn = DriverManager.getConnection(connectionString);
			ps = conn.prepareStatement("INSERT INTO CreatedPolls (UserID, PollID) VALUES ('"+ userID +"', '"+pollID+"')");
			ps.executeUpdate();
			
			} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				if(conn != null){
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
 	}
 	
 	
 	public int getLikes(int pollID){
 		ps = null;
		rs = null;
		conn = null;
		try{
			conn = DriverManager.getConnection(connectionString);
			ps = conn.prepareStatement("SELECT * FROM Polls WHERE PollID = " + pollID);

			rs = ps.executeQuery();
			rs.next();
			int num = rs.getInt("Likes");
			return num;
			} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return -1;
 	}
	//Poll Methods
 	
 	public void addLike(int pollID){
 		int curr = getLikes(pollID);
 		curr++;
 		ps = null;
		rs = null;
		conn = null;
		try{
			conn = DriverManager.getConnection(connectionString);
			ps = conn.prepareStatement("UPDATE Polls SET Likes = '"+curr+"' WHERE PollID = '"+pollID+"';");

			ps.executeUpdate();
			
			} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
 	}
 	
 	public void addPoll(int userID, String question, String category, String answer1, String answer2, String answer3, String answer4){
		ps = null;
		rs = null;
		conn = null;
		try{
			conn = DriverManager.getConnection(connectionString);
			ps = conn.prepareStatement("INSERT INTO Polls (Question, Category, Answer1, Answer2, Answer3, Answer4, TotalCount, Count1, Count2, Count3, Count4, Likes) VALUES ('"+ question +"', '"+category+"', '"+answer1+"', '"+answer2+"', '"+answer3+"', '"+answer4+"', '0', '0', '0', '0', '0', '0');");
			ps.executeUpdate();
			addQuestionWords(question);
			addCreatePoll(userID);
			} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				if(conn != null){
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
	}
	
 	//Gets the total number of polls
	public int getNumberOfPolls(){
		ps = null;
		rs = null;
		conn = null;
		try{
			conn = DriverManager.getConnection(connectionString);
			ps = conn.prepareStatement("SELECT * FROM Polls ORDER BY PollID DESC LIMIT 1");

			rs = ps.executeQuery();
			rs.next();
			int num = rs.getInt(1);
			return num;
			} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return -1;
	}
	
	public int getAnswerCount(int pollID, int answerNumber){
		ps = null;
		rs = null;
		conn = null;
		try{
			conn = DriverManager.getConnection(connectionString);
			ps = conn.prepareStatement("SELECT * FROM Polls WHERE PollID = " + pollID);

			rs = ps.executeQuery();
			rs.next();
			int num = 0;
			if(answerNumber == 1){
				num = rs.getInt("Count1");
			}
			if(answerNumber == 2){
				num = rs.getInt("Count2");
			}
			if(answerNumber == 3){
				num = rs.getInt("Count3");
			}
			if(answerNumber == 4){
				num = rs.getInt("Count4");
			}
			
			return num;
			} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return -1;
	}
	
	//Returns the total number users who have answered a poll
	public int getTotalCount(int pollID){
		ps = null;
		rs = null;
		conn = null;
		try{
			conn = DriverManager.getConnection(connectionString);
			ps = conn.prepareStatement("SELECT * FROM Polls WHERE PollID = " + pollID);

			rs = ps.executeQuery();
			rs.next();
			int num = rs.getInt("TotalCount");
			return num;
			} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return -1;
	}
	
	private void increaseTotalCount(int pollID){
		
		PreparedStatement ps1 = null;
		rs = null;
		Connection conn1 = null;
		try{
			conn1 = DriverManager.getConnection(connectionString);
			int currTotal = getTotalCount(pollID);
			int newTotal = currTotal + 1;
			

			ps1 = conn1.prepareStatement("UPDATE Polls SET TotalCount = '"+newTotal+"' WHERE PollID = '"+pollID+"';");
			ps1.executeUpdate();

	} catch (SQLException sqle) {
		System.out.println("sqle: " + sqle.getMessage());
	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
			
			if (ps1 != null) {
				ps1.close();
			}
			if (conn1 != null) {
				conn1.close();
			}
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
	}
	}
	
	private void increaseAnswerCount(int pollID, int answerChoice){
	
		PreparedStatement ps1 = null;
		rs = null;
		Connection conn1 = null;
	try{
		
		conn1 = DriverManager.getConnection(connectionString);
		if(answerChoice == 1){
			int currAnswerTotal = getAnswerCount(pollID, answerChoice);
			int newAnswerTotal = currAnswerTotal+1;
			
			ps1 = conn1.prepareStatement("UPDATE Polls SET Count1 = "+newAnswerTotal+ " WHERE PollID = " + pollID);
			ps1.executeUpdate();
		}
		else if(answerChoice == 2){
			int currAnswerTotal = getAnswerCount(pollID, answerChoice);
			int newAnswerTotal = currAnswerTotal++;
			ps1 = conn1.prepareStatement("UPDATE Polls SET Count2 = "+newAnswerTotal+ " WHERE PollID = " + pollID);
			ps1.executeUpdate();
		}
		else if(answerChoice == 3){
			int currAnswerTotal = getAnswerCount(pollID, answerChoice);
			int newAnswerTotal = currAnswerTotal++;
			ps1 = conn1.prepareStatement("UPDATE Polls SET Count3 = "+newAnswerTotal+ " WHERE PollID = " + pollID);
			ps1.executeUpdate();
		}
		else if(answerChoice == 4){
			int currAnswerTotal = getAnswerCount(pollID, answerChoice);
			int newAnswerTotal = currAnswerTotal++;
			ps1 = conn1.prepareStatement("UPDATE Polls SET Count4 = "+newAnswerTotal+ " WHERE PollID = " + pollID);
			ps1.executeUpdate();
		}

		
		
		} catch (SQLException sqle) {
		System.out.println("sqle: " + sqle.getMessage());
	} finally {
		try {
			if (rs != null) {
				rs.close();
			}
			
			if (ps1 != null) {
				ps1.close();
			}
			if (conn1 != null) {
				conn1.close();
			}
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		}
	}
	}
	//answerChoice = 1 for answer1 etc.
	public void answerPoll(int pollID, int userID, int answerChoice){
		//1. add to UserPolls table
		ps = null;
		rs = null;
		conn = null;
		try{
			conn = DriverManager.getConnection(connectionString);
			
			//1. add to UserPolls table
			ps = conn.prepareStatement("INSERT INTO UserPolls (UserID, PollID, Answer) VALUES ('"+ userID +"', '"+pollID+"', '"+answerChoice+"');");
			ps.executeUpdate();
			System.out.println("here1");
		} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		//2. increase totalcount 
		increaseTotalCount(pollID);
		increaseAnswerCount(pollID, answerChoice);
	
	
	}
	
	public Poll getPoll(int pollID){
		ps = null;
		rs = null;
		conn = null;
		try{
			conn = DriverManager.getConnection(connectionString);
			ps = conn.prepareStatement("SELECT * FROM Polls WHERE PollID = " + pollID);

			rs = ps.executeQuery();
			rs.next();
			String[] answers;
			String answer1 = rs.getString("Answer1");
			String answer2 = rs.getString("Answer2");
			String answer3 = rs.getString("Answer3");
			String answer4 = rs.getString("Answer4");
			String question = rs.getString("Question");
			String category = rs.getString("Category");
			if(answer3 == null){
				answers = new String[2];
				answers[0] = answer1;
				answers[1] = answer2;
			}
			else if(answer4 == null){
				answers = new String[3];
				answers[0] = answer1;
				answers[1] = answer2;
				answers[2] = answer3;
			}
			else{
				answers = new String[4];
				answers[0] = answer1;
				answers[1] = answer2;
				answers[2] = answer3;
				answers[3] = answer4;
			}
			//String[] answers, String question, String category
			Poll temp = new Poll(answers, question, category,pollID);
			return temp;
			} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return null;
	}
	
	//string to pollID
	public HashMap<String, ArrayList<Integer> > getKeyWordMap(){
		HashMap<String, ArrayList<Integer> > keywords = new HashMap<String, ArrayList<Integer> >();
		ArrayList<String> words = new ArrayList<String>();
		ps = null;
		rs = null;
		conn = null;
		try{
			conn = DriverManager.getConnection(connectionString);		
			ps = conn.prepareStatement("SELECT * from Words;");
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			for(int i = 1; i <= rsmd.getColumnCount(); i++){
				String buffer = rsmd.getColumnName(i);
				System.out.println(buffer);

				
					words.add(buffer);
				
			}
			for(int j = 0; j < words.size(); j++){
				ArrayList<Integer> polls = new ArrayList<Integer>();
				ps = null;
				rs = null;
				ps = conn.prepareStatement("SELECT * from Words;");
				rs = ps.executeQuery();
				while(rs.next()){
					int poll = rs.getInt(words.get(j));
					if(poll != 0){
						System.out.println("pollid is: " + poll);
						polls.add(poll);
					}
				}
				keywords.put(words.get(j), polls);
			}
			
			
			return keywords;
			} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return null;
	}
	
	//Returns HashMap<AnswerNumber, List of UserIDs>
	public HashMap<Integer, ArrayList<Integer> > getUsersWhoAnsweredPoll(int PollID){
		HashMap<Integer, ArrayList<Integer> > users = new HashMap<Integer, ArrayList<Integer> >();
		ps = null;
		rs = null;
		conn = null;
		try{
			conn = DriverManager.getConnection(connectionString);		
			ps = conn.prepareStatement("SELECT * from Polls where PollID = " + PollID + ";");
			rs = ps.executeQuery();
			rs.next();
			int numAnswers = 2;
			if(rs.getString("Answer3") != null){
				numAnswers = 3;
			}
			if(rs.getString("Answer4") != null){
				numAnswers = 4;
			}
			for(int i = 1; i <= numAnswers; i++){
				ArrayList<Integer> userList = getUsersThatAnsweredCertainAnswer(PollID, i);
				users.put(i, userList);
			}
			return users;
			} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		
		return users;
	}
	
	
	public HashMap<String, ArrayList<Integer> > getPollsByCategory(String[] categories){
		HashMap<String, ArrayList<Integer> > Polls = new HashMap<String, ArrayList<Integer> >();
		for(int i = 0; i < categories.length; i++){
			ArrayList<Integer> poll = getPollsByCertainCategory(categories[i]);
			Polls.put(categories[i], poll);
		}
		
		return Polls;
	}
	
	public ArrayList<Integer> getPollsByCertainCategory(String category){
		ArrayList<Integer> pollID = new ArrayList<Integer>();
		ps = null;
		rs = null;
		conn = null;
		try{
			conn = DriverManager.getConnection(connectionString);		
			ps = conn.prepareStatement("SELECT * from Polls");
			rs = ps.executeQuery();
			while(rs.next()){
				if(rs.getString("Category").equalsIgnoreCase(category)){
					pollID.add(rs.getInt("PollID"));
				}
			}
			return pollID;
			} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return pollID;
	}
	//Finds all pollIDs with answernumbers that a user answered
	//Returns HashMap<PollID, AnswerNumber>
	public ArrayList<Integer> getPollsCreatedByUser(int userID){
		ArrayList<Integer> polls = new ArrayList<Integer>();
		ps = null;
		rs = null;
		conn = null;
		try{
			conn = DriverManager.getConnection(connectionString);		
			ps = conn.prepareStatement("SELECT * from CreatedPolls");
			rs = ps.executeQuery();
			while(rs.next()){
				if(rs.getInt("UserID") == userID){
					polls.add(rs.getInt("PollID"));
				}
			}
			
			} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
	}
	return polls;
}
	
	public HashMap<Integer, ArrayList<Integer> > getPollsCreatedByAllUsers(){
		HashMap<Integer, ArrayList<Integer> > pollsForAll = new HashMap<Integer, ArrayList<Integer> >();
		for(int i = 1; i <= getNumberOfUsers(); i++){
			ArrayList<Integer> polls = getPollsCreatedByUser(i);
			pollsForAll.put(i, polls);
		}
		return pollsForAll;
	}
	
	public ArrayList<Integer> getPollsThatUserAnswered(int UserID){
		ArrayList<Integer> polls = new ArrayList<Integer>();
		ps = null;
		rs = null;
		conn = null;
		try{
			conn = DriverManager.getConnection(connectionString);		
			ps = conn.prepareStatement("SELECT * from UserPolls where UserID = " + UserID + ";");
			rs = ps.executeQuery();
			while(rs.next()){
				polls.add(rs.getInt("PollID"));
			}
			
			} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		return polls;
	}

	
	//
	public HashMap<Integer, Poll> getAllPolls(){
		int totalNumber = getNumberOfPolls();
		HashMap<Integer, Poll> allPolls = new HashMap<Integer, Poll>();
		for(int i = 1; i<= totalNumber; i++){
			Poll buffer = getPoll(i);
			allPolls.put(i, buffer);
		}
		return allPolls;
	}
	
	//Finds all users that answered a certain poll with a certain answer
	//Returns ArrayList<UserID>
	public ArrayList<Integer> getUsersThatAnsweredCertainAnswer(int pollID, int answerNum){
		ArrayList<Integer> users = new ArrayList<Integer>();
		ps = null;
		rs = null;
		conn = null;
		try{
			conn = DriverManager.getConnection(connectionString);		
			ps = conn.prepareStatement("SELECT * from UserPolls where PollID = " + pollID + " and Answer = " + answerNum + ";");
			rs = ps.executeQuery();
			while(rs.next()){
				users.add(rs.getInt(1));
			}
			
			} catch (SQLException sqle) {
			System.out.println("sqle: " + sqle.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException sqle) {
				System.out.println("sqle: " + sqle.getMessage());
			}
		}
		
		return users;
	}
	
	public HashMap<Integer, ArrayList<Integer> > getPollsThatAllUsersAnswered(){
		int totalUsers = getNumberOfUsers();
		HashMap<Integer, ArrayList<Integer> > pollsForAllUsers = new HashMap<Integer, ArrayList<Integer> >();
		for(int i = 1; i <= totalUsers; i++){
			ArrayList<Integer> polls = getPollsThatUserAnswered(i);
			pollsForAllUsers.put(i, polls);
		}
		return pollsForAllUsers;
	}
	
	
	/*public static void main (String [] args){
		DatabaseInteraction db = new DatabaseInteraction();
		db.addUser("me@gmail.com", "MyPassword", "LA", 1, 0, 1, 0, "myUsername");
		//Test for null inputs
		db.addPoll(1,"What is life?", "Category1", "SampleAnswer1", "SampleAnswer2", null, null);
		
		if(db.userExist("me@gmail.com")){
			System.out.println("In the system");
		}
		else{
			System.out.println("Not in the System");
		}
		User test1 = db.getUser(1);

	

		
		
		System.out.println("Test1's ID is: "+db.getUserID("me@gmail.com"));

		System.out.println("Number of Users: " + db.getNumberOfUsers());
		System.out.println("Number of Polls: " + db.getNumberOfPolls());
		
		db.addFollow(1, 2);
		db.addFollow(3, 4);
		if(db.followExist(1,2)){
			System.out.println("Follow Exists");
		}
		else{
			System.out.println("Follow Does Not Exist");
		}
		 ArrayList<Integer> Follows = db.getFollows(1);
		 for(int i = 0; i < Follows.size(); i++){
			 System.out.println("UserID 1 Follows: " + Follows.get(i));
		 }
		 ArrayList<Integer> Followers = db.getFollowers(2);
		 for(int i = 0; i < Follows.size(); i++){
			 System.out.println("UserID 2 is followed by : " + Followers.get(i));
		 }
		 
		 System.out.println("Poll ID 1 has a total count of: " +db.getTotalCount(1));
		 System.out.println("Poll ID 1's answer1 has a count of: " + db.getAnswerCount(1, 1));
		 db.answerPoll(1, 1, 1);
		 System.out.println("Now Poll ID 1 has a total count of: " +db.getTotalCount(1));
		 System.out.println("Now Poll ID 1's answer1 has a count of: " + db.getAnswerCount(1, 1));
		 //Poll temp = db.getPoll(1);
		// System.out.println(temp.getID());
		 HashMap<Integer, ArrayList<Integer> > Answers = db.getUsersWhoAnsweredPoll(1);
		 HashMap<String, ArrayList<Integer> > keywords = db.getKeyWordMap();
		
		 System.out.println("");
		 ArrayList<Integer> ids = keywords.get("is");
		 for(int i = 0 ; i < ids.size(); i++){
			 System.out.println(ids.get(i));
		 }
		 HashMap<Integer , Poll> allPolls = db.getAllPolls();
		 for(int i = 1; i <= db.getNumberOfPolls(); i++){
			 System.out.println("All Polls: " + allPolls.get(i).getQuestion());
		 }
		 
		 

		 
	}
	*/
	

}
	

