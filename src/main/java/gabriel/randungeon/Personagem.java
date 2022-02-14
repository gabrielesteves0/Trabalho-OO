//Aluno: Gabriel Antônio Esteves Matta
//Matrícula: 202065125A

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
    private Usuario usuario;
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
    public static final char BOTA = 'x';
    public static final char MANTO = 'm';
    private static final Map<String, Personagem> listaPersonagens = new HashMap<>();
    private static final List<String> auxListaPersonagens = new ArrayList<>();
    private int poderAdicional;
    private int nivelAtual;

    public Personagem(String nome, Usuario usuario) {
        this.nivel = 1;
        this.poderCombate = 1;
        this.nome = nome;
        this.dinheiro = 0;
        this.arma = null;
        this.armadura = null;
        this.manto = null;
        this.bota = null;
        this.nivelAtual = 0;
        listaPersonagens.put(this.nome, this);
        auxListaPersonagens.add(this.nome);
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public int getNivelAtual(){return this.nivelAtual;}
    
    public void deletaPersonagem(){
        listaPersonagens.remove(this.getNome());
    }

    public void leituraPersonagem(int nivel, int nivelAtual, int dinheiro, String arma, String armadura, String manto, String bota, ArrayList<String> listaMochila) {
        this.nivel = nivel;
        this.dinheiro = dinheiro;
        this.nivelAtual = nivelAtual;
        try {
            this.setEquip(Equipavel.getEquipavel(arma));
        } catch (NullPointerException ex) {
        }
        try {
            this.setEquip(Equipavel.getEquipavel(armadura));
        } catch (NullPointerException ex) {
        }
        try {
            this.setEquip(Equipavel.getEquipavel(manto));
        } catch (NullPointerException ex) {
        }
        try {
            this.setEquip(Equipavel.getEquipavel(bota));
        } catch (NullPointerException ex) {
        }
        for (String nome : listaMochila) {
            Item item = Item.getItem(nome);
            mochila.add(item);
        }
    }
    
    public void avancaNivel(){this.nivelAtual++;}

    public String getNome() {
        return this.nome;
    }

    public static List<String> getListaPersonagens() {
        return auxListaPersonagens;
    }

    public static Personagem getPersonagem(String nome) {
        return listaPersonagens.get(nome);
    }

    public int getDinheiro() {
        return this.dinheiro;
    }

    public void addDinheiro(int n) {
        this.dinheiro += n;
    }

    public List<Item> getMochila() {
        return this.mochila;
    }

    public void setEquip(Equipavel n) {
        switch (n.getTipo()) {
            case ARMA:
                if (this.arma != null) {
                    this.arma.desequipa();
                }
                this.arma = n;
                this.mochila.remove(n);
                break;
            case ARMADURA:
                if (this.armadura != null) {
                    this.armadura.desequipa();
                }
                this.armadura = n;
                this.mochila.remove(n);
                break;
            case MANTO:
                if (this.manto != null) {
                    this.manto.desequipa();
                }
                this.manto = n;
                this.mochila.remove(n);
                break;
            default:
                if (this.bota != null) {
                    this.bota.desequipa();
                }
                this.bota = n;
                this.mochila.remove(n);
                break;
        }
        n.equipa();
    }

    public Equipavel getArma() {
        return this.arma;
    }

    public Equipavel getArmadura() {
        return this.armadura;
    }

    public Equipavel getManto() {
        return this.manto;
    }

    public Equipavel getBota() {
        return this.bota;
    }

    public void removeEquip(Equipavel item) {
        item.desequipa();
        if (item == this.arma) {
            this.arma = null;
        } else if (item == this.armadura) {
            this.armadura = null;
        } else if (item == this.manto) {
            this.manto = null;
        } else {
            this.bota = null;
        }
        this.mochila.add(item);
    }

    public void addEfeito(Efeito n) {
        this.efeitosAtivos.add(n);
    }

    public void clearEfeitos() {
        for (Efeito efeito : this.efeitosAtivos) {
            efeito.usoEfeito();
            if (efeito.verificaAcabou()) {
                this.efeitosAtivos.remove(efeito);
            }
        }
    }

    public void addMochila(Item n) {
        this.mochila.add(n);
    }

    public void removeMochila(Item n) {
        this.mochila.remove(n);
    }

    public void addNivel() {
        this.nivel++;
        this.poderCombate++;
    }

    public void morte() {
        this.usuario.removePersonagem(this);
        listaPersonagens.remove(this.nome);
    }

    private int getBuffsDebuffs() {
        int a = 0;
        for (Efeito efeito : this.efeitosAtivos) {
            a += efeito.getPoder();
        }
        return a;
    }

    private void calculaPoderCombate() {
        this.poderCombate = nivel + this.getBuffsDebuffs() + this.poderAdicional;
        try{
            this.poderCombate += this.arma.getPoder();
        }catch(NullPointerException ex){
        }
        try{
            this.poderCombate += this.armadura.getPoder();
        }catch(NullPointerException ex){
        }
        try{
            this.poderCombate += this.manto.getPoder();
        }catch(NullPointerException ex){
        }
        try{
            this.poderCombate += this.bota.getPoder();
        }catch(NullPointerException ex){
        }
    }

    public void usaItem(Consumivel item) {
        this.poderAdicional = item.getPoder();
        this.calculaPoderCombate();
        this.mochila.remove(item);
    }

    public void resetaAuxiliarPoderCombate() {
        this.poderAdicional = 0;
        this.calculaPoderCombate();
    }

    public int getPoderCombate() {
        this.calculaPoderCombate();
        return this.poderCombate;
    }

    public int getNivel() {
        return nivel;
    }

}
