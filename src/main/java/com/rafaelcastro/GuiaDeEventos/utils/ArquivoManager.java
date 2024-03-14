package com.rafaelcastro.GuiaDeEventos.utils;

import com.rafaelcastro.GuiaDeEventos.modelo.Categoria;
import com.rafaelcastro.GuiaDeEventos.modelo.Evento;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ArquivoManager {

  private static final String ARQUIVO_EVENTOS = "events.data";

  public static List<Evento> lerEventos() {
    List<Evento> eventos = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_EVENTOS))) {
      String linha;
      while ((linha = br.readLine()) != null) {
        String[] partes = linha.split(",");
        String nome = partes[0];
        String endereco = partes[1];
        Categoria categoria = new Categoria(partes[2]);
        LocalDateTime horario = LocalDateTime.parse(partes[3], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String descricao = partes[4];
        Evento evento = new Evento(nome, endereco, categoria, horario, descricao);
        eventos.add(evento);
      }
    } catch (IOException e) {
      System.err.println("Erro ao ler o arquivo de eventos: " + e.getMessage());
    }

    return eventos;
  }

  public static void gravarEventos(List<Evento> eventos) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_EVENTOS))) {
      for (Evento evento : eventos) {
        String linha = String.format("%s,%s,%s,%s,%s",
                evento.getNome(),
                evento.getEndereco(),
                evento.getCategoria().getNome(),
                evento.getHorario().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                evento.getDescricao());
        bw.write(linha);
        bw.newLine();
      }
    } catch (IOException e) {
      System.err.println("Erro ao gravar o arquivo de eventos: " + e.getMessage());
    }
  }
}