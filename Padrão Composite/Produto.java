package supermercado.composite;

public class Produto implements ItemSupermercado {

    private final String nome;
    private final double preco;

    public Produto(String nome, double preco) {
        this.nome  = nome;
        this.preco = preco;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public double getPrecoTotal() {
        return preco;
    }

    @Override
    public void exibir(String indentacao) {
        System.out.printf("%s- %s: R$ %.2f%n", indentacao, nome, preco);
    }
}
