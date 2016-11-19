package logic;
import java.util.Scanner;

public class main{
	
	public static void main(String args[]){
		UserData ldb = new UserData();
		while (true){
			System.out.println("If you want to log in, type log in. If you want to "
					+ "retister, type register");
			Scanner sc = new Scanner (System.in);
			String s = sc.nextLine();
			if (s.equals("log in")){
				
				System.out.println("Please enter your email");
				String email = sc.nextLine();
				System.out.println("Please enter your password");
				String password = sc.nextLine();
				boolean exist = ldb.checkUser(email);
				if (exist)
				{
					System.out.println("successfully logged in");
					User user = ldb.getUserByEmail(email);
					System.out.println("user ID: " + user.getUserID());
					//GamePlay gp = new GamePlay(ldb,user);
					//gp.startGame();
					break;
				}
				else
				{
					System.out.println("email does not exist");
				}
			
			}
			else if (s.equals("register")){
				boolean register = false;
				String email = "";
				String password = "";
				String username = "";
				int race = 0;
				int age = 0;
				int education = 0;
				int gender = 0;
				String city = "";
				while (!register){
					System.out.println("Please enter your email");
					email = sc.nextLine();
					System.out.println("Please enter your password");
					password = sc.nextLine();
					System.out.println("Please enter your username");
					username = sc.nextLine();
					System.out.println("Please enter your race");
					race = sc.nextInt();
					System.out.println("Please enter your age range");
					age = sc.nextInt();
					System.out.println("Please enter your educational level");
					education = sc.nextInt();
					System.out.println("Please enter your gender");
					gender = sc.nextInt();
					System.out.println("Please enter your city");
					city = sc.nextLine();
					city = sc.nextLine();
					register = ldb.register(email, password, city, age, gender, race, education, username);
				}
				
				System.out.println("successfully registered");
				
				System.out.println();
			}
		}
	} 

}
