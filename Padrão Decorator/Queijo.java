package hamburgueria.decorator;

public class Queijo extends IngredienteDecorator {

    public Queijo(Lanche lanche) {
        super(lanche);
    }

    @Override
    public String getDescricao() {
        return lanche.getDescricao() + " + Queijo";
    }

    @Override
    public double getPreco() {
        return lanche.getPreco() + 3.00;
    }
}
