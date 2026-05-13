package supermercado.facade;

public class CaixaFacade {

    private final EstoqueService estoque;
    private final FiscalService fiscal;
    private final PagamentoService pagamento;

    public CaixaFacade() {
        this.estoque   = new EstoqueService();
        this.fiscal    = new FiscalService();
        this.pagamento = new PagamentoService();
    }

    public boolean realizarVenda(String produto, int quantidade, double precoUnitario, String formaPagamento) {
        System.out.println("\n=== Iniciando venda ===");

        if (!estoque.verificarDisponibilidade(produto, quantidade)) {
            System.out.println("[Caixa] Produto indisponivel no estoque.");
            return false;
        }

        double valorTotal = precoUnitario * quantidade;
        double impostos   = fiscal.calcularImpostos(valorTotal);
        double valorFinal = valorTotal + impostos;

        boolean aprovado = pagamento.processarPagamento(formaPagamento, valorFinal);
        if (!aprovado) {
            System.out.println("[Caixa] Pagamento recusado. Venda cancelada.");
            return false;
        }

        estoque.baixarEstoque(produto, quantidade);
        fiscal.emitirCupomFiscal(produto, quantidade, valorFinal);
        pagamento.emitirComprovante(formaPagamento, valorFinal);

        System.out.println("=== Venda concluida ===");
        return true;
    }
}
