
package gabriel.randungeon;

import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class Consumivel extends Item implements Itens {
    
    private static int qtdConsumiveis = 0;
    private int id;
    public static ArrayList<Consumivel> listaConsumiveis = new ArrayList<>();
    
    public Consumivel(String nome, int poder, int valor, String img){
        super(nome, poder, valor, img);
        this.id = qtdConsumiveis;
        qtdConsumiveis++;
        listaConsumiveis.add(this);
    }
    
    public static int getQtd(){return qtdConsumiveis;}
    
    public int getId(){return this.id;}
}
