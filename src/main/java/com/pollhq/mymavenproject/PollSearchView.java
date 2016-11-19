package com.pollhq.mymavenproject;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import logic.User;
import logic.UserData;

public class PollSearchView extends PollSearchWindow implements View{

	private MyUI myUI;
	private User user;
	private UserData userData;
	PollSearchView(MyUI myUI){
		super();
		this.myUI = myUI;
		user = myUI.getUser();
		userData = new UserData();
		addAction();
	}
	
	private void addAction(){
		searchButton.addClickListener(new ClickListener(){
			@Override
			public void buttonClick(ClickEvent event) {
				String keyword = (String)keywordField.getValue();
				String category = (String)categoryBox.getValue();
				if (keyword.equals(""))
				{
					userData.generateByCategory(user, category);
				}
				else
				{
					userData.generateByKeyword(user, "single", keyword);
				}
			}
			
		});
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
	}

}
