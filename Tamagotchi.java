//Trabalho de GA - Redes de Computadores: Aplicação e Transporte - Prof. Márcio Garcia
//Ana Beatriz Stahl, Emanuele Schlemmer, Gabriela Bley, Kelly Natasha Fernandes

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Random;


public class Tamagotchi {
    private String nome;
    private int idade;
    private boolean vivo;

    // ATUALIZAR ESTADOS E NIVEIS INICIAIS (TODOS EM 100%)
    private String estado;
    private double fome = 100;
    private double saude = 100;
    private double energia = 100;
    private double felicidade = 100;
    private ScheduledExecutorService scheduler;

    // CONSTRUTOR
    public Tamagotchi(String nome, int idade,boolean vivo) {
        this.nome = nome;
        this.idade = idade;
        this.vivo = vivo;
        iniciarScheduler();
    }

    // GETTERS E SETTERS
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public boolean isVivo() {
        return vivo;
    }
    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public double getFome() {
        return fome;
    }
    public void setFome(double fome) {
        this.fome = fome;
    }

    public double getSaude() {
        return saude;
    }
    public void setSaude(double saude) {
        this.saude = saude;
    }

    public double getEnergia() {
        return energia;
    }
    public void setEnergia(double energia) {
        this.energia = energia;
    }

    public double getFelicidade() {
        return felicidade;
    }
    public void setFelicidade(double felicidade) {
        this.felicidade = felicidade;
    }

    private void iniciarScheduler() {
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            if (vivo) {
                atualizarNecessidades();
            } else {
                scheduler.shutdown();
            }
        }, 0, 10, TimeUnit.SECONDS); // Atualiza a cada 10 segundos. Dá p mudar!
    }

    private void atualizarNecessidades(){
        Random random = new Random();
        atualizaFome(random.nextInt(5)+1);
        atualizaEnergia(random.nextInt(5)+1);
        atualizaFelicidade(random.nextInt(5)+1);
        atualizaSaude(random.nextInt(5)+1);
        System.out.println("ATUALIZAÇÃO DE STATUS: ");
        imprimeInfo();
    }

    // INFORMACOES DO TAMAGOTCHI
    public void imprimeInfo (){
        System.out.println("\n---------------------------------------------------------------------------------------");
        System.out.println("INFORMAÇÕES DE " + getNome() + ":");
        System.out.println("Idade: " + getIdade() + " anos");
        System.out.println("Fome: " + getFome() + "%");
        System.out.println("Saúde: " + getSaude() + "%");
        System.out.println("Energia: " + getEnergia() + "%");
        System.out.println("Diversao: " + getFelicidade() + "%");
        System.out.println("---------------------------------------------------------------------------------------\n");
    }

    // ATUALIZA FOME COM O TEMPO
    public void atualizaFome(double quantidade) {
        setFome(Math.max(0, getFome() - quantidade)); // Evita que a fome fique abaixo de 0
        if (getFome() < 50 && getFome() > 0) {
            System.out.println(nome + " está com fome e precisa comer!");
        } else if (getFome() == 0) {
            verificarEstado();
        }
    }

    // ATUALIZA ENERGIA COM TEMPO
    public void atualizaEnergia(double quantidade) {
        setEnergia(Math.max(0, getEnergia() - quantidade)); // Evita que a energia fique abaixo de 0
        if (getEnergia() < 50 && getEnergia() > 0) {
            System.out.println(getNome() + " está exausto e precisa descansar!");
        } else if (getEnergia() == 0) {
            verificarEstado();
        }
    }

    // ATUALIZA SAUDE COM TEMPO
    public void atualizaSaude(double quantidade) {
        setSaude(Math.max(0, getSaude() - quantidade)); // Evita que a saude fique abaixo de 0
        if (getSaude() <= 30) {
            System.out.println(getNome() + " está doente e precisa de remédios!");
        } else if (getSaude() == 0) {
            verificarEstado();
        }
    }

    // ATUALIZA FELICIDADE COM TEMPO
    public void atualizaFelicidade(double quantidade) {
        setFelicidade(Math.max(0, getFelicidade() - quantidade)); // Evita que a saude fique abaixo de 0
        if (getFelicidade() <= 30) {
            System.out.println(getNome() + " está triste e precisa brincar!");
        }
    }

    // VERIFICA ESTADO DO PET
    public void verificarEstado() {
        if (getFome() == 0 || getEnergia() == 0 || getSaude() == 0) {
            setVivo(false);
            System.out.println(nome + " infelizmente não resistiu e morreu. Ficou com fome, sem energia e sem saúde!");
        }
    }
}

