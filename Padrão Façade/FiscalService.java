package supermercado.facade;

public class FiscalService {

    public void emitirCupomFiscal(String produto, int quantidade, double valorTotal) {
        System.out.printf("[Fiscal] Emitindo cupom: %dx %s — R$ %.2f%n", quantidade, produto, valorTotal);
    }

    public double calcularImpostos(double valorTotal) {
        double imposto = valorTotal * 0.12;
        System.out.printf("[Fiscal] Impostos calculados: R$ %.2f%n", imposto);
        return imposto;
    }
}
