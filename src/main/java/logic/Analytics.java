package logic;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

//import server.AgeChart;

public class Analytics{  //implements Runnable{
private int numAnswered;
private HashMap<Integer,Integer> count;
private Map<String, Integer> cities;
private int[][] gender;
private int[][] race;
private int[][] education;
private int[][] ageRange;
private  HashMap<Integer,ArrayList<Integer>> usersMap;
private DatabaseInteraction di;
private int poll_ID;
private int Answernum;
//private AgeChart chart;

public Analytics(int poll_ID){
	this.poll_ID = poll_ID;
	di = new DatabaseInteraction();
	usersMap = di.getUsersWhoAnsweredPoll(poll_ID);
	int userSum = 0;
	for(int i=1;i<=usersMap.size();i++){
		userSum+=usersMap.get(i).size();
	}
	numAnswered = userSum;
	Answernum = usersMap.size();
	count= new HashMap<Integer,Integer>();
	gender = new int[Answernum][2];
	//need fill out
	race = new int[Answernum][6];
	education = new int[Answernum][4];
	ageRange = new int[Answernum][6];
	for(int i=1;i<Answernum+1;i++){
		if(usersMap.get(i)!=null){
		count.put(i,usersMap.get(i).size());
		for(int j=0;j<2;j++){
			int sum = 0;
			for(int k =0;k<usersMap.get(i).size();k++){
				if(di.getUser(usersMap.get(i).get(k)).getGender()==j){
					sum++;
				}
			}
			gender[i-1][j]=sum;
		}
		for(int j=0;j<6;j++){
			int sum = 0;
			for(int k =0;k<usersMap.get(i).size();k++){
				if(di.getUser(usersMap.get(i).get(k)).getRace()==j){
					sum++;
				}
			}
			race[i-1][j]=sum;
		}
		for(int j=0;j<4;j++){
			int sum = 0;
			for(int k =0;k<usersMap.get(i).size();k++){
				if(di.getUser(usersMap.get(i).get(k)).getEducation()==j){
					sum++;
				}
			}
			education[i-1][j]=sum;
		}
		for(int j=0;j<6;j++){
			int sum = 0;
			for(int k =0;k<usersMap.get(i).size();k++){
				if(di.getUser(usersMap.get(i).get(k)).getAgeRange()==j){
					sum++;
				}
			}
			ageRange[i-1][j]=sum;
		}
	}
	}
	//chart = new AgeChart(this);
}
/*public void increment(){
	numAnswered++;
}*/
public void update(int index,User user){
	di.answerPoll(poll_ID, user.getUserID(), index);
	/*numAnswered++;
	count[index]++;
	gender[index][user.getGender()]++;
	race[index][user.getRace()]++;
	education[index][user.getEducation()]++;
	ageRange[index][user.getAgeRange()]++;*/
}
public HashMap<Integer,Integer> getAnswerCount(){
	return count;
}
public int[][] getGender(){
	return gender;
}
public int[][] getRace(){
	return race;
}
public int [][] getEducation(){	
	return education;
}
public int[][] getAgeRange(){
	return ageRange;
}

	public void run(){
		usersMap = di.getUsersWhoAnsweredPoll(poll_ID);
		for(int i=1;i<Answernum+1;i++){
			if(usersMap.get(i)!=null){
			count.put(i,usersMap.get(i).size());
			for(int j=0;j<2;j++){
				int sum = 0;
				for(int k =0;k<usersMap.get(i).size();k++){
					if(di.getUser(usersMap.get(i).get(k)).getGender()==j){
						sum++;
					}
				}
				gender[i-1][j]=sum;
			}
			for(int j=0;j<6;j++){
				int sum = 0;
				for(int k =0;k<usersMap.get(i).size();k++){
					if(di.getUser(usersMap.get(i).get(k)).getRace()==j){
						sum++;
					}
				}
				race[i-1][j]=sum;
			}
			for(int j=0;j<4;j++){
				int sum = 0;
				for(int k =0;k<usersMap.get(i).size();k++){
					if(di.getUser(usersMap.get(i).get(k)).getEducation()==j){
						sum++;
					}
				}
				education[i-1][j]=sum;
			}
			for(int j=0;j<6;j++){
				int sum = 0;
				for(int k =0;k<usersMap.get(i).size();k++){
					if(di.getUser(usersMap.get(i).get(k)).getAgeRange()==j){
						sum++;
					}
				}
				ageRange[i-1][j]=sum;
			}
			}
		}
	}
}
