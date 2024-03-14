package com.rafaelcastro.GuiaDeEventos.modelo;


public class Usuario {

  // Usuario: Representará um usuário cadastrado no sistema, com atributos como nome, email, telefone, etc.
  private String nome;
  private String email;
  private String telefone;
  private int id;


  public Usuario(String nome, String email, String telefone) {
    this.nome = nome;
    this.email = email;
    this.telefone = telefone;

  }

  public void setNome(String novoNome) {
    this.nome = novoNome;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getNome() {
    return nome;
  }

  public String getEmail() {
    return email;
  }

  public String getTelefone() {
    return telefone;
  }

  public int getId() {
    return id;
  }
}
