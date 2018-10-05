package com.example.binder.test;

import com.vaadin.data.Binder;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class MyTextFieldComponent extends VerticalLayout {

	private static final long serialVersionUID = -5037141605397053284L;

	Binder<Dummy> binder = new Binder<Dummy>(Dummy.class);
	
	TextField textField = new TextField();
	Dummy dummy = new Dummy();
	
	public MyTextFieldComponent() {
		super();
		this.setSizeFull();
		
		dummy.setText("The quick brown fox jumps!!!");
		binder.bind(textField, Dummy::getText, Dummy::setText);
		binder.readBean(dummy);
		
		HorizontalLayout hl = new HorizontalLayout();
        Button button = new Button("Check Bean status", e -> {
            try {
                binder.writeBean(dummy);
            } catch (Exception ex) {
                Notification.show("Oh no, error:" + ex);
            }
            Notification.show("Current bean name: " + dummy.getText());
        });
        hl.addComponents(new Label("TextField"), button);
		
		addComponents(hl, textField);
		
	}
	
	static class Dummy {
		private String text;

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}
	}
}
