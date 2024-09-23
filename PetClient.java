
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

            String userMessage;
            while ((userMessage = userInput.readLine()) != null) {
                out.println(userMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}
