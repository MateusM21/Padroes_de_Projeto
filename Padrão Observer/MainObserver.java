package hamburgueria.observer;


public class MainObserver {

    public static void main(String[] args) {
        System.out.println("=== PADRÃO OBSERVER - Hamburgueria ===\n");

   
        Cozinha cozinha = new Cozinha();


        Caixa caixa           = new Caixa("Ana");
        Entregador entregador = new Entregador("Carlos");
        PainelCliente painel  = new PainelCliente();

        cozinha.adicionarObserver(caixa);
        cozinha.adicionarObserver(entregador);
        cozinha.adicionarObserver(painel);

        cozinha.pedidoPronto("101");

        System.out.println();

        cozinha.removerObserver(entregador);
        System.out.println("[Sistema] Entregador removido (pedido balcão).");

        cozinha.pedidoPronto("102");
    }
}
