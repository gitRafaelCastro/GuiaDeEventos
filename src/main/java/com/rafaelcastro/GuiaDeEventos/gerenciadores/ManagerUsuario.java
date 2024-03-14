package com.rafaelcastro.GuiaDeEventos.gerenciadores;

import com.rafaelcastro.GuiaDeEventos.modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ManagerUsuario {
  private List<Usuario> usuarios;

  public ManagerUsuario() {
    this.usuarios = new ArrayList<>();
  }

  public void cadastrarUsuario(Usuario usuario) {
    usuarios.add(usuario);
  }

  public boolean autenticarUsuario(String email, String telefone) {
    for (Usuario usuario : usuarios) {
      if (usuario.getEmail().equalsIgnoreCase(email) && usuario.getTelefone().equals(telefone)) {
        return true;
      }
    }
    return false;
  }

  public Usuario getUsuarioPorEmailTelefone(String email, String telefone) {
    for (Usuario usuario : usuarios) {
      if (usuario.getEmail().equalsIgnoreCase(email) && usuario.getTelefone().equals(telefone)) {
        return usuario;
      }
    }
    return null;
  }

}
