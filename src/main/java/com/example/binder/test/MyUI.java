package com.example.binder.test;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Accordion;
import com.vaadin.ui.HorizontalLayout;
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
@Widgetset("com.vaadin.v7.Vaadin7WidgetSet")
public class MyUI extends UI {

	
	String[] tabs = {"Mercury", "Venus", "Earth", "Mars"};
	String[] accordions = {"Apollo I"/*, "Apollo XIII", "Apollo XX", "Undefined"*/};
	TabSheet tabsheet = new TabSheet();
	TabSheet tabsheetWithAccordion = new TabSheet();
	Accordion accordion = new Accordion();
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        layout.addComponents(
        		accordion);
        
		createAccordionOutOfTab();
		
        
        setContent(layout);
    }


	private void createAccordionOutOfTab() {
		VerticalLayout accordionContainer = new VerticalLayout();
		accordion.addTab(accordionContainer, "Accordion with RichTextArea from Vaadin 8 & Vaadin 7");
		accordionContainer.addComponent(createMultipleComponents());
	}
    
    
    private HorizontalLayout createMultipleComponents() {
		HorizontalLayout multiple = new HorizontalLayout();
		multiple.addComponents(new MyRichVaadin8Component(),
				new MyRichVaadin7Component());
		return multiple;
    }
    

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
