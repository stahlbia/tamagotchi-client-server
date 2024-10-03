//Trabalho de GA - Redes de Computadores: Aplicação e Transporte - Prof. Márcio Garcia
//Ana Beatriz Stahl, Emanuele Schlemmer, Gabriela Bley, Kelly Natasha Fernandes e Luisa Becker

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Tamagotchi {
    private final String name;
    private final int age;
    private double hunger;
    private double health;
    private double energy;
    private double fun;
    private boolean alive;
    private ScheduledExecutorService scheduler;

    // CONSTRUTOR
    public Tamagotchi(String name, int age,boolean alive) {
        this.name = name;
        this.age = age;
        this.hunger = 100;
        this.health = 100;
        this.energy = 100;
        this.fun = 100;
        this.alive = alive;
        startScheduler();
    }

    // GETTERS E SETTERS
    public String getName() { return name; }

    public int getAge() { return age; }

    public boolean isAlive() { return alive; }
    public void setAlive(boolean alive) { this.alive = alive; }

    public double getHunger() { return hunger; }

    public double getHealth() { return health; }

    public double getEnergy() { return energy; }

    public double getFun() { return fun;}

    private void startScheduler() {
        scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            if (alive) {
                updateStatus();
                System.out.println(getInfo());
            } else {
                scheduler.shutdown();
            }
        }, 0, 10, TimeUnit.SECONDS); // update 10 s
    }

    public void updateStatus() {
        Random random = new Random();
        updateHunger(random.nextInt(5)+1);
        updateEnergy(random.nextInt(5)+1);
        updateFun(random.nextInt(5)+1);
        updateHealth(random.nextInt(5)+1);
    }

    public String getInfo() {
        return String.format(i18n.TAMAGOTCHI_STATUS, getName(), getAge(), getHunger(), getHealth(), getEnergy(), getFun());
    }

    // ATUALIZA FOME COM O TEMPO
    private void updateHunger(double amount) {
        this.hunger = (Math.max(0, getHunger() - amount)); // Evita que a fome fique abaixo de 0
        if (getHunger() < 50 && getHunger() > 0) {
            System.out.printf((i18n.TAMAGOTCHI_HUNGRY) + "%n", name);
        } else if (getHunger() == 0) {
            checkStatus();
        }
    }

    // ATUALIZA ENERGIA COM TEMPO
    private void updateEnergy(double amount) {
        this.energy = (Math.max(0, getEnergy() - amount)); // Evita que a energia fique abaixo de 0
        if (getEnergy() < 50 && getEnergy() > 0) {
            System.out.printf((i18n.TAMAGOTCHI_SLEEPY) + "%n", name);
        } else if (getEnergy() == 0) {
            checkStatus();
        }
    }

    // ATUALIZA SAUDE COM TEMPO
    private void updateHealth(double amount) {
        this.health = (Math.max(0, getHealth() - amount)); // Evita que a saude fique abaixo de 0
        if (getHealth() <= 30) {
            System.out.printf((i18n.TAMAGOTCHI_SICK) + "%n", name);
        } else if (getHealth() == 0) {
            checkStatus();
        }
    }

    // ATUALIZA FELICIDADE COM TEMPO
    private void updateFun(double amount) {
        this.fun = (Math.max(0, getFun() - amount)); // Evita que a saude fique abaixo de 0
        if (getFun() <= 30) {
            System.out.printf((i18n.TAMAGOTCHI_BORED) + "%n", name);
        }
    }

   // ALIMENTAR
    public void feed() {
        if (hunger < 100) {
        hunger = 100;
            System.out.printf((i18n.TAMAGOTCHI_FED_UP) + "%n", name);
        } else {
            System.out.printf((i18n.TAMAGOTCHI_NOT_HUNGRY) + "%n", name);
        }
    }

    // DAR REMÉDIO
    public void medicate() {
        if (health < 100) {
            health = 100;
            System.out.printf((i18n.TAMAGOTCHI_MEDICATED) + "%n", name);
        } else {
            System.out.printf((i18n.TAMAGOTCHI_NOT_SICK) + "%n", name);
        }
    }

    // DORMIR
    public void sleep() {
        if (energy < 100) {
            energy = 100;
            System.out.printf((i18n.TAMAGOTCHI_SLEPT) + "%n", name);
        } else {
            System.out.printf((i18n.TAMAGOTCHI_NOT_SLEEPY) + "%n", name);
        }
    }

    // BRINCAR
    public void play() {
        if (fun < 100) {
            fun = 100;
            System.out.printf((i18n.TAMAGOTCHI_PLAYED) + "%n", name);
        } else {
            System.out.printf((i18n.TAMAGOTCHI_NOT_BORED) + "%n", name);
        }
    }

    // VERIFICA ESTADO DO PET
    public void checkStatus() {
        if (getHunger() == 0 || getEnergy() == 0 || getHealth() == 0) {
            setAlive(false);
            System.out.printf((i18n.TAMAGOTCHI_DEAD) + "%n", name);
        }
    }
}
