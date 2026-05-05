package hamburgueria.decorator;


public class Ovo extends IngredienteDecorator {

    public Ovo(Lanche lanche) {
        super(lanche);
    }

    @Override
    public String getDescricao() {
        return lanche.getDescricao() + " + Ovo";
    }

    @Override
    public double getPreco() {
        return lanche.getPreco() + 2.50;
    }
}
