//Trabalho de GA - Redes de Computadores: Aplicação e Transporte - Prof. Márcio Garcia
//Ana Beatriz Stahl, Emanuele Schlemmer, Gabriela Bley, Kelly Natasha Fernandes e Luisa Becker
import java.io.*;
import java.net.*;
import java.util.*;

public class PetServer {
    private static final List<ClientHandler> clients = new ArrayList<>();
    private static final List<Tamagotchi> tamagotchis = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println(i18n.SERVER_STARTED);
            while (true) try {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            } catch (IOException e) {
                System.err.printf((i18n.ERROR_CONNECT_SERVER) + "%n", e.getMessage());
            }
        }
    }

    public static void broadcast(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
    }

    public static String listTamagotchis() {
        List<String> infos = new ArrayList<>();
        for (Tamagotchi t: tamagotchis) {
            infos.add(t.getInfo());
        }
        return String.join("\n", infos);
    }

    static class ClientHandler implements Runnable {
        private final Socket socket;
        private PrintWriter out;
        private Tamagotchi tamagotchiSelected;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // Receives client name
                String clientName = in.readLine();
                System.out.printf((i18n.SERVER_CLIENT_CONNECTED) + "%n", clientName);

                // Receives tamagotchi's name
                String tamagotchiName = in.readLine();
                Tamagotchi tamagotchi = new Tamagotchi(tamagotchiName, 0, true);
                tamagotchis.add(tamagotchi);

                String message;
                while ((message = in.readLine()) != null) {
                    System.out.printf((i18n.SERVER_RECEIVE_MESSAGE) + "%n", clientName, message);
                    if (message.startsWith(i18n.COMMAND_LIST)) {
                        sendMessage(listTamagotchis());
                    } else if (message.startsWith(i18n.COMMAND_SELECT)) {
                        tamagotchiSelected = getSelectedTamagotchi(message);
                        if (tamagotchiSelected != null) {
                            out.println(String.format(i18n.TAMAGOTCHI_SELECTED, tamagotchiSelected.getName()));
                            out.println(tamagotchiSelected.getInfo());
                            out.println(i18n.getAvailableCommands());
                        }
                    } else if (tamagotchiSelected != null) {
                        switch (message.toLowerCase()) {
                            case "alimentar":
                                if (tamagotchiSelected.isAlive()) {
                                    tamagotchiSelected.feed();
                                    broadcast(String.format(i18n.TAMAGOTCHI_FED_UP, tamagotchiSelected.getName()));
                                } else
                                    sendMessage(String.format(i18n.TAMAGOTCHI_DEAD, tamagotchiSelected.getName()));
                                break;
                            case "brincar":
                                if (tamagotchiSelected.isAlive()) {
                                    tamagotchiSelected.play();
                                    broadcast(String.format(i18n.TAMAGOTCHI_PLAYED, tamagotchiSelected.getName()));
                                } else
                                    sendMessage(String.format(i18n.TAMAGOTCHI_DEAD, tamagotchiSelected.getName()));
                                break;
                            case "dormir":
                                if (tamagotchiSelected.isAlive()) {
                                    tamagotchiSelected.sleep();
                                    broadcast(String.format(i18n.TAMAGOTCHI_SLEPT, tamagotchiSelected.getName()));
                                } else
                                    sendMessage(String.format(i18n.TAMAGOTCHI_DEAD, tamagotchiSelected.getName()));
                                break;
                            case "remedio":
                                if (tamagotchiSelected.isAlive()) {
                                    tamagotchiSelected.medicate();
                                    broadcast(String.format(i18n.TAMAGOTCHI_MEDICATED, tamagotchiSelected.getName()));
                                } else {
                                    sendMessage(String.format(i18n.TAMAGOTCHI_DEAD, tamagotchiSelected.getName()));
                                }
                                break;
                            case "verificar":
                                if (tamagotchiSelected.isAlive())
                                    broadcast(tamagotchiSelected.getInfo());
                                else
                                    sendMessage(String.format(i18n.TAMAGOTCHI_DEAD, tamagotchiSelected.getName()));
                                break;
                            default:
                                sendMessage(i18n.ERROR_INVALID_COMMAND);
                                break;
                        }
                    } else {
                        out.println(i18n.TAMAGOTCHI_NOT_SELECTED);
                    }
                }
            } catch (IOException e) {
                System.err.printf((i18n.ERROR_COMMUNICATE_SERVER) + "%n", e.getMessage());
            }
        }

        public void sendMessage(String message) {
            out.println(message);
        }

        private Tamagotchi getSelectedTamagotchi(String input) {
            String[] parts = input.split(" ");
            if (parts.length == 2) {
                int index = Integer.parseInt(parts[1]);
                if (index >= 0 && index < tamagotchis.size()) {
                    return tamagotchis.get(index);
                } else {
                    out.println(i18n.ERROR_INVALID_INDEX);
                }
            } else {
                out.println(i18n.ERROR_INVALID_COMMAND);
            }
            return null;
        }
    }

}

