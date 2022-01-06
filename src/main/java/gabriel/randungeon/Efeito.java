
package gabriel.randungeon;

/**
 *
 * @author Gabriel
 */
public class Efeito extends Item implements Itens {
    private int duracaoEfeito;
    private static int qtdEfeitos = 0;
    private int id;
    
    public Efeito(String nome, int poder, int duracaoEfeito, String img){
        super(nome, poder, 0, img);
        this.duracaoEfeito = duracaoEfeito;
        this.id = qtdEfeitos;
        qtdEfeitos++;
    }
    
    public void usoEfeito(){this.duracaoEfeito--;}
    
    public static int getQtd(){return qtdEfeitos;}
    
    public int getId(){return this.id;}
    
    public boolean verificaAcabou(){return this.duracaoEfeito == 0;}
}
