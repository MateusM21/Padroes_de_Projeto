package supermercado.facade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CaixaFacadeTest {

    private final ByteArrayOutputStream saida = new ByteArrayOutputStream();
    private CaixaFacade caixa;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(saida));
        caixa = new CaixaFacade();
    }

    @BeforeEach
    void restaurar() {
        System.setOut(System.out);
    }

    @Test
    void vendaValida_deveRetornarTrue() {
        assertTrue(caixa.realizarVenda("Arroz", 1, 10.00, "Pix"));
    }

    @Test
    void vendaValida_devePassarPorEstoque() {
        caixa.realizarVenda("Feijao", 2, 8.00, "Dinheiro");
        assertTrue(saida.toString().contains("Estoque"));
    }

    @Test
    void vendaValida_deveEmitirCupomFiscal() {
        caixa.realizarVenda("Leite", 3, 5.00, "Cartao Debito");
        assertTrue(saida.toString().contains("Fiscal"));
    }

    @Test
    void vendaValida_deveProcessarPagamento() {
        caixa.realizarVenda("Macarrao", 1, 4.50, "Pix");
        assertTrue(saida.toString().contains("Pagamento"));
    }

    @Test
    void vendaValida_deveBaixarEstoque() {
        caixa.realizarVenda("Oleo", 1, 9.00, "Dinheiro");
        assertTrue(saida.toString().toLowerCase().contains("baixando"));
    }

    @Test
    void vendaValida_deveEmitirComprovante() {
        caixa.realizarVenda("Cafe", 2, 12.00, "Cartao Credito");
        assertTrue(saida.toString().toLowerCase().contains("comprovante"));
    }

    @Test
    void valorFinal_deveIncluirImpostos() {
        caixa.realizarVenda("Produto", 1, 100.00, "Pix");
        assertTrue(saida.toString().contains("12,00") || saida.toString().contains("12.00"));
    }

    @Test
    void fachadaDeveOcultarSubsistemas() {
        assertNotNull(caixa);
        assertDoesNotThrow(() -> caixa.realizarVenda("Item", 1, 5.00, "Dinheiro"));
    }

    @Test
    void multipliasVendas_naoDevemLancarExcecao() {
        assertDoesNotThrow(() -> {
            caixa.realizarVenda("A", 1, 1.00, "Pix");
            caixa.realizarVenda("B", 2, 2.00, "Dinheiro");
            caixa.realizarVenda("C", 3, 3.00, "Cartao Debito");
        });
    }

    @Test
    void subsistemas_devemSerInstanciaveis() {
        assertDoesNotThrow(() -> {
            new EstoqueService();
            new FiscalService();
            new PagamentoService();
        });
    }
}
