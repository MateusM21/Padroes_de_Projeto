package biblioteca.memento;

import java.util.ArrayDeque;
import java.util.Deque;

public class HistoricoEmprestimo {

    private final Deque<EmprestimoMemento> historico = new ArrayDeque<>();

    public void salvar(Emprestimo emprestimo) {
        historico.push(emprestimo.salvar());
        System.out.println("[Historico] Estado salvo. Total de snapshots: " + historico.size());
    }

    public EmprestimoMemento desfazer() {
        if (historico.isEmpty()) {
            System.out.println("[Historico] Nenhum estado anterior disponivel.");
            return null;
        }
        EmprestimoMemento memento = historico.pop();
        System.out.println("[Historico] Restaurando estado anterior. Snapshots restantes: " + historico.size());
        return memento;
    }

    public int tamanho() {
        return historico.size();
    }

    public boolean vazio() {
        return historico.isEmpty();
    }
}
