package hotel.mediator;

public class Recepcao extends BaseDepartamento {

    public Recepcao(HotelMediator mediator) {
        super(mediator, "Recepcao");
    }

    @Override
    public void receberMensagem(String mensagem, String remetente) {
        System.out.println("[Recepcao] Mensagem de " + remetente + ": " + mensagem);
    }

    public void realizarCheckIn(String quarto) {
        enviarMensagem("Quarto " + quarto + " ocupado. Favor preparar.", "Limpeza");
        enviarMensagem("Verificar instalacoes do quarto " + quarto + ".", "Manutencao");
    }

    public void realizarCheckOut(String quarto) {
        enviarMensagem("Quarto " + quarto + " liberado. Favor limpar.", "Limpeza");
    }
}
