package hamburgueria.state;


public class EstadoCancelado implements PedidoState {

    @Override
    public void confirmar(Pedido pedido) { System.out.println("[Cancelado] Pedido foi cancelado, não pode ser reativado."); }
    @Override
    public void preparar(Pedido pedido)  { System.out.println("[Cancelado] Pedido foi cancelado."); }
    @Override
    public void finalizar(Pedido pedido) { System.out.println("[Cancelado] Pedido foi cancelado."); }
    @Override
    public void cancelar(Pedido pedido)  { System.out.println("[Cancelado] Pedido já está cancelado."); }

    @Override
    public String getNome() { return "Cancelado"; }
}
