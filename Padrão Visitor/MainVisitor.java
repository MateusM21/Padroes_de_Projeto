package biblioteca.visitor;

import java.util.List;

public class MainVisitor {

    public static void main(String[] args) {
        System.out.println("=== PADRÃO VISITOR - Biblioteca ===\n");

        List<ItemAcervo> acervo = List.of(
                new Livro("Dom Casmurro", "Machado de Assis", 256, 2.00),
                new Revista("National Geographic", 312, 1.50),
                new Dvd("Cidade de Deus", 130, 3.00),
                new Livro("O Senhor dos Aneis", "Tolkien", 576, 2.50),
                new Revista("Veja", 4089, 1.00)
        );

        System.out.println("--- Relatorio do acervo ---");
        RelatorioVisitor relatorio = new RelatorioVisitor();
        acervo.forEach(item -> item.aceitar(relatorio));
        relatorio.imprimir();

        System.out.println("\n--- Calculo de multa (3 dias de atraso) ---");
        CalculadorMultaVisitor multa = new CalculadorMultaVisitor(3);
        acervo.forEach(item -> item.aceitar(multa));
        System.out.printf("Total de multas: R$ %.2f%n", multa.getTotalMulta());

        System.out.println("\n--- Descontos do mes ---");
        DescontoVisitor desconto = new DescontoVisitor();
        acervo.forEach(item -> item.aceitar(desconto));
        System.out.printf("Total economizado: R$ %.2f%n", desconto.getTotalEconomizado());
    }
}
