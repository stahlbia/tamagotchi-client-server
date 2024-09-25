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

            System.out.println("Digite um dos comandos disponíveis:\n");
            System.out.println("alimentar");
            System.out.println("brincar");
            System.out.println("energia");
            System.out.println("verificar");

            String userMessage;
           while ((userMessage = userInput.readLine()) != null) {
               switch (userMessage.toLowerCase()) {
                   case "alimentar":
                   case "brincar":
                   case "verificar energia":
                   case "verificar":
                       out.println(userMessage);
                       break;
                   default:
                       System.out.println("Comando não reconhecido. Tente novamente:");
                       System.out.println("Comandos válidos: alimentar, brincar, energia e verificar");
               }
           }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
