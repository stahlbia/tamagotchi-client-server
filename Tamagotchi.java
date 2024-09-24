import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Tamagotchi {
    private String nome;
    private int idade;
    private boolean vivo;

    // ATUALIZAR ESTADOS E NIVEIS INICIAIS
    private String estado;
    private double fome = 100; // Nivel de fome (100%)
    private double saude = 100; // Nivel de saude (100%)
    private double energia = 100; // Nivel de energia (100%)
    private double felicidade = 100; // Nivel de felicidade (100%)


    // CONSTRUTOR
    public Tamagotchi(String nome, int idade, double peso) {
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
        this.vivo = true; // O Tamagochi começa vivo
    }


    // GETTERS E SETTERS
    // Nome
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Idade
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {1
        this.idade = idade;
    }

    // Vivo
    public boolean isVivo() {
        return vivo;
    }
    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    // Estado
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Fome
    public double getFome() {
        return fome;
    }
    public void setFome(double fome) {
        this.fome = fome;
    }

    // Saude
    public double getSaude() {
        return saude;
    }
    public void setSaude(double saude) {
        this.saude = saude;
    }

    // Energia
    public double getEnergia() {
        return energia;
    }
    public void setEnergia(double energia) {
        this.energia = energia;
    }

    // Felicidade
    public double getFelicidade() {
        return felicidade;
    }
    public void setFelicidade(double felicidade) {
        this.felicidade = felicidade;
    }


    // INFORMACOES DO TAMAGOTCHI
    public void imprimeInfo (){
        System.out.println("\n---------------------------------------------------------------------------------------");
        System.out.println("INFORMACOES " + getNome() + ":");
        System.out.println("Idade: " +this.idade);
        System.out.println("\n STATUS " + getNome() + ":");
        System.out.println("Fome: " + getFome() + "%");
        System.out.println("Saúde: " + getSaude() + "%");
        System.out.println("Energia: " + getEnergia() + "%");
        System.out.println("Diversao: " + getFelicidade() + "%");
        System.out.println("---------------------------------------------------------------------------------------\n");
    }

    // ATUALIZA FOME COM O TEMPO
    public void atualizaFome(double quantidade) {
        fome = getFome();
        setFome(Math.max(0, fome - quantidade)); // Evita que a fome fique abaixo de 0
        if (getFome() < 50 && getFome() > 0) {
            System.out.println(nome + " está com fome e precisa comer!");
        } else if (getFome() == 0) {
            verificarEstado();
        }
    }

    // ATUALIZA ENERGIA COM TEMPO
    public void atualizaEnergia(double quantidade) {
        energia = getEnergia();
        setEnergia(Math.max(0, energia - quantidade)); // Evita que a energia fique abaixo de 0
        if (getEnergia() < 50 && getEnergia() > 0) {
            System.out.println(getNome() + " está exausto e precisa descansar!");
        } else if (getEnergia() == 0) {
            verificarEstado();
        }
    }

    // ATUALIZA SAUDE COM TEMPO
    public void atualizaSaude(double quantidade) {
        saude = getSaude();
        setSaude(Math.max(0, saude - quantidade)); // Evita que a saude fique abaixo de 0
        if (getSaude() <= 30) {
            System.out.println(getNome() + " está doente e precisa de remédios!");
        } else if (getSaude() == 0) {
            verificarEstado();
        }
    }

    // ATUALIZA FELICIDADE COM TEMPO
    public void atualizaFelicidade(double quantidade) {
        saude = getFelicidade();
        setFelicidade(Math.max(0, felicidade - quantidade)); // Evita que a saude fique abaixo de 0
        if (getFelicidade() <= 30) {
            System.out.println(getNome() + " está triste e precisa brincar!");
        }
    }

    // VERIFICA ESTADO DO PET
    public void verificarEstado() {
        if (fome == 0 || energia == 0 || saude == 0) {
            setVivo(false);
            System.out.println(nome + " infelizmente não resistiu e morreu...");
        }
    }
}

