package hotel.templatemethod;

public class MainTemplateMethod {

    public static void main(String[] args) {
        System.out.println("=== PADRÃO TEMPLATE METHOD - Hotel ===\n");

        ProcessoCheckIn checkInComum = new CheckInComum();
        ProcessoCheckIn checkInVip  = new CheckInVip();
        ProcessoCheckIn checkInCorp = new CheckInCorporativo();

        System.out.println("--- Hospede comum ---");
        checkInComum.executar("Carlos Souza", "101");

        System.out.println();
        System.out.println("--- Hospede VIP ---");
        checkInVip.executar("Ana Lima", "501");

        System.out.println();
        System.out.println("--- Hospede corporativo ---");
        checkInCorp.executar("Pedro Neto", "210");
    }
}
