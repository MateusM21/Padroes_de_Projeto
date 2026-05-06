package hamburgueria.chain;


public class Gerente extends BaseHandler {

    private static final double LIMITE = 80.00;
    private final String nome;

    public Gerente(String nome) {
        this.nome = nome;
    }

    @Override
    public void aprovar(Solicitacao solicitacao) {
        if (solicitacao.getValorPedido() <= LIMITE) {
            System.out.printf("[Gerente - %s] Aprovado! %s%n", nome, solicitacao);
        } else {
            System.out.printf("[Gerente - %s] Valor R$ %.2f acima do meu limite (R$ %.2f). Repassando ao Diretor...%n",
                    nome, solicitacao.getValorPedido(), LIMITE);
            repassar(solicitacao);
        }
    }
}
