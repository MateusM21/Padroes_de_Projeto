package hamburgueria.observer;


public class Caixa implements Observer {

    private String nome;

    public Caixa(String nome) {
        this.nome = nome;
    }

    @Override
    public void atualizar(String numeroPedido, String mensagem) {
        System.out.println("[Caixa - " + nome + "] Pedido #" + numeroPedido
                + " pronto. Preparando cobrança...");
    }
}
