package gabriel.randungeon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Gabriel
 */
public class Leitor {

    private static final File EQUIPAVEIS = new File("equipaveis.txt");
    private static final File CONSUMIVEIS = new File("consumiveis.txt");
    private static final File MONSTROS = new File("monstros.txt");
    private static final File EFEITOS = new File("efeitos.txt");

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
    }

    private static void leitorMonstros() throws FileNotFoundException {

        Scanner scanner = new Scanner(MONSTROS);

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String nome;
            String nivel;
            String poder;
            int i = linha.indexOf(' ');
            nome = linha.substring(0, i);
            linha = linha.substring(i + 1, linha.length());
            nivel = linha.substring(0, 1);
            linha = linha.substring(2, linha.length());
            poder = linha;
            Monstro monstro = new Monstro(nome, Integer.parseInt(nivel), Integer.parseInt(poder));
        }
    }

    private static void leitorConsumiveis() throws FileNotFoundException {

        Scanner scanner = new Scanner(CONSUMIVEIS);

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String nome;
            String poder;
            int i = linha.indexOf(' ');
            nome = linha.substring(0, i);
            linha = linha.substring(i + 1, linha.length());
            poder = linha;
            Consumivel consumivel = new Consumivel(nome, Integer.parseInt(poder), (Integer.parseInt(poder) * 5));
        }
    }

    private static void leitorEquipaveis() throws FileNotFoundException {

        Scanner scanner = new Scanner(EQUIPAVEIS);

        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            String nome;
            String poder;
            char tipo;
            int i = linha.indexOf(' ');
            nome = linha.substring(0, i);
            linha = linha.substring(i + 1, linha.length());
            i = linha.indexOf(' ');
            poder = linha.substring(0, i);
            linha = linha.substring(i + 1, linha.length());
            tipo = linha.charAt(0);
            Equipavel equipavel = new Equipavel(nome, tipo, Integer.parseInt(poder), (Integer.parseInt(poder) * 10));
        }
    }

    private static void leitorEfeitos() throws FileNotFoundException {
        
        Scanner scanner = new Scanner(EFEITOS);
        
        while(scanner.hasNextLine()){
            String linha = scanner.nextLine();
            String nome;
            String poder;
            String duracao;
            int i = linha.indexOf(' ');
            nome = linha.substring(0, i);
            linha = linha.substring(i+1, linha.length());
            i = linha.indexOf(' ');
            poder = linha.substring(0, i);
            linha = linha.substring(i+1, linha.length());
            duracao = linha;
            Efeito efeito = new Efeito(nome, Integer.parseInt(poder), Integer.parseInt(duracao));
        }
    }

}
