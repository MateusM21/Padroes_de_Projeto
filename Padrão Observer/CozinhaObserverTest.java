package hamburgueria.observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class CozinhaObserverTest {

    
    static class ObserverFake implements Observer {
        List<String> pedidosRecebidos = new ArrayList<>();
        List<String> mensagensRecebidas = new ArrayList<>();

        @Override
        public void atualizar(String numeroPedido, String mensagem) {
            pedidosRecebidos.add(numeroPedido);
            mensagensRecebidas.add(mensagem);
        }

        int contarNotificacoes() {
            return pedidosRecebidos.size();
        }
    }

    private Cozinha cozinha;
    private ObserverFake obs1;
    private ObserverFake obs2;

    @BeforeEach
    void setUp() {
        cozinha = new Cozinha();
        obs1 = new ObserverFake();
        obs2 = new ObserverFake();
    }

    
    @Test
    void observerRegistrado_deveReceberNotificacao() {
        cozinha.adicionarObserver(obs1);
        cozinha.pedidoPronto("101");

        assertEquals(1, obs1.contarNotificacoes());
    }

    @Test
    void doisObservers_ambosDevemSerNotificados() {
        cozinha.adicionarObserver(obs1);
        cozinha.adicionarObserver(obs2);
        cozinha.pedidoPronto("202");

        assertEquals(1, obs1.contarNotificacoes());
        assertEquals(1, obs2.contarNotificacoes());
    }

   
    @Test
    void notificacao_deveConterNumeroDoPedidoCorreto() {
        cozinha.adicionarObserver(obs1);
        cozinha.pedidoPronto("303");

        assertEquals("303", obs1.pedidosRecebidos.get(0));
    }

    @Test
    void notificacao_deveConterMensagemNaoVazia() {
        cozinha.adicionarObserver(obs1);
        cozinha.pedidoPronto("404");

        assertNotNull(obs1.mensagensRecebidas.get(0));
        assertFalse(obs1.mensagensRecebidas.get(0).isBlank());
    }

    
    @Test
    void observerRemovido_naoDeveReceberNotificacao() {
        cozinha.adicionarObserver(obs1);
        cozinha.removerObserver(obs1);
        cozinha.pedidoPronto("505");

        assertEquals(0, obs1.contarNotificacoes());
    }

    @Test
    void removerUm_outroAindaDeveSerNotificado() {
        cozinha.adicionarObserver(obs1);
        cozinha.adicionarObserver(obs2);
        cozinha.removerObserver(obs1);
        cozinha.pedidoPronto("606");

        assertEquals(0, obs1.contarNotificacoes());
        assertEquals(1, obs2.contarNotificacoes());
    }

    
    @Test
    void doisEventos_observerDeveReceberAmbos() {
        cozinha.adicionarObserver(obs1);
        cozinha.pedidoPronto("101");
        cozinha.pedidoPronto("102");

        assertEquals(2, obs1.contarNotificacoes());
        assertEquals("101", obs1.pedidosRecebidos.get(0));
        assertEquals("102", obs1.pedidosRecebidos.get(1));
    }

    
    @Test
    void semObservers_naoDeveLancarExcecao() {
        assertDoesNotThrow(() -> cozinha.pedidoPronto("999"));
    }

    @Test
    void caixa_deveImplementarObserver() {
        assertInstanceOf(Observer.class, new Caixa("Ana"));
    }

    @Test
    void entregador_deveImplementarObserver() {
        assertInstanceOf(Observer.class, new Entregador("Carlos"));
    }

    @Test
    void painelCliente_deveImplementarObserver() {
        assertInstanceOf(Observer.class, new PainelCliente());
    }

    @Test
    void cozinha_deveImplementarObservable() {
        assertInstanceOf(Observable.class, new Cozinha());
    }
}
