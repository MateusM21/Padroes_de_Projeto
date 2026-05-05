package hamburgueria.decorator;


public abstract class IngredienteDecorator implements Lanche {

    protected Lanche lanche;

    public IngredienteDecorator(Lanche lanche) {
        this.lanche = lanche;
    }

    @Override
    public String getDescricao() {
        return lanche.getDescricao();
    }

    @Override
    public double getPreco() {
        return lanche.getPreco();
    }
}
