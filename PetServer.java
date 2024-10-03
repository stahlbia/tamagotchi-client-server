//Trabalho de GA - Redes de Computadores: Aplicação e Transporte - Prof. Márcio Garcia
//Ana Beatriz Stahl, Emanuele Schlemmer, Gabriela Bley, Kelly Natasha Fernandes
import java.io.*;
import java.net.*;
import java.util.*;

public class PetServer {
    private static List<ClientHandler> clients = new ArrayList<>();
    private static List<Tamagotchi> tamagotchis = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Servidor iniciado...");

        while (true) {
            try {
            Socket socket = serverSocket.accept();
            System.out.println("Novo cliente conectado!");
            ClientHandler clientHandler = new ClientHandler(socket);
            clients.add(clientHandler);
            new Thread(clientHandler).start();
        }
            catch(IOException e){
                System.err.println("Erro ao aceitar a coneção: " + e.getMessage());
            }
        }
    }

    public static void broadcast(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public static String listarTamagotchis() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tamagotchis.size(); i++) {
            sb.append(i).append(": ").append(tamagotchis.get(i).getNome()).append("\n");
        }
        return sb.toString();
    }

    static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private Tamagotchi tamagotchiSelecionado;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // Receber o nome do Tamagotchi do cliente
                String nome = in.readLine();
                Tamagotchi tamagotchi = new Tamagotchi(nome, 0, true);
                tamagotchis.add(tamagotchi);

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Recebido: " + message);
                    if (message.startsWith("interagir")) {
                        sendMessage(listarTamagotchis());
                    } else if (message.startsWith("selecionado")) {
                        tamagotchiSelecionado = getTamagotchiSelecionado(message);
                        if (tamagotchiSelecionado != null) {

                            out.println("Tamagotchi selecionado: " + tamagotchiSelecionado.getNome());
                            out.println("Comandos disponíveis: alimentar, brincar, energia, verificar");
                        }
                    } else if (tamagotchiSelecionado != null) {
                        switch (message.toLowerCase()) {
                            case "alimentar":
                                if (tamagotchiSelecionado.isVivo()) {
                                    tamagotchiSelecionado.alimentar(); //AQUI É NECESSÁRIO CRIAR O MÉTODO DE ALIMENTAÇÃO
                                    broadcast("O Tamagotchi foi alimentado.");
                                } else
                                    sendMessage("O Tamagotchi está morto.");
                                break;
                            case "brincar":
                                if (tamagotchiSelecionado.isVivo()) {
                                    tamagotchiSelecionado.brincar(); //AQUI É NECESSÁRIO CRIAR O MÉTODO DE BRINCAR
                                    broadcast("O Tamagotchi brincou e está feliz.");
                                } else
                                    sendMessage("O Tamagotchi está morto.");
                                break;
                            case "energia":
                                if (tamagotchiSelecionado.isVivo())
                                    tamagotchiSelecionado.recuperaEnergia(); //AQUI É NECESSÁRIO CRIAR O MÉTODO DE AUMENTAR ENERGIA (DORMIR)
                                else
                                    sendMessage("O Tamagotchi está morto.");
                                break;
                            case "verificar":
                                if (tamagotchiSelecionado.isVivo())
                                    tamagotchiSelecionado.imprimeInfo();
                                else
                                    sendMessage("O Tamagotchi está morto.");
                                break;
                            default:
                                sendMessage("Comando não reconhecido.");
                                break;
                        }
                    } else {
                        out.println("Selecione um Tamagotchi primeiro");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendMessage(String message) {
            out.println(message);
        }

        private Tamagotchi getTamagotchiSelecionado(String input) {
            String[] parts = input.split(" ");
            if (parts.length == 2) {
                int index = Integer.parseInt(parts[1]);
                if (index >= 0 && index < tamagotchis.size()) {
                    return tamagotchis.get(index);
                } else {
                    out.println("Indice inválido.");
                }
            } else {
                out.println("Comando invalido.");
            }
            return null;
        }
    }

}

