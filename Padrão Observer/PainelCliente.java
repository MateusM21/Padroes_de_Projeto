package hamburgueria.observer;

public class PainelCliente implements Observer {

    @Override
    public void atualizar(String numeroPedido, String mensagem) {
        System.out.println("[Painel] *** PEDIDO #" + numeroPedido
                + " PRONTO — Retire no balcão! ***");
    }
}
