
package gabriel.randungeon;

/**
 *
 * @author Gabriel
 */
public interface Itens {
    
    public int getPoder();
    
    public String getNome();
    
    public boolean equipado();
    
    public void equipa();
    
    public void desequipa();
    
    public int getId();
    
    public int getValor();
}
