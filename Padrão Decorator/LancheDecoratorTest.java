package hamburgueria.decorator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class LancheDecoratorTest {


    @Test
    void hamburguerSimples_deveRetornarDescricaoCorreta() {
        Lanche lanche = new HamburguerSimples();
        assertEquals("Hambúrguer Simples (pão + carne)", lanche.getDescricao());
    }

    @Test
    void hamburguerSimples_deveRetornarPrecoBase() {
        Lanche lanche = new HamburguerSimples();
        assertEquals(15.00, lanche.getPreco(), 0.001);
    }


    @Test
    void queijo_deveAdicionarDescricao() {
        Lanche lanche = new Queijo(new HamburguerSimples());
        assertTrue(lanche.getDescricao().contains("Queijo"));
    }

    @Test
    void queijo_deveAdicionarPreco() {
        Lanche lanche = new Queijo(new HamburguerSimples());
        assertEquals(18.00, lanche.getPreco(), 0.001); // 15 + 3
    }


    @Test
    void bacon_deveAdicionarDescricao() {
        Lanche lanche = new Bacon(new HamburguerSimples());
        assertTrue(lanche.getDescricao().contains("Bacon"));
    }

    @Test
    void bacon_deveAdicionarPreco() {
        Lanche lanche = new Bacon(new HamburguerSimples());
        assertEquals(20.00, lanche.getPreco(), 0.001); // 15 + 5
    }


    @Test
    void ovo_deveAdicionarDescricao() {
        Lanche lanche = new Ovo(new HamburguerSimples());
        assertTrue(lanche.getDescricao().contains("Ovo"));
    }

    @Test
    void ovo_deveAdicionarPreco() {
        Lanche lanche = new Ovo(new HamburguerSimples());
        assertEquals(17.50, lanche.getPreco(), 0.001); // 15 + 2.50
    }


    @Test
    void queijoEBacon_devemAcumularDescricao() {
        Lanche lanche = new Bacon(new Queijo(new HamburguerSimples()));
        String desc = lanche.getDescricao();
        assertTrue(desc.contains("Queijo"));
        assertTrue(desc.contains("Bacon"));
    }

    @Test
    void queijoEBacon_devemAcumularPreco() {
        Lanche lanche = new Bacon(new Queijo(new HamburguerSimples()));
        assertEquals(23.00, lanche.getPreco(), 0.001); // 15 + 3 + 5
    }

    @Test
    void todosOsAdicionais_devemAcumularPreco() {
        Lanche lanche = new Ovo(new Bacon(new Queijo(new HamburguerSimples())));
        assertEquals(25.50, lanche.getPreco(), 0.001); // 15 + 3 + 5 + 2.50
    }

    @Test
    void todosOsAdicionais_devemConterTodosNaDescricao() {
        Lanche lanche = new Ovo(new Bacon(new Queijo(new HamburguerSimples())));
        String desc = lanche.getDescricao();
        assertTrue(desc.contains("Queijo"));
        assertTrue(desc.contains("Bacon"));
        assertTrue(desc.contains("Ovo"));
    }


    @Test
    void duploQueijo_deveDobraroAdicionaldoQueijo() {
        Lanche lanche = new Queijo(new Queijo(new HamburguerSimples()));
        assertEquals(21.00, lanche.getPreco(), 0.001); // 15 + 3 + 3
    }

    @Test
    void duploQueijo_deveConterQueijoNaDescricao() {
        Lanche lanche = new Queijo(new Queijo(new HamburguerSimples()));
        assertTrue(lanche.getDescricao().contains("Queijo"));
    }

    @Test
    void decoratorDeveImplementarInterfaceLanche() {
        Lanche lanche = new Queijo(new HamburguerSimples());
        assertInstanceOf(Lanche.class, lanche);
    }
}
