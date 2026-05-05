package hamburgueria.decorator;


public class MainDecorator {

    public static void main(String[] args) {

        System.out.println("=== PADRÃO DECORATOR - Hamburgueria ===\n");

        // Lanche simples, sem adicionais
        Lanche lanche1 = new HamburguerSimples();
        exibir(lanche1);

        // Lanche com queijo
        Lanche lanche2 = new Queijo(new HamburguerSimples());
        exibir(lanche2);

        // Lanche com queijo + bacon
        Lanche lanche3 = new Bacon(new Queijo(new HamburguerSimples()));
        exibir(lanche3);

        // Lanche com todos os adicionais (queijo + bacon + ovo)
        Lanche lanche4 = new Ovo(new Bacon(new Queijo(new HamburguerSimples())));
        exibir(lanche4);

        // Dois queijos (decorator aplicado duas vezes)
        Lanche lanche5 = new Queijo(new Queijo(new HamburguerSimples()));
        exibir(lanche5);
    }

    private static void exibir(Lanche lanche) {
        System.out.println("Pedido : " + lanche.getDescricao());
        System.out.printf("Preço  : R$ %.2f%n%n", lanche.getPreco());
    }
}
