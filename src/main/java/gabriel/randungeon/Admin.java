//Aluno: Gabriel Antônio Esteves Matta
//Matrícula: 202065125A

package gabriel.randungeon;

/**
 *
 * @author Gabriel
 */
public class Admin extends Usuario {
    
    public static Admin admin;
    
    public Admin(){
        super("admin", "admin");
        admin = this;
    }
    
    public void adicionaMonstro(String nome, int nivel, int poder){
        Monstro monstro = new Monstro(nome, nivel, poder);
        Leitor.gravaMonstros();
    }
    
    public void AdicionaConsumivel(String nome, int poder, int valor){
        nome = nome + "(" + poder + ")";
        Consumivel consumivel = new Consumivel(nome, poder, valor);
        Leitor.gravaConsumiveis();
    }
    
    public void adicionaEquipavel(String nome, int poder, int valor, char tipo){
        nome = nome + "(" + poder + ")";
        Equipavel equipavel = new Equipavel(nome, tipo, poder, valor);
        Leitor.gravaEquipaveis();
    }
    
    public void adicionaEfeito(String nome, int poder, int duracao){
        Efeito efeito = new Efeito(nome, poder, duracao);
        Leitor.gravaEfeitos();
    }
    
    public void removeMonstro(Monstro n){
        n.removeLista();
        Leitor.gravaMonstros();
    }
    
    public void removeEquipavel(Equipavel n){
        n.removeLista();
        Leitor.gravaEquipaveis();
    }
    
    public void removeConsumivel(Consumivel n){
        n.removeLista();
        Leitor.gravaConsumiveis();
    }
    
    public void removeEfeito(Efeito n){
        n.removeLista();
        Leitor.gravaEfeitos();
    }
    
    public void alteraSenha(Usuario user){
        user.alteraSenha();
    }
    
    public void removeUsuario(Usuario user){
        for(String personagem : user.getListaPersonagens()){
            if(Personagem.getPersonagem(personagem).getUsuario() == user){
                user.removePersonagem(Personagem.getPersonagem(personagem));
            }
        }
        Usuario.getListaUsuarios().remove(user);
    }
}
