package biblioteca.visitor;

public class Livro implements ItemAcervo {

    private final String titulo;
    private final String autor;
    private final int numeroPaginas;
    private final double valorDiaria;

    public Livro(String titulo, String autor, int numeroPaginas, double valorDiaria) {
        this.titulo        = titulo;
        this.autor         = autor;
        this.numeroPaginas = numeroPaginas;
        this.valorDiaria   = valorDiaria;
    }

    @Override
    public void aceitar(ItemVisitor visitor) {
        visitor.visitar(this);
    }

    @Override
    public String getTitulo()       { return titulo; }
    @Override
    public double getValorDiaria()  { return valorDiaria; }
    public String getAutor()        { return autor; }
    public int getNumeroPaginas()   { return numeroPaginas; }
}
