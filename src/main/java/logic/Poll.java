package logic;
import java.io.Serializable;

public class Poll implements Serializable {
public static final long SerialVersionUID = 2L;
private String[] answers;
private String question;
//private int[] Answercount;
private int Poll_ID;
private Analytics analytics;
private String category;
private int numberOfAnswers;
private int likes;
//private int numberOfUsersAnswered;
DatabaseInteraction di;

public Poll(String[] answers, String question, String category,int id) {
    this.question = question;
    this.answers = answers;
    this.category = category;
    Poll_ID = id;
    numberOfAnswers=answers.length;
    //numberOfUsersAnswered=0;
    //Answercount = new int[numberOfAnswers];
    /*for(int i=0;i<numberOfAnswers;i++){
        Answercount[i] = 0;
    }*/
    analytics = new Analytics(Poll_ID);
    di = new DatabaseInteraction();
    likes = di.getLikes(Poll_ID);
}

public int getID(){
    return Poll_ID;
}
public int getLikes(){
    return likes;
}
/*public void chooseAnswer(int choice){
    Answercount[choice]++;
}*/
/*public void increment(){
    //numberOfUsersAnswered++;
    analytics.increment();
}*/
public String getQuestion(){
    return question;
}
public String[] getAnswers(){
    return answers;
}
public String getCategory(){
    return category;
}
public Analytics getAnalytics(){
    return analytics;
}

public void answerQuestion(int index,User user){
    //Answercount[index]++;
    analytics.update(index+1,user);
    di.answerPoll(Poll_ID, user.getUserID(), index);
}
/*private void updateAnaytics(int index,User user){
    analytics.update(index,user);
}*/

public void display(){
    System.out.println(question);
    for(int i=0;i<numberOfAnswers;i++){
        System.out.println(answers[i]);
    }
}

}

