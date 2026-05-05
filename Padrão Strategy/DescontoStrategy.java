package hamburgueria.strategy;


public interface DescontoStrategy {
    double calcularDesconto(double valorTotal);
    String descricao();
}
