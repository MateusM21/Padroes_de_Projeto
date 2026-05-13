package supermercado.facade;

public class PagamentoService {

    public boolean processarPagamento(String formaPagamento, double valor) {
        System.out.printf("[Pagamento] Processando R$ %.2f via %s...%n", valor, formaPagamento);
        System.out.println("[Pagamento] Pagamento aprovado.");
        return true;
    }

    public void emitirComprovante(String formaPagamento, double valor) {
        System.out.printf("[Pagamento] Comprovante emitido: R$ %.2f via %s.%n", valor, formaPagamento);
    }
}
