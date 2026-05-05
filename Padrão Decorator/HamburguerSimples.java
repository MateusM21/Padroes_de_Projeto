package hamburgueria.decorator;


public class HamburguerSimples implements Lanche {

    @Override
    public String getDescricao() {
        return "Hambúrguer Simples (pão + carne)";
    }

    @Override
    public double getPreco() {
        return 15.00;
    }
}
