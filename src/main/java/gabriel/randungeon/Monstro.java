
package gabriel.randungeon;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gabriel
 */
public class Monstro {
    private final String nome;
    private final int nivel;
    private final int poder;
//    private static int qtdMonstros = 0;
    private final int id;
    public static List<Monstro> listaMonstros = new ArrayList<>();
    
    public Monstro(String nome, int nivel, int poder){
        this.nome = nome;
        this.nivel = nivel;
        this.poder = poder;
        if(listaMonstros.isEmpty())
            this.id = listaMonstros.size();
        else
            this.id = 0;
//        qtdMonstros++;
        listaMonstros.add(this);
    }
    
//    public static void leituraMonstros(){}

//    public String getImg(){return this.img;}
    
    public int getId(){return this.id;}
    
    public int getPoder(){return this.poder;}
    
    public int getNivel(){return this.nivel;}
    
    public String getNome(){return this.nome;}
    
    public static int getQtd(){return listaMonstros.size();}
    
    public void removeLista(){listaMonstros.remove(this);}
    
    public static void imprimeNomes(){
        for(Monstro m : listaMonstros){
            System.out.println(m.getNome());
        }
    }
    
    public static Monstro sorteia(){
        int i = (int)((Math.random()) * listaMonstros.size());
        return listaMonstros.get(i);
    }
    
    public int calculaRecompensa(){
        return (int) ((this.nivel * 2) * ((Math.random() * 4)+1));
    }
}
