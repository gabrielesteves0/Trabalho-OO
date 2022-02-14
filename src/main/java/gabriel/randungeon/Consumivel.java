//Aluno: Gabriel Antônio Esteves Matta
//Matrícula: 202065125A

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
    
    public static Consumivel getConsumivelPorNome(String nome) throws NullPointerException{
        for(Consumivel consumivel : listaConsumiveis){
            if(consumivel.getNome().equals(nome))
                return consumivel;
        }
        return null;
    }
    
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
    
    public static List<Consumivel> getLista(){
        return listaConsumiveis;
    }
    
    public static Consumivel sorteia(){
        int i = (int)(Math.random() * listaConsumiveis.size());
        return listaConsumiveis.get(i);
    }
}
