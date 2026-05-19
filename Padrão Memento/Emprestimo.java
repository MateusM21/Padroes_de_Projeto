package biblioteca.memento;

import java.time.LocalDate;

public class Emprestimo {

    private String itemTitulo;
    private String nomeUsuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private String status;

    public Emprestimo(String itemTitulo, String nomeUsuario, LocalDate dataEmprestimo, int prazoDias) {
        this.itemTitulo            = itemTitulo;
        this.nomeUsuario           = nomeUsuario;
        this.dataEmprestimo        = dataEmprestimo;
        this.dataDevolucaoPrevista = dataEmprestimo.plusDays(prazoDias);
        this.status                = "ATIVO";
    }

    public EmprestimoMemento salvar() {
        return new EmprestimoMemento(itemTitulo, nomeUsuario, dataEmprestimo, dataDevolucaoPrevista, status);
    }

    public void restaurar(EmprestimoMemento memento) {
        this.itemTitulo            = memento.getItemTitulo();
        this.nomeUsuario           = memento.getNomeUsuario();
        this.dataEmprestimo        = memento.getDataEmprestimo();
        this.dataDevolucaoPrevista = memento.getDataDevolucaoPrevista();
        this.status                = memento.getStatus();
    }

    public void renovar(int diasExtras) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista.plusDays(diasExtras);
        this.status = "RENOVADO";
    }

    public void devolver() {
        this.status = "DEVOLVIDO";
    }

    public void marcarAtrasado() {
        this.status = "ATRASADO";
    }

    public void exibir() {
        System.out.printf("Emprestimo: '%s' | Usuario: %s | Emprestado: %s | Devolucao: %s | Status: %s%n",
                itemTitulo, nomeUsuario, dataEmprestimo, dataDevolucaoPrevista, status);
    }

    public String getStatus()                    { return status; }
    public String getItemTitulo()                { return itemTitulo; }
    public LocalDate getDataDevolucaoPrevista()  { return dataDevolucaoPrevista; }
}
