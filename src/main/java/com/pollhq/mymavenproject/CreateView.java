package com.pollhq.mymavenproject;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import logic.UserData;

public class CreateView extends CreateWindow implements View{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MyUI myUI;
	private Window subWindow;
	public CreateView(MyUI myUI){
		addAction();
		this.myUI = myUI;
		subWindow = new Window("Unable to create poll");
		subWindow.setHeight("250px");
		subWindow.setWidth("250px");
	}
	public void addAction(){
		pollButton.addClickListener(event -> getUI().getNavigator().navigateTo("poll"));
		logoutButton.addClickListener(event -> getUI().getNavigator().navigateTo(""));
		
		submitButton.addClickListener(new ClickListener(){
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
					if(!questionField.getValue().equals("")){
						if (op1Field.getValue() .equals("") || op2Field.getValue().equals(""))
						{
							VerticalLayout subContent = new VerticalLayout();
							subContent.setMargin(true);
							subWindow.setContent(subContent);
							subContent.addComponent(new Label("You have to have at least two answer options."));
							subWindow.center();
							getUI().addWindow(subWindow);
						}
						else
						{
							String[] answers = new String[4];
							answers[0] =op1Field.getValue(); 
							answers[1] =op2Field.getValue(); 
							answers[2] =op3Field.getValue(); 
							answers[3] =op4Field.getValue(); 
							UserData data = new UserData();
							data.addPoll(answers, questionField.getValue(), myUI.getUser(), (String)categoryBox.getValue());
							
							/*
							subWindow = new Window("Analytics");
							DatabaseInteraction di = new DatabaseInteraction();
							Analytics anal = new Analytics(di.getNumberOfPolls());
							Chart chart = new AgeChart(anal).createChart();
							subWindow.setHeight("400px");
							subWindow.setWidth("400px");
							VerticalLayout subContent = new VerticalLayout();
							subContent.setMargin(true);
							subWindow.setContent(subContent);
							subContent.addComponent(chart);
							subWindow.center();
							getUI().addWindow(subWindow);
							*/
						}
					}
					else
					{
						VerticalLayout subContent = new VerticalLayout();
						subContent.setMargin(true);
						subWindow.setContent(subContent);
						subContent.addComponent(new Label("Your question cannot be empty."));
						subWindow.center();
						getUI().addWindow(subWindow);
					}
			}
		});
		
		
	}
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}

