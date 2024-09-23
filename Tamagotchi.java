
public class Tamagotchi {
    private String estado;
    private String nome;
    private int idade;
    private double peso;
    private boolean vivo;
    private int vezesAcordado;

    //abaixo, construindo o construtor com os parâmetros necessários para o Tamagotchi
    public Tamagotchi (String nome, int idade, double peso, boolean vivo, int vezesAcordado){
        this.nome = nome;
        this.idade = idade;
        this.peso = peso;
        this.vivo = vivo;
        this.vezesAcordado = vezesAcordado;
    }
    //agora criando os métodos de acesso para o Tamagotchi
    public String getNome (){
        return this.nome;
    }
    public int getIdade (){
        return this.idade;
    }
    public double getPeso (){
        return this.peso;
    }
    public boolean getVivo (){
        return this.vivo;
    }
    public int getvezesAcordado (){
        return this.vezesAcordado;
    }
    //agora criando os métodos para alterar algumas informações posteriormente
    public void setNome (String nome){
        this.nome = nome;
    }
    public void setIdade (int idade){
        this.idade = idade;
    }
    public void setPeso (double peso){
        this.peso = peso;
    }
    public void isVivo (boolean vivo){
        this.vivo = vivo;
    }
    public void setvezesAcordado (int vezesAcordado){
        this.vezesAcordado = vezesAcordado;
    }
    //Criando um método para imprimir informações e atualizá-las, para chamá-lo posteriormente na classe Principal
    public void imprimeInfo (){
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
        System.out.println("Tamagotchi");
        System.out.println("nome: " +this.nome);
        System.out.println("idade: " +this.idade);
        System.out.println("peso: "+this.peso+"kg");
        System.out.println("Ele já está a " +this.vezesAcordado+ " dias acordado");
        System.out.println("---------------------------------------------------------------------------------------------------------------------");
    }

    public String getEstado(){
        return estado;
    }
}

