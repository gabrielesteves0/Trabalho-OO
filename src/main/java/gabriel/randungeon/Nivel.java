
package gabriel.randungeon;


/**
 *
 * @author Gabriel
 */

public class Nivel {
    private final int qtdSalas;
    private final char[] salas;
    private static int qtdNiveis = 0;
    public Nivel(){
        qtdNiveis++;
        switch (qtdNiveis) {
            case 1:
                this.qtdSalas = 2;
                break;
            case 2:
                this.qtdSalas = 3;
                break;
            case 3:
                this.qtdSalas = 5;
                break;
            case 4:
                this.qtdSalas = 5;
                break;
            case 5:
                this.qtdSalas = 6;
                break;
            case 6:
                this.qtdSalas = 6;
                break;
            case 7:
                this.qtdSalas = 7;
                break;
            case 8:
                this.qtdSalas = 7;
                break;
            default:
                this.qtdSalas = 9;
                break;
        }
        salas = new char[this.qtdSalas];
        for (int i = 0; i < this.qtdSalas; i++) {
            this.salas[i] = sorteiaTipo();
        }
    }
    
    //Se o valor de salas[i] for 'm' -> monstro; se for 'l' -> lojas;
    //se for 'a' -> armadilha; se for 'i' -> item ou benção; se for 'n' -> nada
    
    private char sorteiaTipo(){
        int x;
        x = (int) ((Math.random() * 100) + 1);
        if(qtdNiveis <= 3){
            if(x <= 30)
                return 'm';
            else if(x <= 35)
                return 'l';
            else if(x <= 50)
                return 'a';
            else if(x <= 80)
                return 'i';
            else
                return 'n';
        }else if(qtdNiveis > 3 && qtdNiveis <= 6){
            if(x <= 40)
                return 'm';
            else if(x <= 55)
                return 'l';
            else if(x <= 75)
                return 'a';
            else if(x <= 95)
                return 'i';
            else
                return 'n';
        }else{
            if(x <= 50)
                return 'm';
            else if(x <= 60)
                return 'l';
            else if(x <= 85)
                return 'a';
            else
                return 'i';
        }
    }
    
    public static int getQtdNiveis(){return qtdNiveis;}
    
    public static void resetQtdNiveis(){qtdNiveis = 0;}
    
}
