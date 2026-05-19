package biblioteca.memento;

import java.time.LocalDate;

public class MainMemento {

    public static void main(String[] args) {
        System.out.println("=== PADRÃO MEMENTO - Biblioteca ===\n");

        Emprestimo emprestimo = new Emprestimo("Dom Casmurro", "Ana Lima",
                LocalDate.of(2024, 6, 1), 7);
        HistoricoEmprestimo historico = new HistoricoEmprestimo();

        System.out.println("--- Estado inicial ---");
        emprestimo.exibir();

        historico.salvar(emprestimo);

        System.out.println("\n--- Renovando por 7 dias ---");
        emprestimo.renovar(7);
        emprestimo.exibir();

        historico.salvar(emprestimo);

        System.out.println("\n--- Marcando como atrasado ---");
        emprestimo.marcarAtrasado();
        emprestimo.exibir();

        historico.salvar(emprestimo);

        System.out.println("\n--- Desfazendo (voltar ao RENOVADO) ---");
        emprestimo.restaurar(historico.desfazer());
        emprestimo.exibir();

        System.out.println("\n--- Desfazendo novamente (voltar ao ATIVO com prazo original) ---");
        emprestimo.restaurar(historico.desfazer());
        emprestimo.exibir();

        System.out.println("\n--- Desfazendo sem historico disponivel ---");
        historico.desfazer();
        historico.desfazer();
    }
}
