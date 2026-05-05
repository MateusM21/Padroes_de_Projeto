package hamburgueria.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


class DescontoStrategyTest {

    private static final double VALOR_BASE = 100.00;
    private static final double DELTA = 0.001;


    @Test
    void semDesconto_deveRetornarZero() {
        DescontoStrategy estrategia = new SemDesconto();
        assertEquals(0.0, estrategia.calcularDesconto(VALOR_BASE), DELTA);
    }

    @Test
    void semDesconto_deveRetornarDescricaoNaoVazia() {
        DescontoStrategy estrategia = new SemDesconto();
        assertNotNull(estrategia.descricao());
        assertFalse(estrategia.descricao().isBlank());
    }


    @Test
    void descontoEstudante_deveRetornar10Porcento() {
        DescontoStrategy estrategia = new DescontoEstudante();
        assertEquals(10.00, estrategia.calcularDesconto(VALOR_BASE), DELTA);
    }

    @Test
    void descontoEstudante_comValorMenor_deveCalcularCorretamente() {
        DescontoStrategy estrategia = new DescontoEstudante();
        assertEquals(4.50, estrategia.calcularDesconto(45.00), DELTA);
    }

    @Test
    void descontoEstudante_deveConterDescricao() {
        DescontoStrategy estrategia = new DescontoEstudante();
        assertFalse(estrategia.descricao().isBlank());
    }


    @Test
    void descontoAniversariante_deveRetornar20Porcento() {
        DescontoStrategy estrategia = new DescontoAniversariante();
        assertEquals(20.00, estrategia.calcularDesconto(VALOR_BASE), DELTA);
    }

    @Test
    void descontoAniversariante_comValorMenor_deveCalcularCorretamente() {
        DescontoStrategy estrategia = new DescontoAniversariante();
        assertEquals(9.00, estrategia.calcularDesconto(45.00), DELTA);
    }

    @Test
    void aniversarianteMaiorQueEstudante_paraMesmoValor() {
        double valor = 200.00;
        double descAniv = new DescontoAniversariante().calcularDesconto(valor);
        double descEst  = new DescontoEstudante().calcularDesconto(valor);
        assertTrue(descAniv > descEst);
    }


    @Test
    void descontoFidelidade_comZeroVisitas_deve5Porcento() {
        DescontoStrategy estrategia = new DescontoFidelidade(0);
        assertEquals(5.00, estrategia.calcularDesconto(VALOR_BASE), DELTA);
    }

    @Test
    void descontoFidelidade_com10Visitas_deve6Porcento() {
        DescontoStrategy estrategia = new DescontoFidelidade(10);
        assertEquals(6.00, estrategia.calcularDesconto(VALOR_BASE), DELTA);
    }

    @Test
    void descontoFidelidade_com50Visitas_deve10Porcento() {
        DescontoStrategy estrategia = new DescontoFidelidade(50);
        assertEquals(10.00, estrategia.calcularDesconto(VALOR_BASE), DELTA);
    }

    @Test
    void descontoFidelidade_com100Visitas_naoDeveUltrapassar15Porcento() {
        DescontoStrategy estrategia = new DescontoFidelidade(100);
        double desconto = estrategia.calcularDesconto(VALOR_BASE);
        assertTrue(desconto <= 15.00 + DELTA);
    }

    @Test
    void descontoFidelidade_com1000Visitas_deveRespeitar_teto15Porcento() {
        DescontoStrategy estrategia = new DescontoFidelidade(1000);
        assertEquals(15.00, estrategia.calcularDesconto(VALOR_BASE), DELTA);
    }


    @Test
    void caixa_deveAceitarQualquerStrategy() {
        Caixa caixa = new Caixa(new SemDesconto());
        assertDoesNotThrow(() -> caixa.processarPagamento("Cliente", 50.00));
    }

    @Test
    void caixa_devePermitirTrocarEstrategiaEmTempoDeExecucao() {
        Caixa caixa = new Caixa(new SemDesconto());

        // Troca para estudante — não deve lançar exceção
        assertDoesNotThrow(() -> {
            caixa.setEstrategia(new DescontoEstudante());
            caixa.processarPagamento("Estudante", 80.00);
        });
    }

    @Test
    void caixa_comValorZero_naoDeveLancarExcecao() {
        Caixa caixa = new Caixa(new DescontoEstudante());
        assertDoesNotThrow(() -> caixa.processarPagamento("Cliente", 0.00));
    }


    @Test
    void semDesconto_deveImplementarDescontoStrategy() {
        assertInstanceOf(DescontoStrategy.class, new SemDesconto());
    }

    @Test
    void descontoEstudante_deveImplementarDescontoStrategy() {
        assertInstanceOf(DescontoStrategy.class, new DescontoEstudante());
    }

    @Test
    void descontoAniversariante_deveImplementarDescontoStrategy() {
        assertInstanceOf(DescontoStrategy.class, new DescontoAniversariante());
    }

    @Test
    void descontoFidelidade_deveImplementarDescontoStrategy() {
        assertInstanceOf(DescontoStrategy.class, new DescontoFidelidade(10));
    }


    @Test
    void caixa_deveMostrarNomeDoClienteNaSaida() {
        ByteArrayOutputStream saida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saida));

        new Caixa(new SemDesconto()).processarPagamento("Maria", 50.00);

        System.setOut(System.out);
        assertTrue(saida.toString().contains("Maria"));
    }
}
