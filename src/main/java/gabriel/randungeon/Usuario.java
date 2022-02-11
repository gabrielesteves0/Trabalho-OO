
package gabriel.randungeon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;


/**
 *
 * @author Gabriel
 */
public class Usuario {
    private String senha;
    private final String nome;
    private Map<String, Personagem> listaPersonagens = new HashMap<>();
    private List<String> auxListaPersonagens = new ArrayList<>();
    private static List<Usuario> listaUsuarios = new ArrayList<>();
    
    public Usuario(String nome, String senha){
        this.nome = nome;
        this.senha = senha;
        listaUsuarios.add(this);
    }
    
    public static Usuario getUsuario(String nome){
        for(Usuario usuario : listaUsuarios){
            if(usuario.getNome().equals(nome))
                return usuario;
        }
        return null;
    }
    
    public boolean listaVazia(){return this.listaPersonagens.isEmpty();}
    
    public String[] getListaPersonagens(){
        int tamanho = this.listaPersonagens.size();
        String[] listaRetorno = new String[tamanho];
        for(int i = 0; i < this.listaPersonagens.size(); i++){
            listaRetorno[i] = this.listaPersonagens.get(auxListaPersonagens.get(i)).getNome();
        }
        return listaRetorno;
    }
    
    public boolean confirmaSenha(String senha){
        return this.senha.equals(senha);
    }
    
    public String getNome(){return this.nome;}
    
    public void addPersonagem(Personagem personagem){
        if(listaPersonagens.size() >= 3)
            JOptionPane.showMessageDialog(null, "Operação inválida! Lista de personagens cheia!");
        else{
            listaPersonagens.put(personagem.getNome(), personagem);
            auxListaPersonagens.add(personagem.getNome());
        }
    }
    
    public void removePersonagem(Personagem personagem){
        if(listaPersonagens.isEmpty())
            JOptionPane.showMessageDialog(null, "Operação inválida! Lista de personagens cheia!");
        else{
            listaPersonagens.remove(personagem.getNome());
            auxListaPersonagens.remove(personagem.getNome());
        }
    }
    
    public void alteraSenha(){
        boolean x;
        String senhaAtual;
        int contador = 0;
        do{
            senhaAtual = JOptionPane.showInputDialog(null, "Insira a senha atual: ", "Troca de senha", JOptionPane.QUESTION_MESSAGE);
            x = senhaAtual.equals(this.senha);
            if(!senhaAtual.equals(this.senha)){
                JOptionPane.showMessageDialog(null, "Senha incorreta!");
            }
        }while(!x || contador < 3);
        if(contador == 3){
            JOptionPane.showMessageDialog(null, "Excedido o número de tentativas para informar a senha!");
        }else{
            do{
                String senhaNova1 = JOptionPane.showInputDialog(null, "Digite a nova senha:", "Troca de senha", JOptionPane.QUESTION_MESSAGE);
                String senhaNova2 = JOptionPane.showInputDialog(null, "Digite a senha novamente:", "Troca de senha", JOptionPane.QUESTION_MESSAGE);
                x = senhaNova1.equals(senhaNova2);
                if(!x)
                    JOptionPane.showMessageDialog(null, "Senhas diferem! Tente novamente!");
                else
                    this.senha = senhaNova1;
            }while(!x);
        }
    }
}
