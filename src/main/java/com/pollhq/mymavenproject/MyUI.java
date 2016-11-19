package com.pollhq.mymavenproject;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import logic.User;
import logic.UserData;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {
	
	public static final String MAINVIEW = "poll";
	private User user;
	private UserData userData;
	private PollView pollView;
	private LoginView loginView;
	private RegistrationView registrationView;
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        //final LoginView design = new LoginView();
        //setContent(design);
    	pollView = new PollView(this);
    	getPage().setTitle("PollHQ");
		Navigator nav = new Navigator(this, this);
        nav.addView("", new LoginView(this));
        nav.addView("poll", pollView);
        nav.addView("registration", new RegistrationView(this));
        nav.addView("create", new CreateView(this));
       
    }
    
    public void setUser(User user){
    	this.user = user;
    }
   
   public void setUserToPoll(){
	   pollView.setUser();
   }
   
   public User getUser(){
	   return user;
   }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    
    
}
