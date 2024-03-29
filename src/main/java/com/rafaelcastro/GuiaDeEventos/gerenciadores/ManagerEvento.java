package com.rafaelcastro.GuiaDeEventos.gerenciadores;

import com.rafaelcastro.GuiaDeEventos.modelo.Evento;
import com.rafaelcastro.GuiaDeEventos.modelo.Usuario;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ManagerEvento {
  private  List<Evento> eventos;
  private List<ParticipacaoEvento> participacoes;

  public ManagerEvento() {
    this.eventos = new ArrayList<>();
    this.participacoes = new ArrayList<>();
  }

  public List<Evento> consultarEventos() {
    return eventos;
  }

  public void cadastrarEvento(Evento evento) {
    eventos.add(evento);
  }

  public void confirmarParticipacao(Evento evento, Usuario usuario) {
    participacoes.add(new ParticipacaoEvento(evento, usuario));
  }

  public void cancelarParticipacao(Evento evento, Usuario usuario) {
    ParticipacaoEvento participacaoEncontrada = null;
    for (ParticipacaoEvento participacao : participacoes) {
      if (participacao.getEvento().equals(evento) && participacao.getUsuario().equals(usuario)) {
        participacaoEncontrada = participacao;
        break;
      }
    }
    if (participacaoEncontrada != null) {
      participacoes.remove(participacaoEncontrada);
    }
  }

  public List<Evento> ordenarEventosPorHorario() {
    List<Evento> eventosCopia = new ArrayList<>(eventos);
    eventosCopia.sort(Comparator.comparing(Evento::getHorario));
    return eventosCopia;
  }

  public boolean usuarioConfirmouParticipacao(Evento evento, Usuario usuario) {
    for (ParticipacaoEvento participacao : participacoes) {
      if (participacao.getEvento().equals(evento) && participacao.getUsuario().equals(usuario)) {
        return true;
      }
    }
    return false;
  }

  private static class ParticipacaoEvento {
    private Evento evento;
    private Usuario usuario;

    public ParticipacaoEvento(Evento evento, Usuario usuario) {
      this.evento = evento;
      this.usuario = usuario;
    }

    public Evento getEvento() {
      return evento;
    }

    public Usuario getUsuario() {
      return usuario;
    }
  }
}
