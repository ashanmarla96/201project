package com.pollhq.mymavenproject;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

import logic.UserData;

public class PollComponent extends PollDesign{
    public PollComponent(String question, String like){
        questionLabel.setValue(question);
        likeLabel.setValue(like);
    }
    public void addAction(UserData data, int pollID){
        likeButton.addClickListener(new ClickListener(){
            @Override
            public void buttonClick(ClickEvent event) {
                data.addLike(pollID);
                likeLabel.setValue(Integer.toString(data.getLikes(pollID)));
            }
            });
    }
}