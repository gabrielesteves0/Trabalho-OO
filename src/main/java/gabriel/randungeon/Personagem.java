
package gabriel.randungeon;
import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class Personagem {
    private String nome;
    private ArrayList<Equipavel> equip = new ArrayList<>();
    private ArrayList<Item> itens = new ArrayList<>();
    private ArrayList<Efeito> efeitosAtivos = new ArrayList<>();
    private int poderCombate;
    private int nivel;
    private int dinheiro;
    
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
        for(Equipavel item : this.equip){
            if(item.equipado() && item.verificaTipo(n.getTipo())){
                item.desequipa();
                n.equipa();
            }
        }
    }
    
    Equipavel getArma(){
        for(Equipavel item : this.equip){
            if(item.equipado() && item.verificaTipo('w'))
                return item;
        }
        return null;
    }
    
    Item getArmadura(){
    for(Equipavel item : this.equip){
            if(item.equipado() && item.verificaTipo('a'))
                return item;
        }
        return null;
    }
    
    Item getManto(){
    for(Equipavel item : this.equip){
            if(item.equipado() && item.verificaTipo('c'))
                return item;
        }
        return null;
    }
    
    Item getBota(){
    for(Equipavel item : this.equip){
            if(item.equipado() && item.verificaTipo('b'))
                return item;
        }
        return null;
    }
    
    void removeEquip(Equipavel item){item.desequipa();}
    
    void addEfeito(Efeito n){this.efeitosAtivos.add(n); }
    
    void clearEfeitos(){
        for(Efeito efeito : this.efeitosAtivos){
            efeito.usoEfeito();
            if(efeito.verificaAcabou())
                this.efeitosAtivos.remove(efeito);
        }
    }
    
    void addEquips(Equipavel n){this.equip.add(n);}
    
    void retiraEquips(Equipavel n){this.equip.remove(n);}
    
    void addItens(Item n){this.itens.add(n);}
    
    void removeItens(Item n){this.itens.remove(n); }
    
    void addNivel(){
        this.nivel++;
        this.poderCombate++;
    }
    
    void morte(){
        this.nivel = 1;
        this.equip.clear();
        this.itens.clear();
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
        int a = 0;
        for(Equipavel item : this.equip){
            if(item.equipado())
                a += item.getPoder();
        }
        this.poderCombate = a + nivel + this.getBuffsDebuffs();
    }
    
    int getPoderCombate(){return this.poderCombate;}
    
    int getNivel(){return nivel;}
    
}
