package com.linkhos.vaadin.demo.ex3;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class Pessoa {

    private String nome;
    private String sobrenome;
    
    @Min(0)
    @Max(130)
    private int idade;

    public Pessoa() {
    }

    public Pessoa(String nome, String sobrenome, int idade) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "nome=" + nome + ", sobrenome=" + sobrenome + ", idade=" + idade + '}';
    }

}
