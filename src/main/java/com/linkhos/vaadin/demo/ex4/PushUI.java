package com.linkhos.vaadin.demo.ex4;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Supplier;

@Theme("valo")
@CDIUI("push")
@Push
public class PushUI extends UI {
    private Timer timer;
    
    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        
        Button iniciarButton = new Button("Iniciar");
        mainLayout.addComponent(iniciarButton);
        
        Button pararButton = new Button("Parar");
        pararButton.setVisible(false);
        mainLayout.addComponent(pararButton);
        
        VerticalLayout resultLayout = new VerticalLayout();
        mainLayout.addComponent(resultLayout);

        setContent(mainLayout);
        
        Supplier<TimerTask> task = ()-> new TimerTask() {
            @Override
            public void run() {
                UI.getCurrent().access(() -> {
                    System.out.println(System.currentTimeMillis());
                    resultLayout.addComponent(new Label(String.valueOf(System.currentTimeMillis())));
                });
            }
        };
        
        iniciarButton.addClickListener(e -> {
            iniciarButton.setVisible(false);
            pararButton.setVisible(true);
            setTimer(new Timer());
            getTimer().schedule(task.get(), 1000, 1000);
        });
        
        pararButton.addClickListener(e -> {
            iniciarButton.setVisible(true);
            pararButton.setVisible(false);
            getTimer().cancel();
            getTimer().purge();
        });
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

}
