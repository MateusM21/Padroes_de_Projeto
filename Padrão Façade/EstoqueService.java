package supermercado.facade;

public class EstoqueService {

    public boolean verificarDisponibilidade(String produto, int quantidade) {
        System.out.println("[Estoque] Verificando " + quantidade + "x " + produto + "...");
        return true;
    }

    public void baixarEstoque(String produto, int quantidade) {
        System.out.println("[Estoque] Baixando " + quantidade + "x " + produto + " do estoque.");
    }
}
