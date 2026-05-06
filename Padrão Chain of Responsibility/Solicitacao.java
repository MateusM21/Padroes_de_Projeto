package hamburgueria.chain;


public class Solicitacao {

    private final String cliente;
    private final double valorPedido;
    private final String motivo;

    public Solicitacao(String cliente, double valorPedido, String motivo) {
        this.cliente     = cliente;
        this.valorPedido = valorPedido;
        this.motivo      = motivo;
    }

    public String getCliente()      { return cliente;     }
    public double getValorPedido()  { return valorPedido; }
    public String getMotivo()       { return motivo;      }

    @Override
    public String toString() {
        return String.format("Solicitação de '%s' | Valor: R$ %.2f | Motivo: %s",
                cliente, valorPedido, motivo);
    }
}
