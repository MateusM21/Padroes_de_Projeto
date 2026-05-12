package hotel.mediator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CentralHotelMediatorTest {

    private final ByteArrayOutputStream saida = new ByteArrayOutputStream();
    private CentralHotel central;
    private Recepcao recepcao;
    private Limpeza limpeza;
    private Manutencao manutencao;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(saida));
        central    = new CentralHotel();
        recepcao   = new Recepcao(central);
        limpeza    = new Limpeza(central);
        manutencao = new Manutencao(central);
        central.registrar(recepcao);
        central.registrar(limpeza);
        central.registrar(manutencao);
    }

    @BeforeEach
    void restaurar() {
        System.setOut(System.out);
    }

    @Test
    void recepcao_checkIn_deveNotificarLimpezaEManutencao() {
        recepcao.realizarCheckIn("101");
        String out = saida.toString();
        assertTrue(out.contains("Limpeza"));
        assertTrue(out.contains("Manutencao"));
    }

    @Test
    void recepcao_checkOut_deveNotificarLimpeza() {
        recepcao.realizarCheckOut("102");
        assertTrue(saida.toString().contains("Limpeza"));
    }

    @Test
    void limpeza_solicitarSuprimentos_deveNotificarManutencao() {
        limpeza.solicitarSuprimentos();
        assertTrue(saida.toString().contains("Manutencao"));
    }

    @Test
    void limpeza_quartoPronto_deveNotificarRecepcao() {
        limpeza.notificarQuartoPronto("205");
        String out = saida.toString();
        assertTrue(out.contains("Recepcao"));
        assertTrue(out.contains("205"));
    }

    @Test
    void manutencao_reportarProblema_deveNotificarRecepcao() {
        manutencao.reportarProblema("Vazamento no banheiro");
        String out = saida.toString();
        assertTrue(out.contains("Recepcao"));
        assertTrue(out.contains("Vazamento"));
    }

    @Test
    void enviarParaDestinatarioInexistente_naoDeveLancarExcecao() {
        assertDoesNotThrow(() -> central.enviar("Teste", "Recepcao", "DepartamentoFake"));
        assertTrue(saida.toString().contains("não encontrado"));
    }

    @Test
    void departamentosDevemImplementarInterface() {
        assertInstanceOf(Departamento.class, recepcao);
        assertInstanceOf(Departamento.class, limpeza);
        assertInstanceOf(Departamento.class, manutencao);
    }

    @Test
    void centralDeveImplementarHotelMediator() {
        assertInstanceOf(HotelMediator.class, central);
    }

    @Test
    void getNome_deveRetornarNomeCorreto() {
        assertEquals("Recepcao",  recepcao.getNome());
        assertEquals("Limpeza",   limpeza.getNome());
        assertEquals("Manutencao", manutencao.getNome());
    }

    @Test
    void mensagemEnviada_deveConterConteudo() {
        recepcao.realizarCheckIn("303");
        assertTrue(saida.toString().contains("303"));
    }

    @Test
    void doisEventosSequenciais_naoDevemLancarExcecao() {
        assertDoesNotThrow(() -> {
            recepcao.realizarCheckIn("401");
            limpeza.notificarQuartoPronto("401");
            recepcao.realizarCheckOut("401");
        });
    }
}
