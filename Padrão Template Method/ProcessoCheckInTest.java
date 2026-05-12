package hotel.templatemethod;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ProcessoCheckInTest {

    private final ByteArrayOutputStream saida = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(saida));
    }

    @BeforeEach
    void restaurar() {
        System.setOut(System.out);
    }

    @Test
    void checkInComum_deveExecutarTodasAsEtapas() {
        new CheckInComum().executar("Maria", "101");
        String out = saida.toString();
        assertTrue(out.contains("Maria"));
        assertTrue(out.contains("101"));
        assertTrue(out.contains("Quarto"));
        assertTrue(out.contains("Chave") || out.contains("chave"));
        assertTrue(out.contains("registrada") || out.contains("registro"));
    }

    @Test
    void checkInVip_deveConterServicosVip() {
        new CheckInVip().executar("Carlos", "501");
        String out = saida.toString().toLowerCase();
        assertTrue(out.contains("spa") || out.contains("mordomo") || out.contains("transfer"));
    }

    @Test
    void checkInVip_deveOverrideEntregarChave() {
        new CheckInVip().executar("Ana", "502");
        assertTrue(saida.toString().toLowerCase().contains("cartao") ||
                   saida.toString().toLowerCase().contains("welcome"));
    }

    @Test
    void checkInCorporativo_deveConterServicosCorporativos() {
        new CheckInCorporativo().executar("Pedro", "210");
        String out = saida.toString().toLowerCase();
        assertTrue(out.contains("empresa") || out.contains("reuniao") || out.contains("nota fiscal"));
    }

    @Test
    void checkInCorporativo_deveOverrideRegistrarEntrada() {
        new CheckInCorporativo().executar("Joao", "211");
        assertTrue(saida.toString().toLowerCase().contains("nota fiscal") ||
                   saida.toString().toLowerCase().contains("empresa"));
    }

    @Test
    void todosOsCheckIns_devemConterNomeDoHospede() {
        String hospede = "HospedeUnico";
        new CheckInComum().executar(hospede, "100");
        assertTrue(saida.toString().contains(hospede));

        saida.reset();
        new CheckInVip().executar(hospede, "500");
        assertTrue(saida.toString().contains(hospede));

        saida.reset();
        new CheckInCorporativo().executar(hospede, "200");
        assertTrue(saida.toString().contains(hospede));
    }

    @Test
    void todosOsCheckIns_devemConterNumeroDoQuarto() {
        new CheckInComum().executar("X", "999");
        assertTrue(saida.toString().contains("999"));
    }

    @Test
    void checkIns_naoDevemLancarExcecao() {
        assertDoesNotThrow(() -> new CheckInComum().executar("A", "1"));
        assertDoesNotThrow(() -> new CheckInVip().executar("B", "2"));
        assertDoesNotThrow(() -> new CheckInCorporativo().executar("C", "3"));
    }

    @Test
    void checkInComum_deveHerdarDeProcessoCheckIn() {
        assertInstanceOf(ProcessoCheckIn.class, new CheckInComum());
    }

    @Test
    void checkInVip_deveHerdarDeProcessoCheckIn() {
        assertInstanceOf(ProcessoCheckIn.class, new CheckInVip());
    }

    @Test
    void checkInCorporativo_deveHerdarDeProcessoCheckIn() {
        assertInstanceOf(ProcessoCheckIn.class, new CheckInCorporativo());
    }
}
