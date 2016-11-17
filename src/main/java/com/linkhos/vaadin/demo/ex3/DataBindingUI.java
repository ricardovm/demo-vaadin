package com.linkhos.vaadin.demo.ex3;

import com.vaadin.annotations.Theme;
import com.vaadin.cdi.CDIUI;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;

@Theme("valo")
@CDIUI("data-binding")
public class DataBindingUI extends UI {
    
    @Override
    protected void init(VaadinRequest request) {
        HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.setSizeFull();
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        
        BeanItemContainer<Pessoa> bic = new BeanItemContainer<>(Pessoa.class);
        bic.addAll(carregar());
        
        Table pessoasTable = new Table();
        pessoasTable.setSizeFull();
        pessoasTable.setContainerDataSource(bic);
        pessoasTable.setSelectable(true);
        pessoasTable.setMultiSelect(false);
        pessoasTable.setVisibleColumns(new Object[] { "nome", "sobrenome", "idade" });
        
        mainLayout.addComponent(pessoasTable);
        
        FieldGroup binder = new FieldGroup();
        binder.setItemDataSource(new BeanItem(new Pessoa(), Pessoa.class));
        
        VerticalLayout editLayout = new VerticalLayout();
        editLayout.setSpacing(true);
        mainLayout.addComponent(editLayout);
        
        PessoaForm pessoaForm = new PessoaForm();
        pessoaForm.setSizeFull();
        editLayout.addComponent(pessoaForm);
        
        Button gravarButton = new Button("Gravar", e -> { 
            try { 
                binder.commit();
                System.out.println(pessoasTable.getValue());
            } catch (FieldGroup.CommitException ex) {
            } 
        });
        editLayout.addComponent(gravarButton);
        
        binder.bindMemberFields(pessoaForm);
        
        pessoasTable.addValueChangeListener(e -> {
            if (pessoasTable.getValue() != null) {
                binder.setItemDataSource(bic.getItem(pessoasTable.getValue()));
            }
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
