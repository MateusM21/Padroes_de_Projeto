package hotel.mediator;

public interface HotelMediator {
    void registrar(Departamento departamento);
    void enviar(String mensagem, String remetente, String destinatario);
}
