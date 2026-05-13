package supermercado.facade;

public class MainFacade {

    public static void main(String[] args) {
        System.out.println("=== PADRÃO FACADE - Supermercado ===");

        CaixaFacade caixa = new CaixaFacade();

        caixa.realizarVenda("Arroz 5kg", 2, 18.90, "Cartao Debito");
        caixa.realizarVenda("Leite Integral", 6, 5.49, "Pix");
        caixa.realizarVenda("Chocolate 200g", 1, 8.75, "Dinheiro");
    }
}
