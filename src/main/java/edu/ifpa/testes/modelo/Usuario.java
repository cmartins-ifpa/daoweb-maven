package edu.ifpa.testes.modelo;

import java.util.Date;

public class Usuario {
    // atributos do domínio da entidade Usuario
    private long id;
    private String codigo;
    private String senha;
    private String nome;
    private java.util.Date ultimoAcesso;

    // construtores / setters & getters
    public Usuario() {
        this.ultimoAcesso = new Date();
    }

    public Usuario(String nome, String codigo, String senha) {
        this.nome = nome;
        this.codigo = codigo;
        this.senha = senha;
        this.ultimoAcesso = new Date();
        // o atributo "id" é criado automaticamente no banco de dados
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(Date ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

}
