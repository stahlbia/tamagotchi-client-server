public class i18n {
    // Messages: server
    static String SERVER_STARTED = "servidor iniciado";
    static String SERVER_CLIENT_CONNECTED = "cliente conectado: %s";
    static String SERVER_RECEIVE_MESSAGE = "recebido de %s: %s";
    static String SERVER_TITLE = "servidor: %s";

    // Messages: client
    static String REQUEST_CLIENT_NAME = "digite seu nome:";
    static String REQUEST_TAMAGOTCHI_NAME = "digite o nome do seu tamagotchi:";
    static String AVAILABLE_COMMANDS = "comandos disponiveis: %s, %s, %s, %s, %s, %s, %s";
    static String COMMAND_LIST = "listar";
    static String COMMAND_SELECT = "selecionar";
    static String COMMAND_FEED = "alimentar";
    static String COMMAND_PLAY = "brincar";
    static String COMMAND_SLEEP = "dormir";
    static String COMMAND_MEDICATE = "remedio";
    static String COMMAND_VERIFY = "verificar";

    // Messages: tamagotchi
    static String TAMAGOTCHI_SELECTED = "tamagotchi selecionado: %s";
    static String TAMAGOTCHI_STATUS = "%s: < %d dias, %.1f%% fome, %.1f%% saúde, %.1f%% energia, %.1f%% diversão >";
    static String TAMAGOTCHI_FED_UP = "%s comeu e esta sem fome";
    static String TAMAGOTCHI_PLAYED = "%s brincou e esta feliz";
    static String TAMAGOTCHI_SLEPT = "%s dormiu e esta descansado";
    static String TAMAGOTCHI_MEDICATED = "%s tomou o remedio e esta melhor";
    static String TAMAGOTCHI_DEAD = "%s nao esta mais entre nos..";
    static String TAMAGOTCHI_NOT_SELECTED = "selecione um tamagotchi usando: selecionar <indice>";
    static String TAMAGOTCHI_HUNGRY = "%s esta com fome!";
    static String TAMAGOTCHI_SLEEPY = "%s esta com sono!";
    static String TAMAGOTCHI_SICK = "%s esta doente";
    static String TAMAGOTCHI_BORED = "%s esta entediado";
    static String TAMAGOTCHI_NOT_HUNGRY = "%s esta sem fome!";
    static String TAMAGOTCHI_NOT_SLEEPY = "%s esta sem sono!";
    static String TAMAGOTCHI_NOT_SICK = "%s nao esta doente";
    static String TAMAGOTCHI_NOT_BORED = "%s nao esta entediado";

    // Messages: error
    static String ERROR_CONNECT_SERVER = "erro ao aceitar a coneção: %s";
    static String ERROR_INVALID_COMMAND = "commando invalido";
    static String ERROR_INVALID_INDEX = "indice invalido";
    static String ERROR_COMMUNICATE_SERVER = "erro ao comunicar com o servidor";
    static String ERROR_CLOSE_SOCKET = "erro ao fechar o socket";

    static String getAvailableCommands() {
        return String.format(AVAILABLE_COMMANDS, COMMAND_LIST, COMMAND_SELECT, COMMAND_FEED, COMMAND_PLAY, COMMAND_SLEEP, COMMAND_MEDICATE, COMMAND_VERIFY);
    }
}
