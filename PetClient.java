//Trabalho de GA - Redes de Computadores: Aplicação e Transporte - Prof. Márcio Garcia
//Ana Beatriz Stahl, Emanuele Schlemmer, Gabriela Bley, Kelly Natasha Fernandes
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
                        System.out.println("Servidor: " + serverMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            // Solicitar o nome do Tamagotchi ao usuário
            System.out.println("Digite o nome do seu Tamagotchi:");
            String nomeTamagotchi = userInput.readLine();
            out.println(nomeTamagotchi);

            // Menu de comandos disponíveis
            System.out.println("Comandos disponíveis:");
            System.out.println("selecionado <índice> - Seleciona um Tamagotchi pelo índice");
            System.out.println("alimentar - Alimenta o Tamagotchi selecionado");
            System.out.println("brincar - Brinca com o Tamagotchi selecionado");
            System.out.println("energia - Verifica a energia do Tamagotchi selecionado");
            System.out.println("verificar - Verifica o status do Tamagotchi selecionado");

            String userMessage;
            while ((userMessage = userInput.readLine()) != null) {
                out.println(userMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
