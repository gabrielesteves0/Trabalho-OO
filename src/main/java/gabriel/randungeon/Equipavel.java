//Aluno: Gabriel Antônio Esteves Matta
//Matrícula: 202065125A

package gabriel.randungeon;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Gabriel
 */
public class Equipavel extends Item implements InterfaceItem {
    
    public static final char ARMA = 'w';
    public static final char ARMADURA = 'a';
    public static final char BOTA = 'x';
    public static final char MANTO = 'm';
    private final char tipo;
//    private static int qtdEquipaveis = 0;
    private final int id;
    private static List<Equipavel> listaEquipaveis = new ArrayList<>();
    
    
    public Equipavel(String nome, char tipo, int poder, int valor){
        super(nome, poder, valor);
        switch (tipo) {
            case ARMA:
                this.tipo = ARMA;
                break;
            case ARMADURA:
                this.tipo = ARMADURA;
                break;
            case BOTA:
                this.tipo = BOTA;
                break;
            default:
                this.tipo = MANTO;
                break;
        }
        if(!listaEquipaveis.isEmpty())
            this.id = listaEquipaveis.size();
        else
            this.id = 0;
//        qtdEquipaveis++;
        listaEquipaveis.add(this);
    }
    
    
    public boolean verificaTipo(char a){
        if(this.tipo == a)
            return true;
        else
            return false;
    }
    
    public static Equipavel getEquipavel(String nome){
        for(Equipavel equipavel : listaEquipaveis){
            if(equipavel.getNome().equals(nome))
                return equipavel;
        }
        return null;
    }
    
    public static int getQtd(){return listaEquipaveis.size();}
    
    @Override
    public int getId(){return this.id;}
    
    public char getTipo(){return this.tipo;}
    
    public static void imprimeLista(){
        for(Equipavel equipavel : listaEquipaveis){
            System.out.println(equipavel.getNome());
        }
    }
    
    @Override
    public void removeLista(){listaEquipaveis.remove(this);}
    
    public boolean estaLista(){
        for(Equipavel equipavel : listaEquipaveis){
            if(this == equipavel)
                return true;
        }
        return false;
    }
    
    @Override
    public void resetaItem(){
        this.equipado = false;
        if(!this.estaLista())
            listaEquipaveis.add(this);
    }
    
    public static Equipavel sorteia(){
        int i = (int)((Math.random()) * listaEquipaveis.size());
        return listaEquipaveis.get(i);
    }
    
    public static List<Equipavel> getLista(){return listaEquipaveis;}
    
}
