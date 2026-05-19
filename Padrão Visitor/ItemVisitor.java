package biblioteca.visitor;

public interface ItemVisitor {
    void visitar(Livro livro);
    void visitar(Revista revista);
    void visitar(Dvd dvd);
}
