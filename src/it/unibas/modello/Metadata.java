package it.unibas.modello;

public class Metadata {
    
    private String nome;
    private String valore;

    public Metadata(String nome, String valore) {
        this.nome = nome;
        this.valore = valore;
    }

    

    public String getNome() {
        return nome;
    }

    public String getValore() {
        return valore;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValore(String valore) {
        this.valore = valore;
    }
    
    
}
