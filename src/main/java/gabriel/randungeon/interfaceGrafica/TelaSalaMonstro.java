//Aluno: Gabriel Antônio Esteves Matta
//Matrícula: 202065125A

package gabriel.randungeon.interfaceGrafica;

import gabriel.randungeon.Leitor;
import gabriel.randungeon.Monstro;
import gabriel.randungeon.Personagem;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
 */
public class TelaSalaMonstro extends JFrame {


    private Personagem personagem;
    private EntreSalas frame;

    public TelaSalaMonstro(Personagem personagem, EntreSalas frame) {
        
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
        this.personagem = personagem;
        personagem.resetaAuxiliarPoderCombate();
        this.frame = frame;
        PainelSalaMonstro painel = new PainelSalaMonstro((Monstro) MenuPrincipal.niveis.get(MenuPrincipal.contadorNivel).getObjetoSala(),
                this.personagem, this);

        this.getContentPane().add(painel);
        this.setVisible(true);
        this.frame.setVisible(false);
    }
    
    public EntreSalas getFrameBase(){return this.frame;}

}
