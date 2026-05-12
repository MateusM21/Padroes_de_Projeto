package hotel.templatemethod;

public abstract class ProcessoCheckIn {

    public final void executar(String hospede, String quarto) {
        verificarReserva(hospede);
        validarDocumento(hospede);
        atribuirQuarto(hospede, quarto);
        entregarChave(hospede, quarto);
        oferecerServicosAdicionais(hospede);
        registrarEntrada(hospede, quarto);
    }

    protected abstract void verificarReserva(String hospede);
    protected abstract void validarDocumento(String hospede);
    protected abstract void oferecerServicosAdicionais(String hospede);

    protected void atribuirQuarto(String hospede, String quarto) {
        System.out.println("Quarto " + quarto + " atribuido para " + hospede + ".");
    }

    protected void entregarChave(String hospede, String quarto) {
        System.out.println("Chave do quarto " + quarto + " entregue a " + hospede + ".");
    }

    protected void registrarEntrada(String hospede, String quarto) {
        System.out.println("Entrada de " + hospede + " registrada no sistema. Quarto: " + quarto + ".");
    }
}
