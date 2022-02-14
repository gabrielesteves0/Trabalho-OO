//Aluno: Gabriel Antônio Esteves Matta
//Matrícula: 202065125A

package gabriel.randungeon.interfaceGrafica;

import gabriel.randungeon.Leitor;
import gabriel.randungeon.Personagem;
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
public class TelaGerenciadorCinto extends JFrame{
    
    private Personagem personagem;
    private EntreSalas frame;
    

    public TelaGerenciadorCinto(Personagem personagem, String[] nomesCinto, String[] nomesMochila, EntreSalas frame) {
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
        this.frame = frame;
        gerenciarCinto(nomesCinto, nomesMochila);
    }
    
    public EntreSalas getFrameBase(){return this.frame;}
    
    private void gerenciarCinto(String[] nomesCinto, String[] nomesMochila){
        PainelGerenciamentoCinto painel = new PainelGerenciamentoCinto(personagem, this);

        this.getContentPane().add(painel);
        DefaultListModel<String> modeloMochila = new DefaultListModel<>();
        DefaultListModel<String> modeloCinto = new DefaultListModel<>();

        modeloMochila.addAll(Arrays.asList(nomesMochila));

        modeloCinto.addAll(Arrays.asList(nomesCinto));
        painel.listaCinto.setModel(modeloCinto);
        painel.listaMochila.setModel(modeloMochila);
        this.setVisible(true);
    }
}
