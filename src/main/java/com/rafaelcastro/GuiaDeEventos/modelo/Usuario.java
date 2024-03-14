package com.rafaelcastro.GuiaDeEventos.modelo;


public class Usuario {

  // Usuario: Representará um usuário cadastrado no sistema, com atributos como nome, email, telefone, etc.
  private String nome;
  private String email;
  private String telefone;


  public Usuario(String nome, String email, String telefone) {
    this.nome = nome;
    this.email = email;
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

}
