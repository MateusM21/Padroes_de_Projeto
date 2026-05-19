package biblioteca.visitor;

public class Dvd implements ItemAcervo {

    private final String titulo;
    private final int duracaoMinutos;
    private final double valorDiaria;

    public Dvd(String titulo, int duracaoMinutos, double valorDiaria) {
        this.titulo          = titulo;
        this.duracaoMinutos  = duracaoMinutos;
        this.valorDiaria     = valorDiaria;
    }

    @Override
    public void aceitar(ItemVisitor visitor) {
        visitor.visitar(this);
    }

    @Override
    public String getTitulo()       { return titulo; }
    @Override
    public double getValorDiaria()  { return valorDiaria; }
    public int getDuracaoMinutos()  { return duracaoMinutos; }
}
