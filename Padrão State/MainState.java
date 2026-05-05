package hamburgueria.state;


public class MainState {

    public static void main(String[] args) {
        System.out.println("=== PADRÃO STATE - Hamburgueria ===\n");

        Pedido pedido = new Pedido(101, "X-Bacon com Refrigerante");
        pedido.exibirStatus();

        System.out.println();
        pedido.preparar();   // inválido neste estado
        pedido.confirmar();  // Recebido → Em Preparo
        pedido.exibirStatus();

        System.out.println();
        pedido.cancelar();   // inválido em preparo
        pedido.finalizar();  // Em Preparo → Pronto
        pedido.exibirStatus();

        System.out.println();
        pedido.finalizar();  // Pronto → Entregue
        pedido.exibirStatus();

        System.out.println("\n--- Segundo pedido, cancelado cedo ---\n");

        Pedido pedido2 = new Pedido(102, "Veggie Burger");
        pedido2.confirmar();
        pedido2.exibirStatus();
        // Tenta cancelar em preparo — não permite
        pedido2.preparar();
        pedido2.cancelar();
        pedido2.exibirStatus();
    }
}
