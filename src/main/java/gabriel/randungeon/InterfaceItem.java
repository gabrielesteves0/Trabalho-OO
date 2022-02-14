//Aluno: Gabriel Antônio Esteves Matta
//Matrícula: 202065125A

package gabriel.randungeon;

/**
 *
 * @author Gabriel
 */

public interface InterfaceItem {
    
    public int getPoder();
    
    public String getNome();
    
    public boolean equipado();
    
    public void equipa();
    
    public void desequipa();
    
    public int getId();
    
    public int getValor();
    
    public void resetaItem();
    
    public void removeLista();
    
}
