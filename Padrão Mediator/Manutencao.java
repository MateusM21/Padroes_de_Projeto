package hotel.mediator;

public class Manutencao extends BaseDepartamento {

    public Manutencao(HotelMediator mediator) {
        super(mediator, "Manutencao");
    }

    @Override
    public void receberMensagem(String mensagem, String remetente) {
        System.out.println("[Manutencao] Mensagem de " + remetente + ": " + mensagem);
    }

    public void reportarProblema(String descricao) {
        enviarMensagem("Problema identificado: " + descricao + ". Quarto indisponivel.", "Recepcao");
    }
}
