package biblioteca.memento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MementoBibliotecaTest {

    private final ByteArrayOutputStream saida = new ByteArrayOutputStream();
    private Emprestimo emprestimo;
    private HistoricoEmprestimo historico;

    private static final LocalDate DATA_BASE = LocalDate.of(2024, 6, 1);

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(saida));
        emprestimo = new Emprestimo("Dom Casmurro", "Ana Lima", DATA_BASE, 7);
        historico  = new HistoricoEmprestimo();
    }

    @BeforeEach
    void restaurarSaida() {
        System.setOut(System.out);
    }

    @Test
    void emprestimo_deveIniciarComoAtivo() {
        assertEquals("ATIVO", emprestimo.getStatus());
    }

    @Test
    void emprestimo_deveTerDataDevolucaoCorreta() {
        assertEquals(DATA_BASE.plusDays(7), emprestimo.getDataDevolucaoPrevista());
    }

    @Test
    void renovar_deveAtualizarStatusEData() {
        emprestimo.renovar(5);
        assertEquals("RENOVADO", emprestimo.getStatus());
        assertEquals(DATA_BASE.plusDays(12), emprestimo.getDataDevolucaoPrevista());
    }

    @Test
    void devolver_deveAtualizarStatus() {
        emprestimo.devolver();
        assertEquals("DEVOLVIDO", emprestimo.getStatus());
    }

    @Test
    void marcarAtrasado_deveAtualizarStatus() {
        emprestimo.marcarAtrasado();
        assertEquals("ATRASADO", emprestimo.getStatus());
    }

    @Test
    void salvar_deveCriarMemento() {
        EmprestimoMemento memento = emprestimo.salvar();
        assertNotNull(memento);
        assertEquals("ATIVO", memento.getStatus());
        assertEquals("Ana Lima", memento.getNomeUsuario());
    }

    @Test
    void restaurar_deveRetornarAoEstadoSalvo() {
        historico.salvar(emprestimo);
        emprestimo.renovar(7);
        assertEquals("RENOVADO", emprestimo.getStatus());

        emprestimo.restaurar(historico.desfazer());
        assertEquals("ATIVO", emprestimo.getStatus());
    }

    @Test
    void restaurar_deveRetornarDataOriginal() {
        historico.salvar(emprestimo);
        emprestimo.renovar(10);
        emprestimo.restaurar(historico.desfazer());
        assertEquals(DATA_BASE.plusDays(7), emprestimo.getDataDevolucaoPrevista());
    }

    @Test
    void historicoVarios_desfazerOrdemCorreta() {
        historico.salvar(emprestimo);
        emprestimo.renovar(7);
        historico.salvar(emprestimo);
        emprestimo.marcarAtrasado();

        emprestimo.restaurar(historico.desfazer());
        assertEquals("RENOVADO", emprestimo.getStatus());

        emprestimo.restaurar(historico.desfazer());
        assertEquals("ATIVO", emprestimo.getStatus());
    }

    @Test
    void historico_tamanhoDeveSerCorreto() {
        assertEquals(0, historico.tamanho());
        historico.salvar(emprestimo);
        assertEquals(1, historico.tamanho());
        historico.salvar(emprestimo);
        assertEquals(2, historico.tamanho());
    }

    @Test
    void desfazer_semHistorico_deveRetornarNull() {
        assertNull(historico.desfazer());
    }

    @Test
    void desfazer_semHistorico_naoDeveLancarExcecao() {
        assertDoesNotThrow(() -> historico.desfazer());
    }

    @Test
    void historico_aposDesfazer_deveDiminuirTamanho() {
        historico.salvar(emprestimo);
        historico.salvar(emprestimo);
        historico.desfazer();
        assertEquals(1, historico.tamanho());
    }

    @Test
    void memento_devePreservarTodosOsDados() {
        EmprestimoMemento m = emprestimo.salvar();
        assertEquals("Dom Casmurro", m.getItemTitulo());
        assertEquals("Ana Lima", m.getNomeUsuario());
        assertEquals(DATA_BASE, m.getDataEmprestimo());
        assertEquals("ATIVO", m.getStatus());
    }

    @Test
    void historico_vazio_deveRetornarTrue() {
        assertTrue(historico.vazio());
        historico.salvar(emprestimo);
        assertFalse(historico.vazio());
    }
}
