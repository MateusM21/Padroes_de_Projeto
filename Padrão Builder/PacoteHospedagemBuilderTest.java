package hotel.builder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PacoteHospedagemBuilderTest {

    @Test
    void buildMinimo_deveGerarPacoteValido() {
        PacoteHospedagem p = new PacoteHospedagemBuilder()
                .quarto("Standard")
                .diarias(1)
                .build();
        assertEquals("Standard", p.getTipoQuarto());
        assertEquals(1, p.getNumeroDiarias());
    }

    @Test
    void semQuarto_deveLancarExcecao() {
        assertThrows(IllegalStateException.class, () ->
                new PacoteHospedagemBuilder().diarias(2).build());
    }

    @Test
    void quartoVazio_deveLancarExcecao() {
        assertThrows(IllegalStateException.class, () ->
                new PacoteHospedagemBuilder().quarto("").diarias(2).build());
    }

    @Test
    void semDiarias_deveLancarExcecao() {
        assertThrows(IllegalStateException.class, () ->
                new PacoteHospedagemBuilder().quarto("Luxo").build());
    }

    @Test
    void diariasZero_deveLancarExcecao() {
        assertThrows(IllegalStateException.class, () ->
                new PacoteHospedagemBuilder().quarto("Luxo").diarias(0).build());
    }

    @Test
    void cafeDaManha_deveSerIncluidoQuandoSolicitado() {
        PacoteHospedagem p = new PacoteHospedagemBuilder()
                .quarto("Standard").diarias(1).comCafeDaManha().build();
        assertTrue(p.isCafeDaManha());
    }

    @Test
    void cafeDaManha_naoDeveSerIncluidoPorPadrao() {
        PacoteHospedagem p = new PacoteHospedagemBuilder()
                .quarto("Standard").diarias(1).build();
        assertFalse(p.isCafeDaManha());
    }

    @Test
    void spa_deveSerIncluidoQuandoSolicitado() {
        PacoteHospedagem p = new PacoteHospedagemBuilder()
                .quarto("Suite").diarias(2).comSpa().build();
        assertTrue(p.isSpa());
    }

    @Test
    void transfer_deveSerIncluidoQuandoSolicitado() {
        PacoteHospedagem p = new PacoteHospedagemBuilder()
                .quarto("Suite").diarias(2).comTransfer().build();
        assertTrue(p.isTransfer());
    }

    @Test
    void estacionamento_deveSerIncluidoQuandoSolicitado() {
        PacoteHospedagem p = new PacoteHospedagemBuilder()
                .quarto("Luxo").diarias(3).comEstacionamento().build();
        assertTrue(p.isEstacionamento());
    }

    @Test
    void servicosExtras_devemAcumular() {
        PacoteHospedagem p = new PacoteHospedagemBuilder()
                .quarto("Suite").diarias(2)
                .servicoExtra("Flores")
                .servicoExtra("Champagne")
                .build();
        assertEquals(2, p.getServicosExtras().size());
        assertTrue(p.getServicosExtras().contains("Flores"));
        assertTrue(p.getServicosExtras().contains("Champagne"));
    }

    @Test
    void pacoteCompleto_deveConterTodosOsServicos() {
        PacoteHospedagem p = new PacoteHospedagemBuilder()
                .quarto("Suite Master").diarias(5)
                .comCafeDaManha().comSpa().comTransfer().comEstacionamento()
                .servicoExtra("Mordomo").build();
        assertTrue(p.isCafeDaManha());
        assertTrue(p.isSpa());
        assertTrue(p.isTransfer());
        assertTrue(p.isEstacionamento());
        assertFalse(p.getServicosExtras().isEmpty());
    }

    @Test
    void toString_deveConterInformacoesDoQuarto() {
        PacoteHospedagem p = new PacoteHospedagemBuilder()
                .quarto("Deluxe").diarias(4).build();
        assertTrue(p.toString().contains("Deluxe"));
        assertTrue(p.toString().contains("4"));
    }

    @Test
    void director_montarEconomico_deveGerarPacoteCorreto() {
        Recepcionista r = new Recepcionista(new PacoteHospedagemBuilder());
        PacoteHospedagem p = r.montarPacoteEconomico();
        assertEquals("Standard", p.getTipoQuarto());
        assertEquals(2, p.getNumeroDiarias());
        assertTrue(p.isCafeDaManha());
    }

    @Test
    void director_montarVip_deveConterTodosServicos() {
        Recepcionista r = new Recepcionista(new PacoteHospedagemBuilder());
        PacoteHospedagem p = r.montarPacoteVip();
        assertTrue(p.isSpa());
        assertTrue(p.isTransfer());
        assertTrue(p.isEstacionamento());
        assertFalse(p.getServicosExtras().isEmpty());
    }

    @Test
    void builderDeveImplementarInterface() {
        assertInstanceOf(PacoteBuilder.class, new PacoteHospedagemBuilder());
    }
}
