
package gabriel.randungeon;

/**
 *
 * @author Gabriel
 */
public class Admin extends Usuario {
    
    public Admin(String nome){
        super(nome, "admin");
    }
    
    public void adicionaMonstro(String nome, int poder, int nivel){
        Monstro monstro = new Monstro(nome, nivel, poder);
    }
    
    public void AdicionaConsumivel(String nome, int poder, int valor){
        Consumivel consumivel = new Consumivel(nome, poder, valor);
    }
    
    public void adicionaEquipavel(String nome, int poder, int valor, char tipo){
        Equipavel equipavel = new Equipavel(nome, tipo, poder, valor);
    }
    
    public void adicionaEfeito(String nome, int poder, int duracao){
        Efeito efeito = new Efeito(nome, poder, duracao);
    }
    
    public void removeMonstro(Monstro n){
        n.removeLista();
    }
    
    public void removeEquipavel(Equipavel n){
        n.removeLista();
    }
    
    public void removeConsumivel(Consumivel n){
        n.removeLista();
    }
    
    public void removeEfeito(Efeito n){
        n.removeLista();
    }
    
//    public void addPersonagem(Personagem personagem, Usuario user){
//        user.addPersonagem(personagem);
//    }
    
//    public void removePersonagem(Personagem personagem, Usuario user){
//        user.removePersonagem(personagem);
//    }
    
    public void alteraSenha(Usuario user){
        user.alteraSenha();
    }
}
