package hamburgueria.chain;


public class MainChain {

    public static void main(String[] args) {
        System.out.println("=== PADRÃO CHAIN OF RESPONSIBILITY - Hamburgueria ===\n");


        Atendente atendente = new Atendente("Carlos");
        Gerente   gerente   = new Gerente("Ana");
        Diretor   diretor   = new Diretor("Roberto");

        atendente.setProximo(gerente);
        gerente.setProximo(diretor);

        System.out.println("Cadeia montada: Atendente → Gerente → Diretor\n");
        System.out.println("-----------------------------------------------");

        Solicitacao s1 = new Solicitacao("João", 15.00, "Troca de refrigerante");
        System.out.println("Enviando: " + s1);
        atendente.aprovar(s1);

        System.out.println();

        Solicitacao s2 = new Solicitacao("Maria", 55.00, "Desconto especial combo família");
        System.out.println("Enviando: " + s2);
        atendente.aprovar(s2);

        System.out.println();

        Solicitacao s3 = new Solicitacao("Empresa XYZ", 250.00, "Pedido corporativo com desconto");
        System.out.println("Enviando: " + s3);
        atendente.aprovar(s3);

        System.out.println();

        Solicitacao s4 = new Solicitacao("Pedro", 20.00, "Cortesia por longa espera");
        System.out.println("Enviando: " + s4);
        atendente.aprovar(s4);

        System.out.println();

        System.out.println("-----------------------------------------------");
        System.out.println("Teste: cadeia sem Diretor\n");
        Atendente at2 = new Atendente("Paula");
        Gerente   ge2 = new Gerente("Luis");
        at2.setProximo(ge2); 

        Solicitacao s5 = new Solicitacao("Cliente VIP", 500.00, "Evento especial");
        System.out.println("Enviando: " + s5);
        at2.aprovar(s5);
    }
}
