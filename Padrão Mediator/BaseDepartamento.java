package hotel.mediator;

public abstract class BaseDepartamento implements Departamento {

    protected final HotelMediator mediator;
    protected final String nome;

    public BaseDepartamento(HotelMediator mediator, String nome) {
        this.mediator = mediator;
        this.nome = nome;
    }

    @Override
    public void enviarMensagem(String mensagem, String destinatario) {
        System.out.println("[" + nome + "] → " + destinatario + ": " + mensagem);
        mediator.enviar(mensagem, nome, destinatario);
    }

    @Override
    public String getNome() {
        return nome;
    }
}
