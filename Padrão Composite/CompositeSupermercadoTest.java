package supermercado.composite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CompositeSupermercadoTest {

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
    void produto_deveRetornarNomeCorreto() {
        Produto p = new Produto("Arroz", 10.00);
        assertEquals("Arroz", p.getNome());
    }

    @Test
    void produto_deveRetornarPrecoCorreto() {
        Produto p = new Produto("Leite", 5.50);
        assertEquals(5.50, p.getPrecoTotal(), 0.001);
    }

    @Test
    void produto_exibir_deveConterNomeEPreco() {
        new Produto("Feijao", 8.00).exibir("");
        String out = saida.toString();
        assertTrue(out.contains("Feijao"));
        assertTrue(out.contains("8,00") || out.contains("8.00"));
    }

    @Test
    void categoria_vazia_deveRetornarPrecoZero() {
        Categoria c = new Categoria("Vazia");
        assertEquals(0.0, c.getPrecoTotal(), 0.001);
    }

    @Test
    void categoria_comUmProduto_deveRetornarPrecoCorreto() {
        Categoria c = new Categoria("Graos");
        c.adicionar(new Produto("Arroz", 18.90));
        assertEquals(18.90, c.getPrecoTotal(), 0.001);
    }

    @Test
    void categoria_comVariosProdutos_deveSomarPrecos() {
        Categoria c = new Categoria("Laticinios");
        c.adicionar(new Produto("Leite", 5.00));
        c.adicionar(new Produto("Queijo", 15.00));
        c.adicionar(new Produto("Manteiga", 10.00));
        assertEquals(30.00, c.getPrecoTotal(), 0.001);
    }

    @Test
    void categoria_removerProduto_deveAtualizarTotal() {
        Categoria c = new Categoria("Higiene");
        Produto shampoo = new Produto("Shampoo", 12.00);
        c.adicionar(shampoo);
        c.adicionar(new Produto("Sabonete", 3.00));
        c.remover(shampoo);
        assertEquals(3.00, c.getPrecoTotal(), 0.001);
    }

    @Test
    void categoriaAninhada_deveSomarRecursivamente() {
        Categoria sub = new Categoria("Sub");
        sub.adicionar(new Produto("A", 10.00));
        sub.adicionar(new Produto("B", 20.00));

        Categoria pai = new Categoria("Pai");
        pai.adicionar(sub);
        pai.adicionar(new Produto("C", 5.00));

        assertEquals(35.00, pai.getPrecoTotal(), 0.001);
    }

    @Test
    void tresNiveisAninhados_deveSomarCorretamente() {
        Categoria nivel3 = new Categoria("N3");
        nivel3.adicionar(new Produto("X", 100.00));

        Categoria nivel2 = new Categoria("N2");
        nivel2.adicionar(nivel3);

        Categoria nivel1 = new Categoria("N1");
        nivel1.adicionar(nivel2);

        assertEquals(100.00, nivel1.getPrecoTotal(), 0.001);
    }

    @Test
    void produto_deveImplementarItemSupermercado() {
        assertInstanceOf(ItemSupermercado.class, new Produto("P", 1.00));
    }

    @Test
    void categoria_deveImplementarItemSupermercado() {
        assertInstanceOf(ItemSupermercado.class, new Categoria("C"));
    }

    @Test
    void exibir_categoriaNested_deveConterNomesDosFilhos() {
        Categoria c = new Categoria("Graos");
        c.adicionar(new Produto("Arroz", 10.00));
        c.exibir("");
        String out = saida.toString();
        assertTrue(out.contains("Graos"));
        assertTrue(out.contains("Arroz"));
    }

    @Test
    void categoria_getItens_deveRetornarListaCorreta() {
        Categoria c = new Categoria("C");
        Produto p1 = new Produto("P1", 1.00);
        Produto p2 = new Produto("P2", 2.00);
        c.adicionar(p1);
        c.adicionar(p2);
        assertEquals(2, c.getItens().size());
    }
}
