//Aluno: Gabriel Antônio Esteves Matta
//Matrícula: 202065125A
package gabriel.randungeon.interfaceGrafica;

import gabriel.randungeon.Item;
import gabriel.randungeon.Leitor;
import gabriel.randungeon.Monstro;
import gabriel.randungeon.Personagem;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
 */
public class PainelSalaMonstro extends javax.swing.JPanel {

    private Monstro monstro;
    protected Personagem personagem;
    private TelaSalaMonstro frame;

    public PainelSalaMonstro(Monstro monstro, Personagem personagem, TelaSalaMonstro frame) {
        this.monstro = monstro;
        this.personagem = personagem;
        this.frame = frame;
        initComponents();
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nomeMonstro = new javax.swing.JLabel();
        poderMonstro = new javax.swing.JLabel();
        poderPlayer = new javax.swing.JLabel();
        botaoLutar = new javax.swing.JButton();
        botaoCinto = new javax.swing.JButton();
        fugir = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(400, 400));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(500, 500));

        nomeMonstro.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        nomeMonstro.setText("Um " + this.monstro.getNome() + " apareceu!");

        poderMonstro.setText("O poder do monstro é " + this.monstro.getPoder());

        poderPlayer.setText("O poder do jogador é " + this.personagem.getPoderCombate());

        botaoLutar.setText("Lutar");
        botaoLutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLutarActionPerformed(evt);
            }
        });

        botaoCinto.setText("Abrir cinto");
        botaoCinto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCintoActionPerformed(evt);
            }
        });

        fugir.setText("Fugir");
        fugir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fugirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(nomeMonstro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(poderMonstro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(poderPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botaoLutar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(botaoCinto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fugir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(226, 226, 226))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(nomeMonstro, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(poderMonstro, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(poderPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoLutar)
                .addGap(18, 18, 18)
                .addComponent(botaoCinto)
                .addGap(18, 18, 18)
                .addComponent(fugir)
                .addContainerGap(217, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botaoLutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLutarActionPerformed
        if (this.personagem.getPoderCombate() > this.monstro.getPoder()) {
            this.personagem.addNivel();
            this.personagem.addDinheiro(this.monstro.calculaRecompensa());
            personagem.resetaAuxiliarPoderCombate();
            JOptionPane.showMessageDialog(this, "Parabéns! Você derrotou o monstro! Sua recompensa foi de: "
                    + this.monstro.calculaRecompensa() + " moedas de ouro!");
            int x = (int) (Math.random() * 100);
            if (x <= 25) {
                Item item = Item.sorteiaItem();
                this.personagem.addMochila(item);
                JOptionPane.showMessageDialog(this, "Um tesouro adicional foi encontrado! Você obteve o item " + item.getNome());
            }
            personagem.clearEfeitos();
            this.frame.setVisible(false);
            this.frame.getFrameBase().setVisible(true);
            this.frame.dispose();
        } else {
            personagem.morte();
            JOptionPane.showMessageDialog(MenuPrincipal.menu, "Você morreu! Tente novamente!");
            this.frame.dispose();
            Leitor.gravaUsuarios();
            Leitor.gravaPersonagens();
            System.exit(0);
        }
    }//GEN-LAST:event_botaoLutarActionPerformed

    private void botaoCintoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCintoActionPerformed
        Cinto abrirCinto = new Cinto(personagem, this);
        DefaultListModel<String> modelo = new DefaultListModel<String>();
        for (Item item : personagem.getMochila()) {
            if (item.equipado()) {
                modelo.addElement(item.getNome());
            }
        }
        abrirCinto.listaCinto.setModel(modelo);
    }//GEN-LAST:event_botaoCintoActionPerformed

    private void fugirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fugirActionPerformed
        int chanceFuga;
        if (monstro.getNivel() > (personagem.getNivel() / 2)) {
            chanceFuga = (monstro.getNivel() - (personagem.getNivel() / 2)) * 10;
        } else {
            chanceFuga = 10;
        }
        int x = (int) (Math.random() * 100);
        if (x <= chanceFuga) {
            JOptionPane.showMessageDialog(this, "Você conseguiu fugir!");
            personagem.clearEfeitos();
            this.frame.setVisible(false);
            this.frame.getFrameBase().setVisible(true);
            this.frame.dispose();
        } else {
            personagem.morte();
            for (int i = 0; i < 8; i++) {
                Item item = Item.sorteiaItem();
                personagem.addMochila(item);
            }
            JOptionPane.showMessageDialog(MenuPrincipal.menu, "Você morreu! Tente novamente!");
            this.frame.dispose();
            System.exit(0);
        }
    }//GEN-LAST:event_fugirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCinto;
    private javax.swing.JButton botaoLutar;
    private javax.swing.JButton fugir;
    private javax.swing.JLabel nomeMonstro;
    private javax.swing.JLabel poderMonstro;
    protected javax.swing.JLabel poderPlayer;
    // End of variables declaration//GEN-END:variables
}
