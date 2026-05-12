package hotel.mediator;

public interface Departamento {
    void enviarMensagem(String mensagem, String destinatario);
    void receberMensagem(String mensagem, String remetente);
    String getNome();
}
