package com.example.binder.test;

import com.vaadin.data.Binder;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.VerticalLayout;

public class MyRichVaadin8Component extends VerticalLayout {

	private static final long serialVersionUID = -5037141605397053284L;

	Binder<Dummy> binder = new Binder<Dummy>(Dummy.class);
	
	RichTextArea rich = new RichTextArea();
	Dummy dummy = new Dummy();
	
	public MyRichVaadin8Component() {
		super();
		this.setSizeFull();
		
		dummy.setText("The quick brown fox jumps!!!");
		binder.bind(rich, Dummy::getText, Dummy::setText);
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
        hl.addComponents(new Label("Vaadin 8 RichText"), button);
		
		addComponents(hl, rich);
		
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
