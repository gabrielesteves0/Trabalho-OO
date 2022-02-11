package gabriel.randungeon;

/**
 *
 * @author Gabriel
 */
public abstract class Item implements InterfaceItem {

    private final int poder;
    private final String nome;
    boolean equipado;
    private final int valor;

    public Item(String nome, int poder, int valor) {
        this.nome = nome;
        this.poder = poder;
        this.valor = valor;
        this.equipado = false;
    }

    public int getPoder() {
        return this.poder;
    }

    public String getNome() {
        return this.nome;
    }

    public boolean equipado() {
        return this.equipado;
    }

    public void equipa() {
        this.equipado = true;
    }

    public void desequipa() {
        this.equipado = false;
    }

    public int getValor() {
        return this.valor;
    }

    public abstract int getId();

    public static Item sorteiaItem() {
        int x = (int) (Math.random() * 100);
        if(x < 50){
            return (Item) Equipavel.sorteia();
        }else
            return (Item) Consumivel.sorteia();

    }

    public abstract void removeLista();

    public void resetaItem() {
        this.equipado = false;
    }

}
