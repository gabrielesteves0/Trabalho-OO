
package gabriel.randungeon;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class Efeito extends Item implements InterfaceItem {
    private int duracaoEfeito;
    private final int DURACAO_EFEITO;
//    private static int qtdEfeitos = 0;
    private final int id;
    private static List<Efeito> listaEfeitos = new ArrayList<>();
    
    public Efeito(String nome, int poder, int duracaoEfeito, String img){
        super(nome, poder, 0);
        this.duracaoEfeito = duracaoEfeito;
        DURACAO_EFEITO = duracaoEfeito;
        if(!listaEfeitos.isEmpty())
            this.id = listaEfeitos.size();
        else
            this.id = 0;
//        qtdEfeitos++;
        listaEfeitos.add(this);
    }
    
    public void usoEfeito(){this.duracaoEfeito--;}
    
    public static int getQtd(){return listaEfeitos.size();}
    
    @Override
    public int getId(){return this.id;}
    
    public boolean verificaAcabou(){return this.duracaoEfeito == 0;}
    
    public void imprimeLista(){}
    
    @Override
    public void removeLista(){listaEfeitos.remove(this);}
    
    private boolean estaLista(){
        for(Efeito efeito : listaEfeitos){
            if(this == efeito)
                return true;
        }
        return false;
    }
    
    @Override
    public void resetaItem(){
        this.equipado = false;
        this.duracaoEfeito = DURACAO_EFEITO;
        if(!this.estaLista())
            listaEfeitos.add(this);
    }
    
    public static Efeito sorteia(){return null;}
}
