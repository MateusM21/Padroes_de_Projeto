package hamburgueria.state;


public class Pedido {

    private int numero;
    private String descricao;
    private PedidoState estado;

    public Pedido(int numero, String descricao) {
        this.numero = numero;
        this.descricao = descricao;
        this.estado = new EstadoRecebido(); // estado inicial
        System.out.println("Pedido #" + numero + " registrado: " + descricao);
    }

    // Delega para o estado atual
    public void confirmar() { estado.confirmar(this); }
    public void preparar()  { estado.preparar(this);  }
    public void finalizar() { estado.finalizar(this); }
    public void cancelar()  { estado.cancelar(this);  }

    public void setEstado(PedidoState novoEstado) {
        this.estado = novoEstado;
    }

    public void exibirStatus() {
        System.out.println(">>> Status atual do pedido #" + numero + ": " + estado.getNome());
    }
}
