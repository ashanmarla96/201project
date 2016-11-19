package com.pollhq.mymavenproject;

import java.util.HashSet;
import java.util.Set;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.OptionGroup;

import logic.Poll;
import logic.User;
import logic.UserData;

public class PollView extends PollScreen implements View{
	private MyUI myUI;
	private User user;
	private Poll currPoll;
	private UserData ud;
	private OptionGroup og;

	public PollView(MyUI myUI){
		super();
		this.myUI = myUI;
		ud = new UserData();
		addAction();
		og = new OptionGroup();
	}
	
	private void populatePoll(){
		if(ud.generatePoll(user)){
			currPoll = user.getCurrentPoll();
			PollComponent comp = new PollComponent(currPoll.getQuestion(),currPoll.getLikes()+"");
			comp.addAction(ud, currPoll.getID());
			pollPanel.setContent(comp);
			String[] answers = currPoll.getAnswers();
			for(int i=0;i<answers.length;i++){
					if(!answers[i].equals("")){
						og.addItem(answers[i]);
				}
				
			}
			/*if(answers.length<3){
				og.setItemEnabled(2, false);
			}*/
			answerPanel.setContent(og);
		}
		else{
			
			
		}
	}

	public void setPoll(Poll poll){
		currPoll = poll;
		PollComponent comp = new PollComponent(currPoll.getQuestion(),currPoll.getLikes()+"");
		comp.addAction(ud, currPoll.getID());
		pollPanel.setContent(comp);
		og = new OptionGroup();
		String[] answers = currPoll.getAnswers();
		for(int i=0;i<answers.length;i++){
		og.addItem(answers[i]);
		}
		answerPanel.setContent(og);
	}
	public void setUser(){
		user = myUI.getUser();
		populatePoll();
	}
	
	public void addAction(){
		createButton.addClickListener(event -> getUI().getNavigator().navigateTo("create"));
		logoutButton.addClickListener(event -> getUI().getNavigator().navigateTo(""));
		submitButton.addClickListener(new ClickListener(){
			@Override
			public void buttonClick(ClickEvent event) {
				ud.submitAnswer((String)og.getValue());
				getUI().getNavigator().navigateTo("search");
			}
			});
		newPollButton.addClickListener(new ClickListener(){
			@Override
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo("search");
			}
			});
		
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
