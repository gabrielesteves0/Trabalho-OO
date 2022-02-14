//Aluno: Gabriel Antônio Esteves Matta
//Matrícula: 202065125A

package gabriel.randungeon.interfaceGrafica;

import gabriel.randungeon.Consumivel;
import gabriel.randungeon.Efeito;
import gabriel.randungeon.Item;
import gabriel.randungeon.Personagem;
import static gabriel.randungeon.Equipavel.ARMA;
import static gabriel.randungeon.Equipavel.ARMADURA;
import static gabriel.randungeon.Equipavel.BOTA;
import static gabriel.randungeon.Equipavel.MANTO;
import gabriel.randungeon.Leitor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
 */
public class EntreSalas extends JFrame {

    protected static JLabel armaEquipada;
    protected static JLabel armaduraEquipada;
    protected static JLabel botaEquipada;
    protected static JLabel mantoEquipado;

    private static JPanel painel = new JPanel();

    protected static char tipoBotaoClicado;

    protected static ArrayList<Consumivel> itensMochila = new ArrayList<>();
    protected static ArrayList<Consumivel> itensCinto = new ArrayList<>();

    private Personagem personagem;

    public EntreSalas(Personagem personagem) {
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
        this.getContentPane().add(painel);
        entreSalas(this.personagem);
    }

    private void visibilidadePainel(boolean x) {
        this.setVisible(x);
    }

    private EntreSalas getThis() {
        return this;
    }

    protected final void entreSalas(Personagem personagem) {

        JButton abrirCinto = new JButton("Gerenciar cinto");
        JButton continuar = new JButton("Continuar");
        JButton equiparArma = new JButton("Equipar arma");
        JButton equiparArmadura = new JButton("Equipar armadura");
        JButton equiparManto = new JButton("Equipar manto");
        JButton equiparBota = new JButton("Equipar bota");

        armaEquipada = new JLabel();
        armaEquipada.setFont(new Font("Arial", Font.PLAIN, 15));
        armaduraEquipada = new JLabel();
        armaduraEquipada.setFont(new Font("Arial", Font.PLAIN, 15));
        botaEquipada = new JLabel();
        botaEquipada.setFont(new Font("Arial", Font.PLAIN, 15));
        mantoEquipado = new JLabel();
        mantoEquipado.setFont(new Font("Arial", Font.PLAIN, 15));

        if (personagem.getArma() == null) {
            armaEquipada.setText("Nenhuma arma equipada");
        } else {
            armaEquipada.setText(personagem.getArma().getNome() + " -> Poder: " + personagem.getArma().getPoder());
        }
        if (personagem.getArmadura() == null) {
            armaduraEquipada.setText("Nenhuma armadura equipada");
        } else {
            armaduraEquipada.setText(personagem.getArmadura().getNome() + " -> Poder: " + personagem.getArmadura().getPoder());
        }
        if (personagem.getManto() == null) {
            mantoEquipado.setText("Nenhum manto equipado");
        } else {
            mantoEquipado.setText(personagem.getManto().getNome() + " -> Poder: " + personagem.getManto().getPoder());
        }
        if (personagem.getBota() == null) {
            botaEquipada.setText("Nenhuma bota equipada");
        } else {
            botaEquipada.setText(personagem.getBota().getNome() + " -> Poder: " + personagem.getBota().getPoder());
        }

        this.setVisible(true);
        painel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 2;
        c.gridwidth = 4;
        JLabel label1 = new JLabel("Tela de gerenciamento de itens");
        label1.setFont(new Font("Arial", Font.BOLD, 20));
        painel.add(label1, c);
        c.gridx = 1;
        c.gridy = 2;
        c.gridheight = 1;
        c.gridwidth = 2;
        painel.add(armaEquipada, c);
        c.gridx = 3;
        c.gridy = 2;
        c.gridwidth = 1;
        painel.add(equiparArma, c);
        c.gridx = 1;
        c.gridy = 3;
        c.gridwidth = 2;
        painel.add(armaduraEquipada, c);
        c.gridx = 3;
        c.gridy = 3;
        c.gridwidth = 1;
        painel.add(equiparArmadura, c);
        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 2;
        painel.add(botaEquipada, c);
        c.gridx = 3;
        c.gridy = 4;
        c.gridwidth = 1;
        painel.add(equiparBota, c);
        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 2;
        painel.add(mantoEquipado, c);
        c.gridx = 3;
        c.gridy = 5;
        c.gridwidth = 1;
        painel.add(equiparManto, c);
        c.gridx = 2;
        c.gridy = 6;
        c.weighty = 0.5;
        painel.add(abrirCinto, c);
        c.gridx = 3;
        c.gridy = 6;
        painel.add(continuar, c);

        this.repaint();

        continuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (personagem.getNivelAtual() >= 9) {
                    JOptionPane.showMessageDialog(null, "Personagem já completou a masmorra!");
                    System.exit(0);
                }
                try {
                    MenuPrincipal.niveis.get(MenuPrincipal.contadorNivel).joga(personagem, getThis());
                } catch (IndexOutOfBoundsException ex) {
                    try {
                        MenuPrincipal.contadorNivel++;
                        personagem.avancaNivel();
                        MenuPrincipal.niveis.get(MenuPrincipal.contadorNivel).joga(personagem, getThis());
                    } catch (NullPointerException ex2) {
                        JOptionPane.showMessageDialog(null, "Parabéns! Você venceu!");
                    }
                }
            }
        });
        abrirCinto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itensMochila.clear();
                itensCinto.clear();
                visibilidadePainel(false);
                for (Item item : personagem.getMochila()) {
                    if (item instanceof Consumivel) {
                        if (!item.equipado()) {
                            itensMochila.add((Consumivel) item);
                        } else {
                            itensCinto.add((Consumivel) item);
                        }
                    }
                }
                int tam = itensMochila.size();
                String nomesMochila[] = new String[tam];
                for (int i = 0; i < tam; i++) {
                    nomesMochila[i] = itensMochila.get(i).getNome();
                }
                tam = itensCinto.size();
                String nomesCinto[] = new String[tam];
                for (int i = 0; i < tam; i++) {
                    nomesCinto[i] = itensCinto.get(i).getNome();
                }
                gerenciarCinto(personagem, nomesCinto, nomesMochila);
            }
        });
        equiparArma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visibilidadePainel(false);
                equipaItem(ARMA, personagem);
            }
        });
        equiparArmadura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visibilidadePainel(false);
                equipaItem(ARMADURA, personagem);
            }
        });
        equiparManto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visibilidadePainel(false);
                equipaItem(MANTO, personagem);
            }
        });
        equiparBota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                visibilidadePainel(false);
                equipaItem(BOTA, personagem);
            }
        });

    }

    private void gerenciarCinto(Personagem personagem, String[] nomesCinto, String[] nomesMochila) {
        TelaGerenciadorCinto tela = new TelaGerenciadorCinto(personagem, nomesCinto, nomesMochila, this);
    }

    private void equipaItem(char tipo, Personagem personagem) {
        TelaAddEquipavel tela = new TelaAddEquipavel(personagem, tipo, this);

    }

    protected void salaMonstro(Personagem personagem) {
        TelaSalaMonstro telaMonstro;
        telaMonstro = new TelaSalaMonstro(personagem, this);

    }

    protected void salaLoja(Personagem personagem) {
        this.setVisible(true);
        Item item = (Item) MenuPrincipal.niveis.get(MenuPrincipal.contadorNivel).getObjetoSala();
        JOptionPane.showMessageDialog(this, "Um mercador apareceu!");
        boolean x = false;
        int escolha = JOptionPane.showConfirmDialog(this, "Você deseja comprar o item "
                + item.getNome() + " por " + item.getValor() + " moedas?", "Mercador",
                JOptionPane.YES_NO_OPTION);
        if (escolha == JOptionPane.YES_OPTION) {
            if (personagem.getDinheiro() >= item.getValor()) {
                personagem.getMochila().add(item);
                personagem.addDinheiro(item.getValor() * (-1));
                JOptionPane.showMessageDialog(this, "Compra concluída! Agradecemos a preferência!");
                x = true;
            } else {
                JOptionPane.showMessageDialog(this, "Dinheiro insuficiente!");
            }
        }else{
            JOptionPane.showMessageDialog(this, "É uma pena... Esperamos fazer negócios em breve...");
        }

    }

    protected void salaItem(Personagem personagem) {
        this.setVisible(true);
        Item item = (Item) MenuPrincipal.niveis.get(MenuPrincipal.contadorNivel).getObjetoSala();
        JOptionPane.showMessageDialog(this, "Você encontrou um item!!!");
        JOptionPane.showMessageDialog(this, "O item encontrado foi: " + item.getNome());
        personagem.getMochila().add(item);
    }

    protected void salaArmadilha(Personagem personagem) {
        this.setVisible(true);
        Efeito efeito = (Efeito)MenuPrincipal.niveis.get(MenuPrincipal.contadorNivel).getObjetoSala();
        if(efeito.getPoder() < 0){
            JOptionPane.showMessageDialog(this, "O azar caiu sobre você! Um efeito negativo lhe afetou!");
        }else
            JOptionPane.showMessageDialog(this, "A sorte lhe agraciou! Um efeito positivo lhe afetou!");
        JOptionPane.showMessageDialog(this, "O efeito foi: " + efeito.getNome() + " com poder de " + efeito.getPoder() + " e duração de " + efeito.getDuracao() + " rodadas.");
        
    }

}
