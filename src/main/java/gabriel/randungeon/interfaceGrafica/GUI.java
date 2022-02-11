package gabriel.randungeon.interfaceGrafica;

import gabriel.randungeon.Consumivel;
import gabriel.randungeon.Equipavel;
import gabriel.randungeon.Item;
import gabriel.randungeon.Personagem;
import gabriel.randungeon.Usuario;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

/**
 *
 * @author Gabriel
 */
public class GUI extends JFrame {

    //Lista de usuarios = Map<Senha, Usuario> -> String é a senha do usuário!!!
    private static final Map<String, Usuario> usuarios = new HashMap<>();
    private static final Map<Integer, Nivel> niveis = new HashMap<>();
    private static int contadorNivel;
    private static final JButton login = new JButton("Entrar");
    private static final JButton admin = new JButton("Entrar como Admin");
    private static final JButton cadastrar = new JButton("Cadastrar um usuário");
    private static final JLabel label1 = new JLabel("Seja bem vindo, jogador!");
    protected static final JPanel menu = new JPanel();
    private static final JFrame gerenciadorPersonagens = new JFrame();
    private static JList list;
    private static DefaultListModel listModel = new DefaultListModel();
    private JLabel nomeMonstro = new JLabel();
    private final JLabel nomeJogador = new JLabel();
    private static final JFrame telaEntreSalas = new JFrame();
    protected static final JFrame telaGerenciadorCinto = new JFrame();
    private final JFrame salaMonstro = new JFrame();
    private final JFrame salaLoja = new JFrame();
    private final JFrame salaArmadilha = new JFrame();
    private final JFrame salaItem = new JFrame();
    private final JFrame salaVazia = new JFrame();
    private final char MONSTRO = 'm';
    private final char LOJA = 'l';
    private final char ARMADILHA = 'a';
    private final char ITEM = 'i';
    
    protected static Usuario usuarioAtual;

    public GUI() {
        contadorNivel = 0;
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(menu);
        gerenciadorPersonagens.setVisible(false);
        gerenciadorPersonagens.setSize(400, 400);
        gerenciadorPersonagens.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaEntreSalas.setSize(400, 400);
        telaEntreSalas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaGerenciadorCinto.setSize(400, 400);
        telaGerenciadorCinto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    }

    public void cadastraractionPerformed(ActionEvent e) {
        String nome = JOptionPane.showInputDialog(menu, "Informe o nome do usuário:", "Cadastrar", JOptionPane.QUESTION_MESSAGE);
        String senha = JOptionPane.showInputDialog(menu, "Informe a senha:", "Cadastrar",
                JOptionPane.QUESTION_MESSAGE);
        String senha2 = JOptionPane.showInputDialog(menu, "Confirme a senha:", "Cadastrar",
                JOptionPane.QUESTION_MESSAGE);
        if (senha.equals(senha2)) {
            Usuario user = new Usuario(nome, senha);
            usuarios.put(senha, user);
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
            if (usuarios.get(senha).equals(Usuario.getUsuario(nome))) {
                user = Usuario.getUsuario(nome);
                menu.setVisible(false);
                this.setVisible(false);
                JOptionPane.showMessageDialog(menu, "Bem-vindo, " + nome + "!");
                usuarioAtual = user;
                telaGerenciamentoPersonagens();
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
        list = new JList(listaNomes);
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
        Personagem personagem = new Personagem(nomePersonagem);
        if (usuarioAtual.listaVazia()) {
            listModel.removeAllElements();
            list.setModel(listModel);
        }
        usuarioAtual.addPersonagem(personagem);
        listModel.addElement(nomePersonagem);
        list.setModel(listModel);
    }

    public static void removePersonagemactionPerformed(ActionEvent e) {
        try {
            int i = list.getSelectedIndex();
            DefaultListModel listModel = (DefaultListModel) list.getModel();
            usuarioAtual.removePersonagem(Personagem.getPersonagem((String) listModel.get(i)));
            listModel.remove(i);
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
            int i = list.getSelectedIndex();
            iniciarJogo(Personagem.getPersonagem((String) listModel.get(i)));
            gerenciadorPersonagens.setVisible(false);
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(menu, "Erro! Por favor, selecione apenas um personagem!");
        } catch (NullPointerException ex2){
            JOptionPane.showMessageDialog(menu, "Erro! Por favor, crie um personagem antes de jogar!");
        }
    }

    private static void iniciarJogo(Personagem personagem) throws NullPointerException {
        for (int i = 0; i < 8; i++) {
            Item item = Item.sorteiaItem();
            personagem.addMochila(item);
        }
        for (int i = 0; i < 10; i++) {
            Nivel nivel = new Nivel();
            niveis.put(i, nivel);
        }

        entreSalas(personagem);
    }

    protected static void entreSalas(Personagem personagem) {
        JButton abrirCinto = new JButton("Gerenciar cinto");
        JButton continuar = new JButton("Continuar");
        JButton equiparArma = new JButton("Equipar arma");
        JButton equiparArmadura = new JButton("Equipar armadura");
        JButton equiparManto = new JButton("Equipar manto");
        JButton equiparBota = new JButton("Equipar bota");
        JLabel armaEquipada = new JLabel();
        armaEquipada.setFont(new Font("Arial", Font.PLAIN, 15));
        JLabel armaduraEquipada = new JLabel();
        armaduraEquipada.setFont(new Font("Arial", Font.PLAIN, 15));
        JLabel botaEquipada = new JLabel();
        botaEquipada.setFont(new Font("Arial", Font.PLAIN, 15));
        JLabel mantoEquipado = new JLabel();
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
        JPanel painel = new JPanel();
        telaEntreSalas.getContentPane().add(painel);
        telaEntreSalas.setVisible(true);
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

        continuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    telaEntreSalas.setVisible(false);
                    niveis.get(contadorNivel).joga(personagem);
                } catch (IndexOutOfBoundsException ex) {
                    try {
                        contadorNivel++;
                        telaEntreSalas.setVisible(false);
                        niveis.get(contadorNivel).joga(personagem);
                    } catch (NullPointerException ex2) {
                        JOptionPane.showMessageDialog(telaEntreSalas, "Parabéns! Você venceu!");
                    }
                }
            }
        });
        abrirCinto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telaEntreSalas.setVisible(false);
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

    }

    protected static ArrayList<Consumivel> itensMochila = new ArrayList<>();
    protected static ArrayList<Consumivel> itensCinto = new ArrayList<>();

    private static void gerenciarCinto(Personagem personagem, String[] nomesCinto, String[] nomesMochila) {

        PainelGerenciamentoCinto painel = new PainelGerenciamentoCinto(personagem);

        telaGerenciadorCinto.getContentPane().add(painel);
        painel.listaCinto.setListData(nomesCinto);
        painel.listaMochila.setListData(nomesMochila);
        telaGerenciadorCinto.setVisible(true);
    }

    private void equipaItem(char tipo) {

    }

    protected static void salaMonstro() {
        JOptionPane.showMessageDialog(menu, "Monstro");
    }

    protected static void salaLoja() {
        JOptionPane.showMessageDialog(menu, "Loja");
    }

    protected static void salaItem() {
        JOptionPane.showMessageDialog(menu, "Item");
    }

    protected static void salaArmadilha() {
        JOptionPane.showMessageDialog(menu, "Trap");
    }

}
