package hamburgueria.strategy;


public class DescontoFidelidade implements DescontoStrategy {

    private int visitas;

    public DescontoFidelidade(int visitas) {
        this.visitas = visitas;
    }

    @Override
    public double calcularDesconto(double valorTotal) {
        double percentual = 0.05 + (visitas / 10) * 0.01;
        percentual = Math.min(percentual, 0.15); // máximo 15%
        return valorTotal * percentual;
    }

    @Override
    public String descricao() {
        return "Desconto Fidelidade (" + visitas + " visitas)";
    }
}
