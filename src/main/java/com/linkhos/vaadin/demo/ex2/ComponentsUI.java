package com.linkhos.vaadin.demo.ex2;

import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import java.util.LinkedHashMap;
import java.util.Map;

@Theme("valo")
@CDIUI("components")
public class ComponentsUI extends UI {
    private static Map<String, String> themeVariants = new LinkedHashMap<String, String>();
    
    @Override
    protected void init(VaadinRequest request) {
        themeVariants.put(ValoTheme.THEME_NAME, "Valo");
        themeVariants.put("midsummer-night", "Midsummer Night");
        themeVariants.put("tests-valo-blueprint", "Blueprint");
        themeVariants.put("tests-valo-dark", "Dark");
        themeVariants.put("tests-valo-facebook", "Facebook");
        themeVariants.put("tests-valo-flatdark", "Flat dark");
        themeVariants.put("tests-valo-flat", "Flat");
        themeVariants.put("tests-valo-light", "Light");
        themeVariants.put("tests-valo-metro", "Metro");
        themeVariants.put("tests-valo-reindeer", "Migrate Reindeer");
        
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        
        mainLayout.addComponent(new TextField("TextField"));
        mainLayout.addComponent(new DateField("DateField"));
        mainLayout.addComponent(new Button("Button"));
        mainLayout.addComponent(new CheckBox("CheckBox"));

        ComboBox<String> comboBox = new ComboBox<>("ComboBoxs");
        mainLayout.addComponent(comboBox);
        
        comboBox.setItemCaptionGenerator(themeVariants::get);
        comboBox.setEmptySelectionAllowed(false);
        comboBox.setItems(themeVariants.keySet());
        
        comboBox.setValue(ValoTheme.THEME_NAME);
        comboBox.addValueChangeListener(e -> setTheme((String) comboBox.getValue()));

        setContent(mainLayout);
    }
}
