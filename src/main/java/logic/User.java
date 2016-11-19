package logic;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
private String email;
private String password;
//private String city;
private String username;
private int ageRange;
private int gender;
private int race;
private int education;
private int userID;
//private Vector<Integer> following;
//private Vector<Integer> follower;
//private Vector<Integer> pollID;
//private Vector<Integer> createdPoll;
private Poll currentPoll;
private DatabaseInteraction di;

public User(String email,String password, String city, int ageRange, 
			int gender, int race, int education, String username){
	this.email = email;
	this.password = password;
	//this.city = city;
	this.ageRange = ageRange;
	this.gender = gender;
	this.race = race;
	this.education = education;
	this.username = username;
	//following = new Vector<Integer>();
	//follower = new Vector<Integer>();
	//pollID = new Vector<Integer>();
	//createdPoll = new Vector<Integer>();
	//pollID.addAll(di.getPollsThatUserAnswered(userID));
	//createdPoll.addAll(di.getPollsCreatedByUser(userID));
	//following.addAll(di.getFollows(userID));
	//follower.addAll(di.getFollowers(userID));
	currentPoll=null;
	di = new DatabaseInteraction();
}

public void assignID(int id){
	this.userID = id;
}
public String getEmail(){
	return email;
}
public String getPassword(){
	return password;
}
/*public String getCity(){
	return city;
}*/
public int getAgeRange(){
	return ageRange;
}
public int getGender(){
	return gender;
}
public int getRace(){
	return race;
}
public int getEducation(){
	return education;
}
public int getUserID(){
	return userID;
}
public String getUsername(){
	return username;
}
public void setPassword(String password){
	this.password = password;
}
/*public Vector<Integer> getPolls(){
	return pollID;
}*/

/*public void answerPoll(int id){
	pollID.add(id);
}*/
/*public void createPoll(int id){
	createdPoll.add(id);
}*/

public Vector<Integer> getAnsweredPolls(){
    Vector<Integer> polls = new Vector<Integer>();
    polls.addAll(di.getPollsThatUserAnswered(userID));
    return polls;
	//return pollID;
}

public Vector<Integer> getCreatedPolls(){
	Vector<Integer> polls = new Vector<Integer>();
    polls.addAll(di.getPollsCreatedByUser(userID));
    return polls;
	//return createdPoll;
}
public void assignCurrentPoll(Poll poll){
	currentPoll= poll;
}
public Poll getCurrentPoll(){
	return currentPoll;
}
public void FollowOther(int id){
	di.addFollow(userID, id);
}
public ArrayList<Integer> getFollowers(){
	return di.getFollowers(userID);
}
public ArrayList<Integer> getFollowing(){
	return di.getFollows(userID);
}

}