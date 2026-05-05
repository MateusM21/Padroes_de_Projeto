package hamburgueria.strategy;

public class DescontoAniversariante implements DescontoStrategy {

    private static final double PERCENTUAL = 0.20;

    @Override
    public double calcularDesconto(double valorTotal) {
        return valorTotal * PERCENTUAL;
    }

    @Override
    public String descricao() {
        return "Desconto Aniversariante (20%)";
    }
}
