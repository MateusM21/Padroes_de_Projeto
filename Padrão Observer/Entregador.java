package hamburgueria.observer;


public class Entregador implements Observer {

    private String nome;

    public Entregador(String nome) {
        this.nome = nome;
    }

    @Override
    public void atualizar(String numeroPedido, String mensagem) {
        System.out.println("[Entregador - " + nome + "] Pedido #" + numeroPedido
                + " pronto. Indo buscar na cozinha...");
    }
}
