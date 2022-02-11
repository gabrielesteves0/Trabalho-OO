
package gabriel.randungeon;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Gabriel
 */
public class Personagem {
    private final String nome;
    private List<Item> mochila = new ArrayList<>();
    private List<Efeito> efeitosAtivos = new ArrayList<>();
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
    private static final Map<String, Personagem> listaPersonagens = new HashMap<>();
    private int poderAdicional;
    
    public Personagem(String nome){
        this.nivel = 1;
        this.poderCombate = 1;
        this.nome = nome;
        this.dinheiro = 0;
        this.arma = null;
        this.armadura = null;
        this.manto = null;
        this.bota = null;
        listaPersonagens.put(this.nome, this);
    }
    
    public String getNome(){return this.nome;}
    
    public static Personagem getPersonagem(String nome){
        return listaPersonagens.get(nome);
    }
    
    int getDinheiro(){return this.dinheiro;}
    
    public void addDinheiro(int n){this.dinheiro += n;}
    
    public List<Item> getMochila(){return this.mochila;}
    
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
    
    public Equipavel getArma(){return this.arma;}
    
    public Equipavel getArmadura(){return this.armadura;}
    
    public Equipavel getManto(){return this.manto;}
    
    public Equipavel getBota(){return this.bota;}
    
    public void removeEquip(Equipavel item){
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
    
    public void addEfeito(Efeito n){this.efeitosAtivos.add(n); }
    
    public void clearEfeitos(){
        for(Efeito efeito : this.efeitosAtivos){
            efeito.usoEfeito();
            if(efeito.verificaAcabou())
                this.efeitosAtivos.remove(efeito);
        }
    }
    
    public void addMochila(Item n){this.mochila.add(n);}
    
    public void removeMochila(Item n){this.mochila.remove(n); }
    
    public void addNivel(){
        this.nivel++;
        this.poderCombate++;
    }
    
    public void morte(){
        this.nivel = 1;
        this.resetaAuxiliarPoderCombate();
        this.mochila.clear();
        this.efeitosAtivos.clear();
        this.armadura = null;
        this.arma = null;
        this.manto = null;
        this.bota = null;
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
                manto.getPoder()+ bota.getPoder() + this.poderAdicional;
    }
    
    public void usaItem(Consumivel item){
        this.poderAdicional = item.getPoder();
        this.calculaPoderCombate();
    }
    
    public void resetaAuxiliarPoderCombate(){
        this.poderAdicional = 0;
        this.calculaPoderCombate();
    }
    
    public int getPoderCombate(){
        this.calculaPoderCombate();
        return this.poderCombate;
    }
    
    public int getNivel(){return nivel;}
    
}
