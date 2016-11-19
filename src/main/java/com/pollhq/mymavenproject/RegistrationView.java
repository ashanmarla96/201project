package com.pollhq.mymavenproject;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import logic.User;
import logic.UserData;
/*
 * protected Label regLabel;
	protected TextField emailField;
	protected TextField userField;
	protected TextField passField;
	protected TextField locField;
	protected ComboBox ageBox;
	protected ComboBox raceBox;
	protected ComboBox genderBox;
	protected ComboBox eduBox;
	protected Button regButton;
 */
@SuppressWarnings("serial")
public class RegistrationView extends RegistrationWindow implements View{
	
	private MyUI myui;
	private UserData ud;
	private String email;
	private String password;
	private String username;
	private String city;
	private String age;
	private int ageIndex;
	private String race;
	private int raceIndex;
	private String gender;
	private int genderIndex;
	private String edu;
	private int eduIndex;
	private User user;
	
	
	public RegistrationView(MyUI myui){
		super();
		this.myui = myui;
		ud = new UserData();
		addAction();
	}
	

	public void addAction(){
		//loginButton.addClickListener(event -> getUI().getNavigator().navigateTo("poll"));
		regButton.addClickListener(new ClickListener(){
			@Override
			public void buttonClick(ClickEvent event) {
				
				email = emailField.getValue();
				password = passField.getValue();
				username = userField.getValue();
				city = locField.getValue();
				age = (String) ageBox.getValue();
				race = (String) raceBox.getValue();
				gender = (String) genderBox.getValue();
				edu = (String) eduBox.getValue();
				
				if(age.equalsIgnoreCase("0-18")){
					ageIndex = 0;
				}
				if(age.equalsIgnoreCase("19-25")){
					ageIndex = 1;
				}
				if(age.equalsIgnoreCase("26-35")){
					ageIndex = 2;
				}
				if(age.equalsIgnoreCase("36-45")){
					ageIndex = 3;
				}
				if(age.equalsIgnoreCase("46-55")){
					ageIndex = 4;
				}
				if(age.equalsIgnoreCase("56-65")){
					ageIndex = 5;
				}
				if(age.equalsIgnoreCase("65+")){
					ageIndex = 6;
				}
				
				
				
				
				if(race.equalsIgnoreCase("White")){
					raceIndex = 0;
				}
				if(race.equalsIgnoreCase("Black")){
					raceIndex = 1;
				}
				if(race.equalsIgnoreCase("Asian")){
					raceIndex = 2;
				}
				if(race.equalsIgnoreCase("Hispanic")){
					raceIndex = 3;
				}
				if(race.equalsIgnoreCase("Indian")){
					raceIndex = 4;
				}
				if(race.equalsIgnoreCase("Other")){
					raceIndex = 5;
				}
				if(gender.equalsIgnoreCase("Male")){
					genderIndex = 0;
				}
				if(gender.equalsIgnoreCase("Female")){
					genderIndex = 1;
				}
				if(gender.equalsIgnoreCase("Other")){
					genderIndex = 2;
				}
				
				if(edu.equalsIgnoreCase("None")){
					eduIndex = 0;
				}
				if(edu.equalsIgnoreCase("High School")){
					eduIndex = 1;
				}
				if(edu.equalsIgnoreCase("Bachelor's Degree")){
					eduIndex = 2;
				}
				if(edu.equalsIgnoreCase("Professional Degree")){
					eduIndex = 3;
				}
				
				
				boolean ready = checkReady();
				
				if(ready){
					
					user = new User(email,password,city, ageIndex, genderIndex, raceIndex, eduIndex, username);
					if(ud.register(email, password,city, ageIndex, genderIndex, raceIndex, eduIndex, username)){
						myui.setUser(user);
						myui.setUserToPoll();
						getUI().getNavigator().navigateTo("poll");
					}
				}
				
				
			}
		});
	}
	
	private boolean checkReady(){
		boolean ready = true;
		if(email.equals("")){
			ready = false;
		}
		if(password.equals("")){
			ready = false;
		}
		if(username.equals("")){
			ready = false;
		}
		if(city.equals("")){
			ready = false;
		}
		return ready;
		
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
