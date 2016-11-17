package com.linkhos.vaadin.demo.ex3;

import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

public class PessoaForm extends FormLayout {
    private TextField nome = new TextField("Nome");
    private TextField sobrenome = new TextField("Sobrenome");
    private TextField idade = new TextField("Idade");

    public PessoaForm() {
        addComponents(nome, sobrenome, idade);
    }
    
}
