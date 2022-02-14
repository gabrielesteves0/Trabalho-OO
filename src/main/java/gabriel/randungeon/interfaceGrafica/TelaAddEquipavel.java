//Aluno: Gabriel Antônio Esteves Matta
//Matrícula: 202065125A

package gabriel.randungeon.interfaceGrafica;

import gabriel.randungeon.Equipavel;
import static gabriel.randungeon.Equipavel.ARMA;
import static gabriel.randungeon.Equipavel.ARMADURA;
import static gabriel.randungeon.Equipavel.MANTO;
import gabriel.randungeon.Item;
import gabriel.randungeon.Leitor;
import gabriel.randungeon.Personagem;
import static gabriel.randungeon.interfaceGrafica.EntreSalas.tipoBotaoClicado;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
 */
public class TelaAddEquipavel extends JFrame {
    
    private Personagem personagem;
    
    protected EntreSalas frameEntreSalas;
    
    public TelaAddEquipavel(Personagem personagem, char tipo, EntreSalas frame){
        this.personagem = personagem;
        this.frameEntreSalas = frame;
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Saída", JOptionPane.YES_NO_OPTION);
                if (i == JOptionPane.YES_OPTION) {
                    Leitor.gravaUsuarios();
                    Leitor.gravaPersonagens();
                    System.exit(0);
                } else {
                    repaint();
                }
            }
        });
        AdicionarEquipavel tela;
        switch (tipo) {
            case ARMA:
                tela = new AdicionarEquipavel(personagem, personagem.getArma(), this);
                tela.jLabel2.update(tela.jLabel2.getGraphics());
                tipoBotaoClicado = tipo;
                break;
            case ARMADURA:
                tela = new AdicionarEquipavel(personagem, personagem.getArmadura(), this);
                tela.jLabel2.update(tela.jLabel2.getGraphics());
                tipoBotaoClicado = tipo;
                break;
            case MANTO:
                tela = new AdicionarEquipavel(personagem, personagem.getManto(), this);
                tela.jLabel2.update(tela.jLabel2.getGraphics());
                tipoBotaoClicado = tipo;
                break;
            default:
                tela = new AdicionarEquipavel(personagem, personagem.getBota(), this);
                tela.jLabel2.update(tela.jLabel2.getGraphics());
                tipoBotaoClicado = tipo;
                break;
        }

        int contador = 0;

        for (Item item : personagem.getMochila()) {
            if (item instanceof Equipavel && !item.equipado()) {
                contador++;
            }
        }
        String[] nomesEquipaveis = new String[contador];
        int i = 0;
        for (Item item : personagem.getMochila()) {
            if (item instanceof Equipavel && !item.equipado()) {
                nomesEquipaveis[i] = item.getNome();
                i++;
            }
        }
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        modeloLista.addAll(Arrays.asList(nomesEquipaveis));

        tela.listaEquipaveis.setModel(modeloLista);
        this.getContentPane().add(tela);
        this.setVisible(true);
    }
    
    protected JFrame getFrameBase(){return this.frameEntreSalas;}
}
