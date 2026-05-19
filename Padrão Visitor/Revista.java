package biblioteca.visitor;

public class Revista implements ItemAcervo {

    private final String titulo;
    private final int edicao;
    private final double valorDiaria;

    public Revista(String titulo, int edicao, double valorDiaria) {
        this.titulo      = titulo;
        this.edicao      = edicao;
        this.valorDiaria = valorDiaria;
    }

    @Override
    public void aceitar(ItemVisitor visitor) {
        visitor.visitar(this);
    }

    @Override
    public String getTitulo()      { return titulo; }
    @Override
    public double getValorDiaria() { return valorDiaria; }
    public int getEdicao()         { return edicao; }
}
