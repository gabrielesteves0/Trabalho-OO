
package gabriel.randungeon;

/**
 *
 * @author Gabriel
 */
public abstract class Item implements Itens {
    private int poder;
    private String nome;
    private String img;
    private boolean equipado;
    private int valor;
    
    public Item(String nome, int poder, int valor, String img){
        this.nome = nome;
        this.poder = poder;
        this.img = img;
        this.valor = valor;
        this.equipado = false;
    }
    
    public int getPoder(){return this.poder;}
    
    public String getNome(){return nome;}
    
    public boolean equipado(){return this.equipado;}
    
    public void equipa(){this.equipado = true;}
    
    public void desequipa(){this.equipado = false;}
    
    public int getValor(){return this.valor;}
}
