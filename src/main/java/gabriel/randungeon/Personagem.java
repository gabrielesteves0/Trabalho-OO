
package gabriel.randungeon;

import java.util.List;

/**
 *
 * @author Gabriel
 */
public class Personagem {
    private final String nome;
    private List<Item> mochila;
    private List<Efeito> efeitosAtivos;
    private int poderCombate;
    private int nivel;
    private int dinheiro;
    private Equipavel arma;
    private Equipavel armadura;
    private Equipavel manto;
    private Equipavel bota;
    public static final char ARMA = 'w';
    public static final char ARMADURA = 'a';
    public static final char BOTA = 'b';
    public static final char MANTO = 'c';
    
    public Personagem(String nome){
        this.nivel = 1;
        this.poderCombate = 1;
        this.nome = nome;
        this.dinheiro = 0;
    }
    
    String getNome(){return this.nome;}
    
    int getDinheiro(){return this.dinheiro;}
    
    void addDinheiro(int n){this.dinheiro += n;}
    
    void setEquip(Equipavel n){
        switch (n.getTipo()) {
            case ARMA:
                if(this.arma != null)
                    this.arma.desequipa();
                this.arma = n;
                break;
            case ARMADURA:
                if(this.armadura != null)
                    this.armadura.desequipa();
                this.armadura = n;
                break;
            case MANTO:
                if(this.manto != null)
                    this.manto.desequipa();
                this.manto = n;
                break;
            default:
                if(this.bota != null)
                    this.bota.desequipa();
                this.bota = n;
                break;
        }
        n.equipa();
    }
    
    Equipavel getArma(){return this.arma;}
    
    Equipavel getArmadura(){return this.armadura;}
    
    Equipavel getManto(){return this.manto;}
    
    Equipavel getBota(){return this.bota;}
    
    void removeEquip(Equipavel item){
        item.desequipa();
        if(item == this.arma)
            this.arma = null;
        else if(item == this.armadura)
            this.armadura = null;
        else if(item == this.manto)
            this.manto = null;
        else
            this.bota = null;
    }
    
    void addEfeito(Efeito n){this.efeitosAtivos.add(n); }
    
    void clearEfeitos(){
        for(Efeito efeito : this.efeitosAtivos){
            efeito.usoEfeito();
            if(efeito.verificaAcabou())
                this.efeitosAtivos.remove(efeito);
        }
    }
    
    void addMochila(Item n){this.mochila.add(n);}
    
    void removeMochila(Item n){this.mochila.remove(n); }
    
    void addNivel(){
        this.nivel++;
        this.poderCombate++;
    }
    
    void morte(){
        this.nivel = 1;
        this.mochila.clear();
        this.efeitosAtivos.clear();
        this.calculaPoderCombate();
    }
    
    private int getBuffsDebuffs(){
        int a = 0;
        for(Efeito efeito : this.efeitosAtivos){
            a += efeito.getPoder();
        }
        return a;
    }
    
    private void calculaPoderCombate(){
        this.poderCombate = nivel + this.getBuffsDebuffs() + arma.getPoder()+ armadura.getPoder()+
                manto.getPoder()+ bota.getPoder();
    }
    
    int getPoderCombate(){return this.poderCombate;}
    
    int getNivel(){return nivel;}
    
}
