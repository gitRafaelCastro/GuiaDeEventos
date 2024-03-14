package com.rafaelcastro.GuiaDeEventos.gerenciadores;

import com.rafaelcastro.GuiaDeEventos.modelo.Usuario;
import com.rafaelcastro.GuiaDeEventos.utils.ArquivoManager;

import java.util.ArrayList;
import java.util.List;

public class ManagerUsuario {
  private List<Usuario> usuarios;

  public ManagerUsuario() {
    this.usuarios = new ArrayList<>();
  }

  public void cadastrarUsuario(Usuario usuario) {
    usuarios.add(usuario);
    ArquivoManager.gravarUsuarios(usuarios);
  }

  public boolean autenticarUsuario(String email, String telefone) {
    List<Usuario> usuarios = ArquivoManager.lerUsuarios(); // Obtendo a lista de usuários do arquivo

    for (Usuario usuario : usuarios) {
      if (usuario.getEmail().equalsIgnoreCase(email) && usuario.getTelefone().equals(telefone)) {
        this.usuarios = usuarios;
        return true; // Usuário encontrado, autenticação bem-sucedida
      }
    }

    return false; // Usuário não encontrado, autenticação falhou
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
