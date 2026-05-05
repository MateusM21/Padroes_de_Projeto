package hamburgueria.observer;

import java.util.ArrayList;
import java.util.List;


public class Cozinha implements Observable {

    private List<Observer> observers = new ArrayList<>();
    private String numeroPedidoAtual;

    @Override
    public void adicionarObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removerObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notificarObservers(String mensagem) {
        for (Observer o : observers) {
            o.atualizar(numeroPedidoAtual, mensagem);
        }
    }

    /**
     * Simula o lanche ficar pronto na cozinha.
     */
    public void pedidoPronto(String numeroPedido) {
        this.numeroPedidoAtual = numeroPedido;
        System.out.println("\n[Cozinha] Pedido #" + numeroPedido + " está PRONTO!");
        notificarObservers("Pedido pronto para retirada/entrega.");
    }
}
