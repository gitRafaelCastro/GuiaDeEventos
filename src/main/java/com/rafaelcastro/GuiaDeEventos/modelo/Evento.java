package com.rafaelcastro.GuiaDeEventos.modelo;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Evento {

  // Evento: Representará um evento cadastrado, com atributos como nome, endereço, categoria, horário, descrição, etc.
  private String nome;
  private String endereco;
  private Categoria categoria;
  private LocalDateTime horario;
  private String descricao;
  private int confirmados;

  public Evento(String nome, String endereco, Categoria categoria, LocalDateTime horario, String descricao) {
    this.nome = nome;
    this.endereco = endereco;
    this.categoria = categoria;
    this.horario = horario;
    this.descricao = descricao;
    this.confirmados = 0;
  }

  public String getNome() {
    return nome;
  }

  public String getEndereco() {
    return endereco;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public LocalDateTime getHorario() {
    return horario;
  }

  public String getHorarioFormatado() {

    ZonedDateTime zonedDateTime = ZonedDateTime.ofLocal(horario, ZoneId.of("America/Sao_Paulo"), null);
    return (zonedDateTime.format(DateTimeFormatter.ofPattern("EEEE, d 'de' MMMM 'de' yyyy 'às' HH:mm")));
  }


  public String getDescricao() {
    return descricao;
  }

  public int consultarConfirmados() {
    return confirmados;
  }

  public void aumentarConfirmados() {
    confirmados += 1;
  }

  public void diminuirConfirmados() {
    confirmados -= 1;

    if (confirmados < 0) {
      confirmados = 0;
    }
  }

}
