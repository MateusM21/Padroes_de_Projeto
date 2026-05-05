package hamburgueria.state;


public class EstadoEntregue implements PedidoState {

    @Override
    public void confirmar(Pedido pedido) { System.out.println("[Entregue] Pedido já encerrado."); }
    @Override
    public void preparar(Pedido pedido)  { System.out.println("[Entregue] Pedido já encerrado."); }
    @Override
    public void finalizar(Pedido pedido) { System.out.println("[Entregue] Pedido já encerrado."); }
    @Override
    public void cancelar(Pedido pedido)  { System.out.println("[Entregue] Pedido já encerrado."); }

    @Override
    public String getNome() { return "Entregue"; }
}
