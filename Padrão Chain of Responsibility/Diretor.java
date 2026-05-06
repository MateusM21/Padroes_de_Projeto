package hamburgueria.chain;


public class Diretor extends BaseHandler {

    private final String nome;

    public Diretor(String nome) {
        this.nome = nome;
    }

    @Override
    public void aprovar(Solicitacao solicitacao) {

        System.out.printf("[Diretor - %s] Aprovado com autoridade máxima! %s%n",
                nome, solicitacao);
    }
}
