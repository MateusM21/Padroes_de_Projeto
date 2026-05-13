package supermercado.composite;

public class MainComposite {

    public static void main(String[] args) {
        System.out.println("=== PADRÃO COMPOSITE - Supermercado ===\n");

        Produto arroz    = new Produto("Arroz 5kg", 18.90);
        Produto feijao   = new Produto("Feijao 1kg", 9.50);
        Produto macarrao = new Produto("Macarrao 500g", 4.75);

        Produto leite    = new Produto("Leite Integral 1L", 5.49);
        Produto queijo   = new Produto("Queijo Mussarela 300g", 14.90);
        Produto manteiga = new Produto("Manteiga 200g", 8.30);

        Produto shampoo  = new Produto("Shampoo 400ml", 12.50);
        Produto sabonete = new Produto("Sabonete 90g", 2.80);

        Categoria graos     = new Categoria("Graos e Massas");
        graos.adicionar(arroz);
        graos.adicionar(feijao);
        graos.adicionar(macarrao);

        Categoria laticinios = new Categoria("Laticinios");
        laticinios.adicionar(leite);
        laticinios.adicionar(queijo);
        laticinios.adicionar(manteiga);

        Categoria higiene = new Categoria("Higiene Pessoal");
        higiene.adicionar(shampoo);
        higiene.adicionar(sabonete);

        Categoria secaoAlimentos = new Categoria("Secao Alimentos");
        secaoAlimentos.adicionar(graos);
        secaoAlimentos.adicionar(laticinios);

        Categoria supermercado = new Categoria("Supermercado Total");
        supermercado.adicionar(secaoAlimentos);
        supermercado.adicionar(higiene);

        supermercado.exibir("");

        System.out.printf("%nTotal geral: R$ %.2f%n", supermercado.getPrecoTotal());
    }
}
