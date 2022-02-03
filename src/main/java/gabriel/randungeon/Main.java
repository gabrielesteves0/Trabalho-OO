
package gabriel.randungeon;

import java.io.FileNotFoundException;

/**
 *
 * @author Gabriel
 */
public class Main {
    public static void main(String[] args){
        try{
           Leitor.leitorEquipaveis(); 
        }catch(FileNotFoundException ex){
            System.out.println("erro nos equipáveis");
        }
        try{
           Leitor.leitorMonstros();
        }catch(FileNotFoundException ex){
            System.out.println("erro nos monstros");
        }
        try{
           Leitor.leitorConsumiveis();
        }catch(FileNotFoundException ex){
            System.out.println("erro nos consumíveis");
        }
    }
}
