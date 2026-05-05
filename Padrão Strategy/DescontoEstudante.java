package hamburgueria.strategy;

public class DescontoEstudante implements DescontoStrategy {

    private static final double PERCENTUAL = 0.10;

    @Override
    public double calcularDesconto(double valorTotal) {
        return valorTotal * PERCENTUAL;
    }

    @Override
    public String descricao() {
        return "Desconto Estudante (10%)";
    }
}
