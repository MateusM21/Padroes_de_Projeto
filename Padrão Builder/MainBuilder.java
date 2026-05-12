package hotel.builder;

public class MainBuilder {

    public static void main(String[] args) {
        System.out.println("=== PADRÃO BUILDER - Hotel ===\n");

        System.out.println("--- Pacote pelo Director (Recepcionista) ---\n");

        Recepcionista recepcionista = new Recepcionista(new PacoteHospedagemBuilder());
        System.out.println(recepcionista.montarPacoteEconomico());
        System.out.println(recepcionista.montarPacoteConforto());
        System.out.println(recepcionista.montarPacoteVip());

        System.out.println("--- Pacote personalizado (cliente monta) ---\n");

        PacoteHospedagem personalizado = new PacoteHospedagemBuilder()
                .quarto("Deluxe Vista Mar")
                .diarias(7)
                .comCafeDaManha()
                .comSpa()
                .comTransfer()
                .servicoExtra("Aula de surf")
                .servicoExtra("Passeio de barco")
                .build();

        System.out.println(personalizado);

        System.out.println("--- Tentativa de build sem quarto (excecao esperada) ---");
        try {
            new PacoteHospedagemBuilder().diarias(3).build();
        } catch (IllegalStateException e) {
            System.out.println("Erro capturado: " + e.getMessage());
        }
    }
}
