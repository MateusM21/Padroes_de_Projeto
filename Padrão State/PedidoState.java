package hamburgueria.state;


public interface PedidoState {
    void confirmar(Pedido pedido);
    void preparar(Pedido pedido);
    void finalizar(Pedido pedido);
    void cancelar(Pedido pedido);
    String getNome();
}
