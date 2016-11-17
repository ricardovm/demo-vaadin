package com.linkhos.vaadin.demo.ex1;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import javax.servlet.annotation.WebServlet;

@Theme("valo")
@CDIUI("hello")
public class MyUI extends UI {
    @Override
    protected void init(VaadinRequest request) {
        final TextField name = new TextField("Nome");
        final Button greetButton = new Button("Olá");

        greetButton.addClickListener(
                e -> Notification.show("Olá " + name.getValue())
        );

        setContent(new VerticalLayout(name, greetButton));
    }
}
