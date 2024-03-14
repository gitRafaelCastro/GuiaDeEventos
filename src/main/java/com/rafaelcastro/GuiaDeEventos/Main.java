package com.rafaelcastro.GuiaDeEventos;

import com.rafaelcastro.GuiaDeEventos.gerenciadores.ManagerEvento;
import com.rafaelcastro.GuiaDeEventos.gerenciadores.ManagerUsuario;
import com.rafaelcastro.GuiaDeEventos.modelo.Categoria;
import com.rafaelcastro.GuiaDeEventos.modelo.Evento;
import com.rafaelcastro.GuiaDeEventos.modelo.Usuario;
import com.rafaelcastro.GuiaDeEventos.utils.ArquivoManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static ManagerEvento managerEvento = new ManagerEvento();
    private static ManagerUsuario managerUsuario = new ManagerUsuario();
    private static Usuario usuarioAutenticado = null;
    private static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        // Carregar eventos do arquivo
        List<Evento> eventosCarregados = ArquivoManager.lerEventos();
        for (Evento evento : eventosCarregados) {
            managerEvento.cadastrarEvento(evento);
        }

        boolean sair = false;
        while (!sair) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha após a entrada numérica

            switch (opcao) {
                case 1 -> cadastrarUsuario();
                case 2 -> autenticarUsuario();
                case 3 -> {
                    if (usuarioAutenticado != null) {
                        cadastrarEvento();
                    } else {
                        System.out.println("Você precisa estar autenticado para cadastrar um evento.");
                    }
                }
                case 4 -> consultarEventos();
                case 5 -> {
                    if (usuarioAutenticado != null) {
                        confirmarParticipacao();
                    } else {
                        System.out.println("Você precisa estar autenticado para confirmar participação.");
                    }
                }
                case 6 -> {
                    if (usuarioAutenticado != null) {
                        cancelarParticipacao();
                    } else {
                        System.out.println("Você precisa estar autenticado para cancelar participação.");
                    }
                }
                case 7 -> consultarEventosProximos();
                case 8 -> consultarEventosPassados();
                case 9 -> sair = true;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }

        // Gravar eventos no arquivo antes de sair
        ArquivoManager.gravarEventos(managerEvento.consultarEventos());
    }

    private static void exibirMenu() {
        System.out.println("\n=== Menu ===");
        System.out.println("1. Cadastrar usuário");
        System.out.println("2. Autenticar usuário");
        System.out.println("3. Cadastrar evento");
        System.out.println("4. Consultar eventos");
        System.out.println("5. Confirmar participação em evento");
        System.out.println("6. Cancelar participação em evento");
        System.out.println("7. Consultar eventos próximos");
        System.out.println("8. Consultar eventos passados");
        System.out.println("9. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarUsuario() {
        System.out.println("Digite o nome do usuário:");
        String nome = scanner.nextLine();

        System.out.println("Digite o email do usuário:");
        String email = scanner.nextLine();

        System.out.println("Digite o telefone do usuário:");
        String telefone = scanner.nextLine();

        Usuario novoUsuario = new Usuario(nome, email, telefone);
        managerUsuario.cadastrarUsuario(novoUsuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void autenticarUsuario() {
        System.out.println("Digite o email do usuário:");
        String email = scanner.nextLine();

        System.out.println("Digite o telefone do usuário:");
        String telefone = scanner.nextLine();

        if (managerUsuario.autenticarUsuario(email, telefone)) {
            Usuario usuario = managerUsuario.getUsuarioPorEmailTelefone(email, telefone);
            usuarioAutenticado = usuario;
            System.out.println("Autenticação bem-sucedida! Bem-vindo(a), " + usuario.getNome());
        } else {
            System.out.println("Credenciais inválidas. Tente novamente.");
        }
    }

    private static void cadastrarEvento() {
        if (usuarioAutenticado != null) {
            System.out.println("Digite o nome do evento:");
            String nome = scanner.nextLine();

            System.out.println("Digite o endereço do evento:");
            String endereco = scanner.nextLine();

            System.out.println("Digite a categoria do evento:");
            String categoriaNome = scanner.nextLine();
            Categoria categoria = new Categoria(categoriaNome);

            System.out.println("Digite o horário do evento (formato: dia/mês/ano hora:minutos):");
            String horarioString = scanner.nextLine();
            LocalDateTime horario = LocalDateTime.parse(horarioString, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

            // DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("M/d/yyyy");
            //    LocalDate date = LocalDate.parse(userInput, dateFormat);
            System.out.println("Digite a descrição do evento:");
            String descricao = scanner.nextLine();

            Evento novoEvento = new Evento(nome, endereco, categoria, horario, descricao);
            managerEvento.cadastrarEvento(novoEvento);
            System.out.println("Evento cadastrado com sucesso!");
        } else {
            System.out.println("Você precisa estar autenticado para cadastrar um evento.");
        }
    }

    private static void consultarEventos() {
        List<Evento> eventos = managerEvento.consultarEventos();
        if (eventos.isEmpty()) {
            System.out.println("Não há eventos cadastrados.");
        } else {
            System.out.println("Eventos cadastrados:\n*Eventos marcados com um X estão com sua presença confirmada.\n");
            for (Evento evento : eventos) {
                String confirmacaoParticipacao = "";
                if (usuarioAutenticado != null) {
                    if (managerEvento.usuarioConfirmouParticipacao(evento, usuarioAutenticado)) {
                        confirmacaoParticipacao = "[x] ";
                    } else {
                        confirmacaoParticipacao = "[ ] ";
                    }
                }
                System.out.println(confirmacaoParticipacao + evento.getNome() + " - " + evento.getHorarioFormatado());
            }
        }
    }

    private static void confirmarParticipacao() {
        System.out.println("Digite o nome do evento para confirmar participação:");
        String nomeEvento = scanner.nextLine();

        Evento eventoEncontrado = null;
        for (Evento evento : managerEvento.consultarEventos()) {
            if (evento.getNome().equalsIgnoreCase(nomeEvento)) {
                eventoEncontrado = evento;
                break;
            }
        }

        if (eventoEncontrado != null) {
            managerEvento.confirmarParticipacao(eventoEncontrado, usuarioAutenticado);
            System.out.println("Participação confirmada no evento: " + eventoEncontrado.getNome());
        } else {
            System.out.println("Evento não encontrado.");
        }
    }

    private static void cancelarParticipacao() {
        System.out.println("Digite o nome do evento para cancelar participação:");
        String nomeEvento = scanner.nextLine();

        Evento eventoEncontrado = null;
        for (Evento evento : managerEvento.consultarEventos()) {
            if (evento.getNome().equalsIgnoreCase(nomeEvento)) {
                eventoEncontrado = evento;
                break;
            }
        }

        if (eventoEncontrado != null) {
            managerEvento.cancelarParticipacao(eventoEncontrado, usuarioAutenticado);
            System.out.println("Participação cancelada no evento: " + eventoEncontrado.getNome());
        } else {
            System.out.println("Evento não encontrado.");
        }
    }

    private static void consultarEventosProximos() {
        List<Evento> eventosProximos = managerEvento.ordenarEventosPorHorario();
        LocalDateTime agora = LocalDateTime.now();

        System.out.println("Eventos próximos:");
        for (Evento evento : eventosProximos) {
            if (evento.getHorario().isAfter(agora)) {
                System.out.println(evento.getNome() + " - " + evento.getHorario());
            }
        }
    }

    private static void consultarEventosPassados() {
        List<Evento> eventosPassados = managerEvento.ordenarEventosPorHorario();
        LocalDateTime agora = LocalDateTime.now();

        System.out.println("Eventos passados:");
        for (Evento evento : eventosPassados) {
            if (evento.getHorario().isBefore(agora)) {
                System.out.println(evento.getNome() + " - " + evento.getHorario());
            }
        }
    }
}