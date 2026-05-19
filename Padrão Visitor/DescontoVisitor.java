package biblioteca.visitor;

public class DescontoVisitor implements ItemVisitor {

    private double totalEconomizado = 0;

    @Override
    public void visitar(Livro livro) {
        double desconto = livro.getValorDiaria() * 0.10;
        totalEconomizado += desconto;
        System.out.printf("[Desconto] Livro '%s': -R$ %.2f (10%% livros)%n", livro.getTitulo(), desconto);
    }

    @Override
    public void visitar(Revista revista) {
        double desconto = revista.getValorDiaria() * 0.20;
        totalEconomizado += desconto;
        System.out.printf("[Desconto] Revista '%s': -R$ %.2f (20%% revistas)%n", revista.getTitulo(), desconto);
    }

    @Override
    public void visitar(Dvd dvd) {
        double desconto = dvd.getValorDiaria() * 0.05;
        totalEconomizado += desconto;
        System.out.printf("[Desconto] DVD '%s': -R$ %.2f (5%% DVDs)%n", dvd.getTitulo(), desconto);
    }

    public double getTotalEconomizado() { return totalEconomizado; }
}
