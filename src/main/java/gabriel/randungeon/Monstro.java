
package gabriel.randungeon;
import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class Monstro {
    private String nome;
    private int nivel;
    private int poder;
    private static int qtdMonstros = 0;
    private int id;
    private String img;
    public static ArrayList<Monstro> listaMonstros = new ArrayList<>();
    
    public Monstro(String nome, int nivel, int poder, String img){
        this.nome = nome;
        this.nivel = nivel;
        this.poder = poder;
        this.img = img;
        this.id = qtdMonstros;
        qtdMonstros++;
        listaMonstros.add(this);
    }
    
//    public static void leituraMonstros(){}
    
    public String getImg(){return this.img;}
    
    public int getId(){return this.id;}
    
    public int getPoder(){return this.poder;}
    
    public int getNivel(){return this.nivel;}
    
    public String getNome(){return this.nome;}
    
    public static int getQtd(){return qtdMonstros;}
}
