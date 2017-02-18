package com.linkhos.vaadin.demo.ex3;

import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Theme("valo")
@CDIUI("data-binding")
public class DataBindingUI extends UI {
    
    @Override
    protected void init(VaadinRequest request) {
        HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.setSizeFull();
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        
        List<Pessoa> pessoas = carregar();
        ListDataProvider<Pessoa> ldp = DataProvider.ofCollection(pessoas);

        Grid<Pessoa> pessoasGrid = new Grid<>();
        pessoasGrid.setDataProvider(ldp);
        pessoasGrid.setSizeFull();
        pessoasGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        pessoasGrid.addColumn(Pessoa::getNome).setCaption("Nome");
        pessoasGrid.addColumn(Pessoa::getSobrenome).setCaption("Sobrenome");
        pessoasGrid.addColumn(Pessoa::getIdade).setCaption("Idade");

        mainLayout.addComponent(pessoasGrid);
        
        Pessoa pessoa = new Pessoa();
        Binder binder = new BeanValidationBinder<>(Pessoa.class);
        //binder.setBean(pessoa);
        binder.readBean(pessoa);
        
        VerticalLayout editLayout = new VerticalLayout();
        editLayout.setSpacing(true);
        mainLayout.addComponent(editLayout);
        
        PessoaForm pessoaForm = new PessoaForm();
        pessoaForm.setSizeFull();
        editLayout.addComponent(pessoaForm);
        
        Button gravarButton = new Button("Gravar", e -> { 
            try {
                Optional<Pessoa> p = pessoasGrid.getSelectedItems().stream().findFirst();
                if (p.isPresent()) {
                    binder.writeBean(p.get());
                }
                pessoasGrid.getDataProvider().refreshAll();
                System.out.println(pessoasGrid.getSelectedItems().stream().findFirst());
            } catch (ValidationException ex) {
                Notification.show("Erro", ex.getMessage(), Notification.Type.WARNING_MESSAGE);
            }
        });
        editLayout.addComponent(gravarButton);
        
        /* Custom binding for incompatible type properties */
        binder.forMemberField(pessoaForm.idade).withConverter(v -> Integer.valueOf((String) v), String::valueOf, "Idade deve ser um número");
        binder.bindInstanceFields(pessoaForm);
        
        pessoasGrid.addSelectionListener(e -> {
            Optional<Pessoa> selecionado = pessoasGrid.getSelectedItems().stream().findFirst();
            selecionado.ifPresent(s -> binder.readBean(s));
        });
        
        setContent(mainLayout);
    }

    private List<Pessoa> carregar() {
        List<Pessoa> pessoas = new ArrayList<>();
        
        pessoas.add(new Pessoa("Ricardo", "Mannrich", 35));
        pessoas.add(new Pessoa("Tiago", "Gois", 27));
        
        return pessoas;
    }
    
}
