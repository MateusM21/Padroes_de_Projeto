package hotel.mediator;

public class Limpeza extends BaseDepartamento {

    public Limpeza(HotelMediator mediator) {
        super(mediator, "Limpeza");
    }

    @Override
    public void receberMensagem(String mensagem, String remetente) {
        System.out.println("[Limpeza] Mensagem de " + remetente + ": " + mensagem);
    }

    public void solicitarSuprimentos() {
        enviarMensagem("Estoque de toalhas baixo. Solicitar reposicao.", "Manutencao");
    }

    public void notificarQuartoPronto(String quarto) {
        enviarMensagem("Quarto " + quarto + " limpo e pronto.", "Recepcao");
    }
}
