package hotel.builder;

import java.util.ArrayList;
import java.util.List;

public class PacoteHospedagem {

    private String tipoQuarto;
    private int numeroDiarias;
    private boolean cafeDaManha;
    private boolean spa;
    private boolean transfer;
    private boolean estacionamento;
    private List<String> servicosExtras;

    public PacoteHospedagem() {
        this.servicosExtras = new ArrayList<>();
    }

    public void setTipoQuarto(String tipoQuarto)           { this.tipoQuarto = tipoQuarto; }
    public void setNumeroDiarias(int numeroDiarias)        { this.numeroDiarias = numeroDiarias; }
    public void setCafeDaManha(boolean cafeDaManha)        { this.cafeDaManha = cafeDaManha; }
    public void setSpa(boolean spa)                        { this.spa = spa; }
    public void setTransfer(boolean transfer)              { this.transfer = transfer; }
    public void setEstacionamento(boolean estacionamento)  { this.estacionamento = estacionamento; }
    public void adicionarServicoExtra(String servico)      { this.servicosExtras.add(servico); }

    public String getTipoQuarto()       { return tipoQuarto; }
    public int getNumeroDiarias()       { return numeroDiarias; }
    public boolean isCafeDaManha()      { return cafeDaManha; }
    public boolean isSpa()              { return spa; }
    public boolean isTransfer()         { return transfer; }
    public boolean isEstacionamento()   { return estacionamento; }
    public List<String> getServicosExtras() { return servicosExtras; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pacote de Hospedagem:\n");
        sb.append("  Quarto       : ").append(tipoQuarto).append("\n");
        sb.append("  Diarias      : ").append(numeroDiarias).append("\n");
        sb.append("  Cafe da manha: ").append(cafeDaManha ? "Sim" : "Nao").append("\n");
        sb.append("  Spa          : ").append(spa         ? "Sim" : "Nao").append("\n");
        sb.append("  Transfer     : ").append(transfer    ? "Sim" : "Nao").append("\n");
        sb.append("  Estacionamento: ").append(estacionamento ? "Sim" : "Nao").append("\n");
        if (!servicosExtras.isEmpty()) {
            sb.append("  Extras       : ").append(String.join(", ", servicosExtras)).append("\n");
        }
        return sb.toString();
    }
}
