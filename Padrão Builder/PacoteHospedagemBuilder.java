package hotel.builder;

public class PacoteHospedagemBuilder implements PacoteBuilder {

    private final PacoteHospedagem pacote;

    public PacoteHospedagemBuilder() {
        this.pacote = new PacoteHospedagem();
    }

    @Override
    public PacoteBuilder quarto(String tipo) {
        pacote.setTipoQuarto(tipo);
        return this;
    }

    @Override
    public PacoteBuilder diarias(int quantidade) {
        pacote.setNumeroDiarias(quantidade);
        return this;
    }

    @Override
    public PacoteBuilder comCafeDaManha() {
        pacote.setCafeDaManha(true);
        return this;
    }

    @Override
    public PacoteBuilder comSpa() {
        pacote.setSpa(true);
        return this;
    }

    @Override
    public PacoteBuilder comTransfer() {
        pacote.setTransfer(true);
        return this;
    }

    @Override
    public PacoteBuilder comEstacionamento() {
        pacote.setEstacionamento(true);
        return this;
    }

    @Override
    public PacoteBuilder servicoExtra(String servico) {
        pacote.adicionarServicoExtra(servico);
        return this;
    }

    @Override
    public PacoteHospedagem build() {
        if (pacote.getTipoQuarto() == null || pacote.getTipoQuarto().isBlank()) {
            throw new IllegalStateException("Tipo de quarto e obrigatorio.");
        }
        if (pacote.getNumeroDiarias() <= 0) {
            throw new IllegalStateException("Numero de diarias deve ser maior que zero.");
        }
        return pacote;
    }
}
