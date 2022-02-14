//Aluno: Gabriel Antônio Esteves Matta
//Matrícula: 202065125A

package gabriel.randungeon.interfaceGrafica;

import gabriel.randungeon.Admin;
import gabriel.randungeon.Item;
import gabriel.randungeon.Leitor;
import gabriel.randungeon.Personagem;
import gabriel.randungeon.Usuario;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

/**
 *
 * @author Gabriel
 */
public class MenuPrincipal extends JFrame {

    protected static final Map<Integer, Nivel> niveis = new HashMap<>();
    protected static int contadorNivel;
    private static final JButton login = new JButton("Entrar");
    private static final JButton admin = new JButton("Entrar como Admin");
    private static final JButton cadastrar = new JButton("Cadastrar um usuário");
    private static final JLabel label1 = new JLabel("Seja bem vindo, jogador!");
    protected static final JPanel menu = new JPanel();
    private static final JFrame gerenciadorPersonagens = new JFrame();
    private static JList<String> list;
    private static DefaultListModel listModel = new DefaultListModel();
    protected static final JFrame equipaItem = new JFrame();

    protected static Usuario usuarioAtual;

    public MenuPrincipal() {
        contadorNivel = 0;
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.getContentPane().add(menu);
        addWindowListener(new WindowAdapter() {
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
        gerenciadorPersonagens.setVisible(false);
        gerenciadorPersonagens.setSize(400, 400);
        gerenciadorPersonagens.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        gerenciadorPersonagens.addWindowListener(new WindowAdapter() {
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
        
        
        equipaItem.setSize(400, 400);
        equipaItem.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        equipaItem.addWindowListener(new WindowAdapter() {
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
        
        menu.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.setVisible(true);
        menu.setVisible(true);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 2;
        label1.setFont(new Font("Arial", Font.PLAIN, 20));
        menu.add(label1, c);
        c.gridx = 0;
        c.gridy = 2;
        menu.add(login, c);
        c.gridx = 0;
        c.gridy = 4;
        menu.add(admin, c);
        c.gridx = 0;
        c.gridy = 6;
        menu.add(cadastrar, c);
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginactionPerformed(evt);
            }
        });
        cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastraractionPerformed(evt);
            }
        });
        admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminactionPerformed(evt);
            }
        });

    }
    
    public void adminactionPerformed(ActionEvent e){
        String nome = JOptionPane.showInputDialog(menu, "Informe o nome do admin:", "Login", JOptionPane.QUESTION_MESSAGE);
            String senha = JOptionPane.showInputDialog(menu, "Informe a senha:", "Login",
                    JOptionPane.QUESTION_MESSAGE);
        if(nome.equals(Admin.admin.getNome()) && senha.equals(Admin.admin.getSenha())){
            JOptionPane.showMessageDialog(this, "Bem vindo, admin!");
            PainelAdmin painel = new PainelAdmin(this);
        }else{
            JOptionPane.showMessageDialog(this, "Erro! Nome e/ou senha inválidos!");
        }
        
    }

    public void cadastraractionPerformed(ActionEvent e) {
        String nome = JOptionPane.showInputDialog(menu, "Informe o nome do usuário:", "Cadastrar", JOptionPane.QUESTION_MESSAGE);
        String senha = JOptionPane.showInputDialog(menu, "Informe a senha:", "Cadastrar",
                JOptionPane.QUESTION_MESSAGE);
        String senha2 = JOptionPane.showInputDialog(menu, "Confirme a senha:", "Cadastrar",
                JOptionPane.QUESTION_MESSAGE);
        if (senha.equals(senha2)) {
            Usuario user = new Usuario(nome, senha);
            JOptionPane.showMessageDialog(menu, "Usuário cadastrado! Bem-vindo, " + nome + "!");
        } else {
            JOptionPane.showMessageDialog(menu, "Erro ao cadastrar usuário! Senhas diferem!");
        }
    }

    public void loginactionPerformed(ActionEvent e) throws NullPointerException {
        try {
            Usuario user;
            String nome = JOptionPane.showInputDialog(menu, "Informe o nome do usuário:", "Login", JOptionPane.QUESTION_MESSAGE);
            String senha = JOptionPane.showInputDialog(menu, "Informe a senha:", "Login",
                    JOptionPane.QUESTION_MESSAGE);
            if (Usuario.getUsuario(nome) != null && senha.equals(Usuario.getUsuario(nome).getSenha())) {
                user = Usuario.getUsuario(nome);
                menu.setVisible(false);
                this.setVisible(false);
                JOptionPane.showMessageDialog(menu, "Bem-vindo, " + nome + "!");
                usuarioAtual = user;
                telaGerenciamentoPersonagens();
            }else{
                JOptionPane.showMessageDialog(menu, "Usuário e/ou senha inválidos!");
            }
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(menu, "Erro! Tente novamente!");
        }

    }

    public static void telaGerenciamentoPersonagens() {
        JButton addPersonagem = new JButton("Adicionar");
        JButton removePersonagem = new JButton("Remover");
        JButton jogar = new JButton("Jogar com o personagem");
        JPanel painel = new JPanel();
        gerenciadorPersonagens.setVisible(true);
        gerenciadorPersonagens.getContentPane().add(painel);
        String[] listaNomes = usuarioAtual.getListaPersonagens();
        list = new JList<>();
        listModel.addAll(Arrays.asList(listaNomes));
        list.setModel(listModel);
        JScrollPane scrollLista = new JScrollPane();
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisible(true);
        list.setBackground(Color.cyan);
        if (usuarioAtual.listaVazia()) {
            listModel.addElement("Lista de personagens vazia!");
            list.setModel(listModel);
        }
        painel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 2;
        c.gridwidth = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        painel.add(list, c);
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 3;
        painel.add(addPersonagem, c);
        c.gridx = 2;
        c.gridy = 3;
        painel.add(removePersonagem, c);
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 3;
        painel.add(jogar, c);
        String nomeUser = usuarioAtual.getNome();

        addPersonagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPersonagemactionPerformed(evt);
            }
        });
        removePersonagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removePersonagemactionPerformed(evt);
            }
        });
        jogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jogaractionPerformed(evt);
            }
        });
    }

    public static void addPersonagemactionPerformed(ActionEvent e) {

        String nomePersonagem = JOptionPane.showInputDialog(gerenciadorPersonagens,
                "Informe o nome do personagem: ", "Criar personagem", JOptionPane.QUESTION_MESSAGE);
        Personagem personagem = new Personagem(nomePersonagem, usuarioAtual);
        if (usuarioAtual.listaVazia()) {
            listModel.removeAllElements();
            list.setModel(listModel);
        }
        usuarioAtual.addPersonagem(personagem);
        listModel.addElement(nomePersonagem);
        list.setModel(listModel);
        for (int i = 0; i < 8; i++) {
            Item item = Item.sorteiaItem();
            personagem.addMochila(item);
        }
    }

    public static void removePersonagemactionPerformed(ActionEvent e) {
        try {
            int i = list.getSelectedIndex();
            DefaultListModel listModel = (DefaultListModel) list.getModel();
            usuarioAtual.removePersonagem(Personagem.getPersonagem((String) listModel.get(i)));
            listModel.remove(i);
            Personagem.getPersonagem((String) listModel.get(i)).deletaPersonagem();
            if (usuarioAtual.listaVazia()) {
                listModel.addElement("Lista de personagens vazia!");
                list.setModel(listModel);
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(menu, "Erro! Por favor, selecione apenas uma linha!");
        }
    }

    public static void jogaractionPerformed(ActionEvent e) {
        try {
            iniciarJogo(Personagem.getPersonagem(list.getSelectedValue()));
            gerenciadorPersonagens.setVisible(false);
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(menu, "Erro! Por favor, selecione apenas um personagem!");
        } catch (NullPointerException ex2) {
            JOptionPane.showMessageDialog(menu, "Erro! Por favor, crie um personagem antes de jogar!");
        }
    }

    private static void iniciarJogo(Personagem personagem) throws NullPointerException {

        for (int i = 0; i < 10; i++) {
            Nivel nivel = new Nivel();
            niveis.put(i, nivel);
        }

        EntreSalas tela = new EntreSalas(personagem);
    }
    
    
    
//    protected static void entreSalas(Personagem personagem) {
//
//        JButton abrirCinto = new JButton("Gerenciar cinto");
//        JButton continuar = new JButton("Continuar");
//        JButton equiparArma = new JButton("Equipar arma");
//        JButton equiparArmadura = new JButton("Equipar armadura");
//        JButton equiparManto = new JButton("Equipar manto");
//        JButton equiparBota = new JButton("Equipar bota");
//        
//        armaEquipada = new JLabel();
//        armaEquipada.setFont(new Font("Arial", Font.PLAIN, 15));
//        armaduraEquipada = new JLabel();
//        armaduraEquipada.setFont(new Font("Arial", Font.PLAIN, 15));
//        botaEquipada = new JLabel();
//        botaEquipada.setFont(new Font("Arial", Font.PLAIN, 15));
//        mantoEquipado = new JLabel();
//        mantoEquipado.setFont(new Font("Arial", Font.PLAIN, 15));
//        
//        if (personagem.getArma() == null) {
//            armaEquipada.setText("Nenhuma arma equipada");
//        } else {
//            armaEquipada.setText(personagem.getArma().getNome() + " -> Poder: " + personagem.getArma().getPoder());
//        }
//        if (personagem.getArmadura() == null) {
//            armaduraEquipada.setText("Nenhuma armadura equipada");
//        } else {
//            armaduraEquipada.setText(personagem.getArmadura().getNome() + " -> Poder: " + personagem.getArmadura().getPoder());
//        }
//        if (personagem.getManto() == null) {
//            mantoEquipado.setText("Nenhum manto equipado");
//        } else {
//            mantoEquipado.setText(personagem.getManto().getNome() + " -> Poder: " + personagem.getManto().getPoder());
//        }
//        if (personagem.getBota() == null) {
//            botaEquipada.setText("Nenhuma bota equipada");
//        } else {
//            botaEquipada.setText(personagem.getBota().getNome() + " -> Poder: " + personagem.getBota().getPoder());
//        }
//        JPanel painel = new JPanel();
//        telaEntreSalas.getContentPane().add(painel);
//        telaEntreSalas.setVisible(true);
//        painel.setLayout(new GridBagLayout());
//        GridBagConstraints c = new GridBagConstraints();
//        c.gridx = 0;
//        c.gridy = 0;
//        c.gridheight = 2;
//        c.gridwidth = 4;
//        JLabel label1 = new JLabel("Tela de gerenciamento de itens");
//        label1.setFont(new Font("Arial", Font.BOLD, 20));
//        painel.add(label1, c);
//        c.gridx = 1;
//        c.gridy = 2;
//        c.gridheight = 1;
//        c.gridwidth = 2;
//        painel.add(armaEquipada, c);
//        c.gridx = 3;
//        c.gridy = 2;
//        c.gridwidth = 1;
//        painel.add(equiparArma, c);
//        c.gridx = 1;
//        c.gridy = 3;
//        c.gridwidth = 2;
//        painel.add(armaduraEquipada, c);
//        c.gridx = 3;
//        c.gridy = 3;
//        c.gridwidth = 1;
//        painel.add(equiparArmadura, c);
//        c.gridx = 1;
//        c.gridy = 4;
//        c.gridwidth = 2;
//        painel.add(botaEquipada, c);
//        c.gridx = 3;
//        c.gridy = 4;
//        c.gridwidth = 1;
//        painel.add(equiparBota, c);
//        c.gridx = 1;
//        c.gridy = 5;
//        c.gridwidth = 2;
//        painel.add(mantoEquipado, c);
//        c.gridx = 3;
//        c.gridy = 5;
//        c.gridwidth = 1;
//        painel.add(equiparManto, c);
//        c.gridx = 2;
//        c.gridy = 6;
//        c.weighty = 0.5;
//        painel.add(abrirCinto, c);
//        c.gridx = 3;
//        c.gridy = 6;
//        painel.add(continuar, c);
//
//        telaEntreSalas.repaint();
//
//        continuar.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                if (personagem.getNivelAtual() >= 9) {
//                    JOptionPane.showMessageDialog(menu, "Personagem já completou a masmorra!");
//                    System.exit(0);
//                }
//                try {
//                    telaEntreSalas.setVisible(false);
//                    niveis.get(contadorNivel).joga(personagem);
//                } catch (IndexOutOfBoundsException ex) {
//                    try {
//                        contadorNivel++;
//                        personagem.avancaNivel();
//                        telaEntreSalas.setVisible(false);
//                        niveis.get(contadorNivel).joga(personagem);
//                    } catch (NullPointerException ex2) {
//                        JOptionPane.showMessageDialog(telaEntreSalas, "Parabéns! Você venceu!");
//                    }
//                }
//            }
//        });
//        abrirCinto.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                itensMochila.clear();
//                itensCinto.clear();
//                telaEntreSalas.setVisible(false);
//                for (Item item : personagem.getMochila()) {
//                    if (item instanceof Consumivel) {
//                        if (!item.equipado()) {
//                            itensMochila.add((Consumivel) item);
//                        } else {
//                            itensCinto.add((Consumivel) item);
//                        }
//                    }
//                }
//                int tam = itensMochila.size();
//                String nomesMochila[] = new String[tam];
//                for (int i = 0; i < tam; i++) {
//                    nomesMochila[i] = itensMochila.get(i).getNome();
//                }
//                tam = itensCinto.size();
//                String nomesCinto[] = new String[tam];
//                for (int i = 0; i < tam; i++) {
//                    nomesCinto[i] = itensCinto.get(i).getNome();
//                }
//                gerenciarCinto(personagem, nomesCinto, nomesMochila);
//            }
//        });
//        equiparArma.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                telaEntreSalas.setVisible(false);
//                equipaItem(ARMA, personagem);
//            }
//        });
//        equiparArmadura.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                telaEntreSalas.setVisible(false);
//                equipaItem(ARMADURA, personagem);
//            }
//        });
//        equiparManto.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                telaEntreSalas.setVisible(false);
//                equipaItem(MANTO, personagem);
//            }
//        });
//        equiparBota.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                telaEntreSalas.setVisible(false);
//                equipaItem(BOTA, personagem);
//            }
//        });
//
//    }


    

}
