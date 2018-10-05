package com.example.binder.test;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
public class MyUI extends UI {

	
	String[] tabs = {"Mercury", "Venus", "Earth", "Mars"};
	String[] accordions = {"Apollo I"/*, "Apollo XIII", "Apollo XX", "Undefined"*/};
	TabSheet tabsheet = new TabSheet();
	TabSheet tabsheetWithAccordion = new TabSheet();
	Accordion accordion = new Accordion();
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        Label separator = new Label("<hr />",ContentMode.HTML);
        	separator.setWidth(100f, Unit.PERCENTAGE);
        layout.addComponents(createMultipleComponents(), 
        		separator,
        		tabsheet,
        		separator,
        		tabsheetWithAccordion,
        		separator,
        		accordion);
        
        createTabs();
        createTabsWithAccordion();
		createAccordionOutOfTab();
		
		
        
        setContent(layout);
    }
    
    private HorizontalLayout createMultipleComponents() {
		HorizontalLayout multiple = new HorizontalLayout();
		multiple.addComponents(new MyRichComponent(), 
							  new MyTextFieldComponent(),
							  new MyCheckBoxComponent());
		return multiple;
    }




	private void createAccordionOutOfTab() {
		VerticalLayout accordionContainer = new VerticalLayout();
		accordion.addTab(accordionContainer, "Accordion out of the tab");
		accordionContainer.addComponent(createMultipleComponents());
	}




	private void createTabs() {
		for (String caption: tabs) {
			VerticalLayout tabContainer = new VerticalLayout();
			tabsheet.addTab(tabContainer, caption, VaadinIcons.ANCHOR);
			tabContainer.addComponent(createMultipleComponents());
			//createAccordionsWithinTab(tabContainer);
           
        }
	}
	
	private void createTabsWithAccordion() {
		for (String caption: tabs) {
			VerticalLayout tabContainer = new VerticalLayout();
			tabsheetWithAccordion.addTab(tabContainer, caption, VaadinIcons.BED);
			createAccordionsWithinTab(tabContainer);
           
        }
	}

	private void createAccordionsWithinTab(VerticalLayout tabContainer) {
		Accordion ac = new Accordion();
		for (String caption: accordions) {
			VerticalLayout accordionContainer = new VerticalLayout();
			ac.addTab(accordionContainer, caption);
			accordionContainer.addComponent(createMultipleComponents());
			tabContainer.addComponent(ac);
		}
	}

	private void buildRichTextArea(VerticalLayout accordionContainer) {
		VerticalLayout richTextContainer = new VerticalLayout();
		accordionContainer.addComponent(richTextContainer);
		richTextContainer.addComponent(new MyRichComponent());
	}
    
    
    

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
