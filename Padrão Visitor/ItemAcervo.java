package biblioteca.visitor;

public interface ItemAcervo {
    void aceitar(ItemVisitor visitor);
    String getTitulo();
    double getValorDiaria();
}
