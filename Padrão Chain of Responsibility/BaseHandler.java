package hamburgueria.chain;


public abstract class BaseHandler implements AprovacaoHandler {

    private AprovacaoHandler proximo;

    @Override
    public void setProximo(AprovacaoHandler proximo) {
        this.proximo = proximo;
    }

  
    protected void repassar(Solicitacao solicitacao) {
        if (proximo != null) {
            proximo.aprovar(solicitacao);
        } else {
            System.out.println("[Cadeia encerrada] Nenhum handler conseguiu aprovar: "
                    + solicitacao);
        }
    }
}
