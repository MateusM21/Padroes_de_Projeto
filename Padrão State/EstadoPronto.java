package hamburgueria.state;

public class EstadoPronto implements PedidoState {

    @Override
    public void confirmar(Pedido pedido) {
        System.out.println("[Pronto] Pedido já está pronto.");
    }

    @Override
    public void preparar(Pedido pedido) {
        System.out.println("[Pronto] Preparo já concluído.");
    }

    @Override
    public void finalizar(Pedido pedido) {
        System.out.println("[Pronto] Pedido entregue ao cliente! Obrigado.");
        pedido.setEstado(new EstadoEntregue());
    }

    @Override
    public void cancelar(Pedido pedido) {
        System.out.println("[Pronto] Pedido pronto não pode ser cancelado.");
    }

    @Override
    public String getNome() {
        return "Pronto";
    }
}
