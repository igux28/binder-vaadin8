package com.example.binder.test;


import com.vaadin.data.Binder;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class MyCheckBoxComponent extends VerticalLayout {

	private static final long serialVersionUID = -5037141605397053284L;

	Binder<Dummy> binder = new Binder<Dummy>(Dummy.class);
	
	CheckBox checkbox = new CheckBox();
	Dummy dummy = new Dummy();
	
	public MyCheckBoxComponent() {
		super();
		this.setSizeFull();
		
		dummy.setChecked(true);
		binder.bind(checkbox, Dummy::isChecked, Dummy::setChecked);
		binder.readBean(dummy);
		
		HorizontalLayout hl = new HorizontalLayout();
        Button button = new Button("Check Bean status", e -> {
            try {
                binder.writeBean(dummy);
            } catch (Exception ex) {
                Notification.show("Oh no, error:" + ex);
            }
            Notification.show("Current bean name: " + dummy.isChecked());
        });
        hl.addComponents(new Label("CheckBox"), button);
		
		addComponents(hl, checkbox);
		
	}
	
	static class Dummy {
		private boolean checked;

		public Boolean isChecked() {
			return checked;
		}

		public void setChecked(Boolean checked) {
			this.checked = checked;
		}


	}
}
