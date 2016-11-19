package logic;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

	public class UserData {
		//private HashMap<Integer, User> currentUsersByID;
		//private HashMap<String, HashSet<Integer>> pollsByCategories;
		private String[] ageRangeOptions;
		private String[] raceOptions;
		private String[] educationOptions;
		private String[] gender;
		//private HashMap<String, Integer> emailMapToUserID;
		private Set<String> cities;
		//private HashMap<Integer, Poll> pollByID;
		//private HashMap<String, HashSet<Integer>> keyWordsMapToPolls;
		private User currentUser;
		private DatabaseInteraction di;
		public UserData(){
			di = new DatabaseInteraction();
			currentUser = null;
			//currentUsersByID = new HashMap<Integer, User>();
			//pollsByCategories = new HashMap<String, HashSet<Integer>>();
			//pollByID = new HashMap<Integer, Poll>();
			ageRangeOptions = new String[7];
			raceOptions = new String[6];
			educationOptions = new String[4];
			//emailMapToUserID = new HashMap<String, Integer>();
			cities = new HashSet<String>(2);
			cities.add("la");
			cities.add("ny");
			
			gender = new String[3];
			gender[0] = "Male";
			gender[1] = "Female";
			gender[2] = "Other";
			
			ageRangeOptions[0] = "0-18";
			ageRangeOptions[1] = "19-25";
			ageRangeOptions[2] = "26-35";
			ageRangeOptions[3] = "36~45";
			ageRangeOptions[4] = "46-55";
			ageRangeOptions[5] = "56-65";
			ageRangeOptions[6] = "65+";
			
			raceOptions[0] = "White";
			raceOptions[1] = "Black";
			raceOptions[2] = "Asian";
			raceOptions[3] = "Hispanic";
			raceOptions[4] = "Indian";
			raceOptions[5] = "Other";
			
			educationOptions[0] = "None";
			educationOptions[1] = "High School";
			educationOptions[2] = "Bachelor's Degree";
			educationOptions[3] = "Professional Degree";
			
			
			
			
			
			//keyWordsMapToPolls = new HashMap<String, HashSet<Integer>>();
		}
		
		/*public int getUserID(){
			return currentUsersByID.size();
		}*/
		
		public Poll getPoll(int id){
			return di.getPoll(id);
		}
		
		/*public void addUser(User user){
			int size = currentUsersByID.size();
			currentUsersByID.put(size, user);
			emailMapToUserID.put(user.getEmail(), size);
			System.out.println("current size:" + size);
			
		}*/
		public int getNumberOfOptions(int pollID){
			Poll p = di.getPoll(pollID);
			String[] answers = p.getAnswers();
			if(answers[2] == null){
				return 2;
			}
			if(answers[3] == null){
				return 3;
			}
			return 4;
			
		}
		
		public HashMap<String, Poll> getAllQuestions(int userID){
			HashMap<String, Poll> allQuestions = new HashMap<String, Poll>();
			ArrayList<Integer> pollIDs = di.getPollsThatUserAnswered(userID);
			for(int i = 0; i < pollIDs.size(); i++){
				allQuestions.put(di.getPoll(pollIDs.get(i)).getQuestion(), di.getPoll(pollIDs.get(i)));
			}
			return allQuestions;
		}
		
		/*public HashMap<String, HashSet<Integer>> getPollsByCategories(){
			return pollsByCategories;
		}*/
		/*public HashMap<Integer,Poll> getPollsByID(){
					return pollByID;
		}*/
		
		/*public HashMap<Integer, User> getUsersByID(){
			return currentUsersByID;
		}
		*/
		public String getAgeRange(int index){
			return ageRangeOptions[index];
		}
		
		public String getEducationLevel(int index){
			return educationOptions[index];
		}
		
		public String getRaceOptions(int index){
			return raceOptions[index];
		}
		
	//changed
		public boolean checkUser(String email){
			//return emailMapToUserID.containsKey(email);
			return di.userExist(email);
		}
	//changed	
		public boolean checkPassword(String email, String password){
			//int id = emailMapToUserID.get(email);
			//User user = currentUsersByID.get(id);
			User user = di.getUser(email);
			if(user.getPassword().equals(password)){
				return true;
			}
			return false;
		}
		
		//need to communicate with database finally
		public boolean cityExist(String city){
			String thecity = city.toLowerCase();
			return cities.contains(thecity);
		}
		//changed
		public boolean register(String email,String password, String city, int ageRange, 
				int gender, int race, int education, String username){
			if(checkUser(email)){
				System.out.println("Your email already exists");
				return false;
			}
			/*else if(!cityExist(city)){
				System.out.println("Your city does not exist");
				return false;
			}*/
			else{
				di.addUser(email, password, city, ageRange, gender, race, education, username);
				System.out.println("successfully regstered");
				User user = di.getUser(di.getUserID(email));
				//System.out.println("Your user ID is:" + user.getUserID());
				return true;
			}
		}
		//changed
		public User getUserByEmail(String email){
			//int id = emailMapToUserID.get(email);
			//User user = currentUsersByID.get(id);
			User user = di.getUser(di.getUserID(email));
			user.assignID(di.getUserID(email));
			return user;
		}
		
		public boolean generatePoll(User user){
			Set<Integer> polls = new HashSet<Integer>();
			for(int i=1;i<di.getNumberOfPolls();i++){
				polls.add(i);
			}
 			if(polls.size()==0){
 				System.out.println("No polls yet");
				return false;
 			}
 			else{
 				Vector<Integer> exclude = new Vector<Integer>();
 				if(user.getAnsweredPolls()!=null){
 					exclude.addAll(user.getAnsweredPolls());
 				}
 				/*if(user.getCreatedPolls()!=null){
 					exclude.addAll(user.getCreatedPolls());
 				}*/
 					int index = generateRandom(polls,exclude);
 				if(index!=-1){
 					//Poll poll = pollByID.get(index);
 					Poll poll = di.getPoll(index);
 					poll.display();
 					//user.answerPoll(poll.getID());
					user.assignCurrentPoll(poll);
					currentUser = user;
 				}
 				else{
 					System.out.println("No poll to display");
					return false;
 				}
				return true;
 			}
 		}
		
		public boolean generateByCategory(User user,String category){
			Set<Integer> polls = new HashSet<Integer>();
			polls.addAll(di.getPollsByCertainCategory(category));
			if(polls.size()==0){
 				System.out.println("No polls yet");
				return false;
 			}
 			else{
 				Vector<Integer> exclude = new Vector<Integer>();
 					exclude.addAll(user.getAnsweredPolls());
 					exclude.addAll(user.getCreatedPolls());
 					int index = generateRandom(polls,exclude);
 				if(index!=-1){
 					//Poll poll = pollByID.get(index);
 					Poll poll = di.getPoll(index);
 					poll.display();
 					//user.answerPoll(poll.getID());
					user.assignCurrentPoll(poll);
					currentUser = user;
 				}
 				else{
 					System.out.println("No poll to display");
					return false;
 				}
				return true;
 			}
		}
		
		public boolean generateByCategoryAndKeyword(User user, String option, String category, String keyword)
		{
			HashSet<Integer> polls = new HashSet<Integer>();
			if (option.equals("single"))
			{	
				polls = searchBySingleWord(keyword);
			}
			else if (option.equals("and"))
			{  
				polls =searchByKeyWordsAnd(keyword);
				
			}
			else if (option.equals("or"))
			{
				polls = searchByKeyWordsOr(keyword);
 			}
			if(polls.size()==0){
 				//System.out.println("No polls yet");
				return false;
 			}
			
			ArrayList<Integer> categoryResult = di.getPollsByCertainCategory(category);
			Iterator<Integer> it = polls.iterator();
			HashSet<Integer> result = new HashSet<>();
			while (it.hasNext())
			{
				int id = it.next();
				if (categoryResult.contains(id))
				{
					result.add(id);
				}
			}
			
			if(result.size()==0){
 				System.out.println("No polls yet");
				return false;
 			}
 			else{
 				Vector<Integer> exclude = new Vector<Integer>();
 					exclude.addAll(user.getAnsweredPolls());
 					//exclude.addAll(user.getCreatedPolls());
 					int index = generateRandom(polls,exclude);
 				if(index!=-1){
 					//Poll poll = pollByID.get(index);
 					Poll poll = di.getPoll(index);
 					poll.display();
 					//user.answerPoll(poll.getID());
					user.assignCurrentPoll(poll);
					currentUser = user;
 				}
 				else{
 					System.out.println("No poll to display");
					return false;
 				}
				return true;
 			}
		}		
			
			
			
		
		
		public boolean generateByKeyword(User user,String option,String keyword){
			HashSet<Integer> polls = new HashSet<Integer>();
			if (option.equals("single"))
			{	
				polls = searchBySingleWord(keyword);
			}
			else if (option.equals("and"))
			{  
				polls =searchByKeyWordsAnd(keyword);
				
			}
			else if (option.equals("or"))
			{
				polls = searchByKeyWordsOr(keyword);
 			}
			if(polls.size()==0){
 				//System.out.println("No polls yet");
				return false;
 			}
 			else{
 				Vector<Integer> exclude = new Vector<Integer>();
 					exclude.addAll(user.getAnsweredPolls());
 					exclude.addAll(user.getCreatedPolls());
 					int index = generateRandom(polls,exclude);
 				if(index!=-1){
 					Poll poll = di.getPoll(index);
 					poll.display();
					user.assignCurrentPoll(poll);
					currentUser = user;
 				}
 				else{
 					//System.out.println("No poll to display");
					return false;
 				}
				return true;
 			}
		}
        
		public int generateRandom(Set<Integer> polls, Vector<Integer> exclude) {
            if (polls.size() == 0)
                return -1;
           Vector<Integer> result = new Vector<Integer>();
           Iterator<Integer> it = polls.iterator();
           while (it.hasNext())
           {
               int id = it.next();
               if (!exclude.contains(id))
               {
                   result.add(id);
               }
           }
           
           int size = result.size();
           if (size == 0)
               return -1;
           Random rand = new Random();
           int index = rand.nextInt(size);
           return result.get(index);
       }
		
		
		public void addPoll(String[] answers,String question, User user, String category){
			/*int size = pollByID.size();
			poll.assignID(size);
			pollByID.put(size,poll);
			String question = poll.getQuestion().replaceAll("-.:_", " ");
			Scanner scanner = new Scanner(question);
			while (scanner.hasNext()){
				String word = scanner.next().toLowerCase();
				if (keyWordsMapToPolls.containsKey(word)){
					keyWordsMapToPolls.get(word).add(size);
				}
				else
				{
					HashSet<Integer> set = new HashSet<Integer>();
					set.add(size);
					keyWordsMapToPolls.put(word, set);
				}
			}
			scanner.close();*/
			di.addPoll(user.getUserID(),question, category, answers[0], answers[1], answers[2], answers[3]);
			//user.createPoll(di.getNumberOfPolls()+1);
			
			/*if (pollsByCategories.containsKey(category)){
				pollsByCategories.get(category).add(size);
			}
			else
			{
				HashSet<Integer> set = new HashSet<Integer>();
				set.add(size);
				pollsByCategories.put(category, set);
			}*/
		}
		
		public HashSet<Integer> searchByKeyWordsAnd(String words){
            Scanner sc = new Scanner(words);
            HashSet<String> keyWords = new HashSet<String>();
            while (sc.hasNext())
            {
                keyWords.add(sc.next());
            }
            sc.close();
            Iterator<Integer> iter = di.getAllPolls().keySet().iterator();
            HashSet<Integer> temp0 = new HashSet<Integer>();
            while (iter.hasNext())
            {
                temp0.add(iter.next());
            }
            Iterator<String> it = keyWords.iterator();
            HashSet<Integer> result = new HashSet<Integer>();
            while (it.hasNext()){
                String word = it.next();
                if (!di.getKeyWordMap().containsKey(word)){
                    return new HashSet<Integer>();
                }
                HashSet<Integer> temp1 = new HashSet<Integer>();
                temp1.addAll(di.getKeyWordMap().get(word));
                Iterator<Integer> it0 = temp1.iterator();
                result= new HashSet<Integer>();
                while (it0.hasNext()){
                    int id = it0.next();
                    if (temp0.contains(id)){
                        result.add(id);
                    }
                }
                temp0 = result;
            }
            return temp0;
        }
		
		public HashSet<Integer> searchByKeyWordsOr(String words){
            HashSet<Integer> result = new HashSet<Integer>();
            Scanner sc = new Scanner(words);
            HashSet<String> keyWords = new HashSet<String>();
            while (sc.hasNext())
            {
                keyWords.add(sc.next());
            }
           // System.out.println("size:" + keyWords.size());
            sc.close();
            Iterator<String> it = keyWords.iterator();
            while (it.hasNext())
            {
                String word = it.next();
               /* if (keyWordsMapToPolls.containsKey(word))
                    result.addAll(keyWordsMapToPolls.get(word));
                   */
                if (di.getKeyWordMap().containsKey(word))
                    result.addAll(di.getKeyWordMap().get(word));
                
            }
            return result;
        }
		
		public HashSet<Integer> searchBySingleWord(String word){
			/*if (keyWordsMapToPolls.containsKey(word))
			{
				return keyWordsMapToPolls.get(word);
			}
			else
			{
				return new HashSet<Integer>();
			}*/
			if (di.getKeyWordMap().containsKey(word))
			{
				ArrayList<Integer> array = di.getKeyWordMap().get(word);
				 HashSet<Integer> set = new HashSet<Integer>();
				set.addAll(array);
				  return set;
			}
			else
			{
				return new HashSet<Integer>();
			}
		}
		

		public void submitAnswer(String answer){
            String[] answers = currentUser.getCurrentPoll().getAnswers();
            for(int i=0;i<answers.length;i++){
                if(answer.equals(answers[i])){
                	currentUser.getCurrentPoll().answerQuestion(i, currentUser);
                }
            }
		}
		
		
		public Set<Integer> CategoriesgetPoll(String category){
			ArrayList<Integer> array = di.getPollsByCertainCategory(category);
			Set<Integer> set = new HashSet<Integer>();
			set.addAll(array);
			return set;
		}
		
		public void FollowOther(int friend_index){
			currentUser.FollowOther(friend_index);
		}
		public ArrayList<Integer> getFollowers(){
			return di.getFollowers(currentUser.getUserID());
		}
		public ArrayList<Integer> getFollowing(){
			return di.getFollows(currentUser.getUserID());
		}
		
		public int getLikes(int pollID){
			return di.getLikes(pollID);
		}
		public void addLike(int pollID){
			di.addLike(pollID);
		}
		
		
		
	}
