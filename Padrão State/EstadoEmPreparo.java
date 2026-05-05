package hamburgueria.state;


public class EstadoEmPreparo implements PedidoState {

    @Override
    public void confirmar(Pedido pedido) {
        System.out.println("[Em Preparo] Pedido já está confirmado e em preparo.");
    }

    @Override
    public void preparar(Pedido pedido) {
        System.out.println("[Em Preparo] Lanche já está sendo preparado na cozinha!");
    }

    @Override
    public void finalizar(Pedido pedido) {
        System.out.println("[Em Preparo] Lanche pronto! Aguardando retirada/entrega.");
        pedido.setEstado(new EstadoPronto());
    }

    @Override
    public void cancelar(Pedido pedido) {
        System.out.println("[Em Preparo] Pedido em preparo não pode ser cancelado.");
    }

    @Override
    public String getNome() {
        return "Em Preparo";
    }
}
