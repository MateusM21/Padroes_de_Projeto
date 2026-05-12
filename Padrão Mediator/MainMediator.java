package hotel.mediator;

public class MainMediator {

    public static void main(String[] args) {
        System.out.println("=== PADRÃO MEDIATOR - Hotel ===\n");

        CentralHotel central = new CentralHotel();

        Recepcao recepcao   = new Recepcao(central);
        Limpeza limpeza     = new Limpeza(central);
        Manutencao manutencao = new Manutencao(central);

        central.registrar(recepcao);
        central.registrar(limpeza);
        central.registrar(manutencao);

        System.out.println("--- Check-in quarto 204 ---");
        recepcao.realizarCheckIn("204");

        System.out.println();
        System.out.println("--- Limpeza solicita suprimentos ---");
        limpeza.solicitarSuprimentos();

        System.out.println();
        System.out.println("--- Manutencao reporta problema ---");
        manutencao.reportarProblema("Ar condicionado com defeito no quarto 204");

        System.out.println();
        System.out.println("--- Limpeza notifica quarto pronto ---");
        limpeza.notificarQuartoPronto("204");

        System.out.println();
        System.out.println("--- Check-out quarto 204 ---");
        recepcao.realizarCheckOut("204");
    }
}
