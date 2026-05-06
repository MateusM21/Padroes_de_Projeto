package hamburgueria.chain;


public class Atendente extends BaseHandler {

    private static final double LIMITE = 20.00;
    private final String nome;

    public Atendente(String nome) {
        this.nome = nome;
    }

    @Override
    public void aprovar(Solicitacao solicitacao) {
        if (solicitacao.getValorPedido() <= LIMITE) {
            System.out.printf("[Atendente - %s] Aprovado! %s%n", nome, solicitacao);
        } else {
            System.out.printf("[Atendente - %s] Valor R$ %.2f acima do meu limite (R$ %.2f). Repassando ao Gerente...%n",
                    nome, solicitacao.getValorPedido(), LIMITE);
            repassar(solicitacao);
        }
    }
}
