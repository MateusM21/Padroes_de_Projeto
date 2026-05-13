package supermercado.composite;

import java.util.ArrayList;
import java.util.List;

public class Categoria implements ItemSupermercado {

    private final String nome;
    private final List<ItemSupermercado> itens = new ArrayList<>();

    public Categoria(String nome) {
        this.nome = nome;
    }

    public void adicionar(ItemSupermercado item) {
        itens.add(item);
    }

    public void remover(ItemSupermercado item) {
        itens.remove(item);
    }

    public List<ItemSupermercado> getItens() {
        return itens;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public double getPrecoTotal() {
        return itens.stream().mapToDouble(ItemSupermercado::getPrecoTotal).sum();
    }

    @Override
    public void exibir(String indentacao) {
        System.out.println(indentacao + "[" + nome + "] — Total: R$ " + String.format("%.2f", getPrecoTotal()));
        for (ItemSupermercado item : itens) {
            item.exibir(indentacao + "  ");
        }
    }
}
