
package gabriel.randungeon;

/**
 *
 * @author Gabriel
 */
public class Equipavel extends Item implements Itens {
    
    private char tipo;
    private static int qtdEquipaveis = 0;
    private int id;
    
    //Tipos: 'w' -> arma (weapon); 'a' -> armadura; 'b' -> botas; 'c' -> manto (capa)
    
    public Equipavel(String nome, char tipo, int poder, int valor, String img){
        super(nome, poder, valor, img);
        this.tipo = tipo;
        this.id = qtdEquipaveis;
        qtdEquipaveis++;
    }
    
    
    public boolean verificaTipo(char a){
        if(this.tipo == a)
            return true;
        else
            return false;
    }
    
    public static int getQtd(){return qtdEquipaveis;}
    
    public int getId(){return this.id;}
    
    public char getTipo(){return this.tipo;}
    
}
