//Trabalho de GA - Redes de Computadores: Aplicação e Transporte - Prof. Márcio Garcia
//Ana Beatriz Stahl, Emanuele Schlemmer, Gabriela Bley, Kelly Natasha Fernandes e Luisa Becker
import java.io.*;
import java.net.*;

public class PetClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            // Thread para receber mensagens do servidor
            new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.printf((i18n.SERVER_TITLE) + "%n", serverMessage);
                    }
                } catch (IOException e) {
                    System.err.printf((i18n.ERROR_COMMUNICATE_SERVER) + "%n", e.getMessage());
                }
            }).start();

            // Solicitar o nome do cliente
            System.out.println(i18n.REQUEST_CLIENT_NAME);
            String clientName = userInput.readLine();
            out.println(clientName);

            // Solicitar o nome do Tamagotchi ao usuário
            System.out.println(i18n.REQUEST_TAMAGOTCHI_NAME);
            String nomeTamagotchi = userInput.readLine();
            out.println(nomeTamagotchi);

            // Menu de comandos disponíveis
            System.out.println(i18n.getAvailableCommands());

            String userMessage;
            while ((userMessage = userInput.readLine()) != null) {
                out.println(userMessage);
            }
        } catch (IOException e) {
            System.err.printf((i18n.ERROR_CONNECT_SERVER) + "%n", e.getMessage());
        }
    }
}
