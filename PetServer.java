//Trabalho de GA - Redes de Computadores: Aplicação e Transporte - Prof. Márcio Garcia
//Ana Beatriz Stahl, Emanuele Schlemmer, Gabriela Bley, Kelly Natasha Fernandes
import java.io.*;
import java.net.*;
import java.util.*;

public class PetServer {
    private static List<ClientHandler> clients = new ArrayList<>();
    private static Tamagotchi tamagotchi = new Tamagotchi(Teclado.leString("Digite o nome de seu Tamagotchi"), 0, true);

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

    static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Recebido: " + message);
                    switch (message.toLowerCase()) {
                        case "alimentar":
                            if (tamagotchi.isVivo()) {
                                tamagotchi.alimentar(); //AQUI É NECESSÁRIO CRIAR O MÉTODO DE ALIMENTAÇÃO
                                broadcast("O Tamagotchi foi alimentado.");
                            } else
                                sendMessage("O Tamagotchi está morto.");
                            break;
                        case "brincar":
                            if (tamagotchi.isVivo()) {
                                tamagotchi.brincar(); //AQUI É NECESSÁRIO CRIAR O MÉTODO DE BRINCAR
                                broadcast("O Tamagotchi brincou e está feliz.");
                            } else
                                sendMessage("O Tamagotchi está morto.");
                            break;
                        case "energia":
                            if (tamagotchi.isVivo())
                                tamagotchi.recuperaEnergia(); //AQUI É NECESSÁRIO CRIAR O MÉTODO DE AUMENTAR ENERGIA (DORMIR)
                            else
                                sendMessage("O Tamagotchi está morto.");
                            break;
                        case "verificar":
                            if (tamagotchi.isVivo())
                                tamagotchi.imprimeInfo();
                            else
                                sendMessage("O Tamagotchi está morto.");
                            break;
                        default:
                            sendMessage("Comando não reconhecido.");
                            break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void sendMessage(String message) {
            out.println(message);
        }
    }

}

