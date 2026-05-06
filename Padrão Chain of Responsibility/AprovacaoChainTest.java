package hamburgueria.chain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;


class AprovacaoChainTest {

    private final ByteArrayOutputStream saida = new ByteArrayOutputStream();
    private final PrintStream saidaOriginal = System.out;

    private Atendente atendente;
    private Gerente   gerente;
    private Diretor   diretor;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(saida));

        atendente = new Atendente("Carlos");
        gerente   = new Gerente("Ana");
        diretor   = new Diretor("Roberto");

        atendente.setProximo(gerente);
        gerente.setProximo(diretor);
    }

    @BeforeEach
    void restaurar() {
        System.setOut(saidaOriginal);
    }

    @Test
    void solicitacao_deveArmazenarDadosCorretamente() {
        Solicitacao s = new Solicitacao("Maria", 50.00, "Desconto");
        assertEquals("Maria", s.getCliente());
        assertEquals(50.00,   s.getValorPedido(), 0.001);
        assertEquals("Desconto", s.getMotivo());
    }

    @Test
    void solicitacao_toString_deveConterCliente() {
        Solicitacao s = new Solicitacao("João", 30.00, "Cortesia");
        assertTrue(s.toString().contains("João"));
    }

    @Test
    void atendente_deveAprovarValorDentroDoLimite() {
        atendente.aprovar(new Solicitacao("Cliente", 15.00, "Troca"));
        assertTrue(saida.toString().contains("Atendente"));
        assertTrue(saida.toString().toLowerCase().contains("aprovado"));
    }

    @Test
    void atendente_deveAprovarValorExatoNoLimite() {
        atendente.aprovar(new Solicitacao("Cliente", 20.00, "Troca"));
        assertTrue(saida.toString().contains("Atendente"));
        assertTrue(saida.toString().toLowerCase().contains("aprovado"));
    }

    @Test
    void atendente_deveRepassarValorAcimaDoLimite() {
        atendente.aprovar(new Solicitacao("Cliente", 21.00, "Desconto"));
        String out = saida.toString();
        assertTrue(out.contains("Atendente"));
        assertTrue(out.contains("Gerente") || out.contains("Diretor"));
    }

    @Test
    void gerente_deveAprovarValorDentroDoLimite() {
        gerente.aprovar(new Solicitacao("Cliente", 60.00, "Combo"));
        assertTrue(saida.toString().contains("Gerente"));
        assertTrue(saida.toString().toLowerCase().contains("aprovado"));
    }

    @Test
    void gerente_deveAprovarValorExatoNoLimite() {
        gerente.aprovar(new Solicitacao("Cliente", 80.00, "Combo"));
        assertTrue(saida.toString().contains("Gerente"));
        assertTrue(saida.toString().toLowerCase().contains("aprovado"));
    }

    @Test
    void gerente_deveRepassarValorAcimaDoLimite() {
        gerente.setProximo(diretor);
        gerente.aprovar(new Solicitacao("Cliente", 200.00, "Evento"));
        String out = saida.toString();
        assertTrue(out.contains("Gerente"));
        assertTrue(out.contains("Diretor"));
    }

    @Test
    void diretor_deveAprovarQualquerValor() {
        diretor.aprovar(new Solicitacao("Empresa", 999.00, "Corporativo"));
        assertTrue(saida.toString().contains("Diretor"));
        assertTrue(saida.toString().toLowerCase().contains("aprovado"));
    }

    @Test
    void diretor_deveAprovarValorMuitoAlto() {
        diretor.aprovar(new Solicitacao("VIP", 10000.00, "Festa"));
        assertTrue(saida.toString().toLowerCase().contains("aprovado"));
    }


    @Test
    void cadeia_valorBaixo_atendenteResolve() {
        atendente.aprovar(new Solicitacao("João", 10.00, "Troca"));
        String out = saida.toString();
        assertTrue(out.contains("Atendente"));

        assertFalse(out.contains("Gerente"));
        assertFalse(out.contains("Diretor"));
    }

    @Test
    void cadeia_valorMedio_gerenteResolve() {
        atendente.aprovar(new Solicitacao("Maria", 55.00, "Desconto"));
        String out = saida.toString();
        assertTrue(out.contains("Atendente"));
        assertTrue(out.contains("Gerente"));   
        assertFalse(out.contains("Diretor"));  
    }

    @Test
    void cadeia_valorAlto_diretorResolve() {
        atendente.aprovar(new Solicitacao("Empresa", 300.00, "Corporativo"));
        String out = saida.toString();
        assertTrue(out.contains("Atendente"));
        assertTrue(out.contains("Gerente"));
        assertTrue(out.contains("Diretor"));
    }


    @Test
    void semProximo_deveInformarEncerramentoDaCadeia() {
        gerente.setProximo(null);
        gerente.aprovar(new Solicitacao("Cliente", 500.00, "Evento"));
        String out = saida.toString();
        assertFalse(out.isBlank());
        assertDoesNotThrow(() -> {});
    }

    @Test
    void cadeia_naoDeveLancarExcecaoEmNenhumCenario() {
        assertDoesNotThrow(() -> {
            atendente.aprovar(new Solicitacao("C1", 0.00,    "Zero"));
            atendente.aprovar(new Solicitacao("C2", 20.00,   "Limite atendente"));
            atendente.aprovar(new Solicitacao("C3", 80.00,   "Limite gerente"));
            atendente.aprovar(new Solicitacao("C4", 999.00,  "Diretor"));
        });
    }


    @Test
    void atendente_deveImplementarAprovacaoHandler() {
        assertInstanceOf(AprovacaoHandler.class, new Atendente("X"));
    }

    @Test
    void gerente_deveImplementarAprovacaoHandler() {
        assertInstanceOf(AprovacaoHandler.class, new Gerente("X"));
    }

    @Test
    void diretor_deveImplementarAprovacaoHandler() {
        assertInstanceOf(AprovacaoHandler.class, new Diretor("X"));
    }

    @Test
    void baseHandler_deveEstenderAprovacaoHandler() {
        assertTrue(AprovacaoHandler.class.isAssignableFrom(Atendente.class));
        assertTrue(AprovacaoHandler.class.isAssignableFrom(Gerente.class));
        assertTrue(AprovacaoHandler.class.isAssignableFrom(Diretor.class));
    }
}
