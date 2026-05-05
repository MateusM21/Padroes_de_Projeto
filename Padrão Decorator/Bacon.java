package hamburgueria.decorator;

public class Bacon extends IngredienteDecorator {

    public Bacon(Lanche lanche) {
        super(lanche);
    }

    @Override
    public String getDescricao() {
        return lanche.getDescricao() + " + Bacon";
    }

    @Override
    public double getPreco() {
        return lanche.getPreco() + 5.00;
    }
}
