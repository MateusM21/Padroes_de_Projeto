package hamburgueria.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


class PedidoStateTest {

    private final ByteArrayOutputStream saida = new ByteArrayOutputStream();
    private final PrintStream saidaOriginal = System.out;

    @BeforeEach
    void redirecionarSaida() {
        System.setOut(new PrintStream(saida));
    }

    @BeforeEach
    void restaurarSaida() {
        System.setOut(saidaOriginal);
    }

 
    @Test
    void pedidoNovo_deveIniciarNoEstadoRecebido() {
        Pedido pedido = new Pedido(1, "X-Burguer");
        assertTrue(saida.toString().contains("registrado"));
    }


    @Test
    void confirmar_deveTransicionarDeRecebidoParaEmPreparo() {
        Pedido pedido = new Pedido(1, "X-Burguer");
        saida.reset();

        pedido.confirmar();

        assertTrue(saida.toString().contains("Em Preparo") ||
                   saida.toString().contains("preparo"));
    }

    @Test
    void finalizar_deveTransicionarDeEmPreparoParaPronto() {
        Pedido pedido = new Pedido(1, "X-Burguer");
        pedido.confirmar();
        saida.reset();

        pedido.finalizar();

        assertTrue(saida.toString().contains("Pronto") ||
                   saida.toString().contains("pronto"));
    }

    @Test
    void finalizar_deveTransicionarDeProntoParaEntregue() {
        Pedido pedido = new Pedido(1, "X-Burguer");
        pedido.confirmar();
        pedido.finalizar(); // Pronto
        saida.reset();

        pedido.finalizar(); // Entregue

        assertTrue(saida.toString().contains("entregue") ||
                   saida.toString().contains("Entregue") ||
                   saida.toString().contains("cliente"));
    }


    @Test
    void cancelar_devePermitirCancelamentoNoEstadoRecebido() {
        Pedido pedido = new Pedido(1, "X-Burguer");
        saida.reset();

        pedido.cancelar();

        assertTrue(saida.toString().contains("cancelado") ||
                   saida.toString().contains("Cancelado"));
    }

    @Test
    void preparar_semConfirmar_naoDeveExplodirEDeveAvisar() {
        Pedido pedido = new Pedido(1, "X-Burguer");
        saida.reset();

        assertDoesNotThrow(() -> pedido.preparar());
        assertFalse(saida.toString().isBlank());
    }

    @Test
    void cancelar_emPreparo_naoDevePermitir() {
        Pedido pedido = new Pedido(1, "X-Burguer");
        pedido.confirmar();
        saida.reset();

        pedido.cancelar();

        String msg = saida.toString().toLowerCase();
        assertTrue(msg.contains("não") || msg.contains("nao") || msg.contains("preparo"));
    }

    @Test
    void acoes_aposEntregue_naoDevemExplodirEDevemAvisar() {
        Pedido pedido = new Pedido(1, "X-Burguer");
        pedido.confirmar();
        pedido.finalizar();
        pedido.finalizar(); // Entregue
        saida.reset();

        assertDoesNotThrow(() -> {
            pedido.confirmar();
            pedido.preparar();
            pedido.finalizar();
            pedido.cancelar();
        });
        assertFalse(saida.toString().isBlank());
    }

    @Test
    void acoes_aposCancelado_naoDevemExplodirEDevemAvisar() {
        Pedido pedido = new Pedido(1, "X-Burguer");
        pedido.cancelar();
        saida.reset();

        assertDoesNotThrow(() -> {
            pedido.confirmar();
            pedido.cancelar();
        });
        assertFalse(saida.toString().isBlank());
    }


    @Test
    void estadoRecebido_deveRetornarNomeCorreto() {
        PedidoState estado = new EstadoRecebido();
        assertEquals("Recebido", estado.getNome());
    }

    @Test
    void estadoEmPreparo_deveRetornarNomeCorreto() {
        PedidoState estado = new EstadoEmPreparo();
        assertEquals("Em Preparo", estado.getNome());
    }

    @Test
    void estadoPronto_deveRetornarNomeCorreto() {
        PedidoState estado = new EstadoPronto();
        assertEquals("Pronto", estado.getNome());
    }

    @Test
    void estadoEntregue_deveRetornarNomeCorreto() {
        PedidoState estado = new EstadoEntregue();
        assertEquals("Entregue", estado.getNome());
    }

    @Test
    void estadoCancelado_deveRetornarNomeCorreto() {
        PedidoState estado = new EstadoCancelado();
        assertEquals("Cancelado", estado.getNome());
    }
}
