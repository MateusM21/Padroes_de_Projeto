package biblioteca.visitor;

public class RelatorioVisitor implements ItemVisitor {

    private final StringBuilder relatorio = new StringBuilder();

    @Override
    public void visitar(Livro livro) {
        relatorio.append(String.format("LIVRO | %s | Autor: %s | %d pags | Diaria: R$ %.2f%n",
                livro.getTitulo(), livro.getAutor(), livro.getNumeroPaginas(), livro.getValorDiaria()));
    }

    @Override
    public void visitar(Revista revista) {
        relatorio.append(String.format("REVISTA | %s | Edicao: %d | Diaria: R$ %.2f%n",
                revista.getTitulo(), revista.getEdicao(), revista.getValorDiaria()));
    }

    @Override
    public void visitar(Dvd dvd) {
        relatorio.append(String.format("DVD | %s | %d min | Diaria: R$ %.2f%n",
                dvd.getTitulo(), dvd.getDuracaoMinutos(), dvd.getValorDiaria()));
    }

    public String getRelatorio() { return relatorio.toString(); }

    public void imprimir() {
        System.out.println("=== Relatorio do Acervo ===");
        System.out.print(relatorio);
    }
}
