package hotel.templatemethod;

public class CheckInComum extends ProcessoCheckIn {

    @Override
    protected void verificarReserva(String hospede) {
        System.out.println("[Comum] Verificando reserva de " + hospede + " no sistema.");
    }

    @Override
    protected void validarDocumento(String hospede) {
        System.out.println("[Comum] Solicitando RG ou CPF de " + hospede + ".");
    }

    @Override
    protected void oferecerServicosAdicionais(String hospede) {
        System.out.println("[Comum] Informando cafe da manha incluso das 07h as 10h.");
    }
}
