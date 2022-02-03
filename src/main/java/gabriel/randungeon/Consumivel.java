
package gabriel.randungeon;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class Consumivel extends Item implements InterfaceItem {
    
//    private static int qtdConsumiveis = 0;
    private final int id;
    public static List<Consumivel> listaConsumiveis = new ArrayList<>();
    
    public Consumivel(String nome, int poder, int valor){
        super(nome, poder, valor);
        if(!listaConsumiveis.isEmpty())
            this.id = listaConsumiveis.size();
        else
            this.id = 0;
//        qtdConsumiveis++;
        listaConsumiveis.add(this);
    }
    
    public static int getQtd(){return listaConsumiveis.size();}
    
    @Override
    public int getId(){return this.id;}
    
    public static void imprimeLista(){
        for(Consumivel consumivel : listaConsumiveis){
            System.out.println(consumivel.getNome());
        }
    }
    
    @Override
    public void removeLista(){listaConsumiveis.remove(this);}
    
    private boolean estaLista(){
        for(Consumivel consumivel : listaConsumiveis){
            if(this == consumivel)
                return true;
        }
        return false;
    }
    
    @Override
    public void resetaItem(){
        this.equipado = false;
        if(!this.estaLista())
            listaConsumiveis.add(this);
    }
    
    public static Consumivel sorteia(){return null;}
}
