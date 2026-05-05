package hamburgueria.strategy;


public class MainStrategy {

    public static void main(String[] args) {
        System.out.println("=== PADRÃO STRATEGY - Hamburgueria ===\n");

        Caixa caixa = new Caixa(new SemDesconto());


        caixa.processarPagamento("João Silva", 45.00);


        caixa.setEstrategia(new DescontoEstudante());
        caixa.processarPagamento("Maria Souza", 45.00);


        caixa.setEstrategia(new DescontoAniversariante());
        caixa.processarPagamento("Pedro Costa", 45.00);


        caixa.setEstrategia(new DescontoFidelidade(25));
        caixa.processarPagamento("Ana Lima", 45.00);


        caixa.setEstrategia(new DescontoFidelidade(100));
        caixa.processarPagamento("Carlos Neto", 45.00);
    }
}
