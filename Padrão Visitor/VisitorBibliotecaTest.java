package biblioteca.visitor;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VisitorBibliotecaTest {

    private final Livro livro       = new Livro("Dom Casmurro", "Machado", 256, 2.00);
    private final Revista revista   = new Revista("National Geographic", 312, 1.50);
    private final Dvd dvd           = new Dvd("Cidade de Deus", 130, 3.00);

    @Test
    void livro_deveAceitarVisitor() {
        CalculadorMultaVisitor v = new CalculadorMultaVisitor(1);
        assertDoesNotThrow(() -> livro.aceitar(v));
    }

    @Test
    void revista_deveAceitarVisitor() {
        CalculadorMultaVisitor v = new CalculadorMultaVisitor(1);
        assertDoesNotThrow(() -> revista.aceitar(v));
    }

    @Test
    void dvd_deveAceitarVisitor() {
        CalculadorMultaVisitor v = new CalculadorMultaVisitor(1);
        assertDoesNotThrow(() -> dvd.aceitar(v));
    }

    @Test
    void multaLivro_deveAplicarFator1_5() {
        CalculadorMultaVisitor v = new CalculadorMultaVisitor(2);
        livro.aceitar(v);
        assertEquals(2.00 * 2 * 1.5, v.getTotalMulta(), 0.001);
    }

    @Test
    void multaRevista_deveAplicarFator1() {
        CalculadorMultaVisitor v = new CalculadorMultaVisitor(3);
        revista.aceitar(v);
        assertEquals(1.50 * 3, v.getTotalMulta(), 0.001);
    }

    @Test
    void multaDvd_deveAplicarFator2() {
        CalculadorMultaVisitor v = new CalculadorMultaVisitor(2);
        dvd.aceitar(v);
        assertEquals(3.00 * 2 * 2.0, v.getTotalMulta(), 0.001);
    }

    @Test
    void multaAcumulada_variosItens() {
        CalculadorMultaVisitor v = new CalculadorMultaVisitor(1);
        livro.aceitar(v);
        revista.aceitar(v);
        dvd.aceitar(v);
        double esperado = (2.00 * 1.5) + (1.50) + (3.00 * 2.0);
        assertEquals(esperado, v.getTotalMulta(), 0.001);
    }

    @Test
    void relatorio_deveConterTituloDoLivro() {
        RelatorioVisitor v = new RelatorioVisitor();
        livro.aceitar(v);
        assertTrue(v.getRelatorio().contains("Dom Casmurro"));
    }

    @Test
    void relatorio_deveConterEdicaoDaRevista() {
        RelatorioVisitor v = new RelatorioVisitor();
        revista.aceitar(v);
        assertTrue(v.getRelatorio().contains("312"));
    }

    @Test
    void relatorio_deveConterDuracaoDoDvd() {
        RelatorioVisitor v = new RelatorioVisitor();
        dvd.aceitar(v);
        assertTrue(v.getRelatorio().contains("130"));
    }

    @Test
    void descontoLivro_deve_ser10Porcento() {
        DescontoVisitor v = new DescontoVisitor();
        livro.aceitar(v);
        assertEquals(2.00 * 0.10, v.getTotalEconomizado(), 0.001);
    }

    @Test
    void descontoRevista_deve_ser20Porcento() {
        DescontoVisitor v = new DescontoVisitor();
        revista.aceitar(v);
        assertEquals(1.50 * 0.20, v.getTotalEconomizado(), 0.001);
    }

    @Test
    void descontoDvd_deve_ser5Porcento() {
        DescontoVisitor v = new DescontoVisitor();
        dvd.aceitar(v);
        assertEquals(3.00 * 0.05, v.getTotalEconomizado(), 0.001);
    }

    @Test
    void elementos_devemImplementarItemAcervo() {
        assertInstanceOf(ItemAcervo.class, livro);
        assertInstanceOf(ItemAcervo.class, revista);
        assertInstanceOf(ItemAcervo.class, dvd);
    }

    @Test
    void visitors_devemImplementarItemVisitor() {
        assertInstanceOf(ItemVisitor.class, new CalculadorMultaVisitor(1));
        assertInstanceOf(ItemVisitor.class, new RelatorioVisitor());
        assertInstanceOf(ItemVisitor.class, new DescontoVisitor());
    }
}
