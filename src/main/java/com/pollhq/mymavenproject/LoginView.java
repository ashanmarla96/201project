package com.pollhq.mymavenproject;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import logic.User;
import logic.UserData;



@SuppressWarnings("serial")

public class LoginView extends LoginScreen implements View {
	private UserData userData;
	private Window subWindow;
	private User user;
	private MyUI myUI;
	public LoginView(MyUI myUI){
		super();
		this.myUI = myUI;
		userData = new UserData();
		subWindow = new Window("Unable to log in");
		subWindow.setHeight("200px");
		subWindow.setWidth("200px");
		addAction();
	}
	
	
	
	public void addAction(){
		//loginButton.addClickListener(event -> getUI().getNavigator().navigateTo("poll"));
		loginButton.addClickListener(new ClickListener(){
			
			
			@Override
			public void buttonClick(ClickEvent event) {
				
				String email = userField.getValue();
				String password = passField.getValue();
				if (email == "")
				{
					VerticalLayout subContent = new VerticalLayout();
					subContent.setMargin(true);
					subWindow.setContent(subContent);
					subContent.addComponent(new Label("Please enter your email"));
					subWindow.center();
					getUI().addWindow(subWindow);
				}
				else if (password == "")
				{
					VerticalLayout subContent = new VerticalLayout();
					subContent.setMargin(true);
					subWindow.setContent(subContent);
					subContent.addComponent(new Label("Please enter your password"));
					subWindow.center();
					getUI().addWindow(subWindow);
				}
				else
				{
					boolean check = userData.checkUser(email);
					if (!check)
					{
						VerticalLayout subContent = new VerticalLayout();
						subContent.setMargin(true);
						subWindow.setContent(subContent);
						subContent.addComponent(new Label("Sorry, the email does not exist"));
						subWindow.center();
						getUI().addWindow(subWindow);
					}
					else
					{
						//event -> getUI().getNavigator().navigateTo("poll");
						if (!userData.checkPassword(email, password))
						{
							VerticalLayout subContent = new VerticalLayout();
							subContent.setMargin(true);
							subWindow.setContent(subContent);
							subContent.addComponent(new Label("Sorry, please enter the correct password"));
							subWindow.center();
							getUI().addWindow(subWindow);
						}
						else
						{
							user = userData.getUserByEmail(email);
							//System.out.println(user == null);
							//System.out.println(myUI == null);
							myUI.setUser(user);
							myUI.setUserToPoll();
							getUI().getNavigator().navigateTo("poll");
						}
						
					}
				}		
			}
			
			
		});
		registerButton.addClickListener(new ClickListener(){
		public void buttonClick(ClickEvent event) {
			
			getUI().getNavigator().navigateTo("registration");

			
		}
		});
	}
	
	public User getUser(){
		return user;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
