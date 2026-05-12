package hotel.templatemethod;

public class CheckInVip extends ProcessoCheckIn {

    @Override
    protected void verificarReserva(String hospede) {
        System.out.println("[VIP] Confirmando reserva premium de " + hospede + " com prioridade.");
    }

    @Override
    protected void validarDocumento(String hospede) {
        System.out.println("[VIP] Validando passaporte ou documento internacional de " + hospede + ".");
    }

    @Override
    protected void oferecerServicosAdicionais(String hospede) {
        System.out.println("[VIP] Oferecendo: transfer, spa, mordomo 24h e late check-out para " + hospede + ".");
    }

    @Override
    protected void entregarChave(String hospede, String quarto) {
        System.out.println("[VIP] Cartao magnetico personalizado do quarto " + quarto + " entregue a " + hospede + " com welcome kit.");
    }
}
