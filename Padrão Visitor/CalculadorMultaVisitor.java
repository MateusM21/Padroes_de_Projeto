package biblioteca.visitor;

public class CalculadorMultaVisitor implements ItemVisitor {

    private final int diasAtraso;
    private double totalMulta = 0;

    public CalculadorMultaVisitor(int diasAtraso) {
        this.diasAtraso = diasAtraso;
    }

    @Override
    public void visitar(Livro livro) {
        double multa = livro.getValorDiaria() * diasAtraso * 1.5;
        totalMulta += multa;
        System.out.printf("[Multa] Livro '%s': R$ %.2f (%d dias)%n", livro.getTitulo(), multa, diasAtraso);
    }

    @Override
    public void visitar(Revista revista) {
        double multa = revista.getValorDiaria() * diasAtraso;
        totalMulta += multa;
        System.out.printf("[Multa] Revista '%s' ed.%d: R$ %.2f (%d dias)%n",
                revista.getTitulo(), revista.getEdicao(), multa, diasAtraso);
    }

    @Override
    public void visitar(Dvd dvd) {
        double multa = dvd.getValorDiaria() * diasAtraso * 2.0;
        totalMulta += multa;
        System.out.printf("[Multa] DVD '%s': R$ %.2f (%d dias)%n", dvd.getTitulo(), multa, diasAtraso);
    }

    public double getTotalMulta() { return totalMulta; }
}
