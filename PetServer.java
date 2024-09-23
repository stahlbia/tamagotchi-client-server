
import java.io.*;
import java.net.*;
import java.util.*;

public class PetServer {
    private static List<ClientHandler> clients = new ArrayList<>();
    private static Tamagotchi tamagotchi = new Tamagotchi(Teclado.leString("Digite o nome de seu Tamagotchi"), 0, 1,true, 0);

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Servidor iniciado...");



        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Novo cliente conectado!");
            ClientHandler clientHandler = new ClientHandler(socket);
            clients.add(clientHandler);
            new Thread(clientHandler).start();
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
                    // Processar a mensagem e atualizar o estado do pet
                    // Exemplo: pet.feed();
                    broadcast("Pet atualizado: " + message);
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

