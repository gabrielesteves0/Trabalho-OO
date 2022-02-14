//Aluno: Gabriel Antônio Esteves Matta
//Matrícula: 202065125A

package gabriel.randungeon.interfaceGrafica;

import gabriel.randungeon.Consumivel;
import gabriel.randungeon.Efeito;
import gabriel.randungeon.Equipavel;
import gabriel.randungeon.Monstro;
import gabriel.randungeon.Personagem;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
 */
public class Nivel {

    private final int qtdSalas;
    private final char[] salas;
    private static int qtdNiveis = 0;
    private final char MONSTRO = 'm';
    private final char LOJA = 'l';
    private final char ARMADILHA = 'a';
    private final char ITEM = 'i';
    private final char NADA = 'n';
    private Map<Integer, Object> objetosNivel = new HashMap<>();
    private int contadorSalas;

    public Nivel() {
        contadorSalas = 0;
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
            switch (this.salas[i]) {
                case MONSTRO:
                    objetosNivel.put(i, Monstro.sorteia());
                    break;
                case LOJA: {
                    int x = (int) ((Math.random()) * 100);
                    if (x >= 40) {
                        objetosNivel.put(i, Consumivel.sorteia());
                    } else {
                        objetosNivel.put(i, Equipavel.sorteia());
                    }
                    break;
                }
                case ARMADILHA:
                    objetosNivel.put(i, Efeito.sorteia());
                    break;
                case ITEM: {
                    int x = (int) (Math.random());
                    if (x == 0) {
                        objetosNivel.put(i, Consumivel.sorteia());
                    } else {
                        objetosNivel.put(i, Equipavel.sorteia());
                    }
                    break;
                }
                default:
                    objetosNivel.put(i, null);
                    break;
            }
        }

    }

    //Se o valor de salas[i] for 'm' -> monstro; se for 'l' -> lojas;
    //se for 'a' -> armadilha; se for 'i' -> item ou benção; se for 'n' -> nada
    private char sorteiaTipo() {
        int x;
        x = (int) ((Math.random() * 100) + 1);
        if (qtdNiveis <= 3) {
            if (x <= 30) {
                return MONSTRO;
            } else if (x <= 35) {
                return LOJA;
            } else if (x <= 50) {
                return ARMADILHA;
            } else if (x <= 80) {
                return ITEM;
            } else {
                return NADA;
            }
        } else if (qtdNiveis > 3 && qtdNiveis <= 6) {
            if (x <= 40) {
                return MONSTRO;
            } else if (x <= 55) {
                return LOJA;
            } else if (x <= 75) {
                return ARMADILHA;
            } else if (x <= 95) {
                return ITEM;
            } else {
                return NADA;
            }
        } else {
            if (x <= 50) {
                return MONSTRO;
            } else if (x <= 60) {
                return LOJA;
            } else if (x <= 85) {
                return ARMADILHA;
            } else {
                return ITEM;
            }
        }
    }

    public static int getQtdNiveis() {
        return qtdNiveis;
    }

    public static void resetQtdNiveis() {
        qtdNiveis = 0;
    }

    public char getTipoSala(int i) {
        return this.salas[i];
    }

    public int getQtdSalas() {
        return this.qtdSalas;
    }
    
    
    public Object getObjetoSala(){return this.objetosNivel.get(this.contadorSalas);}

    public void joga(Personagem personagem, EntreSalas entreSalas)  {
        switch (this.getTipoSala(this.contadorSalas)) {
            case MONSTRO:
                entreSalas.salaMonstro(personagem);
                break;
            case LOJA:
                entreSalas.salaLoja(personagem);
                break;
            case ITEM:
                entreSalas.salaItem(personagem);
                break;
            case ARMADILHA:
                entreSalas.salaArmadilha(personagem);
                break;
            default:
                JOptionPane.showMessageDialog(MenuPrincipal.menu, "Sala vazia!");
                entreSalas.setVisible(true);
                break;
        }
        contadorSalas++;
    }

}
