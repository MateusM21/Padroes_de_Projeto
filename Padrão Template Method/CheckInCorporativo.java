package hotel.templatemethod;

public class CheckInCorporativo extends ProcessoCheckIn {

    @Override
    protected void verificarReserva(String hospede) {
        System.out.println("[Corporativo] Vinculando reserva ao contrato da empresa de " + hospede + ".");
    }

    @Override
    protected void validarDocumento(String hospede) {
        System.out.println("[Corporativo] Verificando cracha e carta da empresa de " + hospede + ".");
    }

    @Override
    protected void oferecerServicosAdicionais(String hospede) {
        System.out.println("[Corporativo] Disponibilizando sala de reunioes e impressora para " + hospede + ".");
    }

    @Override
    protected void registrarEntrada(String hospede, String quarto) {
        System.out.println("[Corporativo] Entrada de " + hospede + " registrada e nota fiscal emitida para a empresa. Quarto: " + quarto + ".");
    }
}
