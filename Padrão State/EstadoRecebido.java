package hamburgueria.state;


public class EstadoRecebido implements PedidoState {

    @Override
    public void confirmar(Pedido pedido) {
        System.out.println("[Recebido] Pedido confirmado! Indo para o preparo.");
        pedido.setEstado(new EstadoEmPreparo());
    }

    @Override
    public void preparar(Pedido pedido) {
        System.out.println("[Recebido] Confirme o pedido antes de iniciar o preparo.");
    }

    @Override
    public void finalizar(Pedido pedido) {
        System.out.println("[Recebido] Não é possível finalizar: pedido ainda não foi confirmado.");
    }

    @Override
    public void cancelar(Pedido pedido) {
        System.out.println("[Recebido] Pedido cancelado antes de ser confirmado.");
        pedido.setEstado(new EstadoCancelado());
    }

    @Override
    public String getNome() {
        return "Recebido";
    }
}
