package hamburgueria.chain;


public interface AprovacaoHandler {

    void setProximo(AprovacaoHandler proximo);

    void aprovar(Solicitacao solicitacao);
}
