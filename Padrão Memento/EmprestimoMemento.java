package biblioteca.memento;

import java.time.LocalDate;

public class EmprestimoMemento {

    private final String itemTitulo;
    private final String nomeUsuario;
    private final LocalDate dataEmprestimo;
    private final LocalDate dataDevolucaoPrevista;
    private final String status;

    public EmprestimoMemento(String itemTitulo, String nomeUsuario,
                              LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista,
                              String status) {
        this.itemTitulo            = itemTitulo;
        this.nomeUsuario           = nomeUsuario;
        this.dataEmprestimo        = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.status                = status;
    }

    public String getItemTitulo()                { return itemTitulo; }
    public String getNomeUsuario()               { return nomeUsuario; }
    public LocalDate getDataEmprestimo()         { return dataEmprestimo; }
    public LocalDate getDataDevolucaoPrevista()  { return dataDevolucaoPrevista; }
    public String getStatus()                    { return status; }
}
