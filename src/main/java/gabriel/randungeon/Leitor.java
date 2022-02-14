//Aluno: Gabriel Antônio Esteves Matta
//Matrícula: 202065125A

package gabriel.randungeon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Gabriel
 */
public class Leitor {

    private static File equipaveis;
    private static File consumiveis;
    private static File monstros;
    private static File efeitos;
    private static final File EFEITOS = new File("backupEfeitos.txt");
    private static final File CONSUMIVEIS = new File("backupConsumiveis.txt");
    private static final File EQUIPAVEIS = new File("backupEquipaveis.txt");
    private static final File MONSTROS = new File("backupMonstros.txt");
    public static final char ARMA = 'w';
    public static final char ARMADURA = 'a';
    public static final char BOTA = 'x';
    public static final char MANTO = 'm';
    private static File usuarios;
    private static File personagens;

    public Leitor() {
        try {
            leitorMonstros();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro nos monstros!");
        }
        try {
            leitorConsumiveis();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro nos consumíveis!");
        }
        try {
            leitorEquipaveis();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro nos equipáveis!");
        }
        try {
            leitorEfeitos();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro nos efeitos!");
        }
        try {
            leitorUsuarios();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro nos usuarios");
        }
        try {
            leitorPersonagens();
        } catch (FileNotFoundException ex) {
            System.out.println("Erro nos personagens");
        }
        Admin admin = new Admin();
        Usuario.getListaUsuarios().remove((Usuario)admin);

    }

    private static void leitorMonstros() throws FileNotFoundException {

        monstros = new File("monstros.txt");
        try {
            if (monstros.createNewFile()) {

                Scanner scanner = new Scanner(MONSTROS);

                while (scanner.hasNextLine()) {
                    String linha = scanner.nextLine();
                    String nome;
                    String nivel;
                    String poder;
                    int i = linha.indexOf('_');
                    nome = linha.substring(0, i);
                    linha = linha.substring(i + 1, linha.length());
                    nivel = linha.substring(0, 1);
                    linha = linha.substring(2, linha.length());
                    poder = linha;
                    Monstro monstro = new Monstro(nome, Integer.parseInt(nivel), Integer.parseInt(poder));
                }
                gravaMonstros();
            } else {
                Scanner scanner = new Scanner(monstros);

                while (scanner.hasNextLine()) {
                    String linha = scanner.nextLine();
                    String nome;
                    String nivel;
                    String poder;
                    int i = linha.indexOf('_');
                    nome = linha.substring(0, i);
                    linha = linha.substring(i + 1, linha.length());
                    nivel = linha.substring(0, 1);
                    linha = linha.substring(2, linha.length());
                    poder = linha;
                    Monstro monstro = new Monstro(nome, Integer.parseInt(nivel), Integer.parseInt(poder));
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(Leitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void leitorConsumiveis() throws FileNotFoundException {

        consumiveis = new File("consumiveis.txt");
        try {
            if (consumiveis.createNewFile()) {
                Scanner scanner = new Scanner(CONSUMIVEIS);

                while (scanner.hasNextLine()) {
                    String linha = scanner.nextLine();
                    String nome;
                    String poder;
                    int i = linha.indexOf('_');
                    nome = linha.substring(0, i);
                    linha = linha.substring(i + 1, linha.length());
                    poder = linha;
                    Consumivel consumivel = new Consumivel(nome, Integer.parseInt(poder), (Integer.parseInt(poder) * 5));
                }
                gravaConsumiveis();
            } else {
                Scanner scanner = new Scanner(consumiveis);

                while (scanner.hasNextLine()) {
                    String linha = scanner.nextLine();
                    String nome;
                    String poder;
                    int i = linha.indexOf('_');
                    nome = linha.substring(0, i);
                    linha = linha.substring(i + 1, linha.length());
                    poder = linha;
                    Consumivel consumivel = new Consumivel(nome, Integer.parseInt(poder), (Integer.parseInt(poder) * 5));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Leitor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void leitorEquipaveis() throws FileNotFoundException {

        equipaveis = new File("equipaveis.txt");

        try {
            if (equipaveis.createNewFile()) {
                Scanner scanner = new Scanner(EQUIPAVEIS);

                while (scanner.hasNextLine()) {
                    String linha = scanner.nextLine();
                    String nome;
                    String poder;
                    char tipo;
                    int i = linha.indexOf('_');
                    nome = linha.substring(0, i);
                    linha = linha.substring(i + 1, linha.length());
                    i = linha.indexOf('_');
                    poder = linha.substring(0, i);
                    linha = linha.substring(i + 1, linha.length());
                    tipo = linha.charAt(0);
                    Equipavel equipavel = new Equipavel(nome, tipo, Integer.parseInt(poder), (Integer.parseInt(poder) * 10));
                }
                gravaEquipaveis();
            } else {
                Scanner scanner = new Scanner(equipaveis);

                while (scanner.hasNextLine()) {
                    String linha = scanner.nextLine();
                    String nome;
                    String poder;
                    char tipo;
                    int i = linha.indexOf('_');
                    nome = linha.substring(0, i);
                    linha = linha.substring(i + 1, linha.length());
                    i = linha.indexOf('_');
                    poder = linha.substring(0, i);
                    linha = linha.substring(i + 1, linha.length());
                    tipo = linha.charAt(0);
                    Equipavel equipavel = new Equipavel(nome, tipo, Integer.parseInt(poder), (Integer.parseInt(poder) * 10));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Leitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void leitorEfeitos() throws FileNotFoundException {

        efeitos = new File("efeitos.txt");

        try {
            if (efeitos.createNewFile()) {
                
                Scanner scanner = new Scanner(EFEITOS);

                while (scanner.hasNextLine()) {
                    String linha = scanner.nextLine();
                    String nome;
                    String poder;
                    String duracao;
                    int i = linha.indexOf('_');
                    nome = linha.substring(0, i);
                    linha = linha.substring(i + 1, linha.length());
                    i = linha.indexOf('_');
                    poder = linha.substring(0, i);
                    linha = linha.substring(i + 1, linha.length());
                    duracao = linha;
                    Efeito efeito = new Efeito(nome, Integer.parseInt(poder), Integer.parseInt(duracao));
                }
                gravaEfeitos();
            } else {
                Scanner scanner = new Scanner(efeitos);

                while (scanner.hasNextLine()) {
                    String linha = scanner.nextLine();
                    String nome;
                    String poder;
                    String duracao;
                    int i = linha.indexOf('_');
                    nome = linha.substring(0, i);
                    linha = linha.substring(i + 1, linha.length());
                    i = linha.indexOf('_');
                    poder = linha.substring(0, i);
                    linha = linha.substring(i + 1, linha.length());
                    duracao = linha;
                    Efeito efeito = new Efeito(nome, Integer.parseInt(poder), Integer.parseInt(duracao));
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Leitor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void leitorUsuarios() throws FileNotFoundException {
        usuarios = new File("usuarios.txt");
        try {
            if (usuarios.createNewFile()) {
                gravaUsuarios();
            } else {
                Scanner scanner = new Scanner(usuarios);
                while (scanner.hasNextLine()) {
                    String linha = scanner.nextLine();
                    int i = linha.indexOf('_');
                    String nome = linha.substring(0, i);
                    linha = linha.substring(i + 1, linha.length());
                    i = linha.indexOf('_');
                    String senha = linha.substring(0, i);
                    linha = linha.substring(i + 1, linha.length());
                    Usuario usuario = new Usuario(nome, senha);
                    ArrayList<String> listaPersonagens = new ArrayList<>();
//                    while(!linha.equals("")){
//                        i = linha.indexOf('_');
//                        String nomePersonagem = linha.substring(0, i);
//                        listaPersonagens.add(nomePersonagem);
//                        Personagem personagem = Personagem.getPersonagem(nomePersonagem);
//                        linha = linha.substring(i+1, linha.length());
//                    }
//                    usuario.leituraUsuario(listaPersonagens);
                }
            }
        } catch (IOException ex) {
            System.out.println("Erro no arquivo!");
        }

    }

    private static void leitorPersonagens() throws FileNotFoundException {
        personagens = new File("personagens.txt");

        try {
            if (personagens.createNewFile()) {
                gravaPersonagens();
            } else {
                Scanner scanner = new Scanner(personagens);
                while (scanner.hasNextLine()) {
                    String linha = scanner.nextLine();
                    int i = linha.indexOf('_');
                    String nome = linha.substring(0, i);
                    linha = linha.substring(i + 1, linha.length());
                    i = linha.indexOf('_');
                    String usuario = linha.substring(0, i);
                    linha = linha.substring(i + 1, linha.length());
                    i = linha.indexOf('_');
                    String nivel = linha.substring(0, i);
                    linha = linha.substring(i + 1, linha.length());
                    i = linha.indexOf('_');
                    String nivelAtual = linha.substring(0, i);
                    linha = linha.substring(i + 1, linha.length());
                    i = linha.indexOf('_');
                    String dinheiro = linha.substring(0, i);
                    linha = linha.substring(i + 1, linha.length());
                    i = linha.indexOf('_');
                    String arma = linha.substring(0, i);
                    linha = linha.substring(i + 1, linha.length());
                    i = linha.indexOf('_');
                    String armadura = linha.substring(0, i);
                    linha = linha.substring(i + 1, linha.length());
                    i = linha.indexOf('_');
                    String manto = linha.substring(0, i);
                    linha = linha.substring(i + 1, linha.length());
                    i = linha.indexOf('_');
                    String bota = linha.substring(0, i);
                    linha = linha.substring(i + 1, linha.length());
                    Personagem personagem = new Personagem(nome, Usuario.getUsuario(usuario));
                    ArrayList<String> listaItens = new ArrayList<>();
                    while (!linha.equals("")) {
                        i = linha.indexOf('_');
                        String nomeItem = linha.substring(0, i);
                        listaItens.add(nomeItem);
                        linha = linha.substring(i + 1, linha.length());
                    }
                    personagem.leituraPersonagem(Integer.parseInt(nivel), Integer.parseInt(nivelAtual), Integer.parseInt(dinheiro), arma, armadura, manto, bota, listaItens);
                    try {
                        Usuario.getUsuario(usuario).addPersonagem(personagem);
                    } catch (NullPointerException ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao adicionar o personagem " + nome);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Leitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void gravaUsuarios() {
        String retorno = "";
        for (Usuario usuario : Usuario.getListaUsuarios()) {
            String user = usuario.getNome() + "_" + usuario.getSenha() + "_";
            for (String personagem : usuario.getListaPersonagens()) {
                user += personagem + "_";
            }
            retorno += user + "\n";
        }
        try {
            FileWriter escritor = new FileWriter(usuarios);
            escritor.write(retorno);
            escritor.close();
        } catch (IOException ex) {
            Logger.getLogger(Leitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void gravaPersonagens() {
        String saida = "";
        for (String nomePersonagem : Personagem.getListaPersonagens()) {
            Personagem personagem = Personagem.getPersonagem(nomePersonagem);
            String retorno = nomePersonagem + "_" + personagem.getUsuario().getNome() + "_" + personagem.getNivel() + "_" + personagem.getNivelAtual() + "_" + personagem.getDinheiro() + "_";
            if (personagem.getArma() == null) {
                retorno += "null_";
            } else {
                retorno += personagem.getArma().getNome() + "_";
            }

            if (personagem.getArmadura() == null) {
                retorno += "null_";
            } else {
                retorno += personagem.getArmadura().getNome() + "_";
            }

            if (personagem.getManto() == null) {
                retorno += "null_";
            } else {
                retorno += personagem.getManto().getNome() + "_";
            }

            if (personagem.getBota() == null) {
                retorno += "null_";
            } else {
                retorno += personagem.getBota().getNome() + "_";
            }

            for (Item item : personagem.getMochila()) {
                retorno += item.getNome() + "_";
            }
            saida += retorno + "\n";
        }
        try {
            FileWriter escritor = new FileWriter(personagens);
            escritor.write(saida);
            escritor.close();
        } catch (IOException ex) {
            Logger.getLogger(Leitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void gravaMonstros(){
        String retorno = "";
        for(Monstro monstro : Monstro.getLista()){
            retorno += monstro.getNome() + "_" + monstro.getNivel() + "_" + monstro.getPoder() + "\n";
        }
        try {
            FileWriter escritor = new FileWriter(monstros);
            escritor.write(retorno);
            escritor.close();
        } catch (IOException ex) {
            Logger.getLogger(Leitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void gravaEfeitos(){
        String retorno = "";
        for(Efeito efeito : Efeito.getLista()){
            retorno += efeito.getNome() + "_" + efeito.getPoder() + "_" + efeito.getDuracao() + "\n";
        }
        try {
            FileWriter escritor = new FileWriter(efeitos);
            escritor.write(retorno);
            escritor.close();
        } catch (IOException ex) {
            Logger.getLogger(Leitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void gravaEquipaveis(){
        String retorno = "";
        for(Equipavel equipavel : Equipavel.getLista()){
            retorno += equipavel.getNome() + "_" + equipavel.getPoder() + "_" + equipavel.getTipo() + "\n";
        }
        try {
            FileWriter escritor = new FileWriter(equipaveis);
            escritor.write(retorno);
            escritor.close();
        } catch (IOException ex) {
            Logger.getLogger(Leitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void gravaConsumiveis(){
        String retorno = "";
        for(Consumivel consumivel : Consumivel.getLista()){
            retorno += consumivel.getNome() + "_" + consumivel.getPoder() + "\n";
        }
        try {
            FileWriter escritor = new FileWriter(consumiveis);
            escritor.write(retorno);
            escritor.close();
        } catch (IOException ex) {
            Logger.getLogger(Leitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
